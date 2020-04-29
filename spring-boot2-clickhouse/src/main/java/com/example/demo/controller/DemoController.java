package com.example.demo.controller;

import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2020/4/10.
 */
@RestController
@RequestMapping("/user")
public class DemoController {
    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("/saveData")
    public String saveData() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(4);
        userInfo.setUserName("winter");
        userInfo.setPassWord("567");
        userInfo.setPhone("13977776789");
        userInfo.setEmail("winter");
        userInfo.setCreateDay("2020-02-20");
        userInfoService.saveData(userInfo);
        return "sus";
    }

    @RequestMapping("/selectById")
    public UserInfo selectById() {
        return userInfoService.selectById(1);
    }

    @RequestMapping("/selectList")
    public List<UserInfo> selectList() {
        return userInfoService.selectList();
    }

}
