package com.baidu.disconf.web.service.user.bo;

import com.baidu.dsp.common.dao.Columns;
import com.baidu.dsp.common.dao.DB;
import com.baidu.unbiz.common.genericdao.annotation.Column;
import com.baidu.unbiz.common.genericdao.annotation.Table;
import com.github.knightliao.apollo.db.bo.BaseObject;

/**
 * 用户表
 *
 * @author liaoqiqi
 * @version 2013-11-28
 */
@Table(db = DB.DB_NAME, name = "user", keyColumn = Columns.USER_ID)
public class User extends BaseObject<Long> {

    private static final long serialVersionUID = 1L;

    // 唯一
    @Column(value = Columns.NAME)
    private String name;

    // token
    @Column(value = Columns.TOKEN)
    private String token;

    // 密码
    @Column(value = Columns.PASSWORD)
    private String password;

    //
    @Column(value = Columns.OWNAPPS)
    private String ownApps;

    /**
     * 角色ID
     */
    @Column(value = Columns.ROLE_ID)
    private int roleId;


    @Column(value = "user_account")
    private String userAccount;

    @Column(value = "mobile_phone")
    private String mobilePhone;

    @Column(value = "email")
    private String email;

    @Column(value = "qq")
    private String qq;

    @Column(value = "weixin")
    private String weixin;

    @Column(value = "department_id")
    private Long departmentId;

    @Column(value = "department_code")
    private String departmentCode;

    @Column(value = "comments")
    private String comments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOwnApps() {
        return ownApps;
    }

    public void setOwnApps(String ownApps) {
        this.ownApps = ownApps;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }


    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", password='" + password + '\'' +
                ", ownApps='" + ownApps + '\'' +
                ", roleId=" + roleId +
                ", userAccount='" + userAccount + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", weixin='" + weixin + '\'' +
                ", departmentId=" + departmentId +
                ", departmentCode='" + departmentCode + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
