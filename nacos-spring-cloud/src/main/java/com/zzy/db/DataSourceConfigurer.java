package com.zzy.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.zzy.CenterMysqlConf;
import com.zzy.CloudMysqlConf;
import com.zzy.SongMysqlConf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2019/4/15.
 */
@Configuration
@Slf4j
public class DataSourceConfigurer {

    @Bean
    public DataSource dbCenter(@Autowired CenterMysqlConf centerMysqlConf) {
        log.info(centerMysqlConf.toString());
        return createDataSource(centerMysqlConf.getUrl(), centerMysqlConf.getUsername(), centerMysqlConf.getPassword(), false);
    }

    @Bean
    public DataSource dbSong(@Autowired SongMysqlConf songMysqlConf) {
        log.info(songMysqlConf.toString());
        return createDataSource(songMysqlConf.getUrl(), songMysqlConf.getUsername(), songMysqlConf.getPassword(), false);
    }

    @Bean
    @Primary
    public DataSource dbBigData(@Autowired CloudMysqlConf cloudMysqlConf) {
        log.info(cloudMysqlConf.toString());
        return createDataSource(cloudMysqlConf.getUrl(), cloudMysqlConf.getUsername(), cloudMysqlConf.getPassword(), false);
    }

    @Bean
    public NamedParameterJdbcTemplate songNamedParameterJdbcTemplate(@Qualifier("dbSong") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate centerNamedParameterJdbcTemplate(@Qualifier("dbCenter") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate dataNamedParameterJdbcTemplate(@Qualifier("dbBigData") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }


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

    private DataSource createDataSource(String dbUrl2, String username2, String password2, boolean readOnly) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setDefaultReadOnly(readOnly);
        datasource.setUrl(dbUrl2);
        datasource.setUsername(username2);
        datasource.setPassword(password2);
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setInitialSize(20);
        datasource.setMinIdle(20);
        datasource.setMaxActive(200);
        datasource.setMaxWait(6000);
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        datasource.setTimeBetweenEvictionRunsMillis(900000);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        datasource.setMinEvictableIdleTimeMillis(900000);
        datasource.setValidationQuery("SELECT 1");
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(true);
        datasource.setTestOnReturn(false);
        // 打开PSCache，并且指定每个连接上PSCache的大小
        datasource.setPoolPreparedStatements(true);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
        try {
            // 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
            datasource.setFilters("stat,wall,slf4j");
        } catch (SQLException e) {
            log.error("druid configuration initialization filter : {0}", e);
        }
        // 通过connectProperties属性来打开mergeSql功能；慢SQL记录
        datasource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500");
        return datasource;
    }

}