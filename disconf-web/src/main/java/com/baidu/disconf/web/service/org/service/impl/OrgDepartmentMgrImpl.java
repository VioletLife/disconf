package com.baidu.disconf.web.service.org.service.impl;

import com.baidu.disconf.web.common.message.CodeMessage;
import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.org.mybatis.OrgDepartment;
import com.baidu.disconf.web.service.org.mybatis.OrgDepartmentDynamicSqlSupport;
import com.baidu.disconf.web.service.org.mybatis.OrgDepartmentMapper;
import com.baidu.disconf.web.service.org.service.OrgDepartmentMgr;
import com.baidu.disconf.web.service.org.vo.OrgDepartmentVo;
import com.baidu.disconf.web.service.user.dto.Visitor;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author QingDengYue
 */
@Service
@Transactional
public class OrgDepartmentMgrImpl implements OrgDepartmentMgr {

    @Autowired
    private OrgDepartmentMapper orgDepartmentMapper;


    @Override
    public List<OrgDepartmentVo> selectCurrentVisitorDepartment() {
        Visitor visitor = getCurrentVisitor();
        List<OrgDepartmentVo> orgDepartmentVos = new ArrayList<>();
        List<OrgDepartment> orgDepartments = orgDepartmentMapper.selectByExample()
                .where(OrgDepartmentDynamicSqlSupport.creator, IsEqualTo.of(visitor::getLoginUserId))
                .build()
                .execute();
        if (orgDepartments != null && orgDepartments.size() > 0) {
            orgDepartments.stream().filter(OrgDepartment::getIsOrgRoot).forEach((department) -> {
                OrgDepartmentVo departmentVo = new OrgDepartmentVo();
                BeanUtils.copyProperties(department, departmentVo);
                generateDepartmentTree(departmentVo.getDepartmentCode(), departmentVo, orgDepartments);
                orgDepartmentVos.add(departmentVo);
            });
        }
        return orgDepartmentVos;
    }

    void generateDepartmentTree(String parentCode, OrgDepartmentVo parentNode, final List<OrgDepartment> source) {
        List<OrgDepartmentVo> children = new ArrayList<>();
        source.stream().filter(department -> department.getParentCode().equalsIgnoreCase(parentCode)).forEach((department -> {
            OrgDepartmentVo child = new OrgDepartmentVo();
            BeanUtils.copyProperties(department, child);
            generateDepartmentTree(department.getDepartmentCode(), child, source);
            children.add(child);
        }));
        parentNode.setChildren(children);
    }


    @Override
    public OrgDepartment insertSelective(OrgDepartment orgDepartment, Consumer<ResponseMessage> consumer) {
        if (orgDepartment != null) {
            if (!orgDepartment.getIsOrgRoot() && StringUtils.isAnyBlank(orgDepartment.getParentCode())) {
                consumer.accept(CodeMessage.CODE_109.toResponseMessage());
            } else {
                Long countRecord = orgDepartmentMapper.countByExample()
                        .where(OrgDepartmentDynamicSqlSupport.departmentCode, IsEqualTo.of(orgDepartment::getDepartmentCode))
                        .build()
                        .execute();
                if (countRecord != null && countRecord > 0) {
                    consumer.accept(CodeMessage.CODE_112.toResponseMessage());
                } else {
                    orgDepartment.setCreateTime(new Date());
                    orgDepartment.setCreator(getCurrentVisitor().getLoginUserId());
                    orgDepartment.setUpdator(getCurrentVisitor().getLoginUserId());
                    orgDepartment.setUpdateTime(new Date());
                    orgDepartmentMapper.insertSelective(orgDepartment);
                }
            }
        }
        return orgDepartment;
    }

    @Override
    public int deleteDepartmentByPrimaryId(Long departmentId, Consumer<ResponseMessage> consumer) {
        //TODO: 判断是否关联用户账户
        if (departmentId != null && departmentId > 0) {
            OrgDepartment department = orgDepartmentMapper.selectByPrimaryKey(departmentId);
            if (department != null && StringUtils.isNotEmpty(department.getDepartmentCode())) {
                deleteChildDepartment(department.getDepartmentCode());
                orgDepartmentMapper.deleteByPrimaryKey(departmentId);
            } else {
                consumer.accept(CodeMessage.CODE_109.toResponseMessage());
            }
        } else {
            consumer.accept(CodeMessage.CODE_111.toResponseMessage());
        }
        return 0;
    }

