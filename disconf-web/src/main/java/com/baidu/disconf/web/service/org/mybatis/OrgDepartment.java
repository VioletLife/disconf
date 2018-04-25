package com.baidu.disconf.web.service.org.mybatis;

import com.baidu.disconf.web.service.BaseVo;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

public class OrgDepartment extends BaseVo implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String departmentCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String departmentName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String departmentComments;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String parentCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Boolean isOrgRoot;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Boolean isHiddenNode;

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
    public String getDepartmentCode() {
        return departmentCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode == null ? null : departmentCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDepartmentName() {
        return departmentName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDepartmentComments() {
        return departmentComments;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDepartmentComments(String departmentComments) {
        this.departmentComments = departmentComments == null ? null : departmentComments.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getParentCode() {
        return parentCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Boolean getIsOrgRoot() {
        return isOrgRoot;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIsOrgRoot(Boolean isOrgRoot) {
        this.isOrgRoot = isOrgRoot;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Boolean getIsHiddenNode() {
        return isHiddenNode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIsHiddenNode(Boolean isHiddenNode) {
        this.isHiddenNode = isHiddenNode;
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