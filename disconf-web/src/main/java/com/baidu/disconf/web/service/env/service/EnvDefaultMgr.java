package com.baidu.disconf.web.service.env.service;

import com.baidu.disconf.web.service.BaseService;
import com.baidu.disconf.web.service.env.vo.EnvDefaultVo;

import java.util.List;
import java.util.Optional;

public interface EnvDefaultMgr extends BaseService {
    Optional<List<EnvDefaultVo>> findAll();
}
