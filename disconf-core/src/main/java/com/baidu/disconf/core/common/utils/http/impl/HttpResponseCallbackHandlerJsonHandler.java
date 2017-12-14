package com.baidu.disconf.core.common.utils.http.impl;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import com.baidu.disconf.core.common.utils.http.HttpResponseCallbackHandler;

/**
 * Created by knightliao on 16/1/7.
 */
public class HttpResponseCallbackHandlerJsonHandler<T> implements HttpResponseCallbackHandler<T> {

    private Class<T> clazz = null;

    public HttpResponseCallbackHandlerJsonHandler(Class<T> clazz) {
        this.clazz = clazz;
    }


    /**
     * 捕获HttpResponse
     * @param requestBody 请求体
     * @param entity HTTP实体对象
     * @return 返回T实例对象
     * @throws IOException 内部异常
     */
    @Override
    public T handleResponse(String requestBody, HttpEntity entity) throws IOException {

        String json = EntityUtils.toString(entity, "UTF-8");

        T response = JSON.parseObject(json,clazz);

        return response;
    }
}
