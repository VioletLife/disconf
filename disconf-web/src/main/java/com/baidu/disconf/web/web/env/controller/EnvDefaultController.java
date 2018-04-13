package com.baidu.disconf.web.web.env.controller;

import com.baidu.disconf.web.service.env.service.EnvDefaultMgr;
import com.baidu.disconf.web.service.env.vo.EnvDefaultVo;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.vo.JsonObjectBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 默认配置环境Controller
 *
 * @author QingDengYue
 */
@RestController
@RequestMapping(WebConstants.API_PREFIX + "/env")
public class EnvDefaultController extends BaseController {

    @Autowired
    EnvDefaultMgr envDefaultMgr;


    @RequestMapping("default/list")
    public JsonObjectBase defaultEnvList() {
        Optional<List<EnvDefaultVo>> defaultMgrAll = envDefaultMgr.findAll();
        return buildListSuccess(defaultMgrAll.isPresent() ? defaultMgrAll.get() : Collections.emptyList(), defaultMgrAll.map(List::size).orElse(0));
    }
}
