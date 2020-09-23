package com.ssh.client.handle;


import com.ssh.client.cache.CacheUtil;
import com.ssh.client.constant.TipsConstants;
import com.ssh.client.manager.ConnectionManager;
import com.ssh.client.constant.CacheConstants;
import com.ssh.client.tree.ChildrenDirectory;
import com.trilead.ssh2.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * @className: CloseHandle
 * @package: com.ssh.client.handle
 * @describe: 关闭连接
 * @author:（willdas）
 * @date: 2020/09/22 10:15
 **/
public class CloseHandle {

    private static Logger log = LogManager.getLogger();

    private static CacheUtil cacheUtil = CacheUtil.getInstance();

    public static void close(Connection connection, JLabel tipLabel) {
        log.info("关闭IP:{}的服务端连接", connection.getHostname());
        new ChildrenDirectory((DefaultTreeModel)((JTree) cacheUtil.get(CacheConstants.getServerTree())).getModel()).removeAllChildrenNode();
        ConnectionManager.close(connection);
        tipLabel.setText(TipsConstants.getCloseFinish());
        tipLabel.setForeground(Color.black);
        CacheUtil.clear();
        log.info("连接关闭完成");
    }

}
