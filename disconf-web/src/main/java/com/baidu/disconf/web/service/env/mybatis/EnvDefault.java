package com.baidu.disconf.web.service.env.mybatis;

import com.baidu.disconf.web.service.BaseVo;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

public class EnvDefault extends BaseVo implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String envName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String envComments;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long creator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date lastUpdateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long lastUpdator;

    private static final long serialVersionUID = 1L;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getEnvName() {
        return envName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEnvName(String envName) {
        this.envName = envName == null ? null : envName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getEnvComments() {
        return envComments;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEnvComments(String envComments) {
        this.envComments = envComments == null ? null : envComments.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCreateTime() {
        return createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getCreator() {
        return creator;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getLastUpdator() {
        return lastUpdator;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLastUpdator(Long lastUpdator) {
        this.lastUpdator = lastUpdator;
    }
}