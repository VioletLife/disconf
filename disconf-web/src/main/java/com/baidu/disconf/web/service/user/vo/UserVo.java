package com.baidu.disconf.web.service.user.vo;

import com.baidu.disconf.web.service.user.mybatis.AuthUserRole;
import com.baidu.disconf.web.service.user.mybatis.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class UserVo extends User {
    List<AuthUserRole> roles = new ArrayList<>();
}
