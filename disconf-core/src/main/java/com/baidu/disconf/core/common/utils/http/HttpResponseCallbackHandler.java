package com.baidu.disconf.core.common.utils.http;

import java.io.IOException;

import org.apache.http.HttpEntity;

/**
 * HttpResponseCallbackHandler
 * @param <T> HttpResponse body实现类
 */
public interface HttpResponseCallbackHandler<T> {

    /**
     * 捕获HttpResponse
     * @param requestBody 请求体
     * @param entity HTTP实体对象
     * @return 返回T实例对象
     * @throws IOException 内部异常
     */
    T handleResponse(String requestBody, HttpEntity entity) throws IOException;
}
