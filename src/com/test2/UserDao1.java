package com.test2;

import com.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao1 {

    public static void main(String[] args) {
        List<User> list = findAll();
        list.stream().forEach(n -> System.out.println(n));
    }

    public static List<User> findAll() {
        List<User> list = new ArrayList<>();
        Connection conn = DBUtil.getconnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from test";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setId(Long.valueOf(rs.getInt("id")));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setSex(rs.getString("sex"));
                user.setCreateTime(rs.getString("create_time"));
                user.setDelFlag(rs.getString("del_flag"));
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
