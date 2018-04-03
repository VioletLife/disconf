package com.baidu.disconf.web.service;

import com.github.knightliao.apollo.db.bo.BaseObject;
import org.objenesis.ObjenesisHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BaseService {


    /**
     * 执行BO到VO的转换
     *
     * @param clazz   Vo Class
     * @param boModel bo数据对象
     * @param <V>     V
     * @param <B>     B
     * @return Vo实例
     */
    default <V extends BaseVo, B extends BaseObject> Optional<V> fromBoToVo(Class<V> clazz, B boModel) {
        V instance = ObjenesisHelper.newInstance(clazz);
        BeanUtils.copyProperties(boModel, instance);
        return Optional.of(instance);
    }


    /**
     * 执行BO到VO的转换
     *
     * @param clazz   Vo Class
     * @param boModel bo数据对象
     * @param <V>     V
     * @param <B>     B
     * @return Vo实例
     */
    default <V extends BaseVo, B extends BaseObject> Optional<List<V>> fromBoToVo(Class<V> clazz, List<B> boModel) {
        List<V> voList = new ArrayList<>();
        if (boModel != null && boModel.size() > 0) {
            for (B model : boModel) {
                V instance = ObjenesisHelper.newInstance(clazz);
                BeanUtils.copyProperties(model, instance);
                voList.add(instance);
            }
        }
        return Optional.of(voList);
    }


    /**
     * 执行VO到BO的转换
     *
     * @param clazz   Bo Class
     * @param voModel Vo数据对象
     * @param <V>     V
     * @param <B>     B
     * @return Bo实例
     */
    default <V extends BaseVo, B extends BaseObject> Optional<B> fromVoToBo(Class<B> clazz, V voModel) {
        B instance = ObjenesisHelper.newInstance(clazz);
        BeanUtils.copyProperties(voModel, instance);
        return Optional.of(instance);
    }


    /**
     * 执行VO到BO的转换
     *
     * @param clazz   Bo Class
     * @param voModel Vo数据对象
     * @param <V>     V
     * @param <B>     B
     * @return Bo实例
     */
    default <V extends BaseVo, B extends BaseObject> Optional<List<B>> fromVoToBo(Class<B> clazz, List<V> voModel) {
        List<B> boList = new ArrayList<>();
        if (voModel != null && voModel.size() > 0) {
            for (V model : voModel) {
                B instance = ObjenesisHelper.newInstance(clazz);
                BeanUtils.copyProperties(model, instance);
                boList.add(instance);
            }
        }
        return Optional.of(boList);
    }

}
