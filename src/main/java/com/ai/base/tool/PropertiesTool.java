package com.ai.base.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

/**
 * 获取扩展配置文件信息
 *
 * @author hejg
 */
public class PropertiesTool {
    private static PropertiesTool instance = new PropertiesTool();
    private static Map<String, String> entryMap = new HashMap<String, String>();

    static {
        InputStream stream = PropertiesTool.class.getResourceAsStream("/extend.properties");
        Properties p = new Properties();
        try {
            p.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Entry<Object, Object> entry : p.entrySet()) {
            entryMap.put((String) entry.getKey(), (String) entry.getValue());
        }
    }

    private PropertiesTool() {
    }

    public static PropertiesTool getInstance() {
        return instance;
    }

    public String get(String key) {
        return entryMap.get(key);
    }

    public String getFtpPath() {
        return entryMap.get("ftp.basepath");
    }
}
