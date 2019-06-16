package com.example.jjwttest.content.utils;

import java.io.FileReader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * @author xuekangle
 * @date 2019-05-22 23:08
 */
public class PropertiesUtil {
    public static String CHARSET;
    public static String DBURL;
    public static String DBUSERNAME;
    public static String DBPASSWORD;

    static{
        try{
            Properties p = new Properties();
            URL url = PropertiesUtil.class.getProtectionDomain().getCodeSource().getLocation();
            String filePath = URLDecoder.decode(url.getPath(),"utf-8");
            //本地测试路径
            String path = filePath.substring(filePath.indexOf(":") + 1, filePath.indexOf("/target"));
            //远程部署
            //String path = filePath.substring(filePath.indexOf(":") + 1, filePath.indexOf("/lib"));
            //load配置文件
            p.load(new FileReader(path + "/conf/config.properties"));

            CHARSET = p.getProperty("CHARSET");
            DBURL = p.getProperty("DBURL");
            DBUSERNAME = p.getProperty("DBUSERNAME");
            DBPASSWORD = p.getProperty("DBPASSWORD");


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
