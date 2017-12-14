package com.baidu.disconf.core.common.restful.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 远程对象URL表示，包括Host地址 和 URL
 *
 * @author liaoqiqi
 * @version 2014-6-10
 */
public class RemoteUrl {

    private String url;
    private List<String> serverList;

    private List<URL> urls = new ArrayList<URL>();

    protected static final Logger logger = LoggerFactory.getLogger(RemoteUrl.class);

    /**
     * 远程对象URL表示，包括Host地址 和 URL
     * @param url URL
     * @param serverList Host地址
     */
    public RemoteUrl(String url, List<String> serverList) {

        this.url = url;
        this.serverList = serverList;

        for (String server : serverList) {

            try {

                if (!server.startsWith("http://")) {
                    if (server.startsWith("https://")) {
                    } else {
                        server = "http://" + server;
                    }
                }

                urls.add(new URL(server + url));

            } catch (MalformedURLException e) {
                logger.error(e.toString());
            }
        }
    }

    /**
     * 获取URL
     * @return URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置URL
     * @param url URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 服务列表
     * @return 服务列表
     */
    public List<String> getServerList() {
        return serverList;
    }

    /**
     * 设置服务列表
     * @param serverList 服务列表
     */
    public void setServerList(List<String> serverList) {
        this.serverList = serverList;
    }


    /**
     * 获取所有的URL
     * @return
     */
    public List<URL> getUrls() {
        return urls;
    }

    @Override
    public String toString() {
        return "RemoteUrl [url=" + url + ", serverList=" + serverList + ", urls=" + urls + "]";
    }

}
