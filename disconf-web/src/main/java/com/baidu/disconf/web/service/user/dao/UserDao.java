package com.baidu.disconf.web.service.user.dao;

import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.unbiz.common.genericdao.dao.BaseDao;

/**
 * @author liaoqiqi
 * @version 2013-11-28
 */
public interface UserDao extends BaseDao<Long, User> {

    void executeSql(String sql);

    User getUserByName(String name);


    /**
     * 查询用户信息
     * @param userAccount 用户名称
     * @return 用户信息
     */
    User getUserByUserAccount(String userAccount);

}
