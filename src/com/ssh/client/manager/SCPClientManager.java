package com.ssh.client.manager;

import com.ssh.client.cache.CacheUtil;
import com.ssh.client.constant.CacheConstants;
import com.ssh.client.entity.ConnectionInfo;
import com.trilead.ssh2.Connection;
import com.trilead.ssh2.SCPClient;
import java.util.Objects;

/**
 * @className: SCPClientManager
 * @package: com.ssh.client.manager
 * @describe: SCP文件处理类
 * @author:（wangzewen）
 * @date: 2020/09/23 10:47
 **/
public class SCPClientManager {

    private static CacheUtil cacheUtil = CacheUtil.getInstance();

    public static SCPClient getSCPClient() {
        try {
            final Connection connection = (Connection) cacheUtil.get(CacheConstants.getConnection(((ConnectionInfo) cacheUtil.get(CacheConstants.getConnectionInfo())).getIp()));
            if (Objects.isNull(connection)) {
                return null;
            }

            SCPClient scpClient = connection.createSCPClient();
            return scpClient;
        } catch (Exception e) {
            return null;
        }
    }

}
