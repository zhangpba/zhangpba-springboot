package com.study.zookeeper.consumer.listener;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * zk监听类
 *
 * @author zhangpba
 * @date 2021-10-13
 */
public class ZookListener {

    private static final String SERVER_PATH = "/product";

    private static final String ZK_ADDRESS = "127.0.0.1:2181";

    private static final int ZK_TIMEOUT = 20000;

    private ZooKeeper zooKeeper;

    private List<String> paths = new LinkedList<>();


    public void init() {
        try {
            zooKeeper = new ZooKeeper(ZK_ADDRESS, ZK_TIMEOUT, (WatchEvent) -> {
                //监听该节点的变化，如果节点出现变化，则重新获取节点下的ip和端口
                if (WatchEvent.getType() == Watcher.Event.EventType.NodeChildrenChanged &&
                        WatchEvent.getPath().equals(SERVER_PATH)) {
                    getChilds();
                }

            });
            getChilds();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getChilds() {
        List<String> ips = new LinkedList<>();
        try {
            //添加监听
            List<String> childs = this.zooKeeper.getChildren(SERVER_PATH, true);
            for (String child : childs) {
                byte[] obj = zooKeeper.getData(SERVER_PATH + "/" + child, false, null);
                String path = new String(obj, "utf-8");
                ips.add(path);
            }
            this.paths = ips;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public String getPath() {
        if (paths.isEmpty()) {
            return null;
        }
        //这里我们随机获取一个ip端口使用
        int index = new Random().nextInt(paths.size());
        return paths.get(index);
    }

}
