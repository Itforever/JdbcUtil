package com.jdb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBConnection {
    //创建一个本地线程，共享链接对象
    private static final ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>();//可以规避线程安全的问题
    private static String dirverName=PropertiesUtil.getValue("jdbc.driver");
    private static String jdbcUrl=PropertiesUtil.getValue("jdbc.url");
    private static String userName=PropertiesUtil.getValue("jdbc.username");
    private static String password=PropertiesUtil.getValue("jdbc.password");
    static{
        try {
            Class.forName(dirverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /***
     * 定义获取数据库链接的方法
     * @return
     */
    public static Connection getConn(){
            Connection conn=threadLocal.get();
            if(null == conn){//如果对象不存在，则创建对象
                try {
                    conn=DriverManager.getConnection(jdbcUrl,userName,password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                threadLocal.set(conn);
            }
            return conn;
    }

    /***
     * 关闭链接
     */
    public static void closeConn(){
        //从本地线程中获取链接对象
        Connection conn=threadLocal.get();
        try {
            if(null != conn){
                if(!conn.isClosed()){//如果没有关闭，则关闭此连接
                    conn.close();
                    threadLocal.remove();
                    conn=null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
