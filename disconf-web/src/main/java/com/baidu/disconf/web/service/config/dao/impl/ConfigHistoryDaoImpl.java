package com.baidu.disconf.web.service.config.dao.impl;

import com.baidu.dsp.common.dao.Columns;
import com.baidu.unbiz.common.genericdao.operator.Match;
import org.springframework.stereotype.Service;

import com.baidu.disconf.web.service.config.bo.ConfigHistory;
import com.baidu.disconf.web.service.config.dao.ConfigHistoryDao;
import com.baidu.dsp.common.dao.AbstractDao;

import java.util.List;

/**
 * Created by knightliao on 15/12/25.
 */
@Service
public class ConfigHistoryDaoImpl extends AbstractDao<Long, ConfigHistory> implements ConfigHistoryDao {
    @Override
    public List<ConfigHistory> getConfigHistoryByConfigId(Long configId) {
        return find(new Match(Columns.CONFIG_ID, configId));
    }
}
