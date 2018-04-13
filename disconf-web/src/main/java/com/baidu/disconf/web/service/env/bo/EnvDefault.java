package com.baidu.disconf.web.service.env.bo;

import com.baidu.dsp.common.dao.Columns;
import com.baidu.dsp.common.dao.DB;
import com.baidu.unbiz.common.genericdao.annotation.Column;
import com.baidu.unbiz.common.genericdao.annotation.Table;
import com.github.knightliao.apollo.db.bo.BaseObject;

import java.util.Date;


@Table(db = DB.DB_NAME, name = "disconf_env_default", keyColumn = Columns.ID)
public class EnvDefault extends BaseObject<Long> {

    private static final long serialVersionUID = 7226002937162613488L;

    @Column(value = "id")
    private Long id;

    @Column(value = "env_name")
    private String envName;

    @Column(value = "env_comments")
    private String envComments;

    @Column(value = "create_time")
    private Date createTime;

    @Column(value = "creator")
    private Long creator;

    @Column(value = "last_update_time")
    private Date lastUpdateTime;

    @Column(value = "last_updator")
    private Long lastUpdator;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public String getEnvComments() {
        return envComments;
    }

    public void setEnvComments(String envComments) {
        this.envComments = envComments;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getLastUpdator() {
        return lastUpdator;
    }

    public void setLastUpdator(Long lastUpdator) {
        this.lastUpdator = lastUpdator;
    }
}
