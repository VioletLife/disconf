package com.baidu.disconf.web.service.app.service.impl;

import com.baidu.disconf.web.common.message.CodeMessage;
import com.baidu.disconf.web.service.app.mybatis.*;
import com.baidu.disconf.web.service.app.service.AppEnvMgr;
import com.baidu.disconf.web.service.app.vo.AppEnvVo;
import com.baidu.disconf.web.service.app.vo.AppVo;
import com.baidu.disconf.web.service.user.dto.Visitor;
import com.baidu.dsp.common.vo.JsonObject;
import com.baidu.dsp.common.vo.JsonObjectBase;
import com.baidu.dsp.common.vo.JsonObjectUtils;
import com.baidu.ub.common.commons.ThreadContext;
import com.github.knightliao.apollo.db.bo.BaseObject;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.mybatis.dynamic.sql.select.SelectDSL.*;

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
    public void createApp(AppVo app, Consumer<CodeMessage> consumer) {
        if (app.getApp() != null) {
            Visitor visitor = ThreadContext.getSessionVisitor();
            Long countResult = appMapper.countByExample().where(AppDynamicSqlSupport.name, IsEqualTo.of(() -> app.getApp().getName())).build().execute();
            if (countResult != null && countResult > 0) {
                if (consumer != null) {
                    consumer.accept(CodeMessage.CODE_100);
                    return;
                }
            }

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

    @Override
    public AppEnv selectAppEnvById(Long id) {
        return appEnvMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<AppEnv> selectAppEnvByAppId(Long appId) {
        List<AppEnv> appEnvs = appEnvMapper.selectByExample().where(AppEnvDynamicSqlSupport.appId, IsEqualTo.of(() -> appId)).build().execute();
        return appEnvs;
    }

    @Override
    public QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AppEnv>>> selectByExample() {
        return appEnvMapper.selectByExample();
    }

    @Override
    public void createEnvForApp(AppEnv appEnv, Consumer<CodeMessage> consumer) {
        if (appEnv != null && appEnv.getAppId() != null && appEnv.getAppId() > 0 && StringUtils.isNotEmpty(appEnv.getEnvName())) {
            Long countRecord = appEnvMapper.countByExample()
                    .where(AppEnvDynamicSqlSupport.appId, IsEqualTo.of(appEnv::getAppId))
                    .and(AppEnvDynamicSqlSupport.envName, IsEqualTo.of(appEnv::getEnvName))
                    .build()
                    .execute();
            if (countRecord != null && countRecord > 0) {
                consumer.accept(CodeMessage.CODE_103);
            } else {
                Visitor visitor = ThreadContext.getSessionVisitor();
                appEnv.setUpdator(visitor.getLoginUserId());
                appEnv.setCreator(visitor.getLoginUserId());
                appEnv.setCreateTime(new Date());
                appEnv.setUpdateTime(new Date());
                appEnvMapper.insertSelective(appEnv);
            }
        } else {
            consumer.accept(CodeMessage.CODE_102);
        }
    }
}
