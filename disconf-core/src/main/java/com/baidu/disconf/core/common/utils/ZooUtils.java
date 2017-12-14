package com.baidu.disconf.core.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZooUtils
 *
 * @author liaoqiqi
 * @version 2014-7-30
 */
public final class ZooUtils {

    protected static final Logger logger = LoggerFactory
            .getLogger(ZooUtils.class);

    private ZooUtils() {

    }

    /**
     * 获取机器IP
     *
     * @return 机器IP
     */
    public static String getIp() {

        try {
            return MachineInfo.getHostIp();
        } catch (Exception e) {
            logger.error("cannot get host info", e);
            return "";
        }
    }

}
