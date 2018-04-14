package com.baidu.disconf.web.service.app.vo;

import com.baidu.disconf.web.service.app.mybatis.AppEnv;
import com.baidu.disconf.web.service.app.mybatis.AppEnvVersion;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
@Data
public class AppEnvVo {
    public AppEnv env;
    public List<AppEnvVersion> versions;
}
