package com.baidu.disconf.core.common.utils;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassLoaderUtil
 */
public final class ClassLoaderUtil {

    protected static final Logger logger = LoggerFactory
            .getLogger(ClassLoaderUtil.class);

    /**
     * class path
     */
    private static String classPath = "";

    /**
     * 当前线程上下文类加载器
     */
    private static ClassLoader loader = Thread.currentThread().getContextClassLoader();

    private ClassLoaderUtil() {
    }

    /**
     * ClassLoader初始化
     */
    static {

        if (loader == null) {
            logger.info("using system class loader!");
            loader = ClassLoader.getSystemClassLoader();
        }

        try {

            java.net.URL url = loader.getResource("");
            // get class path
            File f=new File(url.toURI());
            classPath = f.getAbsolutePath();
            classPath = URLDecoder.decode(classPath, "utf-8");

            // 如果是jar包内的，则返回当前路径
            if (classPath.contains(".jar!")) {
                logger.warn("using config file inline jar!" + classPath);
                classPath = System.getProperty("user.dir");

                //
                addCurrentWorkingDir2Classpath(classPath);
            }

        } catch (Exception e) {
            logger.warn("cannot get classpath using getResource(), now using user.dir");
            classPath = System.getProperty("user.dir");

            //
            addCurrentWorkingDir2Classpath(classPath);
        }

        logger.info("classpath: {}", classPath);
    }

    /**
     *
     * only support 1.7 or higher
     * http://stackoverflow.com/questions/252893/how-do-you-change-the-classpath-within-java
     * @param path2Added 路径
     */
    private static void addCurrentWorkingDir2Classpath(String path2Added) {

        // Add the conf dir to the classpath
        // Chain the current thread classloader
        URLClassLoader urlClassLoader;
        try {
            urlClassLoader = new URLClassLoader(new URL[] {new File(path2Added).toURI().toURL()},
                    loader);
            // Replace the thread classloader - assumes
            // you have permissions to do so
            Thread.currentThread().setContextClassLoader(urlClassLoader);
        } catch (Exception e) {
            logger.warn(e.toString());
        }
    }

    /**
     * ClassPath 路径
     * @return ClassPath 路径
     */
    public static String getClassPath() {
        return classPath;
    }

    /**
     * 获取ClassLoader
     * @return 获取ClassLoader
     */
    public static ClassLoader getLoader() {
        return loader;
    }
}
