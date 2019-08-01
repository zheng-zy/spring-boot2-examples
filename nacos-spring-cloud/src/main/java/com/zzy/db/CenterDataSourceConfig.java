//package com.zzy.db;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * <p>db center 数据源</p>
// * Created by @author zhezhiyong@163.com on 2017/12/25.
// */
//@Configuration
//@MapperScan(basePackages = CenterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "centerSqlSessionFactory")
//public class CenterDataSourceConfig {
//
//    static final String PACKAGE = "com.zzy.dao.dbcenter";
//    private static final String MAPPER_LOCATION = "classpath:mapper/dbcenter/*.xml";
//    private static final String CONFIG_LOCATION = "mybatis-config.xml";
//
//    @Bean(name = "centerDataSourceTransactionManager")
//    public DataSourceTransactionManager centerDataSourceTransactionManager(@Qualifier("dbCenter") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "centerSqlSessionFactory")
//    public SqlSessionFactory centerSqlSessionFactory(@Qualifier("dbCenter") DataSource dataSource) throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setConfigLocation(new ClassPathResource(CONFIG_LOCATION));
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
//        return sessionFactory.getObject();
//    }
//}
