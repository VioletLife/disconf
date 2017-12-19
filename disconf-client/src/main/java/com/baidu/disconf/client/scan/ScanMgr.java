package com.baidu.disconf.client.scan;

import java.util.List;

/**
 * @author liaoqiqi
 * @version 2014-7-29
 */
public interface ScanMgr {

    /**
     *
     * @param packageNameList 包名列表
     * @throws Exception 内部异常
     */
    void firstScan(List<String> packageNameList) throws Exception;

    /**
     * 第二次扫描
     * @throws Exception
     */
    void secondScan() throws Exception;


    /**
     *   重新载入 操作扫描
     * @param fileName
     * @throws Exception
     */
    void reloadableScan(String fileName) throws Exception;
}
