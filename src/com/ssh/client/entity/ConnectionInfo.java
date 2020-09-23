package com.ssh.client.entity;


/**
 * @className: ServerBaseInfo
 * @package: ssh.entity
 * @describe: 连接信息
 * @author:（willdas）
 * @date: 2020/09/21 11:21
 **/
public class ConnectionInfo {

    private String ip;

    private Integer port;

    private String userName;

    private String password;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static ConnectionInfo build(String ip, int port, String userName, String password) {
        ConnectionInfo connectionInfo = new ConnectionInfo();
        connectionInfo.setIp(ip);
        connectionInfo.setPort(port);
        connectionInfo.setUserName(userName);
        connectionInfo.setPassword(password);
        return connectionInfo;
    }
}
