package com.baidu.disconf.web.service.app.mybatis;

import com.baidu.disconf.web.service.BaseVo;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

public class AppEnvVersion extends BaseVo implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long appId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long appEnvId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String versionName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String versionComments;

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
    public Long getAppEnvId() {
        return appEnvId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAppEnvId(Long appEnvId) {
        this.appEnvId = appEnvId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVersionName() {
        return versionName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVersionComments() {
        return versionComments;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVersionComments(String versionComments) {
        this.versionComments = versionComments == null ? null : versionComments.trim();
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