package com.baidu.disconf.web.service.user.vo;

import com.baidu.disconf.web.service.org.mybatis.OrgDepartment;
import com.baidu.disconf.web.service.user.mybatis.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserResponseVo extends User {
    private OrgDepartment orgDepartment;
    private List<AuthRoleUserVo> roles;
}