    /**
     * 删除子节点
     *
     * @param parentCode 父节点编码
     */
    public void deleteChildDepartment(String parentCode) {
        List<OrgDepartment> childrenNodes = orgDepartmentMapper.selectByExample()
                .where(OrgDepartmentDynamicSqlSupport.parentCode, IsEqualTo.of(() -> parentCode))
                .build()
                .execute();
        if (childrenNodes != null && childrenNodes.size() > 0) {
            for (OrgDepartment department : childrenNodes) {
                if (StringUtils.isNotEmpty(department.getDepartmentCode())) {
                    deleteChildDepartment(department.getDepartmentCode());
                }
                orgDepartmentMapper.deleteByPrimaryKey(department.getId());
            }
        }
    }


    @Override
    public String generateDepartmentCode(String parentCode) {
        if (StringUtils.isNotEmpty(parentCode)) {
            List<OrgDepartment> orgDepartments = orgDepartmentMapper.selectByExample()
                    .where(OrgDepartmentDynamicSqlSupport.parentCode, IsEqualTo.of(() -> parentCode))
                    .build()
                    .execute();
            if (orgDepartments != null && orgDepartments.size() > 0) {
                int nextMaxNumber = 1;
                for (OrgDepartment department : orgDepartments) {
                    String nodeNumber = department.getDepartmentCode().substring(parentCode.length());
                    if (Integer.parseInt(nodeNumber) >= nextMaxNumber) {
                        nextMaxNumber = Integer.parseInt(nodeNumber);
                    }
                }
                return parentCode + String.format("%03d", nextMaxNumber + 1);
            } else {
                return parentCode + String.format("%03d", 1);
            }
        } else {
            List<OrgDepartment> orgDepartments = orgDepartmentMapper.selectByExample()
                    .where(OrgDepartmentDynamicSqlSupport.isOrgRoot, IsEqualTo.of(() -> true))
                    .build()
                    .execute();
            if (orgDepartments != null && orgDepartments.size() > 0) {
                int nextMaxNumber = 1;
                for (OrgDepartment orgDepartment : orgDepartments) {
                    if (Integer.parseInt(orgDepartment.getDepartmentCode()) >= nextMaxNumber) {
                        nextMaxNumber = Integer.parseInt(orgDepartment.getDepartmentCode());
                    }
                }
                return String.format("%03d", nextMaxNumber + 1);
            } else {
                return "001";
            }
        }
    }


    @Override
    public int updateSelective(OrgDepartment orgDepartment, Consumer<ResponseMessage> consumer) {
        if (orgDepartment != null && orgDepartment.getId() != null && orgDepartment.getId() > 0 && StringUtils.isNotEmpty(orgDepartment.getDepartmentCode()) && StringUtils.isNotEmpty(orgDepartment.getDepartmentName())) {
            orgDepartment.setUpdateTime(new Date());
            orgDepartment.setUpdator(getCurrentVisitor().getLoginUserId());
            orgDepartmentMapper.updateByPrimaryKeySelective(orgDepartment);
            /**
             * 更新子节点显示和隐藏状态
             */
            if (orgDepartment.getIsHiddenNode()) {
                /**
                 * 如果节点隐藏，则子节点应该全部处于隐藏状态
                 */
                hideChildrenNode(orgDepartment);
            } else {
                /**
                 * 如果子节点处于显示状态，则父级节点也该全部处于显示状态
                 */
                showParentNode(orgDepartment);
            }
            orgDepartmentMapper.updateByPrimaryKeySelective(orgDepartment);
        } else {
            consumer.accept(CodeMessage.CODE_113.toResponseMessage());
        }
        return 0;
    }


    /**
     * 隐藏子节点
     *
     * @param node
     */
    public void hideChildrenNode(OrgDepartment node) {
        if (node != null && StringUtils.isNotEmpty(node.getDepartmentCode())) {
            List<OrgDepartment> orgDepartments = orgDepartmentMapper.selectByExample()
                    .where(OrgDepartmentDynamicSqlSupport.parentCode, IsEqualTo.of(node::getDepartmentCode))
                    .build()
                    .execute();
            for (OrgDepartment department : orgDepartments) {
                hideChildrenNode(department);
                department.setIsHiddenNode(true);
                orgDepartmentMapper.updateByPrimaryKeySelective(department);
            }

        }
    }

    public void showParentNode(OrgDepartment node) {
        if (node != null && !node.getIsOrgRoot()) {
            List<OrgDepartment> orgDepartments = orgDepartmentMapper.selectByExample()
                    .where(OrgDepartmentDynamicSqlSupport.departmentCode, IsEqualTo.of(node::getParentCode))
                    .build()
                    .execute();
            for (OrgDepartment department : orgDepartments) {
                showParentNode(department);
                department.setIsHiddenNode(false);
                orgDepartmentMapper.updateByPrimaryKeySelective(department);
            }

        } else {
            node.setIsHiddenNode(false);
            orgDepartmentMapper.updateByPrimaryKeySelective(node);
        }
    }

}
