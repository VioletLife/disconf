package com.baidu.disconf.web.service.app.service.impl;

import com.baidu.disconf.web.common.message.CodeMessage;
import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.app.mybatis.AppEnvVersion;
import com.baidu.disconf.web.service.app.mybatis.AppEnvVersionDynamicSqlSupport;
import com.baidu.disconf.web.service.app.mybatis.AppEnvVersionMapper;
import com.baidu.disconf.web.service.app.service.AppEnvVersionMgr;
import com.baidu.disconf.web.service.user.dto.Visitor;
import com.baidu.ub.common.commons.ThreadContext;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@Service
public class AppEnvVersionMgrImpl implements AppEnvVersionMgr {

    @Autowired
    public AppEnvVersionMapper appEnvVersionMapper;


    @Override
    public AppEnvVersion createVersionByAppIdAndEnvId(AppEnvVersion version, Consumer<ResponseMessage> consumer) {
        if (version.getAppId() != null && version.getAppId() > 0 && version.getAppEnvId() != null && version.getAppEnvId() > 0 && StringUtils.isNotEmpty(version.getVersionName())) {
            Long countRecord = appEnvVersionMapper.countByExample()
                    .where(AppEnvVersionDynamicSqlSupport.appId, IsEqualTo.of(version::getAppId))
                    .and(AppEnvVersionDynamicSqlSupport.appEnvId, IsEqualTo.of(version::getAppEnvId))
                    .and(AppEnvVersionDynamicSqlSupport.versionName, IsEqualTo.of(version::getVersionName))
                    .build()
                    .execute();
            if (countRecord != null && countRecord > 0) {
                consumer.accept(CodeMessage.CODE_101.toResponseMessage());
                return version;
            }
            Visitor visitor = ThreadContext.getSessionVisitor();
            version.setCreator(visitor.getLoginUserId());
            version.setCreateTime(new Date());
            version.setUpdator(visitor.getLoginUserId());
            version.setUpdateTime(new Date());
            appEnvVersionMapper.insertSelective(version);
        }
        return version;
    }

    @Override
    public List<AppEnvVersion> selectVersionByAppIdAndEnvId(Long appId, Long envId) {
        return appEnvVersionMapper.selectByExample()
                .where(AppEnvVersionDynamicSqlSupport.appId,IsEqualTo.of(()->appId))
                .and(AppEnvVersionDynamicSqlSupport.appEnvId,IsEqualTo.of(()->envId))
                .build()
                .execute()
                ;
    }
}
