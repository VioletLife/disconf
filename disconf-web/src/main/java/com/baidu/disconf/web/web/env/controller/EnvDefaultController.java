package com.baidu.disconf.web.web.env.controller;

import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.Page;
import com.baidu.disconf.web.service.env.service.EnvDefaultMgr;
import com.baidu.disconf.web.service.env.vo.EnvDefaultVo;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.vo.JsonObjectBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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


    /**
     * 获取默认环境信息列表
     *
     * @param envName    环境名称
     * @param pageNumber 当前页码
     * @param pageSize   每一页数据大小
     * @return 环境信息列表
     */
    @RequestMapping("page/list")
    public JsonObjectBase envList(
            @RequestParam(required = false, defaultValue = "") String envName,
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        Page<EnvDefaultVo> page = new Page<>(pageNumber, pageSize);
        Page<EnvDefaultVo> defaultVoPage = envDefaultMgr.selectByExampleWithRowbounds(page, envName);
        return buildSuccess(defaultVoPage);
    }


    /**
     * 创建默认环境
     *
     * @param envDefaultVo 环境信息
     * @return 环境信息
     */
    @RequestMapping(value = "default/create", method = RequestMethod.POST)
    public JsonObjectBase createDefaultEnv(@RequestBody EnvDefaultVo envDefaultVo) {
        AtomicReference<ResponseMessage> reference = new AtomicReference<>(null);
        EnvDefaultVo defaultVo = envDefaultMgr.insertSelective(envDefaultVo, reference::set);
        if (reference.get() != null) {
            return buildResponseMessage(reference.get());
        }
        return buildSuccess(defaultVo);
    }

    /**
     * 创建默认环境
     *
     * @param envDefaultVo 环境信息
     * @return 环境信息
     */
    @RequestMapping(value = "default/update", method = RequestMethod.POST)
    public JsonObjectBase updateDefaultEnv(@RequestBody EnvDefaultVo envDefaultVo) {
        AtomicReference<ResponseMessage> reference = new AtomicReference<>(null);
        EnvDefaultVo defaultVo = envDefaultMgr.updateSelective(envDefaultVo, reference::set);
        if (reference.get() != null) {
            return buildResponseMessage(reference.get());
        }
        return buildSuccess(defaultVo);
    }


    /**
     * 删除默认环境信息
     * @param envId 环境ID
     * @return 返回值
     */
    @RequestMapping(value = "default/delete")
    public JsonObjectBase deleteDefaultEnv(Long envId) {
        return buildSuccess(envDefaultMgr.deleteByPrimaryKey(envId));
    }


}
