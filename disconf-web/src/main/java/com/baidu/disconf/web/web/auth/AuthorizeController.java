package com.baidu.disconf.web.web.auth;

import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.Page;
import com.baidu.disconf.web.service.auth.mybatis.AuthPermission;
import com.baidu.disconf.web.service.auth.service.AuthPermissionMgr;
import com.baidu.disconf.web.service.auth.service.AuthRolePermissionMgr;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.vo.JsonObjectBase;
import com.github.pagehelper.PageRowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author QingDengYue
 */
@RestController
@RequestMapping(WebConstants.API_PREFIX + "/auth")
public class AuthorizeController extends BaseController {


    @Autowired
    private AuthRolePermissionMgr authRolePermissionMgr;

    @Autowired
    private AuthPermissionMgr authPermissionMgr;

    /**
     * 获取当前用户的权限数据列表
     *
     * @param permissionName 权限名称
     * @param pageNumber     页码
     * @param pageSize       每页数据量
     * @return 获取当前用户的权限数据列表
     */
    @RequestMapping("permission/list")
    public JsonObjectBase permissionList(
            @RequestParam(required = false, defaultValue = "") String permissionName,
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        Page<AuthPermission> rowBounds = new Page<>(pageNumber, pageSize);
        Page<AuthPermission> page = authPermissionMgr.selectByExampleWithRowbounds(rowBounds, permissionName);
        return buildSuccess(page);
    }


    /**
     * 新增权限数据
     *
     * @param permission 权限数据
     * @return 数据更新后的权限数据
     */
    @RequestMapping(value = "permission/create", method = RequestMethod.POST)
    public JsonObjectBase permissionList(@RequestBody AuthPermission permission) {
        AtomicReference<ResponseMessage> reference = new AtomicReference<>(null);
        AuthPermission authPermission = authPermissionMgr.insertSelective(permission, reference::set);
        if (reference.get() != null) {
            return buildSuccess(reference.get());
        }
        return buildSuccess(authPermission);
    }
}
