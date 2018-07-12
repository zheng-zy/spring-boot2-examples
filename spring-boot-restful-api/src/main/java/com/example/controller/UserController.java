package com.example.controller;

import com.example.config.Result;
import com.example.entity.SysUser;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/6/30.
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/saveUser")
    public Result saveUser() throws Exception {

        SysUser user = new SysUser();
        user.setUsername("imooc" + new Date());
        user.setNickname("imooc" + new Date());
        user.setPassword("abc123");
        user.setIsDelete(0);
        user.setRegistTime(new Date());

        userService.saveUser(user);

        return Result.ok("保存成功");
    }

    @RequestMapping("/updateUser")
    public Result updateUser() {

        SysUser user = new SysUser();
        user.setId(1);
        user.setUsername("10011001-updated" + new Date());
        user.setNickname("10011001-updated" + new Date());
        user.setPassword("10011001-updated");
        user.setIsDelete(0);
        user.setRegistTime(new Date());

        userService.updateUser(user);

        return Result.ok("保存成功");
    }

    @RequestMapping("/deleteUser")
    public Result deleteUser(String userId) {

        userService.deleteUser(userId);

        return Result.ok("删除成功");
    }

    @RequestMapping("/queryUserById")
    public Result queryUserById(String userId) {

        return Result.ok(userService.queryUserById(userId));
    }

    @RequestMapping("/queryUserList")
    public Result queryUserList() {

        SysUser user = new SysUser();
        user.setUsername("imooc");
        user.setNickname("lee");

        List<SysUser> userList = userService.queryUserList(user);

        return Result.ok(userList);
    }

    @RequestMapping("/queryUserListPaged")
    public Result queryUserListPaged(Integer page) {

        if (page == null) {
            page = 1;
        }

        int pageSize = 10;

        SysUser user = new SysUser();
//		user.setNickname("lee");

        List<SysUser> userList = userService.queryUserListPaged(user, page, pageSize);

        return Result.ok(userList);
    }

    @RequestMapping("/queryUserByIdCustom")
    public Result queryUserByIdCustom(String userId) {

        return Result.ok(userService.queryUserByIdCustom(userId));
    }

    @RequestMapping("/saveUserTransactional")
    public Result saveUserTransactional() {

        SysUser user = new SysUser();
        user.setUsername("lee" + new Date());
        user.setNickname("lee" + new Date());
        user.setPassword("abc123");
        user.setIsDelete(0);
        user.setRegistTime(new Date());

        userService.saveUserTransactional(user);

        return Result.ok("保存成功");
    }
}
