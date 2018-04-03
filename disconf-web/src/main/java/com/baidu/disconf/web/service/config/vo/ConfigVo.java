package com.baidu.disconf.web.service.config.vo;

import com.baidu.disconf.web.service.BaseVo;
import com.baidu.dsp.common.dao.Columns;
import com.baidu.unbiz.common.genericdao.annotation.Column;
import lombok.Data;
import lombok.ToString;
import sun.reflect.CallerSensitive;

@Data
@ToString(callSuper = true)
public class ConfigVo extends BaseVo {
    private Integer type;

    /**
     * status
     */
    private Integer status;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String value;

    /**
     *
     */
    private Long appId;

    /**
     *
     */
    private String version;

    /**
     *
     */
    private Long envId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
}
