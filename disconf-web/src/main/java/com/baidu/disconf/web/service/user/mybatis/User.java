package com.baidu.disconf.web.service.user.mybatis;

import com.baidu.disconf.web.service.BaseVo;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

public class User extends BaseVo implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String password;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String token;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String ownapps;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long roleId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String userAccount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String mobilePhone;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String email;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String qq;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String weixin;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long departmentId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String departmentCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String comments;

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
    public Long getUserId() {
        return userId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUserId(Long userId) {
        this.userId = userId;
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
    public String getPassword() {
        return password;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getToken() {
        return token;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getOwnapps() {
        return ownapps;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOwnapps(String ownapps) {
        this.ownapps = ownapps == null ? null : ownapps.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getRoleId() {
        return roleId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getUserAccount() {
        return userAccount;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getMobilePhone() {
        return mobilePhone;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getEmail() {
        return email;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getQq() {
        return qq;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getWeixin() {
        return weixin;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getDepartmentId() {
        return departmentId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDepartmentCode() {
        return departmentCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode == null ? null : departmentCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getComments() {
        return comments;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
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