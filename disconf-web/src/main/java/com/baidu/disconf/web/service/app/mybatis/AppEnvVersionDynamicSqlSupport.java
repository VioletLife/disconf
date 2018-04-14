package com.baidu.disconf.web.service.app.mybatis;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AppEnvVersionDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final AppEnvVersion appEnvVersion = new AppEnvVersion();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = appEnvVersion.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> appId = appEnvVersion.appId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> appEnvId = appEnvVersion.appEnvId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> versionName = appEnvVersion.versionName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> versionComments = appEnvVersion.versionComments;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = appEnvVersion.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> creator = appEnvVersion.creator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = appEnvVersion.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> updator = appEnvVersion.updator;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class AppEnvVersion extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> appId = column("app_id", JDBCType.BIGINT);

        public final SqlColumn<Long> appEnvId = column("app_env_id", JDBCType.BIGINT);

        public final SqlColumn<String> versionName = column("version_name", JDBCType.VARCHAR);

        public final SqlColumn<String> versionComments = column("version_comments", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> creator = column("creator", JDBCType.BIGINT);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updator = column("updator", JDBCType.BIGINT);

        public AppEnvVersion() {
            super("app_env_version");
        }
    }
}