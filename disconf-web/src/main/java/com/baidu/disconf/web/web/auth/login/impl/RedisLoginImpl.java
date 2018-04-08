package com.baidu.disconf.web.web.auth.login.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.user.constant.UserConstant;
import com.baidu.disconf.web.service.user.dto.Visitor;
import com.baidu.disconf.web.web.auth.constant.LoginConstant;
import com.baidu.disconf.web.web.auth.login.RedisLogin;
import com.baidu.ub.common.commons.ThreadContext;
import com.github.knightliao.apollo.redis.RedisCacheManager;
import com.github.knightliao.apollo.utils.web.CookieUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author liaoqiqi
 * @version 2014-2-4
 */
public class RedisLoginImpl implements RedisLogin {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 获取Redis上的User Key
     *
     * @param baiduId
     * @return
     */
    private String getRedisKey(String baiduId) {
        return baiduId + UserConstant.USER_KEY;
    }

    /**
     * 校验Redis是否登录
     */
    @Override
    public Visitor isLogin(HttpServletRequest request) {

        String xId = CookieUtils.getCookieValue(request, LoginConstant.XONE_COOKIE_NAME_STRING);

        if (xId != null) {

            String visitorCache = redisTemplate.opsForValue().get(this.getRedisKey(xId));
            Visitor visitor = null;
            if (StringUtils.isNotEmpty(visitorCache)) {
                visitor = JSON.parseObject(visitorCache, Visitor.class);
            }

            //
            // 登录了
            //
            if (visitor != null) {

                return visitor;

            } else {

                return null;
            }

        } else {

            return null;
        }
    }

    /**
     * 登录
     */
    @Override
    public void login(HttpServletRequest request, User user, int expireTime) {

        Visitor visitor = new Visitor();

        //
        //
        //
        visitor.setId(user.getId());
        visitor.setLoginUserId(user.getId());
        visitor.setLoginUserName(user.getName());
        visitor.setRoleId(user.getRoleId());
        visitor.setAppIds(user.getOwnApps());

        //
        // 更新session
        //
        updateSessionVisitor(request.getSession(), visitor);

        //
        // 更新Redis数据
        //
        updateRedisVisitor(visitor, request, expireTime);
    }

    /**
     * @param visitor
     */
    private void updateRedisVisitor(Visitor visitor, HttpServletRequest request, int expireTime) {

        String xcookieName = CookieUtils.getCookieValue(request, LoginConstant.XONE_COOKIE_NAME_STRING);

        // 更新Redis数据
        if (xcookieName != null) {

            // 更新
            if (visitor != null) {

                redisTemplate.opsForValue().set(this.getRedisKey(xcookieName), JSON.toJSONString(visitor), expireTime, TimeUnit.SECONDS);
            } else {

                // 删除
                redisTemplate.delete(this.getRedisKey(xcookieName));
            }
        }
    }

    /**
     * 更新Session中的Userid
     *
     * @param session
     * @param visitor
     */
    @Override
    public void updateSessionVisitor(HttpSession session, Visitor visitor) {

        if (visitor != null) {
            // 更新
            session.setAttribute(UserConstant.USER_KEY, visitor);
        } else {

            // 删除
            session.removeAttribute(UserConstant.USER_KEY);
        }

        ThreadContext.putSessionVisitor(visitor);
    }

    /**
     * 登出
     */
    @Override
    public void logout(HttpServletRequest request) {

        // 更新session
        updateSessionVisitor(request.getSession(), null);

        // 更新redis
        updateRedisVisitor(null, request, 0);
    }

}
