package com.baidu.disconf.web.service.env.mybatis;

import static com.baidu.disconf.web.service.env.mybatis.EnvDefaultDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.baidu.disconf.web.service.MybatisMapper;
import com.baidu.disconf.web.service.env.mybatis.EnvDefault;
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
public interface EnvDefaultMapper extends MybatisMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys = true,keyProperty = "record.id")
    int insert(InsertStatementProvider<EnvDefault> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("EnvDefaultResult")
    EnvDefault selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="EnvDefaultResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="env_name", property="envName", jdbcType=JdbcType.VARCHAR),
        @Result(column="env_comments", property="envComments", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.BIGINT),
        @Result(column="last_update_time", property="lastUpdateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_updator", property="lastUpdator", jdbcType=JdbcType.BIGINT)
    })
    List<EnvDefault> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(envDefault);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, envDefault);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, envDefault)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(EnvDefault record) {
        return insert(SqlBuilder.insert(record)
                .into(envDefault)
                .map(id).toProperty("id")
                .map(envName).toProperty("envName")
                .map(envComments).toProperty("envComments")
                .map(createTime).toProperty("createTime")
                .map(creator).toProperty("creator")
                .map(lastUpdateTime).toProperty("lastUpdateTime")
                .map(lastUpdator).toProperty("lastUpdator")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(EnvDefault record) {
        return insert(SqlBuilder.insert(record)
                .into(envDefault)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(envName).toPropertyWhenPresent("envName", record::getEnvName)
                .map(envComments).toPropertyWhenPresent("envComments", record::getEnvComments)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(creator).toPropertyWhenPresent("creator", record::getCreator)
                .map(lastUpdateTime).toPropertyWhenPresent("lastUpdateTime", record::getLastUpdateTime)
                .map(lastUpdator).toPropertyWhenPresent("lastUpdator", record::getLastUpdator)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<EnvDefault>>> selectByExampleWithRowbounds(RowBounds rowBounds) {
        return SelectDSL.selectWithMapper(this::selectMany, id, envName, envComments, createTime, creator, lastUpdateTime, lastUpdator)
                .from(envDefault);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<EnvDefault>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, envName, envComments, createTime, creator, lastUpdateTime, lastUpdator)
                .from(envDefault);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<EnvDefault>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, envName, envComments, createTime, creator, lastUpdateTime, lastUpdator)
                .from(envDefault);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default EnvDefault selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, envName, envComments, createTime, creator, lastUpdateTime, lastUpdator)
                .from(envDefault)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(EnvDefault record) {
        return UpdateDSL.updateWithMapper(this::update, envDefault)
                .set(id).equalTo(record::getId)
                .set(envName).equalTo(record::getEnvName)
                .set(envComments).equalTo(record::getEnvComments)
                .set(createTime).equalTo(record::getCreateTime)
                .set(creator).equalTo(record::getCreator)
                .set(lastUpdateTime).equalTo(record::getLastUpdateTime)
                .set(lastUpdator).equalTo(record::getLastUpdator);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(EnvDefault record) {
        return UpdateDSL.updateWithMapper(this::update, envDefault)
                .set(id).equalToWhenPresent(record::getId)
                .set(envName).equalToWhenPresent(record::getEnvName)
                .set(envComments).equalToWhenPresent(record::getEnvComments)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(lastUpdateTime).equalToWhenPresent(record::getLastUpdateTime)
                .set(lastUpdator).equalToWhenPresent(record::getLastUpdator);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(EnvDefault record) {
        return UpdateDSL.updateWithMapper(this::update, envDefault)
                .set(envName).equalTo(record::getEnvName)
                .set(envComments).equalTo(record::getEnvComments)
                .set(createTime).equalTo(record::getCreateTime)
                .set(creator).equalTo(record::getCreator)
                .set(lastUpdateTime).equalTo(record::getLastUpdateTime)
                .set(lastUpdator).equalTo(record::getLastUpdator)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(EnvDefault record) {
        return UpdateDSL.updateWithMapper(this::update, envDefault)
                .set(envName).equalToWhenPresent(record::getEnvName)
                .set(envComments).equalToWhenPresent(record::getEnvComments)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(lastUpdateTime).equalToWhenPresent(record::getLastUpdateTime)
                .set(lastUpdator).equalToWhenPresent(record::getLastUpdator)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}