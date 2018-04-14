package com.baidu.disconf.web.service.app.mybatis;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AppEnvDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final AppEnv appEnv = new AppEnv();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = appEnv.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> appId = appEnv.appId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> envName = appEnv.envName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> envComments = appEnv.envComments;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> isEnvDefault = appEnv.isEnvDefault;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> envId = appEnv.envId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = appEnv.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> creator = appEnv.creator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = appEnv.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> updator = appEnv.updator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class AppEnv extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> appId = column("app_id", JDBCType.BIGINT);

        public final SqlColumn<String> envName = column("env_name", JDBCType.VARCHAR);

        public final SqlColumn<String> envComments = column("env_comments", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> isEnvDefault = column("is_env_default", JDBCType.BIT);

        public final SqlColumn<Long> envId = column("env_id", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> creator = column("creator", JDBCType.BIGINT);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updator = column("updator", JDBCType.BIGINT);

        public AppEnv() {
            super("app_env");
        }
    }
}