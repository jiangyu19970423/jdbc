package com.jdbctemplate;


import com.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserDao {
    private JdbcTemplate template = new JdbcTemplate(JdbcTempLateUtil.getDataSource());


    public static void main(String[] args) {
//        new UserDao().listUser().stream().forEach(n-> {System.out.println(n);});
//        User user = new UserDao().selectById(3);
//        System.out.println(user);
//        new UserDao().listUser2("夜", 25).stream().forEach(n -> {
//            System.out.println(n);
//        });
        User user = new User();
        user.setName("江流儿");
        user.setAge(15);
        user.setSex("1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format = sdf.format(date);
        user.setCreateTime(format);
        new UserDao().addUser(user);
    }

    //查询全部用query
    public List<User> listUser() {
        String sql = "select * from test";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    //查询单个对象数据
    public User selectById(Integer id) {
        String sql = "select * from test where id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    //根据条件查询
    public List<User> listUser2(String name, Integer age) {
        String sql = "select * from test where name like ? and age>?";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + name + "%", age);
    }


    //添加用户
    public void addUser(User user) {
        String sql = "insert into test(name,age,sex,create_time) values(?,?,?,?)";
        template.update(sql, user.getName(), user.getAge(), user.getSex(), user.getCreateTime());
    }
}
