package com.zzy;

import com.google.common.collect.ImmutableMap;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2019/2/25.
 */
@RestController
@RequestMapping("config")
@RefreshScope
public class ConfigController {

    @Resource
    private RedisProperties redisProperties;
    @Resource
    private DataSourceProperties  dataSourceProperties;

    @RequestMapping("getRedisConfig")
    public Map<String, Object> getRedisConfig() {
        return ImmutableMap.of("host", redisProperties.getHost(),"password", redisProperties.getPassword());
    }

    @RequestMapping("getDatasourceConfig")
    public Map<String, Object> getDatasourceConfig() {
        return ImmutableMap.of("username", dataSourceProperties.getUsername(),"password", dataSourceProperties.getPassword());
    }

}
