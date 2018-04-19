package com.baidu.disconf.web.service.auth.service.impl;

import com.baidu.disconf.web.service.auth.mybatis.AuthRolePermissionMapper;
import com.baidu.disconf.web.service.auth.service.AuthRolePermissionMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthRolePermissionMgrImpl implements AuthRolePermissionMgr {

    @Autowired
    private AuthRolePermissionMapper authRolePermissionMapper;
}
