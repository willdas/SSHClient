package com.ssh.client.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class ChildrenDirectory extends RootDirectory {

    public ChildrenDirectory(DefaultTreeModel treeModel) {
        super(treeModel);
    }

    public ChildrenDirectory(DefaultMutableTreeNode rootNode) {
        super(rootNode);
    }

    public ChildrenDirectory(DefaultMutableTreeNode rootNode, String rootPath) {
        super(rootNode, rootPath);
    }
}
