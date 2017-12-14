package com.baidu.disconf.core.common.utils;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoruilong
 * @since 2017/12/14 13:59
 */
public class FastjsonUtils {
    /**
     * 将对象序列化为JSON字符串
     * @param object 对象
     * @return JSON字符串
     */
    public static String toJson(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 将JSON数据转换为Map形式
     * @param json JSON数据
     * @return Map形式的JSON数据
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parse2Map(String json) {
        return JSON.parseObject(json, Map.class);
    }
}
