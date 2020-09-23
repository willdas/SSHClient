package com.ssh.client.manager;

import com.ssh.client.utils.StreamUtil;
import com.ssh.client.utils.StringUtils;
import com.trilead.ssh2.Connection;
import com.trilead.ssh2.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @className: SendCommandUtil
 * @package: ssh.utils
 * @describe: 发送指令类
 * @author:（willdas）
 * @date: 2020/09/21 14:57
 **/
public class SendCommandManager {

    private Logger log = LogManager.getLogger();

    private Connection connection;

    private Session session;

    public SendCommandManager(Connection connection) {
        this.connection = connection;
    }

    public List<String> getSendCommandResult(String command) {
        try {
            session = this.connection.openSession();
            session.execCommand(command);
            String string = StreamUtil.InputStreamTOString(session.getStdout());
            if (StringUtils.isNotEmpty(string)) {
                //log.info("CommandResult:{}", Arrays.asList(string.split("\n")));
                return Arrays.asList(string.split("\n"));
            }
        } catch (Exception e) {
            log.error("SendCommandUtil->e:{}", e);
        } finally {
            if (Objects.nonNull(this.session)) {
                this.session.close();
            }
        }
        return null;
    }

}
