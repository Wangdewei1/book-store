package com.itwang.book.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource dataSource;

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    /**
     * 初始化
     */
    static {
        try {
            Properties properties = new Properties();

            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");

            properties.load(inputStream);

            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection(){
        Connection connection = threadLocal.get();

        try {
            if (null == connection){
                connection = dataSource.getConnection();
            }
            threadLocal.set(connection);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 释放资源
     */
    public static void releaseDataSource(Connection connection){
        try {
            if (null != connection){
                connection.close();
                threadLocal.remove();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}