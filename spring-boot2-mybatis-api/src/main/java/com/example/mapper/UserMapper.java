package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2020/9/9.
 */
public interface UserMapper extends BaseMapper<User> {

    @Insert("INSERT INTO user (name,age) values (#{name},#{age})")
    boolean addUser(String name, Integer age);

    @Delete("DELETE from user where id = #{id}")
    void deleteUserById(Long id);
}
