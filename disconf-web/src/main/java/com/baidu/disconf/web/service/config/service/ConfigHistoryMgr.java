package com.baidu.disconf.web.service.config.service;

import com.baidu.disconf.web.service.BaseService;
import com.baidu.disconf.web.service.config.bo.ConfigHistory;
import com.baidu.disconf.web.service.config.vo.ConfHistoryVo;

import java.util.List;
import java.util.Optional;

/**
 * Created by knightliao on 15/12/25.
 */
public interface ConfigHistoryMgr extends BaseService {

    void createOne(Long configId, String oldValue, String newValue);

    Optional<List<ConfHistoryVo>> getConfigHistoryByConfigId(Long configId);
}
