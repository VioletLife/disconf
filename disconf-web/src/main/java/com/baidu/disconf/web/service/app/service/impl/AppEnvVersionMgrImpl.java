package com.baidu.disconf.web.service.app.service.impl;

import com.baidu.disconf.web.service.app.mybatis.AppEnvVersionMapper;
import com.baidu.disconf.web.service.app.service.AppEnvVersionMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppEnvVersionMgrImpl implements AppEnvVersionMgr {

    @Autowired
    public AppEnvVersionMapper appEnvVersionMapper;
}
