package com.wjj.shop.web;

import com.wjj.shop.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 基于反射抽取调用父类
 */
public class BaseController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //获取一个方法名
        String methodName = req.getParameter(Constants.TAG);
        if(methodName==null||"".equals(methodName)){
            methodName=Constants.INDEX;
        }
        //反射 获取字节码3种方法
        //1.类名 2.对象3.forName

        try {
            Class<? extends BaseController> clazz = this.getClass();
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            Object result = method.invoke(this, req, resp);
            if(result!=null){
                String str = (String)result;
                if(str.startsWith(Constants.FORWARD)) {
                    //请求转发
                    String path = str.substring(str.indexOf(Constants.FLAG) + 1);
                    req.getRequestDispatcher(path).forward(req, resp);
                }else if (str.startsWith(Constants.REDIRECT)){
                    //重定向
                    String path = str.substring(str.indexOf(Constants.FLAG) + 1);
                    resp.sendRedirect(path);
                }else{
                    //直接对外输出
                    resp.getWriter().print(str);
                }
            }
        } catch (NoSuchMethodException | InvocationTargetException |IllegalAccessException e) {
            e.printStackTrace();
            req.setAttribute("msg","网络异常或者其他各种异常，请检查");
            req.getRequestDispatcher("/message.jsp").forward(req,resp);
        }

    }

    /**
     * 跳转到当前界面
     * @param req
     * @param resp
     * @return
     */
    public String index(HttpServletRequest req, HttpServletResponse resp){

        return Constants.FORWARD+"/index.jsp";
    }
}
