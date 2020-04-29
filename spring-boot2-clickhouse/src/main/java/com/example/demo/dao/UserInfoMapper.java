package com.example.demo.dao;

import com.example.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2020/4/28.
 */
@Mapper
public interface UserInfoMapper {
    // 写入数据
    void saveData(UserInfo userInfo);

    // ID 查询
    UserInfo selectById(@Param("id") Integer id);

    // 查询全部
    List<UserInfo> selectList();
}