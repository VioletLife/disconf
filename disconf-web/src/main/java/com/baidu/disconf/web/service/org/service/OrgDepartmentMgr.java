package com.baidu.disconf.web.service.org.service;

import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.BaseService;
import com.baidu.disconf.web.service.org.mybatis.OrgDepartment;
import com.baidu.disconf.web.service.org.vo.OrgDepartmentVo;

import java.util.List;
import java.util.function.Consumer;

public interface OrgDepartmentMgr extends BaseService {


    /**
     * 获取当前用户的部门设置数据
     *
     * @return 部门数据
     */
    List<OrgDepartmentVo> selectCurrentVisitorDepartment();


    /**
     * 新增部门数据
     *
     * @param orgDepartment 部门数据
     * @param consumer      回调函数
     * @return 部门数据
     */
    OrgDepartment insertSelective(OrgDepartment orgDepartment, Consumer<ResponseMessage> consumer);

    /**
     * 删除部门数据
     * 此处需要进行如下判断:
     * 1.部门是否已关联用户数据,如果已存在关联数据，则提示用户删除关联数据，然后才能删除节点
     * 2.节点是否存在子节点，如果存在则子节点会被一并删除
     *
     * @param departmentId
     * @return
     */
    int deleteDepartmentByPrimaryId(Long departmentId, Consumer<ResponseMessage> consumer);


    /**
     * 根据父级编码生成下一个编码
     * 编码规则:
     * 每三位为一组  每一组从001开始  其子级编码则为 001002 序列增长
     *
     * @param parentCode 父级编码
     * @return 下一个未使用编码
     */
    String generateDepartmentCode(String parentCode);


    /**
     * 更新部门数据
     * 如果当前节点隐藏则其子节点全部隐藏
     * 如果子节点显示且必须父级节点显示 子节点才能够处于显示状态
     *
     * @param orgDepartment 部门数据
     * @param consumer      ResponseMessage
     * @return
     */
    int updateSelective(OrgDepartment orgDepartment, Consumer<ResponseMessage> consumer);
}
