# SSHClient
一个JAVA版的SSH连接客户端，目前支持连接、关闭、上传、下载功能

JDK版本号及jar包:  
1、JDK版本：1.8;  
2、第三方包：eddsa.jar、log4j2.jar、trilead-ssh2.jar  




使用说明:  
1、git clone https://github.com/Willdas/SSHClient.git;  
2、导入到IDEA中;  
3、配置out的路径;  
4、运行SSHClient.java，即可看到界面。

编译成Mac App步骤
1、制作Mac App图标
iconutil -c icns app.iconset
2、生成Mac App程序
javapackager -deploy -native image  -outdir "输出文件的目录" -outfile "输出的文件" -srcfiles "jar文件全路径" -Bicon="图标路径" -appclass "主程序路径" -name "程序名称" 
