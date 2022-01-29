package com.wjj.shop.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *
 */
@WebFilter("/*")
public class EncodeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            //1.解决请求乱码
        servletRequest.setCharacterEncoding("UTF-8");
        //2.解决响应乱码
        servletResponse.setContentType("text/html;charset=UTF-8");
        //3.执行
        filterChain.doFilter(servletRequest,servletResponse);



    }

    @Override
    public void destroy() {

    }
}
