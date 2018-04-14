package com.baidu.disconf.web.service.app.mybatis;

import com.baidu.disconf.web.service.BaseVo;
import java.io.Serializable;
import javax.annotation.Generated;

public class App extends BaseVo implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long appId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String emails;

    private static final long serialVersionUID = 1L;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getAppId() {
        return appId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getName() {
        return name;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDescription() {
        return description;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCreateTime() {
        return createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getUpdateTime() {
        return updateTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getEmails() {
        return emails;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEmails(String emails) {
        this.emails = emails == null ? null : emails.trim();
    }
}