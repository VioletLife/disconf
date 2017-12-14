package com.baidu.disconf.core.common.restful.core;

/**
 * 一个可重试可执行方法
 *
 * @author liaoqiqi
 * @version 2014-6-10
 */
public interface UnreliableInterface {

    /**
     *
     * @param <T> 泛型参数
     * @return 泛型返回值
     * @throws Exception 内部异常
     */
    <T> T call() throws Exception;

}
