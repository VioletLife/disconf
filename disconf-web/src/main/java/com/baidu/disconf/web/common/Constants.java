package com.baidu.disconf.web.common;

import com.baidu.disconf.web.common.message.CodeMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by knightliao on 15/12/25.
 */
public class Constants {

    public final static Integer STATUS_NORMAL = 1;
    public final static Integer STATUS_DELETE = 0;
    /**
     * Properties文件后缀
     */
    public final static String PROPERTIES_SUFFIX = ".properties";


    /**
     * Yaml文件后缀
     */
    public final static String YAML_SUFFIX = ".yaml";


    /**
     * 允许的配置文件后缀
     */
    public final static String[] ACCEPT_CONF_SUFFIX = {PROPERTIES_SUFFIX, YAML_SUFFIX};



}
