package com.baidu.disconf.web.service.user.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.BaseService;
import com.baidu.disconf.web.service.Page;
import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.user.dto.Visitor;
import com.baidu.disconf.web.service.user.vo.ResetPasswordVo;
import com.baidu.disconf.web.service.user.vo.UserResponseVo;
import com.baidu.disconf.web.service.user.vo.UserVo;
import com.baidu.disconf.web.service.user.vo.VisitorVo;

/**
 * @author liaoqiqi
 * @version 2013-11-28
 */
public interface UserMgr extends BaseService {

    /**
     * 获取用户的基本信息（登录用户）
     *
     * @return
     */
    Visitor getVisitor(Long userId);

    VisitorVo getCurVisitor();

    User getUser(Long userId);

    /**
     * @return
     */
    Long create(User user);

    /**
     * @param user
     */
    void create(List<User> user);

    /**
     * @return
     */
    List<User> getAll();

    /**
     * 为某个user添加一个app
     *
     * @param userId
     */
    String addOneAppForUser(Long userId, int appId);

    /**
     * 修改密码
     *
     * @param newPassword
     */
    void modifyPassword(Long userId, String newPassword);


    /**
     * 创建用户
     *
     * @param userVo   用户信息
     * @param consumer 错误信息
     * @return 用户信息
     */
    UserVo createUser(UserVo userVo, Consumer<ResponseMessage> consumer);


    /**
     * 更新用户
     *
     * @param userVo   用户信息
     * @param consumer 错误信息
     * @return 用户信息
     */
    UserVo updateUserSelective(UserVo userVo, Consumer<ResponseMessage> consumer);


    /**
     * 删除用户
     *
     * @param userId   用户ID
     * @param consumer 错误信息
     * @return 删除成功
     */
    int deleteByPrimaryKey(Long userId, Consumer<ResponseMessage> consumer);


    /**
     * 查询用户列表
     *
     * @param rowBounds      分页数据
     * @param userAccount    用户名称
     * @param startTime      开始时间
     * @param endTIme        结束时间
     * @param departmentCode 部门编码
     * @return
     */
    Page<UserResponseVo> selectByExampleWithRowbounds(Page<UserResponseVo> rowBounds, String userAccount, Date startTime, Date endTIme, String departmentCode);


    /**
     * 重置用户密码
     *
     * @param passwordVo 密码数据对象
     * @param consumer   错误信息
     */
    void resetUserPassword(ResetPasswordVo passwordVo, Consumer<ResponseMessage> consumer);


    /**
     * 获取用户信息详情
     *
     * @param userId 用户ID
     * @return 用户详情
     */
    UserResponseVo selectByPrimaryKey(Long userId);


}
