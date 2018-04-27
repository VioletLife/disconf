package com.baidu.disconf.web.service.user.mybatis;

import static com.baidu.disconf.web.service.user.mybatis.UserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.baidu.disconf.web.service.MybatisMapper;
import com.baidu.disconf.web.service.user.mybatis.User;

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
public interface UserMapper extends MybatisMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "record.userId")
    int insert(InsertStatementProvider<User> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("UserResult")
    User selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "UserResult", value = {
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "token", property = "token", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ownapps", property = "ownapps", jdbcType = JdbcType.VARCHAR),
            @Result(column = "role_id", property = "roleId", jdbcType = JdbcType.BIGINT),
            @Result(column = "user_account", property = "userAccount", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mobile_phone", property = "mobilePhone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "qq", property = "qq", jdbcType = JdbcType.VARCHAR),
            @Result(column = "weixin", property = "weixin", jdbcType = JdbcType.VARCHAR),
            @Result(column = "department_id", property = "departmentId", jdbcType = JdbcType.BIGINT),
            @Result(column = "department_code", property = "departmentCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "comments", property = "comments", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "updator", property = "updator", jdbcType = JdbcType.BIGINT)
    })
    List<User> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long userId_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, user)
                .where(userId, isEqualTo(userId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(User record) {
        return insert(SqlBuilder.insert(record)
                .into(user)
                .map(userId).toProperty("userId")
                .map(name).toProperty("name")
                .map(password).toProperty("password")
                .map(token).toProperty("token")
                .map(ownapps).toProperty("ownapps")
                .map(roleId).toProperty("roleId")
                .map(userAccount).toProperty("userAccount")
                .map(mobilePhone).toProperty("mobilePhone")
                .map(email).toProperty("email")
                .map(qq).toProperty("qq")
                .map(weixin).toProperty("weixin")
                .map(departmentId).toProperty("departmentId")
                .map(departmentCode).toProperty("departmentCode")
                .map(comments).toProperty("comments")
                .map(createTime).toProperty("createTime")
                .map(creator).toProperty("creator")
                .map(updateTime).toProperty("updateTime")
                .map(updator).toProperty("updator")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(User record) {
        return insert(SqlBuilder.insert(record)
                .into(user)
                .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(password).toPropertyWhenPresent("password", record::getPassword)
                .map(token).toPropertyWhenPresent("token", record::getToken)
                .map(ownapps).toPropertyWhenPresent("ownapps", record::getOwnapps)
                .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
                .map(userAccount).toPropertyWhenPresent("userAccount", record::getUserAccount)
                .map(mobilePhone).toPropertyWhenPresent("mobilePhone", record::getMobilePhone)
                .map(email).toPropertyWhenPresent("email", record::getEmail)
                .map(qq).toPropertyWhenPresent("qq", record::getQq)
                .map(weixin).toPropertyWhenPresent("weixin", record::getWeixin)
                .map(departmentId).toPropertyWhenPresent("departmentId", record::getDepartmentId)
                .map(departmentCode).toPropertyWhenPresent("departmentCode", record::getDepartmentCode)
                .map(comments).toPropertyWhenPresent("comments", record::getComments)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(creator).toPropertyWhenPresent("creator", record::getCreator)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(updator).toPropertyWhenPresent("updator", record::getUpdator)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<User>>> selectByExampleWithRowbounds(RowBounds rowBounds) {
        return SelectDSL.selectWithMapper(this::selectMany, userId, name, password, token, ownapps, roleId, userAccount, mobilePhone, email, qq, weixin, departmentId, departmentCode, comments, createTime, creator, updateTime, updator)
                .from(user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<User>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, userId, name, password, token, ownapps, roleId, userAccount, mobilePhone, email, qq, weixin, departmentId, departmentCode, comments, createTime, creator, updateTime, updator)
                .from(user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<User>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, userId, name, password, token, ownapps, roleId, userAccount, mobilePhone, email, qq, weixin, departmentId, departmentCode, comments, createTime, creator, updateTime, updator)
                .from(user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default User selectByPrimaryKey(Long userId_) {
        return SelectDSL.selectWithMapper(this::selectOne, userId, name, password, token, ownapps, roleId, userAccount, mobilePhone, email, qq, weixin, departmentId, departmentCode, comments, createTime, creator, updateTime, updator)
                .from(user)
                .where(userId, isEqualTo(userId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(userId).equalTo(record::getUserId)
                .set(name).equalTo(record::getName)
                .set(password).equalTo(record::getPassword)
                .set(token).equalTo(record::getToken)
                .set(ownapps).equalTo(record::getOwnapps)
                .set(roleId).equalTo(record::getRoleId)
                .set(userAccount).equalTo(record::getUserAccount)
                .set(mobilePhone).equalTo(record::getMobilePhone)
                .set(email).equalTo(record::getEmail)
                .set(qq).equalTo(record::getQq)
                .set(weixin).equalTo(record::getWeixin)
                .set(departmentId).equalTo(record::getDepartmentId)
                .set(departmentCode).equalTo(record::getDepartmentCode)
                .set(comments).equalTo(record::getComments)
                .set(createTime).equalTo(record::getCreateTime)
                .set(creator).equalTo(record::getCreator)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updator).equalTo(record::getUpdator);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(name).equalToWhenPresent(record::getName)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(token).equalToWhenPresent(record::getToken)
                .set(ownapps).equalToWhenPresent(record::getOwnapps)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(userAccount).equalToWhenPresent(record::getUserAccount)
                .set(mobilePhone).equalToWhenPresent(record::getMobilePhone)
                .set(email).equalToWhenPresent(record::getEmail)
                .set(qq).equalToWhenPresent(record::getQq)
                .set(weixin).equalToWhenPresent(record::getWeixin)
                .set(departmentId).equalToWhenPresent(record::getDepartmentId)
                .set(departmentCode).equalToWhenPresent(record::getDepartmentCode)
                .set(comments).equalToWhenPresent(record::getComments)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updator).equalToWhenPresent(record::getUpdator);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(name).equalTo(record::getName)
                .set(password).equalTo(record::getPassword)
                .set(token).equalTo(record::getToken)
                .set(ownapps).equalTo(record::getOwnapps)
                .set(roleId).equalTo(record::getRoleId)
                .set(userAccount).equalTo(record::getUserAccount)
                .set(mobilePhone).equalTo(record::getMobilePhone)
                .set(email).equalTo(record::getEmail)
                .set(qq).equalTo(record::getQq)
                .set(weixin).equalTo(record::getWeixin)
                .set(departmentId).equalTo(record::getDepartmentId)
                .set(departmentCode).equalTo(record::getDepartmentCode)
                .set(comments).equalTo(record::getComments)
                .set(createTime).equalTo(record::getCreateTime)
                .set(creator).equalTo(record::getCreator)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updator).equalTo(record::getUpdator)
                .where(userId, isEqualTo(record::getUserId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(name).equalToWhenPresent(record::getName)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(token).equalToWhenPresent(record::getToken)
                .set(ownapps).equalToWhenPresent(record::getOwnapps)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(userAccount).equalToWhenPresent(record::getUserAccount)
                .set(mobilePhone).equalToWhenPresent(record::getMobilePhone)
                .set(email).equalToWhenPresent(record::getEmail)
                .set(qq).equalToWhenPresent(record::getQq)
                .set(weixin).equalToWhenPresent(record::getWeixin)
                .set(departmentId).equalToWhenPresent(record::getDepartmentId)
                .set(departmentCode).equalToWhenPresent(record::getDepartmentCode)
                .set(comments).equalToWhenPresent(record::getComments)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updator).equalToWhenPresent(record::getUpdator)
                .where(userId, isEqualTo(record::getUserId))
                .build()
                .execute();
    }
}