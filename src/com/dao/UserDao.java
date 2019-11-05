package com.dao;

import com.entity.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao {
    //数据库驱动
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //路径端口
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    //账号密码
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
//        List<User> userList = findAll();
//        userList.stream().forEach(n -> {
//            System.out.println(n);
//        });
//        List<User> list = findAll("沈");
//        list.stream().forEach(n -> {
//            System.out.println(n);
//        });
//        deleteById(Long.valueOf(2));
//
        User user = new User();
        user.setName("薛亚东");
        user.setAge(22);
        user.setSex("1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        user.setCreateTime(format);
        addUser(user);
    }

    //查询方法
    public static List<User> findAll(String cName) {
        List<User> list = new ArrayList<>();
        String sql = "select * from test where name like ?";
        //注册驱动
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(DRIVER);
            //获取连接
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //实例化
            ps = conn.prepareStatement(sql);
            ps.setObject(1, "%" + cName + "%");
            //结果集
            rs = ps.executeQuery();

            //遍历结果集
            User user = null;
            while (rs.next()) {
                //把结果集中的数据放入list集合
                user = new User();
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Integer age = rs.getInt("age");
                String sex = rs.getString("sex");
                String createTime = rs.getString("create_time");
                String delFlag = rs.getString("del_flag");
                user.setId(Long.valueOf(id));
                user.setName(name);
                user.setAge(age);
                user.setSex(sex);
                user.setCreateTime(createTime);
                user.setDelFlag(delFlag);
                list.add(user);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    //删除数据
    public static void deleteById(Long id) {
        String sql = "delete from test where id=?";
        int count;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    //增加数据
    public static void addUser(User user) {
        String sql = "insert test(name,age,sex,create_time) values(?,?,?,?)";
        int count;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement(sql);
            ps.setObject(1, user.getName());
            ps.setObject(2, user.getAge());
            ps.setObject(3, user.getSex());
            ps.setObject(4, user.getCreateTime());

            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

}




