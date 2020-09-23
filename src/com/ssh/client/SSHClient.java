package com.ssh.client;

import javax.swing.SwingUtilities;

/**
 * @className: SSHClient
 * @package: com.ssh.client
 * @describe: 创建并显示GUI。出于线程安全的考虑， 这个方法在事件调用线程中调用。
 * @author:（willdas）
 * @date: 2020/09/22 10:28
 **/
public class SSHClient {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DrawGui().createAndShowGUI());
    }
}


