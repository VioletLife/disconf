package com.baidu.disconf.web.service.config.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class StatisticsVo {
    public List<ConfHistoryVo> history;
    public List<ConfigVo> configFiles;
    public List<ConfigVo> configItems;
}
