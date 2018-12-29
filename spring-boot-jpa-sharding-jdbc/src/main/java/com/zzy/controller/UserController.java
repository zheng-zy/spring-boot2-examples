package com.zzy.controller;

import com.google.common.collect.ImmutableBiMap;
import com.zzy.db.entity.User;
import com.zzy.db.repository.UserRepository;
import com.zzy.util.QueryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/25.
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Resource
    private UserRepository userRepository;

    @RequestMapping("addUser")
    @ResponseBody
    public Map<String, Object> addUser(@RequestBody User user) {
        log.info("db:{}", user.toString());
        user = userRepository.save(user);
        log.info("userId:{}", user.getUserId());
        return ImmutableBiMap.of("code", 0, "db", user);
    }

    @RequestMapping("getUser/{userId}")
    @ResponseBody
    public Map<String, Object> getUser(@PathVariable Long userId) {
        log.info("userId:{}", userId);
        User user = userRepository.findById(userId).get();
        return ImmutableBiMap.of("code", 0, "db", user);
    }

    @RequestMapping("getByIds/{userIds}")
    @ResponseBody
    public Map<String, Object> getById(@PathVariable String userIds) {
        log.info("userIds:{}", userIds);
        List<Long> ids = Arrays.stream(userIds.split(",")).distinct().map(Long::valueOf).collect(Collectors.toList());
        List<User> userList = userRepository.findAllById(ids);
        return ImmutableBiMap.of("code", 0, "userList", userList);
    }

    @RequestMapping("getUserByParam")
    @ResponseBody
    public Map<String, Object> getUserByParam(@RequestParam Long start, @RequestParam Long end) {
        List<User> userList = userRepository.findAllByUserIdBetweenOrderByUserIdAsc(start, end);
        return ImmutableBiMap.of("code", 0, "userList", userList);
    }

    /**
     * http://127.0.0.1:8080/user/getUserPage?page=1&pageSize=10&sortCol=userId&sortType=asc
     * @param page
     * @param pageSize
     * @param sortType
     * @param sortCol
     * @return
     */
    @RequestMapping("getUserPage")
    @ResponseBody
    public Map<String, Object> getUserPage(@RequestParam int page, @RequestParam int pageSize,
                                           @RequestParam(value = "", required = false) String sortType,
                                           @RequestParam(value = "", required = false) String sortCol) {
        Page<User> userPage = userRepository.findAll(QueryUtil.buildPageRequest(page, pageSize, sortType, sortCol));
        return ImmutableBiMap.of("code", 0, "userPage", userPage);
    }


}
