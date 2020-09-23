package com.ssh.client.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @className: WindowEventListener
 * @package: com.ssh.client.listener
 * @describe: 窗口事件监听器
 * @author:（willdas）
 * @date: 2020/09/23 14:02
 **/
public class WindowEventListener extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

}


