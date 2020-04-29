package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2020/4/28.
 */
@Component
@Data
@ConfigurationProperties(prefix = "spring.datasource.click")
public class JdbcParamConfig {
    private String driverClassName;
    private String url;
    private Integer initialSize;
    private Integer maxActive;
    private Integer minIdle;
    private Integer maxWait;
}
