package com.zzy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

@SpringBootApplication
public class NacosSpringCloudApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringCloudApplication.class, args);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Resource
    private SongMysqlConf songMysqlConf;
    @Resource
    private CenterMysqlConf centerMysqlConf;
    @Resource
    private CloudMysqlConf cloudMysqlConf;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("songMysqlConf = " + songMysqlConf.toString());
        System.out.println("cloudMysqlConf = " + cloudMysqlConf.toString());
        System.out.println("centerMysqlConf = " + centerMysqlConf.toString());
    }
}
