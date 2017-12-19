package com.baidu.disconf.client.watch;

import com.baidu.disconf.client.common.model.DisConfCommonModel;
import com.baidu.disconf.client.core.processor.DisconfCoreProcessor;
import com.baidu.disconf.core.common.constants.DisConfigTypeEnum;

/**
 * 监控的接口
 *
 * @author liaoqiqi
 * @version 2014-7-29
 */
public interface WatchMgr {

    /**
     * 初始化
     * @param hosts hosts
     * @param zooUrlPrefix zooUrlPrefix  前缀
     * @param debug 是否开启调试
     * @throws Exception 内部异常
     */
    void init(String hosts, String zooUrlPrefix, boolean debug) throws Exception;

    /**
     * 监控路径,监控前会事先创建路径,并且会新建一个自己的Temp子结点
     * @param disconfCoreMgr Disconf 核心处理器
     * @param disConfCommonModel APP信息
     * @param keyName Key名称
     * @param disConfigTypeEnum  Disconf 配置类枚举
     * @param value 节点值
     * @throws Exception
     */
    void watchPath(DisconfCoreProcessor disconfCoreMgr, DisConfCommonModel disConfCommonModel, String keyName,
                   DisConfigTypeEnum disConfigTypeEnum, String value) throws Exception;

    /**
     * 资源释放
     */
    void release();
}
