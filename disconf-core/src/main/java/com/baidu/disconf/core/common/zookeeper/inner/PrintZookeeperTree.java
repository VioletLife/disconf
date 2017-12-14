package com.baidu.disconf.core.common.zookeeper.inner;

import java.nio.charset.Charset;
import java.util.List;

import com.baidu.disconf.core.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZK打印
 *
 * @author liaoqiqi
 * @version 2014-7-7
 */
public class PrintZookeeperTree extends ConnectionWatcher {

    protected static final Logger logger = LoggerFactory.getLogger(PrintZookeeperTree.class);

    /**
     * 默认的字符集为UTF-8
     */
    private static final Charset CHARSET = Charset.forName("UTF-8");

    /**
     * 构造器
     */
    public PrintZookeeperTree() {
        super(true);
    }

    /**
     * 输出节点内容
     *
     * @param groupName 路径名称
     * @throws KeeperException      Zookeeper异常
     * @throws InterruptedException Zookeeper异常
     */
    public void list(String groupName) throws KeeperException, InterruptedException {

        try {

            StringBuilder sb = new StringBuilder();

            int pathLength = StringUtils.countMatches(groupName, Constants.SEP_STRING);
            for (int i = 0; i < pathLength - 1; ++i) {
                sb.append("\t");
            }

            if (!groupName.equalsIgnoreCase(Constants.SEP_STRING)) {
                String node = StringUtils.substringAfterLast(groupName, Constants.SEP_STRING);
                sb.append("|----" + node);
                Stat stat = new Stat();
                byte[] data = zk.getData(groupName, null, stat);

                if (data != null) {
                    sb.append("\t" + new String(data, CHARSET));
                }
                if (stat != null) {
                    sb.append("\t" + stat.getEphemeralOwner());
                }
            } else {
                sb.append(groupName);
            }

            logger.info(sb.toString());

            List<String> children = zk.getChildren(groupName, false);
            /**
             * 递归获取
             */
            for (String child : children) {
                if (!groupName.equalsIgnoreCase(Constants.SEP_STRING)) {
                    list(groupName + Constants.SEP_STRING + child);
                } else {
                    list(groupName + child);
                }
            }

        } catch (KeeperException.NoNodeException e) {
            logger.info("Group %s does not exist\n", groupName);
        }
    }

    public static void main(String[] args) throws Exception {

        if (args == null || args.length != 1) {
            logger.error("PrintZookeeperTree argu error!");
            System.exit(2);
        }

        PrintZookeeperTree printZookeeperTree = new PrintZookeeperTree();
        printZookeeperTree.connect(args[0]);

        Thread.sleep(2000);

        System.out.println("\n\n==================");

        printZookeeperTree.list("/");

        System.out.println("\n\n==================");

        printZookeeperTree.close();

        System.out.println("\n\n==================");

    }
}
