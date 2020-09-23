package com.ssh.client.handle;

import com.ssh.client.cache.CacheUtil;
import com.ssh.client.constant.TipsConstants;
import com.ssh.client.manager.ConnectionManager;
import com.ssh.client.manager.SendCommandManager;
import com.trilead.ssh2.Connection;
import com.ssh.client.constant.CacheConstants;
import com.ssh.client.constant.CommandConstants;
import com.ssh.client.entity.ConnectionInfo;
import com.ssh.client.tree.ChildrenDirectory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.Objects;

/**
 * @className: ConnectionHandle
 * @package: com.ssh.client.handle
 * @describe: 开始连接
 * @author:（willdas）
 * @date: 2020/09/22 10:17
 **/
public class ConnectionHandle {

    private static Logger log = LogManager.getLogger();

    private ConnectionInfo connectionInfo;
    private JLabel tipLabel;
    private static CacheUtil cacheUtil = CacheUtil.getInstance();

    public ConnectionHandle(ConnectionInfo connectionInfo, JLabel jLabel) {
        this.connectionInfo = connectionInfo;
        this.tipLabel = jLabel;
    }

    public Connection getConnection() {
        log.info("开始与IP:{}的服务端进行连接", connectionInfo.getIp());
        if (Objects.nonNull(cacheUtil.get(CacheConstants.getConnection(connectionInfo.getIp())))) {
            log.info("IP:{}的服务端已连接", connectionInfo.getIp());
            return (Connection) cacheUtil.get(CacheConstants.getConnection(connectionInfo.getIp()));
        }

        Connection connection = ConnectionManager.connection(connectionInfo);
        if (Objects.nonNull(connection)) {
            this.tipLabel.setText(TipsConstants.getConnectionSuccess());
            this.tipLabel.setForeground(Color.green);
            cacheUtil.set(CacheConstants.getConnectionInfo(), connectionInfo);
            cacheUtil.set(CacheConstants.getConnection(connectionInfo.getIp()), connection, 1000 * 60 * 30);
            JTree jTree = (JTree) cacheUtil.get(CacheConstants.getServerTree());
            new ChildrenDirectory((DefaultTreeModel) jTree.getModel()).addServerTreeNode(new SendCommandManager(connection).getSendCommandResult(CommandConstants.getCdLs()));
            log.info("IP:{}的服务端连接成功", connectionInfo.getIp());
        } else {
            this.tipLabel.setText(TipsConstants.getConnectionFail());
            this.tipLabel.setForeground(Color.red);
            log.info("IP:{}的服务端连接失败", connectionInfo.getIp());
        }
        return connection;
    }
}
