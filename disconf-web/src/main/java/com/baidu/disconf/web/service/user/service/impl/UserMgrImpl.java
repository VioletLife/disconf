package com.baidu.disconf.web.service.user.service.impl;

import java.util.*;
import java.util.function.Consumer;

import com.baidu.disconf.web.common.message.CodeMessage;
import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.Page;
import com.baidu.disconf.web.service.auth.mybatis.*;
import com.baidu.disconf.web.service.auth.vo.AuthRolePermissionVo;
import com.baidu.disconf.web.service.org.mybatis.OrgDepartmentMapper;
import com.baidu.disconf.web.service.user.mybatis.*;
import com.baidu.disconf.web.service.user.vo.*;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.dynamic.sql.SortSpecification;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SimpleSortSpecification;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanOrEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsLessThanOrEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsLikeCaseInsensitive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.disconf.web.service.sign.utils.SignUtils;
import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.user.dao.UserDao;
import com.baidu.disconf.web.service.user.dto.Visitor;
import com.baidu.disconf.web.service.user.service.UserInnerMgr;
import com.baidu.disconf.web.service.user.service.UserMgr;
import com.baidu.ub.common.commons.ThreadContext;

/**
 * @author liaoqiqi
 * @version 2013-12-5
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserMgrImpl implements UserMgr {

    protected static final Logger LOG = LoggerFactory.getLogger(UserMgrImpl.class);

    @Autowired
    private UserInnerMgr userInnerMgr;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthUserRoleMapper authUserRoleMapper;


    @Autowired
    private AuthRoleMapper authRoleMapper;

    @Autowired
    private AuthPermissionMapper authPermissionMapper;

    @Autowired
    private AuthRolePermissionMapper authRolePermissionMapper;


    @Autowired
    private OrgDepartmentMapper orgDepartmentMapper;

    @Override
    public Visitor getVisitor(Long userId) {

        return userInnerMgr.getVisitor(userId);
    }

    @Override
    public VisitorVo getCurVisitor() {

        Visitor visitor = ThreadContext.getSessionVisitor();
        if (visitor == null) {
            return null;
        }

        VisitorVo visitorVo = new VisitorVo();
        visitorVo.setId(visitor.getId());
        visitorVo.setName(visitor.getLoginUserName());

        return visitorVo;
    }

    /**
     * 创建
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Long create(User user) {

        user = userDao.create(user);
        return user.getId();
    }

    /**
     *
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void create(List<User> users) {

        userDao.create(users);
    }

    @Override
    public List<User> getAll() {

        return userDao.findAll();
    }

    /**
     * @param userId
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public String addOneAppForUser(Long userId, int appId) {

        User user = getUser(userId);
        String ownAppIds = user.getOwnApps();
        if (ownAppIds.contains(",")) {
            ownAppIds = ownAppIds + "," + appId;

        } else {
            ownAppIds = String.valueOf(appId);
        }
        user.setOwnApps(ownAppIds);
        userDao.update(user);

        return ownAppIds;
    }

    /**
     * @param newPassword
     */
    @Override
    public void modifyPassword(Long userId, String newPassword) {

        String passwordWithSalt = SignUtils.createPassword(newPassword);

        User user = userDao.get(userId);
        user.setPassword(passwordWithSalt);

        userDao.update(user);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public User getUser(Long userId) {

        return userDao.get(userId);
    }

    @Override
    public UserVo createUser(UserVo userVo, Consumer<ResponseMessage> consumer) {
        if (StringUtils.isNotEmpty(userVo.getUserAccount())
                && StringUtils.isNotEmpty(userVo.getPassword())
                && userVo.getDepartmentId() != null
                && userVo.getDepartmentId() > 0
                && userVo.getRoles() != null
                && userVo.getRoles().size() > 0
                ) {
            Long countRecord = userMapper.countByExample()
                    .where(UserDynamicSqlSupport.userAccount, IsEqualTo.of(userVo::getUserAccount))
                    .build()
                    .execute();
            if (countRecord != null && countRecord > 0) {
                consumer.accept(CodeMessage.CODE_115.toResponseMessage());
            } else {
                userVo.setUserId(null);
                userVo.setPassword(SignUtils.createPassword(userVo.getPassword()));
                userVo.setCreateTime(new Date());
                userVo.setUpdateTime(new Date());
                userVo.setCreator(getCurrentVisitor().getLoginUserId());
                userVo.setUpdator(getCurrentVisitor().getLoginUserId());
                userMapper.insertSelective(userVo);
                insertSelectiveUserRole(userVo);

            }
        } else {
            consumer.accept(CodeMessage.CODE_114.toResponseMessage());
        }
        return userVo;
    }

    @Override
    public UserVo updateUserSelective(UserVo userVo, Consumer<ResponseMessage> consumer) {
        if (StringUtils.isNotEmpty(userVo.getUserAccount())
                && StringUtils.isNotEmpty(userVo.getPassword())
                && userVo.getDepartmentId() != null
                && userVo.getDepartmentId() > 0
                && userVo.getRoles() != null
                && userVo.getRoles().size() > 0
                && userVo.getUserId() != null
                && userVo.getUserId() > 0
                ) {
            userVo.setPassword(SignUtils.createPassword(userVo.getPassword()));
            userVo.setUpdateTime(new Date());
            userVo.setUpdator(getCurrentVisitor().getLoginUserId());
            userMapper.updateByPrimaryKeySelective(userVo);
            authUserRoleMapper.deleteByExample()
                    .where(AuthUserRoleDynamicSqlSupport.userId, IsEqualTo.of(userVo::getUserId))
                    .build()
                    .execute();
            insertSelectiveUserRole(userVo);
        } else {
            consumer.accept(CodeMessage.CODE_114.toResponseMessage());
        }
        return userVo;
    }

    private void insertSelectiveUserRole(UserVo userVo) {
        if (userVo.getUserId() != null && userVo.getUserId() > 0) {
            for (AuthUserRole userRole : userVo.getRoles()) {
                if (userRole.getRoleId() != null && userRole.getRoleId() > 0) {
                    userRole.setUserId(userVo.getUserId());
                    userRole.setCreateTime(new Date());
                    userRole.setUpdateTime(new Date());
                    userRole.setCreator(getCurrentVisitor().getLoginUserId());
                    userRole.setUpdator(getCurrentVisitor().getLoginUserId());
                    userRole.setId(null);
                    authUserRoleMapper.insertSelective(userRole);
                }
            }
        }
    }


    @Override
    public int deleteByPrimaryKey(Long userId, Consumer<ResponseMessage> consumer) {
        if (userId != null && userId > 0) {
            authUserRoleMapper.deleteByExample()
                    .where(AuthUserRoleDynamicSqlSupport.userId, IsEqualTo.of(() -> userId))
                    .build()
                    .execute();
            return userMapper.deleteByPrimaryKey(userId);
        } else {
            consumer.accept(CodeMessage.CODE_116.toResponseMessage());
        }
        return 0;
    }

    @Override
    public Page<UserResponseVo> selectByExampleWithRowbounds(Page<UserResponseVo> rowBounds, String userAccount, Date startTime, Date endTIme, String departmentCode) {
        PageHelper.startPage(rowBounds.getOffset(), rowBounds.getLimit());
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<com.baidu.disconf.web.service.user.mybatis.User>>> expressionDSL = userMapper.selectByExampleWithRowbounds(rowBounds);
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<com.baidu.disconf.web.service.user.mybatis.User>>>.QueryExpressionWhereBuilder where = expressionDSL.where(UserDynamicSqlSupport.creator, IsEqualTo.of(() -> getCurrentVisitor().getLoginUserId()));
        if (StringUtils.isNotEmpty(userAccount)) {
            where.and(UserDynamicSqlSupport.userAccount, IsLikeCaseInsensitive.of(() -> "%" + userAccount + "%"));
        }
        if (startTime != null) {
            where.and(UserDynamicSqlSupport.createTime, IsGreaterThanOrEqualTo.of(() -> startTime));
        }

        if (endTIme != null) {
            where.and(UserDynamicSqlSupport.createTime, IsLessThanOrEqualTo.of(() -> endTIme));
        }

        if (StringUtils.isNotEmpty(departmentCode)) {
            where.and(UserDynamicSqlSupport.departmentCode, IsLikeCaseInsensitive.of(() -> departmentCode + "%"));
        }
        List<com.baidu.disconf.web.service.user.mybatis.User> users = where.orderBy(SimpleSortSpecification.of("create_time").descending())
                .build()
                .execute();
        List<UserResponseVo> lastUsers = new ArrayList<>();
        if (users instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page page = (com.github.pagehelper.Page) users;
            rowBounds.setTotal(page.getTotal());
            List<com.baidu.disconf.web.service.user.mybatis.User> result = (List<com.baidu.disconf.web.service.user.mybatis.User>) page.getResult();
            for (com.baidu.disconf.web.service.user.mybatis.User user : result) {
                UserResponseVo userResponseVo = getUserResponseVo(user);
                lastUsers.add(userResponseVo);
            }
        }
        rowBounds.setResult(Optional.of(lastUsers));
        return rowBounds;
    }

    private UserResponseVo getUserResponseVo(com.baidu.disconf.web.service.user.mybatis.User user) {
        /**
         * 获取每一个用户的角色和权限数据
         */
        UserResponseVo userResponseVo = new UserResponseVo();
        BeanUtils.copyProperties(user, userResponseVo);
        userResponseVo.setOrgDepartment(orgDepartmentMapper.selectByPrimaryKey(userResponseVo.getDepartmentId()));

        /**
         * 获取角色的角色列表
         */
        List<AuthUserRole> authUserRoles = authUserRoleMapper.selectByExample()
                .where(AuthUserRoleDynamicSqlSupport.userId, IsEqualTo.of(userResponseVo::getUserId))
                .build()
                .execute();
        List<AuthRoleUserVo> authRoleUserVos = new ArrayList<>();
        if (authUserRoles != null && authUserRoles.size() > 0) {
            for (AuthUserRole authUserRole : authUserRoles) {
                /**
                 * 获取每一个角色所包含的权限数据
                 */
                AuthRoleUserVo authRoleUserVo = new AuthRoleUserVo();
                BeanUtils.copyProperties(authUserRole, authRoleUserVo);
                if (authUserRole.getRoleId() > 0) {
                    AuthRolePermissionVo authRolePermissionVo = new AuthRolePermissionVo();
                    AuthRole authRole = authRoleMapper.selectByPrimaryKey(authRoleUserVo.getRoleId());
                    /**
                     * 保存角色信息
                     */
                    authRolePermissionVo.setRole(authRole);

                    /**
                     * 查询角色权限关联信息
                     */
                    List<AuthRolePermission> authRolePermissions = authRolePermissionMapper.selectByExample()
                            .where(AuthRolePermissionDynamicSqlSupport.roleId, IsEqualTo.of(authRoleUserVo::getRoleId))
                            .build()
                            .execute();
                    List<AuthPermission> authPermissionList = new ArrayList<>();
                    if (authRolePermissions != null && authRolePermissions.size() > 0) {
                        for (AuthRolePermission authRolePermission : authRolePermissions) {
                            /**
                             * 查询权限信息
                             */
                            AuthPermission permission = authPermissionMapper.selectByPrimaryKey(authRolePermission.getPermissionId());
                            authPermissionList.add(permission);
                        }
                    }
                    authRolePermissionVo.setPermissions(authPermissionList);
                    authRoleUserVo.setRolePermission(authRolePermissionVo);
                }
                authRoleUserVos.add(authRoleUserVo);
            }
        }
        userResponseVo.setRoles(authRoleUserVos);
        return userResponseVo;
    }


    @Override
    public void resetUserPassword(ResetPasswordVo passwordVo, Consumer<ResponseMessage> consumer) {
        if (passwordVo != null
                && passwordVo.getUserId() != null
                && passwordVo.getUserId() > 0
                && StringUtils.isNotEmpty(passwordVo.getOldPassword())
                && StringUtils.isNotEmpty(passwordVo.getNewPassword())
                ) {
            Long countRecord = userMapper.countByExample()
                    .where(UserDynamicSqlSupport.userId, IsEqualTo.of(passwordVo::getUserId))
                    .and(UserDynamicSqlSupport.password, IsEqualTo.of(() -> SignUtils.createPassword(passwordVo.getOldPassword())))
                    .build()
                    .execute();
            if (countRecord != null && countRecord > 0) {
                com.baidu.disconf.web.service.user.mybatis.User updateUser = userMapper.selectByPrimaryKey(passwordVo.getUserId());
                updateUser.setPassword(SignUtils.createPassword(passwordVo.getNewPassword()));
                updateUser.setUpdateTime(new Date());
                updateUser.setUpdator(getCurrentVisitor().getLoginUserId());
                userMapper.updateByPrimaryKeySelective(updateUser);
            } else {
                consumer.accept(CodeMessage.CODE_117.toResponseMessage());
            }
        } else {
            consumer.accept(CodeMessage.CODE_117.toResponseMessage());
        }
    }

    @Override
    public UserResponseVo selectByPrimaryKey(Long userId) {
        if (userId != null && userId > 0) {
            com.baidu.disconf.web.service.user.mybatis.User user = userMapper.selectByPrimaryKey(userId);
            return getUserResponseVo(user);
        }
        return null;
    }
}
