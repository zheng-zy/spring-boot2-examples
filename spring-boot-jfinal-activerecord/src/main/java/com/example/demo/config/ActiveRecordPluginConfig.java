package com.example.demo.config;

import com.alibaba.druid.filter.logging.Log4j2Filter;
import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.filter.logging.LogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.example.demo.entity._MappingKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.source.ClassPathSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.time.Duration;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2020/4/10.
 */
@Configuration
public class ActiveRecordPluginConfig {
    // 这里可以直接注入您自己的datasource
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String url;

    @Bean
    public ActiveRecordPlugin ininitActiveRecordPlugin() {
        DruidPlugin druidPlugin = new DruidPlugin(url, username, password);
        // 加强数据库安全
        WallFilter wallFilter = new WallFilter();
        wallFilter.setDbType("mysql");
        druidPlugin.addFilter(wallFilter);
        // 添加 StatFilter 才会有统计数据
        StatFilter statFilter = new StatFilter();
        statFilter.setMergeSql(true);
        statFilter.setLogSlowSql(true);
        statFilter.setSlowSqlMillis(Duration.ofMillis(20).toMillis());
        druidPlugin.addFilter(statFilter);
        // 配置日志输出
        Log4j2Filter log4j2Filter = new Log4j2Filter();
        log4j2Filter.setStatementExecutableSqlLogEnable(true);
        druidPlugin.addFilter(log4j2Filter);
        // 配置db参数
        druidPlugin.setInitialSize(1);
        druidPlugin.setMaxActive(20);
        // 必须手动调用start
        druidPlugin.start();
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.setTransactionLevel(Connection.TRANSACTION_READ_COMMITTED);
        _MappingKit.mapping(arp);
        arp.setShowSql(false);
        arp.getEngine().setSourceFactory(new ClassPathSourceFactory());
//        arp.addSqlTemplate("/sql/all_sqls.sql");
        // 必须手动调用start
        arp.start();
        return arp;
    }
}
