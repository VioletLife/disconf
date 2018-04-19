package com.baidu.disconf.web.service.auth.mybatis;

import com.baidu.disconf.web.service.BaseVo;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

public class AuthPermission extends BaseVo implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String permissionCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String permissionName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String permissionComments;

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
    public String getPermissionCode() {
        return permissionCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode == null ? null : permissionCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getPermissionName() {
        return permissionName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getPermissionComments() {
        return permissionComments;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPermissionComments(String permissionComments) {
        this.permissionComments = permissionComments == null ? null : permissionComments.trim();
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