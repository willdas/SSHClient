package com.ssh.client.constant;

public class TipsConstants {

    private static String CONNECTION_SUCCESS = "连接成功";

    private static String CONNECTION_FAIL = "连接失败";

    private static String CLOSE_FINISH = "关闭完成";

    private static String UPLOAD_FINISH = "上传完成";

    private static String DOWNLOAD_FINISH = "下载完成";

    private static String DIR_ERROR = "目录选择错误";

    private static String FILE_ERROR = "文件选择错误";

    private static String CONNECTION_ERROR = "连接出现错误";

    public static String getConnectionSuccess () {
        return CONNECTION_SUCCESS;
    }

    public static String getCloseFinish() {
        return CLOSE_FINISH;
    }

    public static String getConnectionFail() {
        return CONNECTION_FAIL;
    }

    public static String getUploadFinish() {
        return UPLOAD_FINISH;
    }

    public static String getDownloadFinish() {
        return DOWNLOAD_FINISH;
    }

    public static String getDirError() {
        return DIR_ERROR;
    }

    public static String getFileError() {
        return FILE_ERROR;
    }

    public static String getConnectionError() {
        return CONNECTION_ERROR;
    }
}

