package com.baidu.disconf.web.service.env.service;

import com.baidu.disconf.web.common.message.ResponseMessage;
import com.baidu.disconf.web.service.BaseService;
import com.baidu.disconf.web.service.Page;
import com.baidu.disconf.web.service.env.vo.EnvDefaultVo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface EnvDefaultMgr extends BaseService {
    Optional<List<EnvDefaultVo>> findAll();


    /**
     * 获取默认的环境数据
     *
     * @param rowBounds 分页数据
     * @param envName   环境名称
     * @return 环境数据
     */
    Page<EnvDefaultVo> selectByExampleWithRowbounds(Page<EnvDefaultVo> rowBounds, String envName);


    /**
     * 创建默认环境
     *
     * @param defaultVo 默认Env对象
     * @param consumer  错误信息
     * @return Env对象
     */
    EnvDefaultVo insertSelective(EnvDefaultVo defaultVo, Consumer<ResponseMessage> consumer);



    /**
     * 更新默认环境
     *
     * @param defaultVo 默认Env对象
     * @param consumer  错误信息
     * @return Env对象
     */
    EnvDefaultVo updateSelective(EnvDefaultVo defaultVo, Consumer<ResponseMessage> consumer);


    /**
     * 删除指定Key 的默认环境信息
     *
     * @param envId 环境Key
     * @return 返回值
     */
    int deleteByPrimaryKey(Long envId);
}
