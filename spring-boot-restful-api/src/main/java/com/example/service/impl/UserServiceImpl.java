package com.example.service.impl;

import com.example.dao.SysUserMapper;
import com.example.dao.SysUserMapperCustom;
import com.example.entity.SysUser;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserMapperCustom userMapperCustom;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(SysUser user) throws Exception {

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        userMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(SysUser user) {
//		userMapper.updateByPrimaryKeySelective(user);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(String userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SysUser queryUserById(String userId) {

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return userMapper.selectByPrimaryKey(Integer.parseInt(userId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SysUser> queryUserList(SysUser user) {

        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(user.getUsername())) {
//			criteria.andEqualTo("username", user.getUsername());
            criteria.andLike("username", "%" + user.getUsername() + "%");
        }

        if (!StringUtils.isEmpty(user.getNickname())) {
            criteria.andLike("nickname", "%" + user.getNickname() + "%");
        }

        List<SysUser> userList = userMapper.selectByExample(example);

        return userList;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
        // 开始分页
        PageHelper.startPage(page, pageSize);

        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(user.getNickname())) {
            criteria.andLike("nickname", "%" + user.getNickname() + "%");
        }
        example.orderBy("registTime").desc();
        List<SysUser> userList = userMapper.selectByExample(example);

        return userList;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SysUser queryUserByIdCustom(String userId) {

        List<SysUser> userList = userMapperCustom.queryUserSimplyInfoById(userId);

        if (userList != null && !userList.isEmpty()) {
            return (SysUser) userList.get(0);
        }

        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUserTransactional(SysUser user) {
        userMapper.insert(user);
        int a = 1 / 0;
        user.setIsDelete(1);
        userMapper.updateByPrimaryKeySelective(user);
    }
}
