package com.example.service;

import com.example.entity.SysUser;

import java.util.List;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/6/30.
 */
public interface UserService {
    public void saveUser(SysUser user) throws Exception;

    public void updateUser(SysUser user);

    public void deleteUser(String userId);

    public SysUser queryUserById(String userId);

    public List<SysUser> queryUserList(SysUser user);

    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize);

    public SysUser queryUserByIdCustom(String userId);

    public void saveUserTransactional(SysUser user);
}
