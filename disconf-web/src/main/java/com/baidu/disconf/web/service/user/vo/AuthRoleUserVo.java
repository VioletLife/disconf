package com.baidu.disconf.web.service.user.vo;

import com.baidu.disconf.web.service.auth.vo.AuthRolePermissionVo;
import com.baidu.disconf.web.service.user.mybatis.AuthUserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class AuthRoleUserVo extends AuthUserRole {
    private AuthRolePermissionVo rolePermission;
}
