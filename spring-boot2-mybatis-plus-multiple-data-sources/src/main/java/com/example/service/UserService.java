package com.example.service;

import com.example.entity.User;

import java.util.List;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2020/9/9.
 */
public interface UserService {
    List<User> selectUsersFromMaster();

    List<User> selectUsersFromSlave();

    void addUser(User user);

    void deleteUserById(Long id);
}
