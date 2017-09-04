package com.ai.base.tool;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * File工具类
 * @author hejg
 *
 */
public class FileTool {
	private static String hostname = PropertiesTool.getInstance().get("app.basepath");
	
	/**
     * 从项目资源读取文档文件
     * @param filePath 文件目录
     * @param fileName 文件名称
     * @return
     */
    public static String readResourceFile(String filePath, String fileName) {
    	StringBuffer sb = new StringBuffer();
    	try {
    		char[] ch = new char[1024];
    		InputStream stream = FileTool.class.getResourceAsStream(hostname+File.separator+filePath+File.separator+fileName);
    		InputStreamReader reader = new InputStreamReader(stream);
    		while(reader.read(ch) > 0){
    			sb.append(ch);
    		};
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return sb.toString();
    }
}
