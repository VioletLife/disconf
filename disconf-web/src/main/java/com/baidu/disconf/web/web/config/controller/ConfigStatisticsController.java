package com.baidu.disconf.web.web.config.controller;

import com.baidu.disconf.core.common.constants.DisConfigTypeEnum;
import com.baidu.disconf.web.service.config.service.ConfigHistoryMgr;
import com.baidu.disconf.web.service.config.service.ConfigMgr;
import com.baidu.disconf.web.service.config.vo.ConfHistoryVo;
import com.baidu.disconf.web.service.config.vo.ConfigVo;
import com.baidu.disconf.web.service.config.vo.StatisticsVo;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.vo.JsonObjectBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 配置文件部分统计数据
 *
 * @author QingDengYue
 */
@Controller
@RequestMapping(WebConstants.API_PREFIX + "/web/config/statistics")
public class ConfigStatisticsController extends BaseController {


    @Autowired
    ConfigHistoryMgr configHistoryMgr;

    @Autowired
    ConfigMgr configMgr;

    /**
     * 获取指定configId 的历史记录
     *
     * @param configId configId
     * @param appId    应用ID
     * @param envId    环境ID
     * @param version  版本信息
     * @return 历史记录对象
     */
    @RequestMapping("file")
    @ResponseBody
    public JsonObjectBase fileKeyStatistics(Long configId, Long appId, String version, Long envId) {
        StatisticsVo statisticsVo = new StatisticsVo();
        Optional<List<ConfHistoryVo>> history = configHistoryMgr.getConfigHistoryByConfigId(configId);
        Optional<List<ConfigVo>> configContentFile = configMgr.getConfigContent(appId, envId, version, DisConfigTypeEnum.FILE);
        Optional<List<ConfigVo>> configContentItem = configMgr.getConfigContent(appId, envId, version, DisConfigTypeEnum.ITEM);
        history.ifPresent(statisticsVo::setHistory);

        configContentFile.ifPresent(statisticsVo::setConfigFiles);

        configContentItem.ifPresent(statisticsVo::setConfigItems);

        return buildSuccess(statisticsVo);
    }
}
