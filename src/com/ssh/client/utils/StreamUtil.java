package com.ssh.client.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtil {

    private static Logger log = LogManager.getLogger();

    final static int BUFFER_SIZE = 1024;

    public static String InputStreamTOString(InputStream in) {

        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
            byte[] data = new byte[BUFFER_SIZE];
            int count;
            while ((count = in.read(data, 0, BUFFER_SIZE)) != -1) {
                outStream.write(data, 0, count);
            }
            return new String(outStream.toByteArray(), "ISO-8859-1");
        } catch (Exception e) {
            log.error("StreamUtil->e:{}", e);
            return null;
        }
    }

}
