package com.jdb.util;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/***
 * properties文件内容读取
 */
public class PropertiesUtil {
    //ResourceBundle是一个抽象类
    private static ResourceBundle bundle=ResourceBundle.getBundle("jdbc");

    private static ResourceBundle resourceBundle=PropertyResourceBundle.getBundle("jdbc");

    /**
     * 定义一个获取jdbc.properties文件的方法
     */
    public static String getValue(String key){
        return bundle.getString(key);
//        resourceBundle.getString(key);
    }

}
