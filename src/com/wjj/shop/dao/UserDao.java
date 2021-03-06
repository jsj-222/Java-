package com.wjj.shop.dao;

import com.wjj.shop.entity.User;

import java.sql.SQLException;

public interface UserDao {
     User getUserByName(String username) throws SQLException;

     int insertUser(User user) throws SQLException;

    User getUserByCode(String code) throws SQLException;

    int updateStateById(Integer id) throws SQLException;
}
