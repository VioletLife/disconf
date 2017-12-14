package com.baidu.disconf.core.common.restful.type;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.disconf.core.common.restful.core.UnreliableInterface;
import com.baidu.disconf.core.common.utils.OsUtil;

/**
 * 下载文件
 *
 * @author liaoqiqi
 */
public class FetchConfFile implements UnreliableInterface {

    protected static final Logger logger = LoggerFactory.getLogger(FetchConfFile.class);

    private URL remoteUrl;
    private File localTmpFile;

    /**
     * 远程文件，本地文件
     *
     * @param remoteUrl
     * @param localTmpFile
     */
    public FetchConfFile(URL remoteUrl, File localTmpFile) {

        this.remoteUrl = remoteUrl;
        this.localTmpFile = localTmpFile;
    }

    /**
     * 进行下载
     * @param <T> 泛型参数
     * @return 泛型返回值
     * @throws Exception 内部异常
     */
    @Override
    public <T> T call() throws Exception {

        // 删除临时文件
        // LOGGER.info("start to remove tmp download file: " + ""
        // + localTmpFile.getAbsolutePath());
        if (localTmpFile.exists()) {
            localTmpFile.delete();
        }

        /**
         * 开始下载日志
         */
        logger.debug("start to download. From: " + remoteUrl + " , TO: " + localTmpFile.getAbsolutePath());

        /**
         * 文件下载
         */
        FileUtils.copyURLToFile(remoteUrl, localTmpFile);

        /**
         * 确认本地文件存在
         */
        if (!OsUtil.isFileExist(localTmpFile.getAbsolutePath())) {
            throw new Exception("download is ok, but cannot find downloaded file." + localTmpFile);
        }

        /**
         * download success
         */
        logger.debug("download success!  " + localTmpFile.getAbsolutePath());

        return null;
    }

}
