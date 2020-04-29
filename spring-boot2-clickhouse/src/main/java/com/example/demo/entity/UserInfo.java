package com.example.demo.entity;

import lombok.Data;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2020/4/28.
 */
@Data
public class UserInfo {

    private Integer id;
    private String userName;
    private String passWord;
    private String phone;
    private String email;
    private String createDay;
}
