package com.baidu.disconf.web.service.user.service.impl;

import com.baidu.disconf.web.service.user.mybatis.AuthUserRoleMapper;
import com.baidu.disconf.web.service.user.service.AuthUserRoleMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthUserRoleMgrImpl implements AuthUserRoleMgr {
    @Autowired
    private AuthUserRoleMapper authUserRoleMapper;
}
