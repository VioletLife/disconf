package com.baidu.disconf.core.common.zookeeper.inner;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaoqiqi
 * @version 2014-6-16
 */
public class ConfigWatcher implements Watcher {


    Logger logger = LoggerFactory.getLogger(ConfigUpdater.class);

    private ResilientActiveKeyValueStore store;

    /**
     * 构造器
     *
     * @param hosts host地址
     * @throws IOException          Zookeeper异常
     * @throws InterruptedException Zookeeper异常
     */
    public ConfigWatcher(String hosts) throws IOException, InterruptedException {
        store = new ResilientActiveKeyValueStore(true);
        store.connect(hosts);
    }

    /**
     * 显示配置信息
     * @throws InterruptedException  Zookeeper异常
     * @throws KeeperException Zookeeper异常
     */
    public void displayConfig() throws InterruptedException, KeeperException {
        String value = store.read(ConfigUpdater.PATH, this, null);
        if (logger.isInfoEnabled()) {
            logger.info(String.format("Read %s as %s\n", ConfigUpdater.PATH, value));
        }
    }

    /**
     * Zookeeper 节点内容变动事件处理
     * @param event 事件
     */
    @Override
    public void process(WatchedEvent event) {

        if (event.getType() == EventType.NodeDataChanged) {
            try {
                displayConfig();
            } catch (InterruptedException e) {
                if (logger.isErrorEnabled()) {
                    logger.error("Interrupted. Exiting.");
                }
                Thread.currentThread().interrupt();
            } catch (KeeperException e) {
                if (logger.isErrorEnabled()) {
                    logger.error("KeeperException: %s. Exiting.\n", e);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {

        ConfigWatcher configWatcher = new ConfigWatcher(args[0]);
        configWatcher.displayConfig();

        // stay alive until process is killed or thread is interrupted
        Thread.sleep(Long.MAX_VALUE);
    }
}
