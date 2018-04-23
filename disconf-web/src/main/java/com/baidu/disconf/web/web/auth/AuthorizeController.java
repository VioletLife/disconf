package com.baidu.disconf.web.web.auth;

import com.baidu.disconf.web.common.message.CodeMessage;
import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.Page;
import com.baidu.disconf.web.service.auth.mybatis.AuthPermission;
import com.baidu.disconf.web.service.auth.service.AuthPermissionMgr;
import com.baidu.disconf.web.service.auth.service.AuthRolePermissionMgr;
import com.baidu.disconf.web.service.auth.vo.AuthRolePermissionVo;
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
     * 获取当前用户的角色数据列表
     *
     * @param roleName   角色名称
     * @param pageNumber 页码
     * @param pageSize   每页数据量
     * @return 获取当前用户的权限数据列表
     */
    @RequestMapping("role/list")
    public JsonObjectBase roleList(
            @RequestParam(required = false, defaultValue = "") String roleName,
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        Page<AuthRolePermissionVo> rowBounds = new Page<>(pageNumber, pageSize);
        Page<AuthRolePermissionVo> page = authRolePermissionMgr.selectByExampleWithRowbounds(rowBounds, roleName);
        return buildSuccess(page);
    }


    /**
     * 新增权限数据
     *
     * @param permission 权限数据
     * @return 数据更新后的权限数据
     */
    @RequestMapping(value = "permission/create", method = RequestMethod.POST)
    public JsonObjectBase permissionCreate(@RequestBody AuthPermission permission) {
        AtomicReference<ResponseMessage> reference = new AtomicReference<>(null);
        AuthPermission authPermission = authPermissionMgr.insertSelective(permission, reference::set);
        if (reference.get() != null) {
            return buildResponseMessage(reference.get());
        }
        return buildSuccess(authPermission);
    }


    /**
     * 更新权限数据
     * 权限编码不能更新，如果出现权限编码错误，则只能删除重新添加
     *
     * @param permission 权限更新数据
     * @return 数据跟新后的权限数据
     */
    @RequestMapping(value = "permission/update", method = RequestMethod.POST)
    public JsonObjectBase permissionUpdate(@RequestBody AuthPermission permission) {
        AtomicReference<ResponseMessage> reference = new AtomicReference<>(null);
        AuthPermission authPermission = authPermissionMgr.updateSelective(permission, reference::set);
        if (reference.get() != null) {
            return buildResponseMessage(reference.get());
        }
        return buildSuccess(authPermission);
    }


    /**
     * 删除权限数据
     *
     * @param permissionId 权限ID
     * @return 删除权限数据
     */
    @RequestMapping(value = "permission/delete", method = RequestMethod.GET)
    public JsonObjectBase permissionDelete(Long permissionId) {
        AtomicReference<ResponseMessage> reference = new AtomicReference<>(null);
        int delete = authPermissionMgr.deleteByPrimaryKey(permissionId, reference::set);
        if (reference.get() != null) {
            return buildResponseMessage(reference.get());
        }
        return buildSuccess(delete);
    }

    /**
     * 创建用户角色
     *
     * @param authRolePermissionVo 角色数据对象(包含角色信息和权限信息)
     * @return 角色数据对象
     */
    @RequestMapping(value = "role/create", method = RequestMethod.POST)
    public JsonObjectBase roleCreate(@RequestBody AuthRolePermissionVo authRolePermissionVo) {
        AtomicReference<ResponseMessage> reference = new AtomicReference<>(null);
        authRolePermissionMgr.insertSelective(authRolePermissionVo, reference::set);
        if (reference.get() != null) {
            return buildResponseMessage(reference.get());
        }
        return buildSuccess(authRolePermissionVo);
    }

    /**
     * 更新用户角色
     *
     * @param authRolePermissionVo 角色数据对象(包含角色信息和权限信息)
     * @return 角色数据对象
     */
    @RequestMapping(value = "role/update", method = RequestMethod.POST)
    public JsonObjectBase roleUpdate(@RequestBody AuthRolePermissionVo authRolePermissionVo) {
        AtomicReference<ResponseMessage> reference = new AtomicReference<>(null);
        authRolePermissionMgr.updateSelective(authRolePermissionVo, reference::set);
        if (reference.get() != null) {
            return buildResponseMessage(reference.get());
        }
        return buildSuccess(authRolePermissionVo);
    }

    /**
     * 删除角色
     *
     * @param roleId 角色ID
     * @return 删除角色
     */
    @RequestMapping(value = "role/delete", method = RequestMethod.GET)
    public JsonObjectBase roleDelete(Long roleId) {
        AtomicReference<ResponseMessage> reference = new AtomicReference<>(null);
        int delete = authRolePermissionMgr.deleteByPrimaryKey(roleId, reference::set);
        if (reference.get() != null) {
            return buildResponseMessage(reference.get());
        }
        return buildSuccess(delete);
    }
}
