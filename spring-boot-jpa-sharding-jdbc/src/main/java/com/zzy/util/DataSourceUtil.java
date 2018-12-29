package com.zzy.util;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/26.
 */
public class DataSourceUtil {

    public static DataSource dataSource(String dbName) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl(String.format("jdbc:mysql://192.168.97.57:3306/%s", dbName));
        dataSource.setPoolName("db:" + dbName + System.currentTimeMillis());
        dataSource.setUsername("root");
        dataSource.setPassword("666666");
        return dataSource;
    }

}
