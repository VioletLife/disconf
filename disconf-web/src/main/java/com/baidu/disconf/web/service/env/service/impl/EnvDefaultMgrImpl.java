package com.baidu.disconf.web.service.env.service.impl;

import com.baidu.disconf.web.service.BaseService;
import com.baidu.disconf.web.service.env.bo.EnvDefault;
import com.baidu.disconf.web.service.env.dao.EnvDefaultDao;
import com.baidu.disconf.web.service.env.mybatis.EnvDefaultMapper;
import com.baidu.disconf.web.service.env.service.EnvDefaultMgr;
import com.baidu.disconf.web.service.env.vo.EnvDefaultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}
