package com.baidu.disconf.web.service.app.service;

import com.baidu.disconf.web.common.message.CodeMessage;
import com.baidu.disconf.web.service.app.mybatis.AppEnv;
import com.baidu.disconf.web.service.app.vo.AppVo;
import com.baidu.dsp.common.vo.JsonObjectBase;

import java.util.List;
import java.util.function.Consumer;

public interface AppEnvMgr {
    void createApp(AppVo app, Consumer<CodeMessage> consumer);
    AppEnv selectAppEnvById(Long id);
    List<AppEnv> selectAppEnvByAppId(Long appId);
}
