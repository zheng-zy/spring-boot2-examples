package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2020/9/9.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Api(tags = "用户列表")
public class UserController {

    private static final Random RANDOM = new Random();
    private final UserService userService;

    @GetMapping("master")
    public List<User> users1() {
        return userService.selectUsersFromMaster();
    }

    @GetMapping("db147")
    public List<User> users2() {
        return userService.selectUsersFromSlave();
    }

    @PostMapping
    public User addUser(User user) {
        user.setAge(RANDOM.nextInt(100));
        userService.addUser(user);
        return user;
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "成功删除用户" + id;
    }
}
