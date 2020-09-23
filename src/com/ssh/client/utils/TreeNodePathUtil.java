package com.ssh.client.utils;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.io.File;
import java.util.Objects;

/**
 * @className: TreeNodePathUtil
 * @package: com.ssh.client.utils
 * @describe: 获取树节点全路径
 * @author:（willdas）
 * @date: 2020/09/23 10:49
 **/
public class TreeNodePathUtil {

    public static String getNodePath(DefaultMutableTreeNode selectedNode) {
        TreeNode[] treeNodes = selectedNode.getPath();
        if (Objects.isNull(treeNodes)) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < treeNodes.length; i++) {
            stringBuilder.append(treeNodes[i].toString());
            if (!treeNodes[i].toString().equals("/") && i < treeNodes.length - 1) {
                stringBuilder.append(File.separator);
            }
        }
        return stringBuilder.toString();
    }

}
