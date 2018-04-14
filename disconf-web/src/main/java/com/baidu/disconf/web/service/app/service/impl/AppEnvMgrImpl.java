package com.baidu.disconf.web.service.app.service.impl;

import com.baidu.disconf.web.service.app.mybatis.AppEnvMapper;
import com.baidu.disconf.web.service.app.mybatis.AppEnvVersionMapper;
import com.baidu.disconf.web.service.app.mybatis.AppMapper;
import com.baidu.disconf.web.service.app.service.AppEnvMgr;
import com.baidu.disconf.web.service.app.vo.AppEnvVo;
import com.baidu.disconf.web.service.app.vo.AppVo;
import com.baidu.disconf.web.service.user.dto.Visitor;
import com.baidu.dsp.common.vo.JsonObjectBase;
import com.baidu.ub.common.commons.ThreadContext;
import com.github.knightliao.apollo.db.bo.BaseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
@Transactional
public class AppEnvMgrImpl implements AppEnvMgr {
    @Autowired
    public AppEnvMapper appEnvMapper;

    @Autowired
    public AppEnvVersionMapper appEnvVersionMapper;


    @Autowired
    public AppMapper appMapper;

    @Override
    public <T extends JsonObjectBase> void createApp(AppVo app, Consumer<T> consumer) {
        if (app.getApp() != null) {
            Visitor visitor = ThreadContext.getSessionVisitor();

            app.getApp().setCreateTime(Long.toString(System.currentTimeMillis()));
            app.getApp().setUpdateTime(Long.toString(System.currentTimeMillis()));
            appMapper.insertSelective(app.getApp());
            if (app.getAppEnvs() != null) {
                Optional.of(app.getAppEnvs()).ifPresent(appEnvVos -> appEnvVos.stream().filter(appEnvVo -> appEnvVo.getEnv() != null).forEach(appEnvVo -> {
                    appEnvVo.getEnv().setAppId(app.getApp().getAppId());
                    appEnvVo.getEnv().setCreateTime(new Date());
                    appEnvVo.getEnv().setUpdateTime(new Date());
                    appEnvVo.getEnv().setCreator(visitor.getLoginUserId());
                    appEnvVo.getEnv().setUpdator(visitor.getLoginUserId());
                    appEnvMapper.insertSelective(appEnvVo.getEnv());
                    if (appEnvVo.getVersions() != null) {
                        Optional.of(appEnvVo.getVersions()).ifPresent(appEnvVersions -> appEnvVersions.forEach(appEnvVersion -> {
                            appEnvVersion.setAppEnvId(appEnvVo.getEnv().getId());
                            appEnvVersion.setAppId(app.getApp().getAppId());
                            appEnvVersion.setCreateTime(new Date());
                            appEnvVersion.setUpdateTime(new Date());
                            appEnvVersion.setCreator(visitor.getLoginUserId());
                            appEnvVersion.setUpdator(visitor.getLoginUserId());
                            appEnvVersionMapper.insertSelective(appEnvVersion);
                        }));
                    }
                }));
            }
        }
    }
}
