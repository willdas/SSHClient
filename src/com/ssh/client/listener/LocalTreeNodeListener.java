package com.ssh.client.listener;


import com.ssh.client.cache.CacheUtil;
import com.ssh.client.constant.CacheConstants;
import com.ssh.client.tree.ChildrenDirectory;
import com.ssh.client.utils.TreeNodePathUtil;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.Objects;

/**
 * @className: LocalTreeNodeListener
 * @package: com.ssh.client.listener
 * @describe: 本地目录节点事件监听器
 * @author:（willdas）
 * @date: 2020/09/22 10:13
 **/
public class LocalTreeNodeListener implements TreeSelectionListener {

    private JTree tree;

    public LocalTreeNodeListener(JTree tree) {
        this.tree = tree;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (Objects.isNull(selectedNode)) {
            return;
        }

        String nodePath = TreeNodePathUtil.getNodePath(selectedNode);
        CacheUtil.getInstance().set(CacheConstants.getLocalPath(), nodePath);
        File[] files = new File(nodePath).listFiles();
        new ChildrenDirectory(selectedNode).addLocalTreeNode(files);
    }
}
