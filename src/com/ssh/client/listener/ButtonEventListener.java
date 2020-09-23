package com.ssh.client.listener;


import com.ssh.client.constant.ButtonConstants;
import com.ssh.client.entity.ConnectionInfo;
import com.ssh.client.handle.ConnectionHandle;
import com.ssh.client.handle.DownLoadHandle;
import com.ssh.client.handle.CloseHandle;
import com.ssh.client.handle.UpLoadHandle;
import com.trilead.ssh2.Connection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @className: ButtonEventListener
 * @package: ssh
 * @describe: 鼠标点击事件监听器
 * @author:（willdas）
 * @date: 2020/09/19 22:34
 **/
public class ButtonEventListener implements ActionListener {

    private JTextField ipTextField;
    private JTextField portTextField;
    private JTextField userNameJTextField;
    private JPasswordField passwordField;
    private JLabel tipLabel;
    private JLabel infoLabel;
    private Connection connection;

    public ButtonEventListener(JTextField ipTextField, JTextField portTextField, JTextField userNameJTextField, JPasswordField passwordField, JLabel tipLabel, JLabel infoLabel) {
        this.ipTextField = ipTextField;
        this.portTextField = portTextField;
        this.userNameJTextField = userNameJTextField;
        this.passwordField = passwordField;
        this.tipLabel = tipLabel;
        this.infoLabel = infoLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ButtonConstants.getConnection().equals(e.getActionCommand())) {
            ConnectionInfo connectionInfo = ConnectionInfo.build(
                    this.ipTextField.getText().trim(), Integer.valueOf(this.portTextField.getText().trim()),
                    this.userNameJTextField.getText().trim(), new String(this.passwordField.getPassword()).trim());
            this.connection = new ConnectionHandle(connectionInfo, this.tipLabel).getConnection();
        } else if (ButtonConstants.getClose().equals(e.getActionCommand())) {
            CloseHandle.close(this.connection, tipLabel);
        } else if (ButtonConstants.getUpload().contains(e.getActionCommand())) {
            UpLoadHandle.uploadFile(infoLabel);
        } else if (ButtonConstants.getDownload().contains(e.getActionCommand())) {
            DownLoadHandle.downLoad(infoLabel);
        }
    }

}
