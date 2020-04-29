package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2020/4/28.
 */
@Configuration
public class DruidConfig {
    @Resource
    private JdbcParamConfig jdbcParamConfig;

    @Bean
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(jdbcParamConfig.getUrl());
        datasource.setDriverClassName(jdbcParamConfig.getDriverClassName());
        datasource.setInitialSize(jdbcParamConfig.getInitialSize());
        datasource.setMinIdle(jdbcParamConfig.getMinIdle());
        datasource.setMaxActive(jdbcParamConfig.getMaxActive());
        datasource.setMaxWait(jdbcParamConfig.getMaxWait());
        datasource.setValidationQuery("select *");
        return datasource;
    }
}