package com.ssh.client.constant;

public class CacheConstants {

    private static String CONNECTION_INFO = "server_info";

    private static String CONNECTION = "server";

    private static String SERVER_TREE = "server_tree";

    private static String SERVER_PATH = "server_path";

    private static String LOCAL_PATH = "local_path";

    public static String getConnectionInfo() {
        return CONNECTION_INFO;
    }

    public static String getConnection(String ip) {
        return String.format("%s_%s", CONNECTION, ip);
    }

    public static String getServerTree() {
        return SERVER_TREE;
    }

    public static String getServerPath() {
        return SERVER_PATH;
    }

    public static String getLocalPath() {
        return LOCAL_PATH;
    }
}
