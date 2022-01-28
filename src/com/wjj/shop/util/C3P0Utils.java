package com.wjj.shop.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
获取数据源的工具类
 */
public class C3P0Utils {

    private static DataSource ds = new ComboPooledDataSource();

    /**
     * 获取资源。
     * @return
     */
    public static DataSource getDataSource(){
        return ds;
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 释放资源
     * @param rs
     * @param statement
     * @param conn
     */
    public static void release(ResultSet rs, Statement statement,Connection conn){
        try {
            if(rs!=null)
                rs.close();

            if(statement!=null)
                statement.close();

            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
