package com.baidu.disconf.core.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * MachineInfo
 *
 * @author liaoqiqi
 * @version 2014-7-30
 */
public final class MachineInfo {

    private MachineInfo() {

    }


    /**
     *  获取机器名
     * @return 机器名
     * @throws Exception 内部异常
     */
    public static String getHostName() throws Exception {

        try {
            InetAddress addr = InetAddress.getLocalHost();
            String hostname = addr.getHostName();

            return hostname;

        } catch (UnknownHostException e) {

            throw new Exception(e);
        }
    }


    /**
     *  获取机器IP
     * @return 机器IP
     * @throws Exception 内部异常
     */
    public static String getHostIp() throws Exception {

        try {
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress();

            return ip;

        } catch (UnknownHostException e) {

            throw new Exception(e);
        }
    }

}
