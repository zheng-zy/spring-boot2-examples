package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.config.Result;
import com.example.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/7/2.
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("test")
    public Result test(){
        SysUser sysUser = new SysUser();
        sysUser.setId(1);
        stringRedisTemplate.opsForValue().set("user", JSON.toJSONString(sysUser));
        SysUser sysUser2 = JSON.parseObject(stringRedisTemplate.opsForValue().get("user"), SysUser.class);
        return Result.ok(sysUser2);
    }

}
