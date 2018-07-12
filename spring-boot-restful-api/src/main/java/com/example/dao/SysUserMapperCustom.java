package com.example.dao;


import com.example.entity.SysUser;

import java.util.List;

public interface SysUserMapperCustom {
	
	List<SysUser> queryUserSimplyInfoById(String id);
}