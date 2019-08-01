package com.example.job;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2019/8/1.
 */
@Configuration //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 2.开启定时任务
public class SimpleScheduleConfig {
    //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    private void configureTasks() {
        System.err.println("执行定时任务1: " + LocalDateTime.now());
    }
}
