package com.baidu.disconf.core.common.path;

import com.baidu.disconf.core.common.constants.Constants;

/**
 * Zoo path 管理
 *
 * @author liaoqiqi
 * @version 2014-6-16
 */
public class ZooPathMgr {
    /**
     *
     * @param urlPrefix url前缀
     * @param app app
     * @param env env
     * @param version version
     * @return URL地址
     * @author liaoqiqi
     * @date 2013-6-17
     */
    public static String getZooBaseUrl(String urlPrefix, String app, String env, String version) {

        StringBuffer sb = new StringBuffer();
        sb.append(urlPrefix);

        sb.append(Constants.SEP_STRING);
        sb.append(app);

        sb.append(Constants.SEP_APP);
        sb.append(version);

        sb.append(Constants.SEP_APP);
        sb.append(env);

        return sb.toString();
    }

    /**
     * 组合两个路径
     * @param path1 路径1
     * @param path2 路径2
     * @return 结果路径
     */
    public static String joinPath(String path1, String path2) {
        return path1 + Constants.SEP_STRING + path2;
    }

    /**
     * 获取配置项路径
     * @param baseUrl 基础URL
     * @return 路径
     * @author liaoqiqi
     * @date 2013-6-17
     */
    public static String getItemZooPath(String baseUrl) {

        return baseUrl + Constants.SEP_STRING + Constants.STORE_ITEM_URL_KEY;
    }

    /**
     * 获取文件路径
     * @param baseUrl 基础URL
     * @return 文件路径
     * @author liaoqiqi
     * @date 2013-6-17
     */
    public static String getFileZooPath(String baseUrl) {

        return baseUrl + Constants.SEP_STRING + Constants.STORE_FILE_URL_KEY;
    }
}
