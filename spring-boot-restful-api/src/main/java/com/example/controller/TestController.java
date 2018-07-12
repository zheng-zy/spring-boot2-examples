package com.example.controller;

import com.example.config.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/6/30.
 */
@RestController
public class TestController {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("test")
    @ResponseBody
    public Result test() {
        log.debug("test");
        log.info("test");
        log.warn("test");
        log.error("test");
        return Result.ok();
    }

    @GetMapping("testError")
    @ResponseBody
    public Result error() {
        boolean flag = new Random().nextBoolean();
        if (flag) {
            throw new RuntimeException("查询报错");
        } else {
            return Result.ok();
        }

    }



//    @ExceptionHandler
//    @ResponseBody
//    public String exceptionHandler(Exception e) {
//        log.error(e.getMessage(), e);
//        return e.getMessage();
//    }

}
