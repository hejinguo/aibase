package com.ai.base.tool;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPCmd;
import org.apache.commons.net.ftp.FTPReply;

/**
 * FTP工具类
 *
 * @author hejg
 */
public class FTPTool {
    private static String hostname = PropertiesTool.getInstance().get("ftp.hostname");
    private static int hostport = Integer.valueOf(PropertiesTool.getInstance().get("ftp.hostport"));
    private static String username = PropertiesTool.getInstance().get("ftp.username");
    private static String password = PropertiesTool.getInstance().get("ftp.password");
    private static String basepath = PropertiesTool.getInstance().get("ftp.basepath");
    private static String encoding = PropertiesTool.getInstance().get("ftp.encoding");

    /**
     * 切换FTP服务器工作目录
     *
     * @param targetPath
     * @param ftp
     * @throws IOException
     */
    private static void createAndChangeRemoteDirectory(String targetPath, FTPClient ftp) throws IOException {
    	String sepChar = File.separator.equals("\\") ? "\\\\" : File.separator;//将配置的目录格式转换为环境支持的分隔符
        targetPath = targetPath.replaceAll("\\\\", sepChar).replaceAll("/", sepChar);
        String[] dirs = targetPath.split(sepChar);
        for (String dir : dirs) {
            if (dir != null && !"".equals(dir.trim())) {
                if (!ftp.changeWorkingDirectory(dir)) {
                    ftp.makeDirectory(dir);
                    ftp.changeWorkingDirectory(dir);
                }else{
                	ftp.changeWorkingDirectory("/");
                }
            }
        }
    }
    
    /**
     * 存放文档文件到FTP服务器
     *
     * @param filePath 文件目录
     * @param fileName 文件名称
     * @param fileContent 文件内容
     */
    public static void putFile(String filePath, String fileName, String fileContent) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(hostname, hostport);
            ftp.login(username, password);
            if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.setControlEncoding(encoding);
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                String targetPath = basepath + File.separator + filePath;
                if (!ftp.changeWorkingDirectory(targetPath)) {
                    createAndChangeRemoteDirectory(targetPath, ftp);
                }
                ByteArrayInputStream stream = new ByteArrayInputStream(fileContent.getBytes());
                ftp.storeFile(fileName, stream);
                stream.close();
                ftp.logout();
            } else {
                ftp.disconnect();
                throw new AIException("未连接到FTP,用户名或密码错误.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {

                }
            }
        }
    }

    /**
     * 存放任意文件到FTP服务器
     *
     * @param filePath 文件目录
     * @param fileName 文件名称
     * @param inputStream 文件流
     */
    public static void putFile(String filePath, String fileName, InputStream inputStream) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(hostname, hostport);
            ftp.login(username, password);
            if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.setControlEncoding(encoding);
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                String targetPath = basepath + File.separator + filePath;
                if (!ftp.changeWorkingDirectory(targetPath)) {
                    createAndChangeRemoteDirectory(targetPath, ftp);
                }
                ftp.storeFile(fileName, inputStream);
                ftp.logout();
            } else {
                ftp.disconnect();
                throw new AIException("未连接到FTP,用户名或密码错误.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {

                }
            }
        }
    }

    /**
     * 从FTP服务器读取文档文件
     * @param filePath 文件目录
     * @param fileName 文件名称
     * @return
     */
    public static String readFile(String filePath, String fileName) {
        StringBuffer buffer = new StringBuffer();
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(hostname, hostport);
            ftp.login(username, password);
            if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.setControlEncoding(encoding);
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                String targetPath = basepath + File.separator + filePath;
                if (!ftp.changeWorkingDirectory(targetPath)) {
                    createAndChangeRemoteDirectory(targetPath, ftp);
                }
                InputStream inputStream = ftp.retrieveFileStream(fileName);
                if(null == inputStream){
                	ftp.disconnect();
                    throw new AIException("未找到您要查看的相关数据文件.");
                }
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                /*
                String line = null;
                while ((line = in.readLine()) != null) {
                    buffer.append(line);
                }
                */
                char[] tt = new char[1024];
                int b = 0;
                while((b=in.read(tt)) != -1){
                	buffer.append(new String(tt,0,b));
                }
                in.close();
                ftp.logout();
            } else {
                ftp.disconnect();
                throw new AIException("未连接到FTP,用户名或密码错误.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {

                }
            }
        }
        return buffer.toString();
    }

    /**
     * 从FTP获取任意类型文件
     *
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @param outputStream 输出位置
     * @return
     */
    public static void getFile(String filePath, String fileName, OutputStream outputStream) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(hostname, hostport);
            ftp.login(username, password);
            if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.setControlEncoding(encoding);
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                String targetPath = basepath + File.separator + filePath;
                if (!ftp.changeWorkingDirectory(targetPath)) {
                    createAndChangeRemoteDirectory(targetPath, ftp);
                }
                ftp.retrieveFile(fileName, outputStream);
                ftp.logout();
            } else {
                ftp.disconnect();
                throw new AIException("未连接到FTP,用户名或密码错误.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {

                }
            }
        }
    }

    /**
     * 从FPT服务器删除文件
     *
     * @param filePath 文件路径
     * @param fileName 文件名称
     */
    public static void deleteFile(String filePath, String fileName) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(hostname, hostport);
            ftp.login(username, password);
            if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.setControlEncoding(encoding);
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                String targetPath = basepath + File.separator + filePath;
                if (!ftp.changeWorkingDirectory(targetPath)) {
                    createAndChangeRemoteDirectory(targetPath, ftp);
                }
                ftp.deleteFile(fileName);
                ftp.logout();
            } else {
                ftp.disconnect();
                throw new AIException("未连接到FTP,用户名或密码错误.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {

                }
            }
        }
    }
    
    /**
     * 在FTP服务器中移动文件(更改文件名称)
     * @param fromPathFileName
     * @param toPathFileName
     */
    public static void moveFile(String fromPathFileName,String toPathFileName){
    	FTPClient ftp = new FTPClient();
        try {
            ftp.connect(hostname, hostport);
            ftp.login(username, password);
            if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.setControlEncoding(encoding);
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
//                String targetPath = basepath + File.separator + (toPathFileName.substring(0, toPathFileName.indexOf(File.separator)));
//                ftp.makeDirectory(targetPath)
                ftp.deleteFile(basepath + File.separator + toPathFileName);//boolean deleteFile = 
//                ftp.rename(File.separator + basepath + File.separator + fromPathFileName, File.separator + basepath + File.separator+toPathFileName);
                ftp.sendCommand(FTPCmd.RNFR,basepath + File.separator + fromPathFileName);//int RNFR = 
                ftp.sendCommand(FTPCmd.RNTO,basepath + File.separator + toPathFileName);//int RANTO = 
                ftp.logout();
            } else {
                ftp.disconnect();
                throw new AIException("未连接到FTP,用户名或密码错误.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {

                }
            }
        }
    }
}
