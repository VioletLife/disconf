package com.baidu.disconf.core.common.zookeeper.inner;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaoqiqi
 * @version 2014-6-16
 */
public class ConfigUpdater {

    Logger logger = LoggerFactory.getLogger(ConfigUpdater.class);

    public static final String PATH = "/config";

    private ResilientActiveKeyValueStore store;
    private Random random = new Random();

    /**
     * 构造器
     *
     * @param hosts host地址
     * @throws IOException          Zookeeper异常
     * @throws InterruptedException Zookeeper异常
     */
    public ConfigUpdater(String hosts) throws IOException, InterruptedException {
        store = new ResilientActiveKeyValueStore(true);
        store.connect(hosts);
    }

    public void run() throws InterruptedException, KeeperException {
        while (true) {
            String value = random.nextInt(100) + "";
            store.write(PATH, value);
            if (logger.isInfoEnabled()) {
                logger.info(String.format("Set %s to %s\n", PATH, value));
            }
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        }
    }

    public static void main(String[] args) throws Exception {

        ConfigUpdater configUpdater = new ConfigUpdater(args[0]);
        configUpdater.run();
    }
}
