package com.baidu.disconf.client.scan;

import com.baidu.disconf.client.scan.impl.ScanMgrImpl;
import com.baidu.disconf.client.support.registry.Registry;

/**
 * 扫描器工厂
 *
 * @author liaoqiqi
 * @version 2014-7-29
 */
public class ScanFactory {


    /**
     *
     * @param registry 注册实例
     * @return 扫描器
     * @throws Exception 内部异常
     */
    public static ScanMgr getScanMgr(Registry registry) throws Exception {

        ScanMgr scanMgr = new ScanMgrImpl(registry);
        return scanMgr;
    }
}
