package com.baidu.disconf.web.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.Page;
import com.baidu.disconf.web.service.user.vo.UserResponseVo;
import com.baidu.disconf.web.service.user.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.baidu.disconf.web.service.sign.form.SigninForm;
import com.baidu.disconf.web.service.sign.service.SignMgr;
import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.user.dto.Visitor;
import com.baidu.disconf.web.service.user.form.PasswordModifyForm;
import com.baidu.disconf.web.service.user.service.UserMgr;
import com.baidu.disconf.web.service.user.vo.VisitorVo;
import com.baidu.disconf.web.web.auth.constant.LoginConstant;
import com.baidu.disconf.web.web.auth.login.RedisLogin;
import com.baidu.disconf.web.web.auth.validator.AuthValidator;
import com.baidu.dsp.common.annotation.NoAuth;
import com.baidu.dsp.common.constant.ErrorCode;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.vo.JsonObjectBase;
import com.baidu.ub.common.commons.ThreadContext;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liaoqiqi
 * @version 2014-1-20
 */
@Controller
@RequestMapping(WebConstants.API_PREFIX + "/account")
public class UserController extends BaseController {

    protected static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMgr userMgr;

    @Autowired
    private AuthValidator authValidator;

    @Autowired
    private SignMgr signMgr;

    @Autowired
    private RedisLogin redisLogin;

    /**
     * GET 获取
     *
     * @param
     * @return
     */
    @NoAuth
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    @ResponseBody
    public JsonObjectBase get() {

        VisitorVo visitorVo = userMgr.getCurVisitor();
        if (visitorVo != null) {

            return buildSuccess("visitor", visitorVo);

        } else {

            // 没有登录啊
            return buildGlobalError("syserror.inner", ErrorCode.GLOBAL_ERROR);
        }
    }

    /**
     * 登录
     *
     * @param signin
     * @param request
     * @return
     */
    @NoAuth
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    public JsonObjectBase signin(@Valid SigninForm signin, HttpServletRequest request) {

        LOG.info(signin.toString());

        // 验证
        authValidator.validateLogin(signin);

        // 数据库登录
        User user = signMgr.signin(signin.getName());

        // 过期时间
        int expireTime = LoginConstant.SESSION_EXPIRE_TIME;
        if (signin.getRemember().equals(1)) {
            expireTime = LoginConstant.SESSION_EXPIRE_TIME2;
        }

        // redis login
        redisLogin.login(request, user, expireTime);

        VisitorVo visitorVo = userMgr.getCurVisitor();

        return buildSuccess("visitor", visitorVo);
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @NoAuth
    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    @ResponseBody
    public JsonObjectBase signout(HttpServletRequest request) {

        redisLogin.logout(request);

        return buildSuccess("ok", "ok");
    }

    /**
     * 修改密码
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    @ResponseBody
    public JsonObjectBase password(@Valid PasswordModifyForm passwordModifyForm, HttpServletRequest request) {

        // 校验
        authValidator.validatePasswordModify(passwordModifyForm);

        // 修改
        Visitor visitor = ThreadContext.getSessionVisitor();
        userMgr.modifyPassword(visitor.getLoginUserId(), passwordModifyForm.getNew_password());

        // re login
        redisLogin.logout(request);

        return buildSuccess("修改成功，请重新登录");
    }


    /**
     * 创建新用户
     *
     * @param userVo 用户信息对象
     * @return 用户信息对象
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public JsonObjectBase createAccount(@RequestBody UserVo userVo) {
        AtomicReference<ResponseMessage> reference = new AtomicReference<>(null);
        UserVo user = userMgr.createUser(userVo, reference::set);
        if (reference.get() != null) {
            return buildResponseMessage(reference.get());
        }
        return buildSuccess(user);
    }


    /**
     * 更新用户信息
     *
     * @param userVo 用户信息对象
     * @return 用户信息对象
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonObjectBase updateAccount(@RequestBody UserVo userVo) {
        AtomicReference<ResponseMessage> reference = new AtomicReference<>(null);
        UserVo user = userMgr.updateUserSelective(userVo, reference::set);
        if (reference.get() != null) {
            return buildResponseMessage(reference.get());
        }
        return buildSuccess(user);
    }


    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @return 删除成功
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonObjectBase deleteAccount(Long userId) {
        AtomicReference<ResponseMessage> reference = new AtomicReference<>(null);
        int key = userMgr.deleteByPrimaryKey(userId, reference::set);
        if (reference.get() != null) {
            return buildResponseMessage(reference.get());
        }
        return buildSuccess(key);
    }


    @RequestMapping(value = "/list")
    @ResponseBody
    public JsonObjectBase usersList(
            @RequestParam(required = false, defaultValue = "") String userAccount,
            @RequestParam(required = false, defaultValue = "") String departmentCode,
            @RequestParam(required = false) Date startTime,
            @RequestParam(required = false) Date endTime,
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Page<UserResponseVo> page = new Page<>(pageNumber, pageSize);
        Page<UserResponseVo> responseVoPage = userMgr.selectByExampleWithRowbounds(page, userAccount, startTime, endTime, departmentCode);
        return buildSuccess(responseVoPage);
    }
}
