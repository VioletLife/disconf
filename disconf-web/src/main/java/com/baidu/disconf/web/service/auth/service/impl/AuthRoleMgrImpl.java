package com.baidu.disconf.web.service.auth.service.impl;

import com.baidu.disconf.web.service.auth.mybatis.AuthRoleMapper;
import com.baidu.disconf.web.service.auth.service.AuthRoleMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.soap.Addressing;

@Service
@Transactional
public class AuthRoleMgrImpl implements AuthRoleMgr {
    @Autowired
    private AuthRoleMapper authRoleMapper;
}
