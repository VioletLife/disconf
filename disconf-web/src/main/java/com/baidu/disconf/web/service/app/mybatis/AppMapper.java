package com.baidu.disconf.web.service.app.mybatis;

import static com.baidu.disconf.web.service.app.mybatis.AppDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.baidu.disconf.web.service.MybatisMapper;
import com.baidu.disconf.web.service.app.mybatis.App;

import java.util.List;
import javax.annotation.Generated;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

@Mapper
public interface AppMapper extends MybatisMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "record.appId")
    int insert(InsertStatementProvider<App> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("AppResult")
    App selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "AppResult", value = {
            @Result(column = "app_id", property = "appId", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "emails", property = "emails", jdbcType = JdbcType.VARCHAR)
    })
    List<App> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(app);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, app);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long appId_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, app)
                .where(appId, isEqualTo(appId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(App record) {
        return insert(SqlBuilder.insert(record)
                .into(app)
                .map(appId).toProperty("appId")
                .map(name).toProperty("name")
                .map(description).toProperty("description")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .map(emails).toProperty("emails")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @Options(useGeneratedKeys = true, keyProperty = "record.appId")
    default int insertSelective(App record) {
        return insert(SqlBuilder.insert(record)
                .into(app)
                .map(appId).toPropertyWhenPresent("appId", record::getAppId)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(emails).toPropertyWhenPresent("emails", record::getEmails)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<App>>> selectByExampleWithRowbounds(RowBounds rowBounds) {
        return SelectDSL.selectWithMapper(this::selectMany, appId, name, description, createTime, updateTime, emails)
                .from(app);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<App>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, appId, name, description, createTime, updateTime, emails)
                .from(app);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<App>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, appId, name, description, createTime, updateTime, emails)
                .from(app);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default App selectByPrimaryKey(Long appId_) {
        return SelectDSL.selectWithMapper(this::selectOne, appId, name, description, createTime, updateTime, emails)
                .from(app)
                .where(appId, isEqualTo(appId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(App record) {
        return UpdateDSL.updateWithMapper(this::update, app)
                .set(appId).equalTo(record::getAppId)
                .set(name).equalTo(record::getName)
                .set(description).equalTo(record::getDescription)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(emails).equalTo(record::getEmails);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(App record) {
        return UpdateDSL.updateWithMapper(this::update, app)
                .set(appId).equalToWhenPresent(record::getAppId)
                .set(name).equalToWhenPresent(record::getName)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(emails).equalToWhenPresent(record::getEmails);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(App record) {
        return UpdateDSL.updateWithMapper(this::update, app)
                .set(name).equalTo(record::getName)
                .set(description).equalTo(record::getDescription)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(emails).equalTo(record::getEmails)
                .where(appId, isEqualTo(record::getAppId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(App record) {
        return UpdateDSL.updateWithMapper(this::update, app)
                .set(name).equalToWhenPresent(record::getName)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(emails).equalToWhenPresent(record::getEmails)
                .where(appId, isEqualTo(record::getAppId))
                .build()
                .execute();
    }
}