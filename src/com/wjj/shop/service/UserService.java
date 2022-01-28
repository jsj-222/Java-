package com.wjj.shop.service;

import com.wjj.shop.entity.User;

import java.sql.SQLException;

public interface UserService {
    boolean checkUser(String username);

    int register(User user);
}
