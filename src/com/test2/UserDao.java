package com.test2;

import com.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static void main(String[] args) {
//        List<User> list = findAll();
//        list.stream().forEach(n -> {
//            System.out.println(n);
//        });
        PageBean pageBean = new PageBean();
        pageBean.setPage(1);
        listUser(pageBean).stream().forEach(n-> System.out.println(n));
    }


    public static List<User> findAll() {
        List<User> list = new ArrayList<>();
        Connection conn = DBUtil.getconnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from test";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }


    //删除数据
    public static void deleteById(Long id) {
        Connection conn = DBUtil.getconnection();
        int count;
        PreparedStatement ps = null;
        try {
            String sql = "delete from test where id=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    //查询分页方法
    public static List<User> listUser(PageBean page) {
        List<User> list = new ArrayList<>();
        Connection conn = DBUtil.getconnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from test where 1=1 limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, (page.getPage() - 1) * page.getPageSize());
            ps.setObject(2, page.getPageSize());

            rs = ps.executeQuery();
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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }
}