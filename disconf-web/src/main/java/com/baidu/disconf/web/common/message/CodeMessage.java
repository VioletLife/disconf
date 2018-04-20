package com.baidu.disconf.web.common.message;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 接口状态码
 *
 * @author QingDengYue
 */
public enum CodeMessage {
    /**
     * 状态码:100 表示应用已存在
     * 状态码:0 表示接口数据正常
     */
    CODE_100(100, "应用已存在"),
    CODE_101(101, "版本号已经存在"),
    CODE_102(102, "AppId无效或者环境名称无效"),
    CODE_103(103, "当前应用已经存在同名称的环境信息"),
    CODE_104(104, "该权限编码已存在"),
    CODE_105(105, "权限编码或者权限名称无效"),
    CODE_106(106, "权限ID错误"),
    CODE_0(0, "Ok");
    private int code;
    private String message;

    CodeMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 转换为JSON对象
     *
     * @return SON对象
     */
    public ResponseMessage toResponseMessage() {
        ResponseMessage responseMessage = new ResponseMessage(this.code, this.message);
        return responseMessage;
    }
}