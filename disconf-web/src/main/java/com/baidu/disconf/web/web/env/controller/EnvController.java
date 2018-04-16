package com.baidu.disconf.web.web.env.controller;

import java.util.List;

import com.baidu.disconf.web.service.app.mybatis.AppEnv;
import com.baidu.disconf.web.service.app.mybatis.AppEnvDynamicSqlSupport;
import com.baidu.disconf.web.service.app.mybatis.AppEnvMapper;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.disconf.web.service.env.service.EnvMgr;
import com.baidu.disconf.web.service.env.vo.EnvListVo;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.vo.JsonObjectBase;

/**
 * @author liaoqiqi
 * @version 2014-6-16
 */
@Controller
@RequestMapping(WebConstants.API_PREFIX + "/env")
public class EnvController extends BaseController {

    protected static final Logger LOG = LoggerFactory.getLogger(EnvController.class);

    @Autowired
    private EnvMgr envMgr;


    @Autowired
    private AppEnvMapper appEnvMapper;

    /**
     * list
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonObjectBase list() {

        List<EnvListVo> envListVos = envMgr.getVoList();

        return buildListSuccess(envListVos, envListVos.size());
    }


    @RequestMapping(value = "/app/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonObjectBase appEnvList(Long appId) {
        List<AppEnv> appEnvs = appEnvMapper.selectByExample()
                .where(AppEnvDynamicSqlSupport.appId, IsEqualTo.of(() -> appId))
                .build()
                .execute();
        return buildListSuccess(appEnvs);
    }

}
