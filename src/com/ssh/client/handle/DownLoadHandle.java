package com.ssh.client.handle;

import com.ssh.client.cache.CacheUtil;
import com.ssh.client.constant.CacheConstants;
import com.ssh.client.constant.TipsConstants;
import com.ssh.client.entity.ConnectionInfo;
import com.ssh.client.manager.SCPClientManager;
import com.ssh.client.utils.StringUtils;
import com.trilead.ssh2.Connection;
import com.trilead.ssh2.SCPClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * @className: DownLoadHandle
 * @package: com.ssh.client.handle
 * @describe: 下载
 * @author:（willdas）
 * @date: 2020/09/22 11:04
 **/
public class DownLoadHandle {

    private static Logger log = LogManager.getLogger();

    private static CacheUtil cacheUtil = CacheUtil.getInstance();

    public static void downLoad(JLabel infoLabel) {
        final String serverPath = (String) cacheUtil.get(CacheConstants.getServerPath());
        final String localPath = (String) cacheUtil.get(CacheConstants.getLocalPath());

        if (StringUtils.isEmpty(localPath) || localPath.contains(".")) {
            infoLabel.setText(TipsConstants.getDirError());
            infoLabel.setForeground(Color.red);
            return;
        }
        if (StringUtils.isEmpty(serverPath) || !serverPath.contains(".")) {
            infoLabel.setText(TipsConstants.getFileError());
            infoLabel.setForeground(Color.red);
            return;
        }

        try {
            final SCPClient scpClient = SCPClientManager.getSCPClient();
            scpClient.get(serverPath, localPath);
            infoLabel.setText(TipsConstants.getDownloadFinish());
            infoLabel.setForeground(Color.green);
        } catch (Exception e) {
            log.error("下载失败->{}", e.toString());
            infoLabel.setText(TipsConstants.getConnectionError());
            infoLabel.setForeground(Color.red);
        }
    }
}
