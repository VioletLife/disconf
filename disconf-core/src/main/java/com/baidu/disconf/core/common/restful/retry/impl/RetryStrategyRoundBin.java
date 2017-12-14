package com.baidu.disconf.core.common.restful.retry.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.disconf.core.common.restful.core.UnreliableInterface;
import com.baidu.disconf.core.common.restful.retry.RetryStrategy;

/**
 * 轮循重试
 *
 * @author liaoqiqi
 * @version 2014-8-4
 */
public class RetryStrategyRoundBin implements RetryStrategy {

    protected static final Logger logger = LoggerFactory.getLogger(RetryStrategyRoundBin.class);

    /**
     * 执行重试过程
     *
     * @param unreliableImpl 实现类
     * @param retryTimes     重试次数
     * @param sleepSeconds   睡眠时间(秒)
     * @param <T>            泛型类
     * @return 泛型类T实例
     * @throws Exception 内部异常
     */
    @Override
    public <T> T retry(UnreliableInterface unreliableImpl, int retryTimes, int sleepSeconds) throws Exception {

        int cur_time = 0;
        for (; cur_time < retryTimes; ++cur_time) {

            try {

                return unreliableImpl.call();

            } catch (Exception e) {

                logger.warn("cannot reach, will retry " + cur_time + " .... " + e.toString());

                try {
                    Thread.sleep(sleepSeconds * 1000);
                } catch (InterruptedException e1) {
                }
            }
        }

        logger.warn("finally failed....");

        throw new Exception();
    }
}
