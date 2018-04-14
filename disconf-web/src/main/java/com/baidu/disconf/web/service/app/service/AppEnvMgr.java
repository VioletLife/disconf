package com.baidu.disconf.web.service.app.service;

import com.baidu.disconf.web.service.app.vo.AppVo;
import com.baidu.dsp.common.vo.JsonObjectBase;

import java.util.function.Consumer;

public interface AppEnvMgr {
    <T extends JsonObjectBase>  void  createApp(AppVo app, Consumer<T> consumer);
}
