package com.baidu.disconf.web.service.user.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ResetPasswordVo {
    private Long userId;
    private String oldPassword;
    private String newPassword;
}
