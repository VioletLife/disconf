package com.baidu.disconf.core.common.utils;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoruilong
 * @since 2017/12/14 13:59
 */
public class FastjsonUtils {
    public static String toJson(Object object) {
        return JSON.toJSONString(object);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> parse2Map(String json) {
        return JSON.parseObject(json, Map.class);
    }
}
