package com.ssh.client.manager;

import com.ssh.client.entity.ConnectionInfo;
import com.trilead.ssh2.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @className: ConnectionUtil
 * @package: com.ssh.client.connection
 * @describe: 连接处理类
 * @author:（willdas）
 * @date: 2020/09/22 10:08
 **/
public class ConnectionManager {

    private static Logger log = LogManager.getLogger();

    private final static int MAX_COUNT = 3;

    public static Connection connection(ConnectionInfo serverBaseInfo) {
        Connection connection = new Connection(serverBaseInfo.getIp(), serverBaseInfo.getPort());
        int count = 1;
        while (count <= MAX_COUNT) {
            try {
                connection.connect();
                connection.authenticateWithPassword(serverBaseInfo.getUserName(), serverBaseInfo.getPassword());
                return connection;
            } catch (Exception e) {
                log.error("连接失败->e:" + e);
                log.error(String.format("重试第%d次", count));
                count++;
            }
        }
        return null;
    }

    public static void close(Connection connection) {
        if (connection != null) {
            connection.close();
        }
    }

}
