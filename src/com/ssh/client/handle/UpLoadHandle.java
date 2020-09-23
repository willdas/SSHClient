package com.ssh.client.handle;

import com.ssh.client.cache.CacheUtil;
import com.ssh.client.constant.CacheConstants;
import com.ssh.client.constant.TipsConstants;
import com.ssh.client.manager.SCPClientManager;
import com.ssh.client.utils.StringUtils;
import com.trilead.ssh2.SCPClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * @className: UpLoadHandle
 * @package: com.ssh.client.handle
 * @describe: 上传处理
 * @author:（willdas）
 * @date: 2020/09/22 11:35
 **/
public class UpLoadHandle {

    private static Logger log = LogManager.getLogger();

    private static CacheUtil cacheUtil = CacheUtil.getInstance();

    public static void uploadFile(JLabel infoLabel) {
        final String serverPath = (String) cacheUtil.get(CacheConstants.getServerPath());
        final String localPath = (String) cacheUtil.get(CacheConstants.getLocalPath());

        if (StringUtils.isEmpty(localPath) || !localPath.contains(".")) {
            infoLabel.setText(TipsConstants.getFileError());
            infoLabel.setForeground(Color.red);
            return;
        }
        if (StringUtils.isEmpty(serverPath) || serverPath.contains(".")) {
            infoLabel.setText(TipsConstants.getDirError());
            infoLabel.setForeground(Color.red);
            return;
        }

        try {
            final SCPClient scpClient = SCPClientManager.getSCPClient();
            scpClient.put(localPath, serverPath);
            infoLabel.setText(TipsConstants.getUploadFinish());
            infoLabel.setForeground(Color.green);
        } catch (Exception e) {
            log.error("上传失败->{}", e);
            infoLabel.setText(TipsConstants.getConnectionError());
            infoLabel.setForeground(Color.red);
        }
    }
}
