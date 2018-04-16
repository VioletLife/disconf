package com.baidu.disconf.web.web.version;


import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.app.mybatis.AppEnvVersion;
import com.baidu.disconf.web.service.app.mybatis.AppEnvVersionMapper;
import com.baidu.disconf.web.service.app.service.AppEnvVersionMgr;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.vo.JsonObjectBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 版本Controller
 *
 * @author QingDengYue
 */
@RestController
@RequestMapping(WebConstants.API_PREFIX + "/version")
public class VersionController extends BaseController {


    @Autowired
    private AppEnvVersionMgr appEnvVersionMgr;

    /**
     * 在指定的AppId和指定的EnvId下创建版本号
     *
     * @param version 版本号
     * @return
     */
    @RequestMapping(value = "app/env/create", method = RequestMethod.POST)
    public JsonObjectBase createVersionByAppIdAndEnvId(@RequestBody AppEnvVersion version) {
        AtomicReference<ResponseMessage> response = new AtomicReference<>(null);
        AppEnvVersion versionData = appEnvVersionMgr.createVersionByAppIdAndEnvId(version, response::set);
        if (response.get() != null) {
            return buildResponseMessage(response.get());
        }
        return buildSuccess(versionData);
    }
}
