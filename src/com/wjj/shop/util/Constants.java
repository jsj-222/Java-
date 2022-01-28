package com.wjj.shop.util;

/**
 * 项目中的常量类
 */
public class Constants {

    /**
     * 获取请求方法的标签
     */
    public static final String TAG = "method";

    public static final String INDEX = "index";

    public static final String FORWARD = "forward:";

    public static final String REDIRECT = "redirect:";

    public static final String FLAG = ":";

    /**
     * 用户已存在
     */
    public static final String HAS_USER = "1";

    /**
     * 用户不存在
     */
    public static final String NOT_HAS_USER = "0";

    /**
     * 用户已激活
     */
    public static final String USER_ACTIVE = "1";

    /**
     * 甩户未激活
     */
    public static final String USER_NOT_ACTIVE = "0";

    /**
     * 客户
     */
    public static final int ROLE_CUSTOMER = 0;

    /**
     * 管理员
     */
    public static final int ROLE_ADMIN = 1;

    /**
     * 用户模块的激活状态
     */
    public static final int ACTIVE_FAIL = 0;
    /**
     * 激活成功
     */
    public static final int ACTIVE_SUCCESS = 1;
    /**
     * 已激活
     */
    public static final int ACTIVE_ALREADY = 2;

    /**
     * 自动登录的cookie名
     */
    public static final String AUTO_NAME = "autoUser";

}
