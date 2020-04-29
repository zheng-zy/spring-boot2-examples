package com.example.demo.service.impl;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2020/4/28.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public void saveData(UserInfo userInfo) {
        userInfoMapper.saveData(userInfo);
    }

    @Override
    public UserInfo selectById(Integer id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public List<UserInfo> selectList() {
        return userInfoMapper.selectList();
    }
}
