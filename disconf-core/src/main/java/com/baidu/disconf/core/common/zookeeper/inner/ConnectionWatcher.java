package com.baidu.disconf.core.common.zookeeper.inner;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 连接管理
 *
 * @author liaoqiqi
 */
public class ConnectionWatcher implements Watcher {

    protected static final Logger logger = LoggerFactory.getLogger(ConnectionWatcher.class);

    /**
     * 10 秒会话时间 ，避免频繁的session expired
     */
    private static final int SESSION_TIMEOUT = 10000;

    /**
     * 3秒
     */
    private static final int CONNECT_TIMEOUT = 3000;

    protected ZooKeeper zk;
    private CountDownLatch connectedSignal = new CountDownLatch(1);

    private static String internalHost = "";

    /**
     * 是否调试状态
     */
    private boolean debug = false;

    /**
     * @param debug
     */
    public ConnectionWatcher(boolean debug) {
        this.debug = debug;
    }

    /**
     * 连接ZK
     *
     * @param hosts
     * @throws IOException          Zookeeper异常
     * @throws InterruptedException Zookeeper异常
     * @author liaoqiqi
     * @date 2013-6-14
     */
    public void connect(String hosts) throws IOException, InterruptedException {
        internalHost = hosts;
        zk = new ZooKeeper(internalHost, SESSION_TIMEOUT, this);

        /**
         * 连接有超时哦
         */
        connectedSignal.await(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);

        logger.info("zookeeper: " + hosts + " , connected.");
    }

    /**
     * 当连接成功时调用的
     */
    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == KeeperState.SyncConnected) {

            logger.info("zk SyncConnected");
            connectedSignal.countDown();

        } else if (event.getState().equals(KeeperState.Disconnected)) {

            /**
             * 这时收到断开连接的消息，这里其实无能为力，因为这时已经和ZK断开连接了，只能等ZK再次开启了
             */
            logger.warn("zk Disconnected");

        } else if (event.getState().equals(KeeperState.Expired)) {

            if (!debug) {

                /**
                 * 这时收到这个信息，表示，ZK已经重新连接上了，但是会话丢失了，这时需要重新建立会话。
                 */
                logger.error("zk Expired");

                // just reconnect forever
                reconnect();
            } else {
                logger.info("zk Expired");
            }

        } else if (event.getState().equals(KeeperState.AuthFailed)) {

            logger.error("zk AuthFailed");
        }
    }

    /**
     * 含有重试机制的retry，加锁, 一直尝试连接，直至成功
     */
    public synchronized void reconnect() {

        logger.info("start to reconnect....");

        int retries = 0;
        while (true) {

            try {

                if (!zk.getState().equals(States.CLOSED)) {
                    break;
                }

                logger.warn("zookeeper lost connection, reconnect");

                close();

                connect(internalHost);

            } catch (Exception e) {

                logger.error(retries + "\t" + e.toString());

                // sleep then retry
                try {
                    int sec = ResilientActiveKeyValueStore.RETRY_PERIOD_SECONDS;
                    logger.warn("sleep " + sec);
                    TimeUnit.SECONDS.sleep(sec);
                } catch (InterruptedException e1) {
                }
            }
        }
    }

    /**
     * 断开Zookeeper链接
     * @author liaoqiqi
     * @throws InterruptedException Zookeeper异常
     * @date 2013-6-14
     */
    public void close() throws InterruptedException {
        zk.close();
    }

    /**
     * 获取 Zookeeper 实例
     * @return Zookeeper 实例
     */
    public ZooKeeper getZk() {
        return zk;
    }

    /**
     * 设置 Zookeeper 实例
     * @param zk Zookeeper 实例
     */
    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }
}
