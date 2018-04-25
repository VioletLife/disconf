package com.baidu.disconf.web.web.org.controller;


import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.org.mybatis.OrgDepartment;
import com.baidu.disconf.web.service.org.service.OrgDepartmentMgr;
import com.baidu.disconf.web.service.org.vo.OrgDepartmentVo;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.vo.JsonObjectBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author QingDengYue
 */
@Controller
@RequestMapping(WebConstants.API_PREFIX + "/org")
public class OrgDepartmentController extends BaseController {


    @Autowired
    private OrgDepartmentMgr orgDepartmentMgr;

    /**
     * 获取当前用户的部门列表
     *
     * @return 当前用户部门列表
     */
    @RequestMapping("department/list")
    public JsonObjectBase departmentList() {
        List<OrgDepartmentVo> departmentVos;
        departmentVos = orgDepartmentMgr.selectCurrentVisitorDepartment();
        return buildSuccess(departmentVos);
    }

    /**
     * 创建部门
     *
     * @param orgDepartment 部门数据对象
     * @return 部门数据对象
     */
    @RequestMapping(value = "department/create", method = RequestMethod.POST)
    public JsonObjectBase createDepartment(@RequestBody OrgDepartment orgDepartment) {
        List<OrgDepartmentVo> departmentVos;
        departmentVos = orgDepartmentMgr.selectCurrentVisitorDepartment();
        return buildSuccess(departmentVos);
    }

    /**
     * 删除部门数据
     *
     * @param departmentId 部门ID
     * @return 0 表示删除成功
     */
    @RequestMapping(value = "department/delete", method = RequestMethod.POST)
    public JsonObjectBase deleteDepartment(Long departmentId) {
        AtomicReference<ResponseMessage> reference = new AtomicReference<>(null);
        orgDepartmentMgr.deleteDepartmentByPrimaryId(departmentId, reference::set);
        if (reference.get() != null) {
            return buildResponseMessage(reference.get());
        }
        return buildSuccess(0);
    }


    /**
     * 自动生成系统编码
     *
     * @param parentCode 父级编码
     * @return 未使用的下一个编码
     */
    @RequestMapping(value = "department/code")
    public JsonObjectBase generateDepartmentCode(@RequestParam(required = false, defaultValue = "") String parentCode) {
        return buildSuccess(orgDepartmentMgr.generateDepartmentCode(parentCode));
    }
}
