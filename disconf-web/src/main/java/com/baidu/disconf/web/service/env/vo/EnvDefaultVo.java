package com.baidu.disconf.web.service.env.vo;

import com.baidu.disconf.web.service.BaseVo;
import com.baidu.unbiz.common.genericdao.annotation.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
public class EnvDefaultVo extends BaseVo {
    private Long id;

    private String envName;

    private String envComments;

    private Date createTime;

    private Long creator;

    private Date lastUpdateTime;

    private Long lastUpdator;
}
