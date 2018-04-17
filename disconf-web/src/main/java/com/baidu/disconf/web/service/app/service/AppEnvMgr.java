package com.baidu.disconf.web.service.app.service;

import com.baidu.disconf.web.common.message.CodeMessage;
import com.baidu.disconf.web.service.app.mybatis.AppEnv;
import com.baidu.disconf.web.service.app.vo.AppVo;
import com.baidu.dsp.common.vo.JsonObjectBase;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;

import java.util.List;
import java.util.function.Consumer;

public interface AppEnvMgr {
    void createApp(AppVo app, Consumer<CodeMessage> consumer);
    AppEnv selectAppEnvById(Long id);
    List<AppEnv> selectAppEnvByAppId(Long appId);
    QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AppEnv>>> selectByExample();
    void createEnvForApp(AppEnv appEnv,Consumer<CodeMessage> consumer);
}
