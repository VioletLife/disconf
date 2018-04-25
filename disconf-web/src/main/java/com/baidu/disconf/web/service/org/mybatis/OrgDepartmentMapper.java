package com.baidu.disconf.web.service.org.mybatis;

import static com.baidu.disconf.web.service.org.mybatis.OrgDepartmentDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.baidu.disconf.web.service.MybatisMapper;
import com.baidu.disconf.web.service.org.mybatis.OrgDepartment;

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
public interface OrgDepartmentMapper extends MybatisMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @Options(useGeneratedKeys = true, keyColumn = "record.id")
    int insert(InsertStatementProvider<OrgDepartment> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("OrgDepartmentResult")
    OrgDepartment selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "OrgDepartmentResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "department_code", property = "departmentCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "department_name", property = "departmentName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "department_comments", property = "departmentComments", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parent_code", property = "parentCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_org_root", property = "isOrgRoot", jdbcType = JdbcType.BIT),
            @Result(column = "is_hidden_node", property = "isHiddenNode", jdbcType = JdbcType.BIT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "updator", property = "updator", jdbcType = JdbcType.BIGINT)
    })
    List<OrgDepartment> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(orgDepartment);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, orgDepartment);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, orgDepartment)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(OrgDepartment record) {
        return insert(SqlBuilder.insert(record)
                .into(orgDepartment)
                .map(id).toProperty("id")
                .map(departmentCode).toProperty("departmentCode")
                .map(departmentName).toProperty("departmentName")
                .map(departmentComments).toProperty("departmentComments")
                .map(parentCode).toProperty("parentCode")
                .map(isOrgRoot).toProperty("isOrgRoot")
                .map(isHiddenNode).toProperty("isHiddenNode")
                .map(createTime).toProperty("createTime")
                .map(creator).toProperty("creator")
                .map(updateTime).toProperty("updateTime")
                .map(updator).toProperty("updator")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(OrgDepartment record) {
        return insert(SqlBuilder.insert(record)
                .into(orgDepartment)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(departmentCode).toPropertyWhenPresent("departmentCode", record::getDepartmentCode)
                .map(departmentName).toPropertyWhenPresent("departmentName", record::getDepartmentName)
                .map(departmentComments).toPropertyWhenPresent("departmentComments", record::getDepartmentComments)
                .map(parentCode).toPropertyWhenPresent("parentCode", record::getParentCode)
                .map(isOrgRoot).toPropertyWhenPresent("isOrgRoot", record::getIsOrgRoot)
                .map(isHiddenNode).toPropertyWhenPresent("isHiddenNode", record::getIsHiddenNode)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(creator).toPropertyWhenPresent("creator", record::getCreator)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(updator).toPropertyWhenPresent("updator", record::getUpdator)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<OrgDepartment>>> selectByExampleWithRowbounds(RowBounds rowBounds) {
        return SelectDSL.selectWithMapper(this::selectMany, id, departmentCode, departmentName, departmentComments, parentCode, isOrgRoot, isHiddenNode, createTime, creator, updateTime, updator)
                .from(orgDepartment);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<OrgDepartment>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, departmentCode, departmentName, departmentComments, parentCode, isOrgRoot, isHiddenNode, createTime, creator, updateTime, updator)
                .from(orgDepartment);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<OrgDepartment>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, departmentCode, departmentName, departmentComments, parentCode, isOrgRoot, isHiddenNode, createTime, creator, updateTime, updator)
                .from(orgDepartment);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default OrgDepartment selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, departmentCode, departmentName, departmentComments, parentCode, isOrgRoot, isHiddenNode, createTime, creator, updateTime, updator)
                .from(orgDepartment)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(OrgDepartment record) {
        return UpdateDSL.updateWithMapper(this::update, orgDepartment)
                .set(id).equalTo(record::getId)
                .set(departmentCode).equalTo(record::getDepartmentCode)
                .set(departmentName).equalTo(record::getDepartmentName)
                .set(departmentComments).equalTo(record::getDepartmentComments)
                .set(parentCode).equalTo(record::getParentCode)
                .set(isOrgRoot).equalTo(record::getIsOrgRoot)
                .set(isHiddenNode).equalTo(record::getIsHiddenNode)
                .set(createTime).equalTo(record::getCreateTime)
                .set(creator).equalTo(record::getCreator)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updator).equalTo(record::getUpdator);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(OrgDepartment record) {
        return UpdateDSL.updateWithMapper(this::update, orgDepartment)
                .set(id).equalToWhenPresent(record::getId)
                .set(departmentCode).equalToWhenPresent(record::getDepartmentCode)
                .set(departmentName).equalToWhenPresent(record::getDepartmentName)
                .set(departmentComments).equalToWhenPresent(record::getDepartmentComments)
                .set(parentCode).equalToWhenPresent(record::getParentCode)
                .set(isOrgRoot).equalToWhenPresent(record::getIsOrgRoot)
                .set(isHiddenNode).equalToWhenPresent(record::getIsHiddenNode)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updator).equalToWhenPresent(record::getUpdator);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(OrgDepartment record) {
        return UpdateDSL.updateWithMapper(this::update, orgDepartment)
                .set(departmentCode).equalTo(record::getDepartmentCode)
                .set(departmentName).equalTo(record::getDepartmentName)
                .set(departmentComments).equalTo(record::getDepartmentComments)
                .set(parentCode).equalTo(record::getParentCode)
                .set(isOrgRoot).equalTo(record::getIsOrgRoot)
                .set(isHiddenNode).equalTo(record::getIsHiddenNode)
                .set(createTime).equalTo(record::getCreateTime)
                .set(creator).equalTo(record::getCreator)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updator).equalTo(record::getUpdator)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(OrgDepartment record) {
        return UpdateDSL.updateWithMapper(this::update, orgDepartment)
                .set(departmentCode).equalToWhenPresent(record::getDepartmentCode)
                .set(departmentName).equalToWhenPresent(record::getDepartmentName)
                .set(departmentComments).equalToWhenPresent(record::getDepartmentComments)
                .set(parentCode).equalToWhenPresent(record::getParentCode)
                .set(isOrgRoot).equalToWhenPresent(record::getIsOrgRoot)
                .set(isHiddenNode).equalToWhenPresent(record::getIsHiddenNode)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updator).equalToWhenPresent(record::getUpdator)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}