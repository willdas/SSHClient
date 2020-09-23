package com.ssh.client.tree;


import com.ssh.client.cache.CacheUtil;
import com.ssh.client.constant.CacheConstants;
import com.ssh.client.listener.LocalTreeNodeListener;
import com.ssh.client.listener.ServerTreeNodeListener;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * @className: RootDirectory
 * @package: com.ssh.client.tree
 * @describe: 根目录树
 * @author:（willdas）
 * @date: 2020/09/22 10:14
 **/
public class RootDirectory {

    private JTree tree;

    private DefaultTreeModel treeModel;

    private DefaultMutableTreeNode rootNode;

    private DefaultMutableTreeNode childrenNode;

    private String rootPath;

    public RootDirectory(DefaultTreeModel treeModel) {
        this.treeModel = treeModel;
        this.rootNode = (DefaultMutableTreeNode) treeModel.getRoot();
    }

    public RootDirectory(DefaultMutableTreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public RootDirectory(DefaultMutableTreeNode rootNode, String rootPath) {
        this.rootNode = rootNode;
        this.rootPath = rootPath;
    }

    public JTree getDirectoryTree(Boolean local) {
        tree = new JTree(rootNode);
        tree.setShowsRootHandles(true);
        tree.setEditable(false);
        tree.setCellRenderer(new TreeRenderer());
        if (local) {
            File[] files = new File(this.rootPath).listFiles();
            this.addLocalTreeNode(files);
            tree.addTreeSelectionListener(new LocalTreeNodeListener(tree));
        } else {
            tree.addTreeSelectionListener(new ServerTreeNodeListener(tree));
            CacheUtil.getInstance().set(CacheConstants.getServerTree(), tree);
        }
        return tree;
    }

    public void addLocalTreeNode(File[] files) {
        if (Objects.isNull(files)) {
            return;
        }

        for (int i = 0; i < files.length; i++) {
            if (!files[i].isHidden()) {
                this.addChildrenNode(files[i].getName());
            }
        }
    }

    public void addServerTreeNode(List<String> stringList) {
        if (Objects.isNull(stringList)) {
            return;
        }

        stringList.forEach(s -> this.addChildrenNode(s));
        if (Objects.nonNull(treeModel)) {
            treeModel.reload();
        }
    }

    public void addChildrenNode(String nodeName) {
        childrenNode = new DefaultMutableTreeNode(nodeName);
        rootNode.add(childrenNode);
    }

    public void removeChildrenNode(DefaultMutableTreeNode treeNode) {
        TreeNode parent = treeNode.getParent();
        if (Objects.nonNull(parent)) {
            treeModel.removeNodeFromParent(childrenNode);
        }
    }

    public void removeAllChildrenNode() {
        rootNode = (DefaultMutableTreeNode) treeModel.getRoot();
        rootNode.removeAllChildren();
        treeModel.reload();
    }

}
