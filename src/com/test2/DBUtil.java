package com.test2;


import java.sql.*;

public class DBUtil {
    //数据库驱动
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //路径端口
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    //账号密码
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    /**
     * 获取连接
     **/
    public static Connection getconnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            //获取连接
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /***
     * 关闭连接
     * */
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

