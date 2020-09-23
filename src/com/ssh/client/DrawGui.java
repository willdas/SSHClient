package com.ssh.client;


import com.ssh.client.constant.ButtonConstants;
import com.ssh.client.listener.ButtonEventListener;
import com.ssh.client.listener.WindowEventListener;
import com.ssh.client.tree.ChildrenDirectory;
import com.ssh.client.tree.RootDirectory;
import com.ssh.client.utils.ListUtil;

import java.util.List;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionListener;

/**
 * @className: DrawGui
 * @package: com.ssh.client
 * @describe: 构建界面
 * @author:（willdas）
 * @date: 2020/09/22 10:27
 **/
public class DrawGui extends JFrame {

    private final int y = 20;
    private final int height = 35;
    private final int width = 100;
    private final int textFieldWidth = 150;
    private final int scrollPaneY = 100;
    private final int scrollPaneWidth = 500;
    private final int scrollPaneHeight = 450;

    private final JPanel panel = new JPanel();
    private JTextField ipTextField;
    private JTextField portTextField;
    private JTextField userNameJTextField;
    private JPasswordField passwordField;
    private JLabel tipLabel;
    private JLabel infoLabel;
    private ButtonEventListener buttonEventListener;
    private static RootDirectory localDirectory = new ChildrenDirectory(new DefaultMutableTreeNode(System.getProperty("user.home")), System.getProperty("user.home"));
    private static RootDirectory serverDirectory = new ChildrenDirectory(new DefaultMutableTreeNode("/"), "/");

    {
        this.setTitle("SSHClient");
        this.setBounds(100, 100, 1200, 650);
        this.setResizable(false);
        this.add(panel);
        this.setVisible(true);
        this.addWindowListener(new WindowEventListener());
        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    public void createAndShowGUI() {
        this.placeComponents(panel);
    }

    /**
     * @methodName: placeComponents
     * @param: [panel]
     * @desc: 添加组件到面板
     * @author:（willdas）
     * @date: 2020/09/22 19:35
     **/
    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        this.ipTextField = this.getJTextField(this.getX(0, 15, 1), y, textFieldWidth, height);
        ipTextField.setText("45.207.49.71");
        this.portTextField = this.getJTextField(this.getX(ipTextField.getX(), ipTextField.getWidth(), 2), y, textFieldWidth, height);
        portTextField.setText("22");
        this.userNameJTextField = this.getJTextField(this.getX(portTextField.getX(), portTextField.getWidth(), 2), y, textFieldWidth, height);
        userNameJTextField.setText("root");
        this.passwordField = this.getJPasswordField(this.getX(userNameJTextField.getX(), userNameJTextField.getWidth(), 2), y, textFieldWidth, height);
        passwordField.setText("n5C4aPvp1IEZ");
        this.tipLabel = this.getJLabel("未连接", this.getX(passwordField.getX(), passwordField.getWidth(), 7), y, textFieldWidth, height);
        this.infoLabel = this.getJLabel("", this.getX(passwordField.getX(), passwordField.getWidth(), 7), scrollPaneY + scrollPaneHeight + 10, textFieldWidth, height);
        this.buttonEventListener = new ButtonEventListener(ipTextField, portTextField, userNameJTextField, passwordField, tipLabel, infoLabel);
        List<JComponent> jComponents = ListUtil.newArrayList(
                this.getJLabel(ButtonConstants.getIp(), 30, y, width, height),
                this.ipTextField,
                this.getJLabel(ButtonConstants.getPort(), this.getX(ipTextField.getX(), ipTextField.getWidth(), 1), y, width, height),
                this.portTextField,
                this.getJLabel(ButtonConstants.getUser(), this.getX(portTextField.getX(), portTextField.getWidth(), 1), y, width, height),
                this.userNameJTextField,
                this.getJLabel(ButtonConstants.getPassword(), this.getX(userNameJTextField.getX(), userNameJTextField.getWidth(), 1), y, width, height),
                this.passwordField,
                this.getJButton(ButtonConstants.getConnection(), this.getX(passwordField.getX(), passwordField.getWidth(), 1), y, width, height, buttonEventListener),
                this.getJButton(ButtonConstants.getClose(), this.getX(passwordField.getX(), passwordField.getWidth(), 4), y, width, height, buttonEventListener),
                this.tipLabel,
                this.infoLabel,
                this.getJScrollPane(30, scrollPaneY, scrollPaneWidth, scrollPaneHeight, serverDirectory.getDirectoryTree(false)),
                this.getJScrollPane(600, scrollPaneY, scrollPaneWidth, scrollPaneHeight, localDirectory.getDirectoryTree(true)),
                this.getJButton(ButtonConstants.getUpload(), this.getX(passwordField.getX(), passwordField.getWidth(), 1), scrollPaneY + scrollPaneHeight + 10, width, height, buttonEventListener),
                this.getJButton(ButtonConstants.getDownload(), this.getX(passwordField.getX(), passwordField.getWidth(), 4), scrollPaneY + scrollPaneHeight + 10, width, height, buttonEventListener)
        );
        this.addJComponent(jComponents);
    }

    private int getX(int x, int width, int num) {
        x = x + width + num * 35;
        return x;
    }

    public JLabel getJLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }

    public JTextField getJTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField(20);
        textField.setBounds(x, y, width, height);
        return textField;
    }

    private JPasswordField getJPasswordField(int x, int y, int width, int height) {
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(x, y, width, height);
        return passwordText;
    }

    private JButton getJButton(String text, int x, int y, int width, int height, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(actionListener);
        return button;
    }

    private JScrollPane getJScrollPane(int x, int y, int width, int height, JTree tree) {
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setBounds(x, y, width, height);
        return scrollPane;
    }

    private void addJComponent(List<JComponent> jComponents) {
        jComponents.forEach(c -> {
            panel.add(c);
        });
    }

}
