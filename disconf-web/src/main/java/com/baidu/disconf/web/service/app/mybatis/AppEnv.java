package com.baidu.disconf.web.service.app.mybatis;

import com.baidu.disconf.web.service.BaseVo;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

public class AppEnv extends BaseVo implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long appId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String envName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String envComments;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Boolean isEnvDefault;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long envId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long creator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long updator;

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
    public Long getAppId() {
        return appId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAppId(Long appId) {
        this.appId = appId;
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
    public Boolean getIsEnvDefault() {
        return isEnvDefault;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIsEnvDefault(Boolean isEnvDefault) {
        this.isEnvDefault = isEnvDefault;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getEnvId() {
        return envId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEnvId(Long envId) {
        this.envId = envId;
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
    public Date getUpdateTime() {
        return updateTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getUpdator() {
        return updator;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdator(Long updator) {
        this.updator = updator;
    }
}