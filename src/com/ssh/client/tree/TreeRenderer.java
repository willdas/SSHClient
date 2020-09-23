package com.ssh.client.tree;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * @className: TreeRenderer
 * @package: com.ssh.client.tree
 * @describe: 文件目录树渲染器
 * @author:（willdas）
 * @date: 2020/09/19 22:31
 **/
public class TreeRenderer extends DefaultTreeCellRenderer {

    private Logger log = LogManager.getLogger();

    public TreeRenderer() {
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        if (node.getUserObject() instanceof String) {
            String fileSuffix = ((String) node.getUserObject()).toLowerCase();
            if (fileSuffix.indexOf(".") == -1) {
                this.setIcon(createImageIcon("icons/dir.png"));
            } else if (fileSuffix.contains("xml")) {
                this.setIcon(createImageIcon("icons/xml.png"));
            } else if (fileSuffix.contains("jar")) {
                this.setIcon(createImageIcon("icons/jar.png"));
            } else if (fileSuffix.contains("class")) {
                this.setIcon(createImageIcon("icons/class.png"));
            } else if (fileSuffix.contains("properties")) {
                this.setIcon(createImageIcon("icons/properties.png"));
            } else if (fileSuffix.contains("zip") || fileSuffix.contains("rar") || fileSuffix.contains("tar")) {
                this.setIcon(createImageIcon("icons/zip.png"));
            } else if (fileSuffix.contains("doc") || fileSuffix.contains("docx") ||
                    fileSuffix.contains("ppt") || fileSuffix.contains("pptx") ||
                    fileSuffix.contains("pdf") || fileSuffix.contains("xlsx") || fileSuffix.contains("xls")) {
                this.setIcon(createImageIcon("icons/doc.png"));
            } else if (fileSuffix.contains("png") || fileSuffix.contains("jpg") ||
                    fileSuffix.contains("jpeg") || fileSuffix.contains("gif")) {
                this.setIcon(createImageIcon("icons/png.png"));
            } else {
                this.setIcon(createImageIcon("icons/file.png"));
            }
        }
        return this;
    }

    private ImageIcon createImageIcon(String path) {
        try {
            final URL url = TreeRenderer.class.getResource(String.format("/%s", path));
            return new ImageIcon(url);
        } catch (Exception e) {
            log.error("createImageIcon->e:{}", e);
            return null;
        }
    }
}
