package com.baidu.disconf.web.service.app.vo;

import com.baidu.disconf.web.service.app.mybatis.App;
import com.baidu.disconf.web.service.app.mybatis.AppEnv;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
@Data
public class AppVo {
    public App app;
    public List<AppEnvVo> appEnvs;
}
