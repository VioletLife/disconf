package com.baidu.disconf.web.service.app.service;

import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.app.mybatis.AppEnv;
import com.baidu.disconf.web.service.app.mybatis.AppEnvVersion;

import java.util.List;
import java.util.function.Consumer;

public interface AppEnvVersionMgr {
    AppEnvVersion createVersionByAppIdAndEnvId(AppEnvVersion version, Consumer<ResponseMessage> consumer);

    List<AppEnvVersion> selectVersionByAppIdAndEnvId(Long appId,Long envId);
}
