package com.baidu.disconf.web.service.auth.service.impl;

import com.baidu.disconf.web.common.message.CodeMessage;
import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.Page;
import com.baidu.disconf.web.service.auth.mybatis.AuthPermission;
import com.baidu.disconf.web.service.auth.mybatis.AuthPermissionDynamicSqlSupport;
import com.baidu.disconf.web.service.auth.mybatis.AuthPermissionMapper;
import com.baidu.disconf.web.service.auth.service.AuthPermissionMgr;
import com.baidu.disconf.web.service.user.dto.Visitor;
import com.baidu.ub.common.commons.ThreadContext;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
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
import java.util.function.Supplier;

@Service
@Transactional
public class AuthPermissionMgrImpl implements AuthPermissionMgr {
    @Autowired
    private AuthPermissionMapper authPermissionMapper;


    @Override
    public Page<AuthPermission> selectByExampleWithRowbounds(Page<AuthPermission> rowBounds, String permissionName) {
        PageHelper.startPage(rowBounds.getOffset(), rowBounds.getLimit());
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AuthPermission>>> expressionDSL = authPermissionMapper.selectByExampleWithRowbounds(rowBounds);
        List<AuthPermission> cache;
        Visitor visitor = ThreadContext.getSessionVisitor();
        if (StringUtils.isNotEmpty(permissionName)) {
            cache = expressionDSL
                    .where(AuthPermissionDynamicSqlSupport.permissionName, IsLikeCaseInsensitive.of(() -> "%" + permissionName + "%"))
                    .and(AuthPermissionDynamicSqlSupport.creator, IsEqualTo.of(visitor::getLoginUserId))
                    .orderBy(SimpleSortSpecification.of("create_time").descending())
                    .build()
                    .execute();
        } else {
            cache = expressionDSL
                    .where(AuthPermissionDynamicSqlSupport.creator, IsEqualTo.of(visitor::getLoginUserId))
                    .orderBy(SimpleSortSpecification.of("create_time").descending())
                    .build()
                    .execute();
        }
        com.github.pagehelper.Page page = null;
        Optional<List<AuthPermission>> authPermissions = Optional.of(Collections.emptyList());
        if (cache instanceof com.github.pagehelper.Page) {
            page = (com.github.pagehelper.Page) cache;
            rowBounds.setTotal(page.getTotal());
            authPermissions = Optional.of(page.getResult());
        }
        rowBounds.setResult(authPermissions);
        return rowBounds;
    }


    @Override
    public AuthPermission insertSelective(AuthPermission permission, Consumer<ResponseMessage> consumer) {
        if (permission != null && StringUtils.isNotEmpty(permission.getPermissionCode()) && StringUtils.isNotEmpty(permission.getPermissionName())) {
            Long countRecord = authPermissionMapper.countByExample()
                    .where(AuthPermissionDynamicSqlSupport.permissionCode, IsEqualTo.of(permission::getPermissionCode))
                    .build()
                    .execute();
            if (countRecord != null && countRecord > 0) {
                consumer.accept(CodeMessage.CODE_104.toResponseMessage());
            } else {
                Visitor visitor = ThreadContext.getSessionVisitor();
                permission.setId(null);
                permission.setCreateTime(new Date());
                permission.setCreator(visitor.getLoginUserId());
                permission.setUpdateTime(new Date());
                permission.setUpdator(visitor.getLoginUserId());
                authPermissionMapper.insertSelective(permission);
            }
        } else {
            consumer.accept(CodeMessage.CODE_105.toResponseMessage());
        }
        return permission;
    }

    @Override
    public AuthPermission updateSelective(AuthPermission permission, Consumer<ResponseMessage> consumer) {
        if (permission != null && permission.getId() != null && permission.getId() > 0) {
            Visitor visitor = ThreadContext.getSessionVisitor();
            permission.setUpdator(visitor.getLoginUserId());
            permission.setUpdateTime(new Date());
            authPermissionMapper.updateByPrimaryKeySelective(permission);
        } else {
            consumer.accept(CodeMessage.CODE_106.toResponseMessage());
        }
        return permission;
    }


    @Override
    public int deleteByPrimaryKey(Long permissionId, Consumer<ResponseMessage> consumer) {
        if (permissionId != null && permissionId > 0) {
            return authPermissionMapper.deleteByPrimaryKey(permissionId);
        } else {
            consumer.accept(CodeMessage.CODE_106.toResponseMessage());
        }
        return 0;
    }
}
