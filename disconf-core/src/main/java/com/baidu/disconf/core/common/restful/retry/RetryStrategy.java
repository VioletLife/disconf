package com.baidu.disconf.core.common.restful.retry;

import com.baidu.disconf.core.common.restful.core.UnreliableInterface;

/**
 * 重试的策略
 *
 * @author liaoqiqi
 * @version 2014-6-10
 */
public interface RetryStrategy {

    /**
     * 执行重试过程
     * @param unreliableImpl 实现类
     * @param retryTimes 重试次数
     * @param sleepSeconds 睡眠时间(秒)
     * @param <T> 泛型类
     * @return 泛型类T实例
     * @throws Exception 内部异常
     */
    <T> T retry(UnreliableInterface unreliableImpl, int retryTimes, int sleepSeconds) throws Exception;

}
