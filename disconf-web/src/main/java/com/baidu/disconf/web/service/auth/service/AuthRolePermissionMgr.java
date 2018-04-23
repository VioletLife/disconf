package com.baidu.disconf.web.service.auth.service;

import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.BaseService;
import com.baidu.disconf.web.service.Page;
import com.baidu.disconf.web.service.auth.mybatis.AuthPermission;
import com.baidu.disconf.web.service.auth.mybatis.AuthRole;
import com.baidu.disconf.web.service.auth.vo.AuthRolePermissionVo;
import org.apache.ibatis.session.RowBounds;

import java.util.function.Consumer;

public interface AuthRolePermissionMgr extends BaseService {
    /**
     * 查询当前用户的角色列表
     *
     * @param rowBounds 分页数据
     * @param roleName  角色名称
     * @return 角色数据列表
     */
    Page<AuthRolePermissionVo> selectByExampleWithRowbounds(Page<AuthRolePermissionVo> rowBounds, String roleName);

    AuthRolePermissionVo insertSelective(AuthRolePermissionVo role, Consumer<ResponseMessage> consumer);

    AuthRolePermissionVo updateSelective(AuthRolePermissionVo role, Consumer<ResponseMessage> consumer);

    int deleteByPrimaryKey(Long roleId, Consumer<ResponseMessage> consumer);
}
