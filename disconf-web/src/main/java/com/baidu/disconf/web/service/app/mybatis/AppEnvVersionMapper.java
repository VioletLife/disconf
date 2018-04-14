package com.baidu.disconf.web.service.app.mybatis;

import static com.baidu.disconf.web.service.app.mybatis.AppEnvVersionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.baidu.disconf.web.service.MybatisMapper;
import com.baidu.disconf.web.service.app.mybatis.AppEnvVersion;

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
public interface AppEnvVersionMapper extends MybatisMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    int insert(InsertStatementProvider<AppEnvVersion> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("AppEnvVersionResult")
    AppEnvVersion selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "AppEnvVersionResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "app_id", property = "appId", jdbcType = JdbcType.BIGINT),
            @Result(column = "app_env_id", property = "appEnvId", jdbcType = JdbcType.BIGINT),
            @Result(column = "version_name", property = "versionName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "version_comments", property = "versionComments", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "updator", property = "updator", jdbcType = JdbcType.BIGINT)
    })
    List<AppEnvVersion> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(appEnvVersion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, appEnvVersion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, appEnvVersion)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(AppEnvVersion record) {
        return insert(SqlBuilder.insert(record)
                .into(appEnvVersion)
                .map(id).toProperty("id")
                .map(appId).toProperty("appId")
                .map(appEnvId).toProperty("appEnvId")
                .map(versionName).toProperty("versionName")
                .map(versionComments).toProperty("versionComments")
                .map(createTime).toProperty("createTime")
                .map(creator).toProperty("creator")
                .map(updateTime).toProperty("updateTime")
                .map(updator).toProperty("updator")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(AppEnvVersion record) {
        return insert(SqlBuilder.insert(record)
                .into(appEnvVersion)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(appId).toPropertyWhenPresent("appId", record::getAppId)
                .map(appEnvId).toPropertyWhenPresent("appEnvId", record::getAppEnvId)
                .map(versionName).toPropertyWhenPresent("versionName", record::getVersionName)
                .map(versionComments).toPropertyWhenPresent("versionComments", record::getVersionComments)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(creator).toPropertyWhenPresent("creator", record::getCreator)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(updator).toPropertyWhenPresent("updator", record::getUpdator)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AppEnvVersion>>> selectByExampleWithRowbounds(RowBounds rowBounds) {
        return SelectDSL.selectWithMapper(this::selectMany, id, appId, appEnvId, versionName, versionComments, createTime, creator, updateTime, updator)
                .from(appEnvVersion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AppEnvVersion>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, appId, appEnvId, versionName, versionComments, createTime, creator, updateTime, updator)
                .from(appEnvVersion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AppEnvVersion>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, appId, appEnvId, versionName, versionComments, createTime, creator, updateTime, updator)
                .from(appEnvVersion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default AppEnvVersion selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, appId, appEnvId, versionName, versionComments, createTime, creator, updateTime, updator)
                .from(appEnvVersion)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(AppEnvVersion record) {
        return UpdateDSL.updateWithMapper(this::update, appEnvVersion)
                .set(id).equalTo(record::getId)
                .set(appId).equalTo(record::getAppId)
                .set(appEnvId).equalTo(record::getAppEnvId)
                .set(versionName).equalTo(record::getVersionName)
                .set(versionComments).equalTo(record::getVersionComments)
                .set(createTime).equalTo(record::getCreateTime)
                .set(creator).equalTo(record::getCreator)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updator).equalTo(record::getUpdator);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(AppEnvVersion record) {
        return UpdateDSL.updateWithMapper(this::update, appEnvVersion)
                .set(id).equalToWhenPresent(record::getId)
                .set(appId).equalToWhenPresent(record::getAppId)
                .set(appEnvId).equalToWhenPresent(record::getAppEnvId)
                .set(versionName).equalToWhenPresent(record::getVersionName)
                .set(versionComments).equalToWhenPresent(record::getVersionComments)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updator).equalToWhenPresent(record::getUpdator);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(AppEnvVersion record) {
        return UpdateDSL.updateWithMapper(this::update, appEnvVersion)
                .set(appId).equalTo(record::getAppId)
                .set(appEnvId).equalTo(record::getAppEnvId)
                .set(versionName).equalTo(record::getVersionName)
                .set(versionComments).equalTo(record::getVersionComments)
                .set(createTime).equalTo(record::getCreateTime)
                .set(creator).equalTo(record::getCreator)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updator).equalTo(record::getUpdator)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(AppEnvVersion record) {
        return UpdateDSL.updateWithMapper(this::update, appEnvVersion)
                .set(appId).equalToWhenPresent(record::getAppId)
                .set(appEnvId).equalToWhenPresent(record::getAppEnvId)
                .set(versionName).equalToWhenPresent(record::getVersionName)
                .set(versionComments).equalToWhenPresent(record::getVersionComments)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updator).equalToWhenPresent(record::getUpdator)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}