package com.baidu.disconf.web.web.config.dto;

import com.baidu.disconf.web.service.app.bo.App;
import com.baidu.disconf.web.service.app.mybatis.AppEnv;
import com.baidu.disconf.web.service.env.bo.Env;

/**
 * @author liaoqiqi
 * @version 2014-9-11
 */
public class ConfigFullModel {

    private App app;
    private AppEnv env;
    private String version;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public AppEnv getEnv() {
        return env;
    }

    public void setEnv(AppEnv env) {
        this.env = env;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ConfigFullModel(App app, AppEnv env, String version, String key) {
        super();
        this.app = app;
        this.env = env;
        this.version = version;
        this.key = key;
    }
}
