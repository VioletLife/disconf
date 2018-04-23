package com.baidu.disconf.web.service.auth.service.impl;

import com.baidu.disconf.web.common.message.CodeMessage;
import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.Page;
import com.baidu.disconf.web.service.auth.mybatis.*;
import com.baidu.disconf.web.service.auth.service.AuthRolePermissionMgr;
import com.baidu.disconf.web.service.auth.vo.AuthRolePermissionVo;
import com.baidu.disconf.web.service.user.dto.Visitor;
import com.baidu.ub.common.commons.ThreadContext;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SimpleSortSpecification;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsLikeCaseInsensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Consumer;

@Service
@Transactional
public class AuthRolePermissionMgrImpl implements AuthRolePermissionMgr {

    @Autowired
    private AuthRolePermissionMapper authRolePermissionMapper;

    @Autowired
    private AuthRoleMapper authRoleMapper;

    @Autowired
    private AuthPermissionMapper authPermissionMapper;


    @Override
    public Page<AuthRolePermissionVo> selectByExampleWithRowbounds(Page<AuthRolePermissionVo> rowBounds, String roleName) {
        PageHelper.startPage(rowBounds.getOffset(), rowBounds.getLimit());
        List<AuthRolePermissionVo> authRolePermissionVoList = new ArrayList<>();
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AuthRole>>> expressionDSL = authRoleMapper.selectByExample();
        List<AuthRole> cache;
        Visitor visitor = ThreadContext.getSessionVisitor();
        if (StringUtils.isNotEmpty(roleName)) {
            cache = expressionDSL
                    .where(AuthRoleDynamicSqlSupport.roleName, IsLikeCaseInsensitive.of(() -> roleName))
                    .and(AuthRoleDynamicSqlSupport.creator, IsEqualTo.of(visitor::getLoginUserId))
                    .orderBy(SimpleSortSpecification.of("create_time").descending())
                    .build()
                    .execute();
        } else {
            cache = expressionDSL
                    .where(AuthRoleDynamicSqlSupport.creator, IsEqualTo.of(visitor::getLoginUserId))
                    .orderBy(SimpleSortSpecification.of("create_time").descending())
                    .build()
                    .execute();
        }
        if (cache instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page page = (com.github.pagehelper.Page) cache;
            rowBounds.setTotal(page.getTotal());
            List<AuthRole> cacheRole = (List<AuthRole>) page.getResult();
            for (AuthRole role : cacheRole) {
                AuthRolePermissionVo authRolePermissionVo = new AuthRolePermissionVo();
                authRolePermissionVo.setRole(role);
                List<AuthPermission> permissions = new ArrayList<>();

                List<AuthRolePermission> authRolePermissions = authRolePermissionMapper.selectByExample()
                        .where(AuthRolePermissionDynamicSqlSupport.roleId, IsEqualTo.of(role::getId))
                        .build()
                        .execute();
                if (authRolePermissions != null && authRolePermissions.size() > 0) {
                    for (AuthRolePermission authRolePermission : authRolePermissions) {
                        AuthPermission authPermission = authPermissionMapper.selectByPrimaryKey(authRolePermission.getPermissionId());
                        permissions.add(authPermission);
                    }
                }
                authRolePermissionVo.setPermissions(permissions);
                authRolePermissionVoList.add(authRolePermissionVo);
            }
        }
        rowBounds.setResult(Optional.of(authRolePermissionVoList));
        return rowBounds;
    }

    @Override
    public AuthRolePermissionVo insertSelective(AuthRolePermissionVo role, Consumer<ResponseMessage> consumer) {
        if (StringUtils.isNotEmpty(role.getRole().getRoleCode()) && StringUtils.isNotEmpty(role.getRole().getRoleName())) {
            role.getRole().setId(null);
            Visitor visitor = ThreadContext.getSessionVisitor();
            role.getRole().setCreateTime(new Date());
            role.getRole().setUpdateTime(new Date());
            role.getRole().setCreator(visitor.getLoginUserId());
            role.getRole().setUpdator(visitor.getLoginUserId());
            authRoleMapper.insertSelective(role.getRole());
            insertRolesPermissions(role, visitor);
        } else {
            consumer.accept(CodeMessage.CODE_107.toResponseMessage());
        }
        return role;
    }

    private void insertRolesPermissions(AuthRolePermissionVo role, Visitor visitor) {
        if (role.getRole().getId() != null && role.getRole().getId() > 0 && role.getPermissions().size() > 0) {
            for (AuthPermission rolePermission : role.getPermissions()) {
                AuthRolePermission authRolePermission = new AuthRolePermission();
                authRolePermission.setCreateTime(new Date());
                authRolePermission.setCreator(visitor.getLoginUserId());
                authRolePermission.setUpdateTime(new Date());
                authRolePermission.setUpdator(visitor.getLoginUserId());
                authRolePermission.setRoleId(role.getRole().getId());
                authRolePermission.setPermissionId(rolePermission.getId());
                authRolePermissionMapper.insertSelective(authRolePermission);
            }
        }
    }

    @Override
    public AuthRolePermissionVo updateSelective(AuthRolePermissionVo role, Consumer<ResponseMessage> consumer) {
        if (role.getRole() != null && StringUtils.isNotEmpty(role.getRole().getRoleName()) && role.getRole().getId() != null && role.getRole().getId() > 0) {
            role.getRole().setUpdator(getCurrentVisitor().getLoginUserId());
            role.getRole().setUpdateTime(new Date());
            authRoleMapper.updateByPrimaryKeySelective(role.getRole());
            deleteRolePermissionsByRoleId(role.getRole().getId(), getCurrentVisitor());
            insertRolesPermissions(role, getCurrentVisitor());
        } else {
            consumer.accept(CodeMessage.CODE_108.toResponseMessage());
        }
        return role;
    }

    @Override
    public int deleteByPrimaryKey(Long roleId, Consumer<ResponseMessage> consumer) {
        Visitor visitor = ThreadContext.getSessionVisitor();
        if (roleId > 0) {
            deleteRolePermissionsByRoleId(roleId, visitor);
            authRoleMapper.deleteByExample()
                    .where(AuthRoleDynamicSqlSupport.id, IsEqualTo.of(() -> roleId))
                    .and(AuthRoleDynamicSqlSupport.creator, IsEqualTo.of(visitor::getLoginUserId))
                    .build()
                    .execute();
        } else {
            consumer.accept(CodeMessage.CODE_108.toResponseMessage());
        }
        return 0;
    }

    private void deleteRolePermissionsByRoleId(Long roleId, Visitor visitor) {
        authRolePermissionMapper.deleteByExample()
                .where(AuthRolePermissionDynamicSqlSupport.roleId, IsEqualTo.of(() -> roleId))
                .and(AuthRolePermissionDynamicSqlSupport.creator, IsEqualTo.of(visitor::getLoginUserId))
                .build().
                execute();
    }
}
