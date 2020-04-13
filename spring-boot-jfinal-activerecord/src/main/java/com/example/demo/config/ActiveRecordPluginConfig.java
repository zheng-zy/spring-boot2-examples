package com.example.demo.config;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.example.demo.entity._MappingKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.source.ClassPathSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
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

    /**
     * 配置监控服务器
     *
     * @return 返回监控注册的servlet对象
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加IP白名单
//        servletRegistrationBean.addInitParameter("allow", "192.168.97.120,127.0.0.1");
        // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
//        servletRegistrationBean.addInitParameter("deny", "192.168.25.123");
        // 添加控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 配置服务过滤器
     *
     * @return 返回过滤器配置对象
     */
    @Bean
    public FilterRegistrationBean statFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略过滤格式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
        return filterRegistrationBean;
    }
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
        Slf4jLogFilter logFilter = new Slf4jLogFilter();
//        Log4j2Filter logFilter = new Log4j2Filter();
        logFilter.setStatementExecutableSqlLogEnable(true);
        druidPlugin.addFilter(logFilter);
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
