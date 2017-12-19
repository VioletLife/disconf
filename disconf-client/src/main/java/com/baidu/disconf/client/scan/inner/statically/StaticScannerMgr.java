package com.baidu.disconf.client.scan.inner.statically;

import java.util.Set;

import com.baidu.disconf.client.scan.inner.statically.model.ScanStaticModel;

/**
 * @author liaoqiqi
 * @version 2014-9-9
 */
public interface StaticScannerMgr {

    /**
     * 扫描数据并存储
     * @param scanModel  {@link ScanStaticModel}
     */
    void scanData2Store(ScanStaticModel scanModel);

    /**
     * 去除指定的Key 集
     * @param keySet 集合
     */
    void exclude(Set<String> keySet);
}
