package com.wjj.shop.dao.impl;

import com.wjj.shop.dao.UserDao;
import com.wjj.shop.entity.User;
import com.wjj.shop.util.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User getUserByName(String username) throws SQLException {
        QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());

        String sql="select id as id,u_name as username,u_password as password,u_sex as sex," +
                " u_email as email,u_state as state,u_code as code,u_role as role " +
                "from shop_user where u_name = ?";


        return runner.query(sql,new BeanHandler<>(User.class),username);




    }



    @Override
    public int insertUser(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql="insert into shop_user(u_name,u_password,u_email,u_sex,u_role,u_code,u_state) values(?,?,?,?,?,?,?)";

        return queryRunner.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getSex(),user.getRole(),user.getCode(),user.getState());
    }
}
