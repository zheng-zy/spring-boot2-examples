package com.zzy.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzy.feign.F1000Mapper;
import com.zzy.feign.ITestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2019/4/26.
 */
@Slf4j
@RestController
public class FeignController {

    @Resource
    private ITestClient testClient;

    @GetMapping("/getSongScore")
    public JSONObject getSongScore() {
        F1000Mapper mapper = new F1000Mapper();
        mapper.setCustomerId("37703389");
        mapper.setCompanyCode("18887");
        mapper.setStartTime("2019-04-26 11:16:57");
        mapper.setEndTime("2019-04-26 12:16:57");
        JSONObject object = testClient.getSongScore(mapper);
        System.out.println("object = " + object.toJSONString());
        return object;
    }

}
