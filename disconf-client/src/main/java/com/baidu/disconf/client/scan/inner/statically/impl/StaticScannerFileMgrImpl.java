package com.baidu.disconf.client.scan.inner.statically.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.constants.SupportFileTypeEnum;
import com.baidu.disconf.client.common.model.DisConfCommonModel;
import com.baidu.disconf.client.common.model.DisconfCenterBaseModel;
import com.baidu.disconf.client.common.model.DisconfCenterFile;
import com.baidu.disconf.client.common.model.DisconfCenterFile.FileItemValue;
import com.baidu.disconf.client.config.DisClientSysConfig;
import com.baidu.disconf.client.scan.inner.statically.StaticScannerMgr;
import com.baidu.disconf.client.scan.inner.statically.model.ScanStaticModel;
import com.baidu.disconf.client.store.DisconfStoreProcessorFactory;
import com.baidu.disconf.client.support.utils.MethodUtils;
import com.baidu.disconf.core.common.constants.DisConfigTypeEnum;
import com.baidu.disconf.core.common.path.DisconfWebPathMgr;

/**
 * 配置文件的静态扫描
 *
 * @author liaoqiqi
 * @version 2014-9-9
 */
public class StaticScannerFileMgrImpl extends StaticScannerMgrImplBase implements StaticScannerMgr {

    protected static final Logger logger = LoggerFactory.getLogger(StaticScannerFileMgrImpl.class);

    /**
     * 扫描数据并存储
     * @param scanModel  {@link ScanStaticModel}
     */
    @Override
    public void scanData2Store(ScanStaticModel scanModel) {

        // 转换配置文件
        List<DisconfCenterBaseModel> disconfCenterFiles = getDisconfFiles(scanModel);
        DisconfStoreProcessorFactory.getDisconfStoreFileProcessor().transformScanData(disconfCenterFiles);
    }

    /**
     *
     */
    @Override
    public void exclude(Set<String> keySet) {
        DisconfStoreProcessorFactory.getDisconfStoreFileProcessor().exclude(keySet);
    }

    /**
     * 获取配置文件数据
     */
    private static List<DisconfCenterBaseModel> getDisconfFiles(ScanStaticModel scanModel) {

        /**
         * 配置基类
         */
        List<DisconfCenterBaseModel> disconfCenterFiles = new ArrayList<DisconfCenterBaseModel>();

        Set<Class<?>> classSet = scanModel.getDisconfFileClassSet();
        for (Class<?> disconfFile : classSet) {

            Set<Method> methods = scanModel.getDisconfFileItemMap().get(disconfFile);
            if (methods == null) {
                continue;
            }

            DisconfCenterFile disconfCenterFile = transformScanFile(disconfFile, methods);

            disconfCenterFiles.add(disconfCenterFile);
        }

        return disconfCenterFiles;
    }

    /**
     * 转换配置文件
     * @param disconfFileClass Class对象
     * @param methods Class对象中的方法
     * @return 配置文件内容数据
     */
    private static DisconfCenterFile transformScanFile(Class<?> disconfFileClass, Set<Method> methods) {

        DisconfCenterFile disconfCenterFile = new DisconfCenterFile();

        /**
         * 保存Class信息
         */
        disconfCenterFile.setCls(disconfFileClass);

        /**
         * 获取类上的注解 {@link DisconfFile}
         */
        DisconfFile disconfFileAnnotation = disconfFileClass.getAnnotation(DisconfFile.class);

        /**
         * 配置文件名
         */
        disconfCenterFile.setFileName(disconfFileAnnotation.filename());

        /**
         * 配置文件路径
         */
        disconfCenterFile.setTargetDirPath(disconfFileAnnotation.targetDirPath().trim());

        /**
         * 配置文件类型枚举
         */
        disconfCenterFile.setSupportFileTypeEnum(SupportFileTypeEnum.getByFileName(disconfFileAnnotation.filename()));

        /**
         * Disconf中设置的APP的信息
         */
        DisConfCommonModel disConfCommonModel =
                makeDisConfCommonModel(disconfFileAnnotation.app(), disconfFileAnnotation.env(), disconfFileAnnotation
                        .version());


        /**
         * 保存APP设置信息
         */
        disconfCenterFile.setDisConfCommonModel(disConfCommonModel);

        /**
         * 取得配置远程URL地址
         */
        String url = DisconfWebPathMgr.getRemoteUrlParameter(DisClientSysConfig.getInstance().CONF_SERVER_STORE_ACTION,
                disConfCommonModel.getApp(),
                disConfCommonModel.getVersion(),
                disConfCommonModel.getEnv(),
                disconfCenterFile.getFileName(),
                DisConfigTypeEnum.FILE);
        disconfCenterFile.setRemoteServerUrl(url);

        /**
         * 获取属性字段
         */
        Field[] expectedFields = disconfFileClass.getDeclaredFields();


        Map<String, FileItemValue> keyMaps = new HashMap<String, FileItemValue>();

        for (Method method : methods) {

            /**
             * 获取指定的域
             */
            Field field = MethodUtils.getFieldFromMethod(method, expectedFields, DisConfigTypeEnum.FILE);
            if (field == null) {
                continue;
            }

            /**
             * 获取 {@link DisconfFileItem} 枚举
             */
            DisconfFileItem disconfFileItem = method.getAnnotation(DisconfFileItem.class);
            String keyName = disconfFileItem.name();

            /**
             * 设置访问级别
             */
            field.setAccessible(true);

            /**
             * 获取Setter方法
             */
            Method setterMethod = MethodUtils.getSetterMethodFromField(disconfFileClass, field);

            /**
             * 检测是否是静态域
             */
            if (Modifier.isStatic(field.getModifiers())) {

                try {
                    FileItemValue fileItemValue = new FileItemValue(field.get(null), field, setterMethod);
                    keyMaps.put(keyName, fileItemValue);

                } catch (Exception e) {
                    logger.error(e.toString());
                }

            } else {

                /**
                 * 非static则为Null, 这里我们没有必要获取其Bean的值
                 */
                FileItemValue fileItemValue = new FileItemValue(null, field, setterMethod);
                keyMaps.put(keyName, fileItemValue);
            }
        }
        disconfCenterFile.setKeyMaps(keyMaps);
        return disconfCenterFile;
    }

}
