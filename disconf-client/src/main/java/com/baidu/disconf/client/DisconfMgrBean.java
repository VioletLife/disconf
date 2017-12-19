package com.baidu.disconf.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

import com.baidu.disconf.client.store.aspect.DisconfAspectJ;
import com.baidu.disconf.client.store.inner.DisconfCenterHostFilesStore;
import com.baidu.disconf.client.support.utils.StringUtil;

/**
 * 第一次扫描，静态扫描
 *
 * @author liaoqiqi
 * @version 2014-6-17
 */
public class DisconfMgrBean implements BeanDefinitionRegistryPostProcessor, PriorityOrdered, ApplicationContextAware {

    public final static String SCAN_SPLIT_TOKEN = ",";

    private ApplicationContext applicationContext;

    private String scanPackage = null;

    public void destroy() {

        DisconfMgr.getInstance().close();
    }

    /**
     * 设置扫描包路径
     *
     * @param scanPackage 包路径
     */
    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    /**
     * 执行优先级
     *
     * @return 优先级
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

    /**
     * 这个函数无法达到最高优先级，例如PropertyPlaceholderConfigurer
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }


    /**
     * Spring内部Bean初始化完成，下次自动BeanFactoryPostProcessor处理前调用。此处增加用于随后使用的Bean定义
     *
     * @param registry ${@link BeanDefinitionRegistry}
     * @throws BeansException Beans异常
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        // 为了做兼容
        DisconfCenterHostFilesStore.getInstance().addJustHostFileSet(fileList);

        /**
         * ${@link SCAN_SPLIT_TOKEN}分割的包名
         */
        List<String> scanPackList = StringUtil.parseStringToStringList(scanPackage, SCAN_SPLIT_TOKEN);
        /**
         * 包路径唯一
         */
        Set<String> hs = new HashSet<String>();
        hs.addAll(scanPackList);
        scanPackList.clear();
        scanPackList.addAll(hs);

        /**
         * 设置ApplicationContext
         */
        DisconfMgr.getInstance().setApplicationContext(applicationContext);

        /**
         * 开始第一遍扫描过程
         */
        DisconfMgr.getInstance().firstScan(scanPackList);

        /**
         * 注册 ${@link DisconfAspectJ} Bean 默认名称为:disconfAspectJ
         */
        registerAspect(registry);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * register aspectJ for disconf get request
     *
     * @param registry ${@link BeanDefinitionRegistry}
     */
    private void registerAspect(BeanDefinitionRegistry registry) {

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DisconfAspectJ.class);
        beanDefinition.setLazyInit(false);
        beanDefinition.setAbstract(false);
        beanDefinition.setAutowireCandidate(true);
        beanDefinition.setScope("singleton");

        registry.registerBeanDefinition("disconfAspectJ", beanDefinition);
    }

    /**
     * 已经废弃不推荐使用
     */
    @Deprecated
    private Set<String> fileList = new HashSet<String>();

    @Deprecated
    public Set<String> getFileList() {
        return fileList;
    }

    @Deprecated
    public void setFileList(Set<String> fileList) {
        this.fileList = fileList;
    }
}
