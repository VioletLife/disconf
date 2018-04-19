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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
@Transactional
public class AuthPermissionMgrImpl implements AuthPermissionMgr {
    @Autowired
    private AuthPermissionMapper authPermissionMapper;


    @Override
    public Page<AuthPermission> selectByExampleWithRowbounds(Page<AuthPermission> rowBounds, String permissionName) {
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
        Optional<List<AuthPermission>> authPermissions = Optional.of(cache);
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
                authPermissionMapper.insertSelective(permission);
            }
        } else {
            consumer.accept(CodeMessage.CODE_105.toResponseMessage());
        }
        return permission;
    }
}
