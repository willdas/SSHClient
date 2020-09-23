package com.ssh.client.listener;

import com.ssh.client.cache.CacheUtil;
import com.ssh.client.constant.CacheConstants;
import com.ssh.client.constant.CommandConstants;
import com.ssh.client.entity.ConnectionInfo;
import com.ssh.client.tree.ChildrenDirectory;
import com.ssh.client.manager.SendCommandManager;
import com.ssh.client.utils.TreeNodePathUtil;
import com.trilead.ssh2.Connection;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Objects;

/**
 * @className: ServerTreeNodeListener
 * @package: com.ssh.client.listener
 * @describe: 服务器目录节点事件监听器
 * @author:（willdas）
 * @date: 2020/09/22 10:13
 **/
public class ServerTreeNodeListener implements TreeSelectionListener {

    private JTree tree;

    private static CacheUtil cacheUtil = CacheUtil.getInstance();

    public ServerTreeNodeListener(JTree tree) {
        this.tree = tree;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (Objects.isNull(selectedNode)) {
            return;
        }

        if (Objects.isNull(selectedNode.getParent())) {
            return;
        }

        String nodePath = TreeNodePathUtil.getNodePath(selectedNode);
        cacheUtil.set(CacheConstants.getServerPath(), nodePath);
        final Connection connection = (Connection) cacheUtil.get(CacheConstants.getConnection(((ConnectionInfo) cacheUtil.get(CacheConstants.getConnectionInfo())).getIp()));
        if (Objects.isNull(connection)) {
            return;
        }

        new ChildrenDirectory(selectedNode).addServerTreeNode(new SendCommandManager(connection).getSendCommandResult(CommandConstants.getCdPath(nodePath)));
    }

}
