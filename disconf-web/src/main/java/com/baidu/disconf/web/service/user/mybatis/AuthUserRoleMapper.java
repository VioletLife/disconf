package com.baidu.disconf.web.service.user.mybatis;

import static com.baidu.disconf.web.service.user.mybatis.AuthUserRoleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.baidu.disconf.web.service.MybatisMapper;
import com.baidu.disconf.web.service.user.mybatis.AuthUserRole;
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
public interface AuthUserRoleMapper extends MybatisMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys = true,keyColumn = "record.id")
    int insert(InsertStatementProvider<AuthUserRole> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AuthUserRoleResult")
    AuthUserRole selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AuthUserRoleResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updator", property="updator", jdbcType=JdbcType.BIGINT)
    })
    List<AuthUserRole> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(authUserRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, authUserRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, authUserRole)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(AuthUserRole record) {
        return insert(SqlBuilder.insert(record)
                .into(authUserRole)
                .map(id).toProperty("id")
                .map(userId).toProperty("userId")
                .map(roleId).toProperty("roleId")
                .map(createTime).toProperty("createTime")
                .map(creator).toProperty("creator")
                .map(updateTime).toProperty("updateTime")
                .map(updator).toProperty("updator")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(AuthUserRole record) {
        return insert(SqlBuilder.insert(record)
                .into(authUserRole)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(creator).toPropertyWhenPresent("creator", record::getCreator)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(updator).toPropertyWhenPresent("updator", record::getUpdator)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AuthUserRole>>> selectByExampleWithRowbounds(RowBounds rowBounds) {
        return SelectDSL.selectWithMapper(this::selectMany, id, userId, roleId, createTime, creator, updateTime, updator)
                .from(authUserRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AuthUserRole>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, userId, roleId, createTime, creator, updateTime, updator)
                .from(authUserRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AuthUserRole>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, userId, roleId, createTime, creator, updateTime, updator)
                .from(authUserRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default AuthUserRole selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, userId, roleId, createTime, creator, updateTime, updator)
                .from(authUserRole)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(AuthUserRole record) {
        return UpdateDSL.updateWithMapper(this::update, authUserRole)
                .set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(roleId).equalTo(record::getRoleId)
                .set(createTime).equalTo(record::getCreateTime)
                .set(creator).equalTo(record::getCreator)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updator).equalTo(record::getUpdator);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(AuthUserRole record) {
        return UpdateDSL.updateWithMapper(this::update, authUserRole)
                .set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updator).equalToWhenPresent(record::getUpdator);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(AuthUserRole record) {
        return UpdateDSL.updateWithMapper(this::update, authUserRole)
                .set(userId).equalTo(record::getUserId)
                .set(roleId).equalTo(record::getRoleId)
                .set(createTime).equalTo(record::getCreateTime)
                .set(creator).equalTo(record::getCreator)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updator).equalTo(record::getUpdator)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(AuthUserRole record) {
        return UpdateDSL.updateWithMapper(this::update, authUserRole)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updator).equalToWhenPresent(record::getUpdator)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}