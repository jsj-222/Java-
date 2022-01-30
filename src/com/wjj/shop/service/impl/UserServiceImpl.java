package com.wjj.shop.service.impl;

import com.wjj.shop.dao.UserDao;
import com.wjj.shop.dao.impl.UserDaoImpl;
import com.wjj.shop.entity.User;
import com.wjj.shop.service.UserService;
import com.wjj.shop.util.Constants;
import com.wjj.shop.util.MailUtils;

import java.sql.SQLException;


public class UserServiceImpl implements UserService {

    @Override
    public boolean checkUser(String username){
        //如何根据用户名查询对应的用户---数据库
//        UserDao userDao = new UserDaoImpl();
//        //根据用户名查询用户
//        User user= null;
//        try {
//            user = userDao.getUserByname(username);
//            return user!=null ;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }

        UserDao userDao = new UserDaoImpl();

        //根据用户名查询用户
        try {
            User user = userDao.getUserByName(username);
            return user != null;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public int register(User user) {
        //添加用户
        UserDao userDao = new UserDaoImpl();
        try {
            //FIXME :可能有错误还没改

            int result = userDao.insertUser(user);
            if(result>0){
                //TODO 后来要做的事情还没有做 ：发送注册邮件
                MailUtils.sendMail(user);
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * @param code
     * @return
     */
    @Override
    public int activeUser(String code) {
        UserDao userDao = new UserDaoImpl();
        try {
            User user = userDao.getUserByCode(code);
            //校验用户的状态
            if(user==null){
                return Constants.ACTIVE_FAIL;
            }
            //为什么不用==
            if(user.getState().equals(Constants.USER_ACTIVE)){
                return Constants .ACTIVE_ALREADY;
            }
            //更改用户的状态
            int result = userDao.updateStateById(user.getId());
            if(result>0){
                return Constants.ACTIVE_SUCCESS;
            }
            return Constants.ACTIVE_FAIL;
        } catch (SQLException e) {
            e.printStackTrace();
            return Constants.ACTIVE_FAIL;
        }
    }


}
