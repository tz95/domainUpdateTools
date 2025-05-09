package org.eu.tz95.tools.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * @author tz95
 * @project dnspod-update
 * @date 2025/5/9
 */
public class ResourceUtil {

    private static File resources;

    public static Properties readProperties(String fileName) {
        if (fileName.endsWith(".properties")) {
            Properties prop = new Properties();
            InputStream is = null;
            if (haveResource()){
                // 如果resources文件夹存在，则从该文件夹读取配置文件
                File file = new File(resources, fileName);
                try{
                    is = new FileInputStream(file);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                try{
                    is = ResourceUtil.class.getResourceAsStream("/"+fileName);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    prop.load(is);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("File not found: " + fileName);
            }
            return prop;
        }else if (fileName.endsWith(".json")) {
            // 暂不支持读取json文件
            System.out.println("Json file is not supported yet");
        }
        return null;
    }

    public static boolean haveResource() {
        String path = null;
        try {
            path = ResourceUtil.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();

            // 获取jar包的路径
            File jarFile = new File(path);

            // 获取jar包的父目录
            File parentFile = jarFile.getParentFile();

            String targetFolderName = "resources";
            resources = new File(parentFile, targetFolderName);

            // 检查目标文件夹是否存在
            if (resources.exists() && resources.isDirectory()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
