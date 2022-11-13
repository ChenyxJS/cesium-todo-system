package com.chenyx.util;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by 蒋鹏程 on 2016/8/24.
 */
public class PropertiesUtil {
    //属性文件的路径
 /*   static String profilepath = PropertiesUtil.class.getClassLoader().getResource("redis.properties").getPath();//获取发布服务器配置文件
    private static Properties props = new Properties();*/
    //根据Key读取Value
    public static String GetValueByKey(String filePath, String key) {
//        System.out.println(filePath);
        filePath = URLDecoder.decode(filePath);
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            pps.load(new InputStreamReader(in,"utf-8"));
            String value = pps.getProperty(key);
           // System.out.println(key + " = " + value);
            return value;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //读取Properties的全部信息
    public static void GetAllProperties(String filePath) throws IOException {
        Properties pps = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        pps.load(in);
        Enumeration en = pps.propertyNames(); //得到配置文件的名字
        while (en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
//            System.out.println(strKey + "=" + strValue);
        }
    }

    //写入Properties信息
    public static void WriteProperties(String filePath, String pKey, String pValue) throws IOException {
        Properties pps = new Properties();
        InputStream in = new FileInputStream(filePath);
        //从输入流中读取属性列表（键和元素对）
        pps.load(in);
        //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
        //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
        OutputStream out = new FileOutputStream(filePath);
        pps.setProperty(pKey, pValue);
        //以适合使用 load 方法加载到 Properties 表中的格式，
        //将此 Properties 表中的属性列表（键和元素对）写入输出流
        pps.store(out, "Update " + pKey + " name");
    }



    public static String getFilePath(String filenName) {
        String filePath = "";
        ClassLoader cl = PropertiesUtil.class.getClassLoader();
        String url = null;
        try {
            url = cl.getResource(filenName).toURI().getPath();

        } catch (URISyntaxException e) {
            e.printStackTrace();

        }
        try {
            filePath = URLDecoder.decode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }

        return filePath;
    }
    //根据Key读取Value
    public static String getValueByKey(String filePath, String key) {
//        System.out.println(filePath);
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            pps.load(new InputStreamReader(in,"utf-8"));
            String value = pps.getProperty(key);
            // System.out.println(key + " = " + value);
            return value;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //读取Properties的全部信息
    public static void getAllProperties(String filePath) throws IOException {
        Properties pps = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        pps.load(in);
        Enumeration en = pps.propertyNames(); //得到配置文件的名字
        while (en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }
    }

    //写入Properties信息
    public static void writeProperties(String filePath, String pKey, String pValue) throws IOException {
        Properties pps = new Properties();
        InputStream in = new FileInputStream(filePath);
        //从输入流中读取属性列表（键和元素对）
        pps.load(in);
        //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
        //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
        OutputStream out = new FileOutputStream(filePath);
        pps.setProperty(pKey, pValue);
        //以适合使用 load 方法加载到 Properties 表中的格式，
        //将此 Properties 表中的属性列表（键和元素对）写入输出流
        pps.store(out, "Update " + pKey + " name");
    }
}
