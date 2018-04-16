package com.baidu.disconf.web.service.app.service;

import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.app.mybatis.AppEnvVersion;

import java.util.function.Consumer;

public interface AppEnvVersionMgr {
    AppEnvVersion createVersionByAppIdAndEnvId(AppEnvVersion version, Consumer<ResponseMessage> consumer);
}
