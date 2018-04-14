package com.baidu.disconf.web.service.app.mybatis;

import static com.baidu.disconf.web.service.app.mybatis.AppEnvDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.baidu.disconf.web.service.MybatisMapper;
import com.baidu.disconf.web.service.app.mybatis.AppEnv;

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
public interface AppEnvMapper extends MybatisMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    int insert(InsertStatementProvider<AppEnv> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("AppEnvResult")
    AppEnv selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "AppEnvResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "app_id", property = "appId", jdbcType = JdbcType.BIGINT),
            @Result(column = "env_name", property = "envName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "env_comments", property = "envComments", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_env_default", property = "isEnvDefault", jdbcType = JdbcType.BIT),
            @Result(column = "env_id", property = "envId", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "updator", property = "updator", jdbcType = JdbcType.BIGINT)
    })
    List<AppEnv> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(appEnv);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, appEnv);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, appEnv)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(AppEnv record) {
        return insert(SqlBuilder.insert(record)
                .into(appEnv)
                .map(id).toProperty("id")
                .map(appId).toProperty("appId")
                .map(envName).toProperty("envName")
                .map(envComments).toProperty("envComments")
                .map(isEnvDefault).toProperty("isEnvDefault")
                .map(envId).toProperty("envId")
                .map(createTime).toProperty("createTime")
                .map(creator).toProperty("creator")
                .map(updateTime).toProperty("updateTime")
                .map(updator).toProperty("updator")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(AppEnv record) {
        return insert(SqlBuilder.insert(record)
                .into(appEnv)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(appId).toPropertyWhenPresent("appId", record::getAppId)
                .map(envName).toPropertyWhenPresent("envName", record::getEnvName)
                .map(envComments).toPropertyWhenPresent("envComments", record::getEnvComments)
                .map(isEnvDefault).toPropertyWhenPresent("isEnvDefault", record::getIsEnvDefault)
                .map(envId).toPropertyWhenPresent("envId", record::getEnvId)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(creator).toPropertyWhenPresent("creator", record::getCreator)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(updator).toPropertyWhenPresent("updator", record::getUpdator)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AppEnv>>> selectByExampleWithRowbounds(RowBounds rowBounds) {
        return SelectDSL.selectWithMapper(this::selectMany, id, appId, envName, envComments, isEnvDefault, envId, createTime, creator, updateTime, updator)
                .from(appEnv);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AppEnv>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, appId, envName, envComments, isEnvDefault, envId, createTime, creator, updateTime, updator)
                .from(appEnv);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AppEnv>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, appId, envName, envComments, isEnvDefault, envId, createTime, creator, updateTime, updator)
                .from(appEnv);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default AppEnv selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, appId, envName, envComments, isEnvDefault, envId, createTime, creator, updateTime, updator)
                .from(appEnv)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(AppEnv record) {
        return UpdateDSL.updateWithMapper(this::update, appEnv)
                .set(id).equalTo(record::getId)
                .set(appId).equalTo(record::getAppId)
                .set(envName).equalTo(record::getEnvName)
                .set(envComments).equalTo(record::getEnvComments)
                .set(isEnvDefault).equalTo(record::getIsEnvDefault)
                .set(envId).equalTo(record::getEnvId)
                .set(createTime).equalTo(record::getCreateTime)
                .set(creator).equalTo(record::getCreator)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updator).equalTo(record::getUpdator);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(AppEnv record) {
        return UpdateDSL.updateWithMapper(this::update, appEnv)
                .set(id).equalToWhenPresent(record::getId)
                .set(appId).equalToWhenPresent(record::getAppId)
                .set(envName).equalToWhenPresent(record::getEnvName)
                .set(envComments).equalToWhenPresent(record::getEnvComments)
                .set(isEnvDefault).equalToWhenPresent(record::getIsEnvDefault)
                .set(envId).equalToWhenPresent(record::getEnvId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updator).equalToWhenPresent(record::getUpdator);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(AppEnv record) {
        return UpdateDSL.updateWithMapper(this::update, appEnv)
                .set(appId).equalTo(record::getAppId)
                .set(envName).equalTo(record::getEnvName)
                .set(envComments).equalTo(record::getEnvComments)
                .set(isEnvDefault).equalTo(record::getIsEnvDefault)
                .set(envId).equalTo(record::getEnvId)
                .set(createTime).equalTo(record::getCreateTime)
                .set(creator).equalTo(record::getCreator)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updator).equalTo(record::getUpdator)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(AppEnv record) {
        return UpdateDSL.updateWithMapper(this::update, appEnv)
                .set(appId).equalToWhenPresent(record::getAppId)
                .set(envName).equalToWhenPresent(record::getEnvName)
                .set(envComments).equalToWhenPresent(record::getEnvComments)
                .set(isEnvDefault).equalToWhenPresent(record::getIsEnvDefault)
                .set(envId).equalToWhenPresent(record::getEnvId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updator).equalToWhenPresent(record::getUpdator)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}