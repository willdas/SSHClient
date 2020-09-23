package com.ssh.client.constant;

public class CommandConstants {

    private final static String CD_AND_LS = "cd .. && ls";

    private final static String CD_PATH = "cd %s && ls";

    public static String getCdLs() {
        return CD_AND_LS;
    }

    public static String getCdPath(String path) {
        return String.format(CD_PATH, path);
    }
}
