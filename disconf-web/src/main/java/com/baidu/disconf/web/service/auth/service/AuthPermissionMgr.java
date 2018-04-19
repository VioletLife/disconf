package com.baidu.disconf.web.service.auth.service;

import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.Page;
import com.baidu.disconf.web.service.auth.mybatis.AuthPermission;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface AuthPermissionMgr {
    Page<AuthPermission> selectByExampleWithRowbounds(Page<AuthPermission> rowBounds, String permissionName);
    AuthPermission insertSelective(AuthPermission permission, Consumer<ResponseMessage> consumer);
}
