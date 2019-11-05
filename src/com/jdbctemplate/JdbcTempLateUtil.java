package com.jdbctemplate;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcTempLateUtil {
    //声明数据资源
    private static DataSource dataSource;

    //初始化datasource
    static {
        Properties pro = new Properties();
        try {
            InputStream in = JdbcTempLateUtil.class.getResourceAsStream("/jdbctemplate.properties");
            pro.load(in);
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取数据源
    public static DataSource getDataSource() {
        return dataSource;
    }

    //获取连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    //关闭连接
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}



