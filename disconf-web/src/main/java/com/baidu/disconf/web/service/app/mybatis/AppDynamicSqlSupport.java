package com.baidu.disconf.web.service.app.mybatis;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AppDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final App app = new App();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> appId = app.appId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = app.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = app.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> createTime = app.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> updateTime = app.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> emails = app.emails;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class App extends SqlTable {
        public final SqlColumn<Long> appId = column("app_id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> createTime = column("create_time", JDBCType.VARCHAR);

        public final SqlColumn<String> updateTime = column("update_time", JDBCType.VARCHAR);

        public final SqlColumn<String> emails = column("emails", JDBCType.VARCHAR);

        public App() {
            super("app");
        }
    }
}