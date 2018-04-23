package com.baidu.disconf.web.service.auth.vo;

import com.baidu.disconf.web.service.BaseVo;
import com.baidu.disconf.web.service.auth.mybatis.AuthPermission;
import com.baidu.disconf.web.service.auth.mybatis.AuthRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthRolePermissionVo extends BaseVo {
    private AuthRole role;
    private List<AuthPermission> permissions;
}
