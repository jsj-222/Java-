package com.wjj.shop.web;

import com.wjj.shop.entity.User;
import com.wjj.shop.service.UserService;
import com.wjj.shop.service.impl.UserServiceImpl;
import com.wjj.shop.util.Constants;
import com.wjj.shop.util.MD5Util;
import com.wjj.shop.util.RandomUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user")
public class CheckController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.取出用户名
        String username = req.getParameter("username");

        //2.根据用户名,判断数据库中是否存在当前用户
        UserService userService = new UserServiceImpl();
        boolean result = userService.checkUser(username);
        PrintWriter writer = resp.getWriter();

        if(result){
            writer.print(Constants.HAS_USER);
        }else{
            writer.print(Constants.NOT_HAS_USER);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //请求乱码
        req.setCharacterEncoding("UTF-8");
        //响应乱码 resp.setContentType();
        Map<String,String[]> parameterMap=req.getParameterMap();
        for(Map.Entry<String,String[]> entry:parameterMap.entrySet()){
            System.out.println("key="+entry.getKey());
            System.out.println("value="+entry.getValue());
        }
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);//反射
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        user.setPassword(MD5Util.md5(user.getPassword()));
        user.setState(Constants.USER_NOT_ACTIVE);
        user.setRole(Constants.ROLE_CUSTOMER);
        user.setCode(RandomUtils.getActiveCode());
        UserServiceImpl userService = new UserServiceImpl();
        int result = userService.register(user);
        if(result<=0){
            //重定向地址栏会变      不可以传值    可以往外部资源跳转
            // 请求转发 不变       可以传值      内部资源
            req.setAttribute("registerMsg","注册失败");
            req.getRequestDispatcher("/register.jsp").forward(req,resp);

        }
        req.getRequestDispatcher("/registerSuccess.jsp").forward(req,resp);



    }

}
