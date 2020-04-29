package com.example.demo.service;

import com.example.demo.entity.UserInfo;

import java.util.List;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2020/4/28.
 */
public interface UserInfoService {
    // 写入数据
    void saveData(UserInfo userInfo);

    // ID 查询
    UserInfo selectById(Integer id);

    // 查询全部
    List<UserInfo> selectList();
}
