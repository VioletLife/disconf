package com.baidu.disconf.client.support.registry;

import java.util.List;

/**
 * Created by knightliao on 15/11/26.
 */
public interface Registry {

    /**
     *
     * @param type 类型
     * @param newInstance  实例
     * @param <T> 泛型类
     * @return 可找到的Bean的实例列表
     */
    <T> List<T> findByType(Class<T> type, boolean newInstance);


    /**
     *
     * @param type 类型
     * @param newInstance 实例
     * @param <T> 泛型类
     * @return 可找到的Bean的实例列表
     */
    <T> T getFirstByType(Class<T> type, boolean newInstance);


    /**
     *
     * @param type 类型
     * @param newInstance 新实例
     * @param withProxy 代理
     * @param <T> 泛型类
     * @return 可找到的Bean的实例列表
     */
    <T> T getFirstByType(Class<T> type, boolean newInstance, boolean withProxy);
}
