package com.baidu.disconf.web.service.auth.mybatis;

import static com.baidu.disconf.web.service.auth.mybatis.AuthRoleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.baidu.disconf.web.service.MybatisMapper;
import com.baidu.disconf.web.service.auth.mybatis.AuthRole;
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
public interface AuthRoleMapper extends MybatisMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    int insert(InsertStatementProvider<AuthRole> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AuthRoleResult")
    AuthRole selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AuthRoleResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_code", property="roleCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_comments", property="roleComments", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator", property="creator", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updator", property="updator", jdbcType=JdbcType.BIGINT)
    })
    List<AuthRole> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(authRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, authRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, authRole)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(AuthRole record) {
        return insert(SqlBuilder.insert(record)
                .into(authRole)
                .map(id).toProperty("id")
                .map(roleCode).toProperty("roleCode")
                .map(roleName).toProperty("roleName")
                .map(roleComments).toProperty("roleComments")
                .map(createTime).toProperty("createTime")
                .map(creator).toProperty("creator")
                .map(updateTime).toProperty("updateTime")
                .map(updator).toProperty("updator")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(AuthRole record) {
        return insert(SqlBuilder.insert(record)
                .into(authRole)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(roleCode).toPropertyWhenPresent("roleCode", record::getRoleCode)
                .map(roleName).toPropertyWhenPresent("roleName", record::getRoleName)
                .map(roleComments).toPropertyWhenPresent("roleComments", record::getRoleComments)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(creator).toPropertyWhenPresent("creator", record::getCreator)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(updator).toPropertyWhenPresent("updator", record::getUpdator)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AuthRole>>> selectByExampleWithRowbounds(RowBounds rowBounds) {
        return SelectDSL.selectWithMapper(this::selectMany, id, roleCode, roleName, roleComments, createTime, creator, updateTime, updator)
                .from(authRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AuthRole>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, roleCode, roleName, roleComments, createTime, creator, updateTime, updator)
                .from(authRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AuthRole>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, roleCode, roleName, roleComments, createTime, creator, updateTime, updator)
                .from(authRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default AuthRole selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, roleCode, roleName, roleComments, createTime, creator, updateTime, updator)
                .from(authRole)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(AuthRole record) {
        return UpdateDSL.updateWithMapper(this::update, authRole)
                .set(id).equalTo(record::getId)
                .set(roleCode).equalTo(record::getRoleCode)
                .set(roleName).equalTo(record::getRoleName)
                .set(roleComments).equalTo(record::getRoleComments)
                .set(createTime).equalTo(record::getCreateTime)
                .set(creator).equalTo(record::getCreator)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updator).equalTo(record::getUpdator);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(AuthRole record) {
        return UpdateDSL.updateWithMapper(this::update, authRole)
                .set(id).equalToWhenPresent(record::getId)
                .set(roleCode).equalToWhenPresent(record::getRoleCode)
                .set(roleName).equalToWhenPresent(record::getRoleName)
                .set(roleComments).equalToWhenPresent(record::getRoleComments)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updator).equalToWhenPresent(record::getUpdator);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(AuthRole record) {
        return UpdateDSL.updateWithMapper(this::update, authRole)
                .set(roleCode).equalTo(record::getRoleCode)
                .set(roleName).equalTo(record::getRoleName)
                .set(roleComments).equalTo(record::getRoleComments)
                .set(createTime).equalTo(record::getCreateTime)
                .set(creator).equalTo(record::getCreator)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updator).equalTo(record::getUpdator)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(AuthRole record) {
        return UpdateDSL.updateWithMapper(this::update, authRole)
                .set(roleCode).equalToWhenPresent(record::getRoleCode)
                .set(roleName).equalToWhenPresent(record::getRoleName)
                .set(roleComments).equalToWhenPresent(record::getRoleComments)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updator).equalToWhenPresent(record::getUpdator)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}