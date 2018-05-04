package com.baidu.disconf.web.service.env.service.impl;

import com.baidu.disconf.web.common.message.CodeMessage;
import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.BaseService;
import com.baidu.disconf.web.service.Page;
import com.baidu.disconf.web.service.env.bo.EnvDefault;
import com.baidu.disconf.web.service.env.dao.EnvDefaultDao;
import com.baidu.disconf.web.service.env.mybatis.EnvDefaultDynamicSqlSupport;
import com.baidu.disconf.web.service.env.mybatis.EnvDefaultMapper;
import com.baidu.disconf.web.service.env.service.EnvDefaultMgr;
import com.baidu.disconf.web.service.env.vo.EnvDefaultVo;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SimpleSortSpecification;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsLikeCaseInsensitive;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author QingDengYue
 */
@Service
public class EnvDefaultMgrImpl implements EnvDefaultMgr {

    @Autowired
    EnvDefaultDao envDefaultDao;


    @Autowired
    EnvDefaultMapper envDefaultMapper;

    @Override
    public Optional<List<EnvDefaultVo>> findAll() {
        List<EnvDefault> defaultDaoAll = envDefaultDao.findAll();
        Optional<List<EnvDefaultVo>> envDefaultVos = fromBoToVo(EnvDefaultVo.class, defaultDaoAll);
        return envDefaultVos;
    }


    @Override
    public Page<EnvDefaultVo> selectByExampleWithRowbounds(Page<EnvDefaultVo> rowBounds, String envName) {
        PageHelper.startPage(rowBounds.getOffset(), rowBounds.getLimit());
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<com.baidu.disconf.web.service.env.mybatis.EnvDefault>>> expressionDSL = envDefaultMapper.selectByExample();
        List<com.baidu.disconf.web.service.env.mybatis.EnvDefault> envDefaults;
        List<EnvDefaultVo> envDefaultVos = new ArrayList<>();
        if (StringUtils.isNotEmpty(envName)) {
            envDefaults = expressionDSL.where(EnvDefaultDynamicSqlSupport.envName, IsLikeCaseInsensitive.of(() -> "%" + envName + "%"))
                    .and(EnvDefaultDynamicSqlSupport.creator, IsEqualTo.of(() -> getCurrentVisitor().getLoginUserId()))
                    .orderBy(SimpleSortSpecification.of("create_time").descending())
                    .build()
                    .execute();
        } else {
            envDefaults = expressionDSL
                    .where(EnvDefaultDynamicSqlSupport.creator, IsEqualTo.of(() -> getCurrentVisitor().getLoginUserId()))
                    .orderBy(SimpleSortSpecification.of("create_time").descending())
                    .build()
                    .execute();
        }
        if (envDefaults instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page page = (com.github.pagehelper.Page) envDefaults;
            rowBounds.setTotal(page.getTotal());
            List<com.baidu.disconf.web.service.env.mybatis.EnvDefault> pageResult = (List<com.baidu.disconf.web.service.env.mybatis.EnvDefault>) page.getResult();

            for (com.baidu.disconf.web.service.env.mybatis.EnvDefault envDefault : pageResult) {
                EnvDefaultVo envDefaultVo = new EnvDefaultVo();
                BeanUtils.copyProperties(envDefault, envDefaultVo);
                envDefaultVos.add(envDefaultVo);
            }
            rowBounds.setResult(Optional.of(envDefaultVos));
        }
        return rowBounds;
    }

    @Override
    public EnvDefaultVo insertSelective(EnvDefaultVo defaultVo, Consumer<ResponseMessage> consumer) {
        if (defaultVo != null && StringUtils.isNotEmpty(defaultVo.getEnvName())) {
            Long countRecord = envDefaultMapper.countByExample()
                    .where(EnvDefaultDynamicSqlSupport.envName, IsEqualTo.of(defaultVo::getEnvName))
                    .build()
                    .execute();
            if (countRecord != null && countRecord > 0) {
                consumer.accept(CodeMessage.CODE_119.toResponseMessage());
            } else {
                com.baidu.disconf.web.service.env.mybatis.EnvDefault envDefault = new com.baidu.disconf.web.service.env.mybatis.EnvDefault();
                BeanUtils.copyProperties(defaultVo, envDefault);
                envDefault.setId(null);
                envDefault.setCreator(getCurrentVisitor().getLoginUserId());
                envDefault.setLastUpdator(getCurrentVisitor().getLoginUserId());
                envDefault.setLastUpdateTime(new Date());
                envDefault.setCreateTime(new Date());
                envDefaultMapper.insertSelective(envDefault);
            }

        } else {
            consumer.accept(CodeMessage.CODE_118.toResponseMessage());
        }
        return defaultVo;
    }

    @Override
    public int deleteByPrimaryKey(Long envId) {
        return envDefaultMapper.deleteByExample()
                .where(EnvDefaultDynamicSqlSupport.creator, IsEqualTo.of(() -> getCurrentVisitor().getLoginUserId()))
                .and(EnvDefaultDynamicSqlSupport.id, IsEqualTo.of(() -> envId))
                .build()
                .execute();
    }


    @Override
    public EnvDefaultVo updateSelective(EnvDefaultVo defaultVo, Consumer<ResponseMessage> consumer) {
        if (defaultVo != null && defaultVo.getId() != null && defaultVo.getId() > 0 && StringUtils.isNotEmpty(defaultVo.getEnvName())) {
            Long countRecord = envDefaultMapper.countByExample()
                    .where(EnvDefaultDynamicSqlSupport.envName, IsEqualTo.of(defaultVo::getEnvName))
                    .build()
                    .execute();
            /**
             * 表示相同名称存在多条记录
             */
            if (countRecord != null && countRecord >= 2) {
                consumer.accept(CodeMessage.CODE_120.toResponseMessage());
            } else {
                com.baidu.disconf.web.service.env.mybatis.EnvDefault envDefault = new com.baidu.disconf.web.service.env.mybatis.EnvDefault();
                BeanUtils.copyProperties(defaultVo, envDefault);
                envDefault.setLastUpdator(getCurrentVisitor().getLoginUserId());
                envDefault.setLastUpdateTime(new Date());
                envDefaultMapper.updateByPrimaryKeySelective(envDefault);
            }
        } else {
            consumer.accept(CodeMessage.CODE_120.toResponseMessage());
        }
        return defaultVo;
    }
}
