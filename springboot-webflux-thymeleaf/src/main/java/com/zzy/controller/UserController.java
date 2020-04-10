package com.zzy.controller;

import com.zzy.entity.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2019/3/6.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{user}")
    public Mono<User> getUser(@PathVariable Long user) {
        return null;
    }



    @DeleteMapping("/{user}")
    public Mono<User> deleteUser(@PathVariable Long user) {
        return null;
    }

}
