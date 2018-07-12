package com.example;

import com.example.dao.SysRoleMapper;
import com.example.entity.SysRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRestfulApiApplicationTests {

	@Resource
	private SysRoleMapper sysRoleMapper;

	@Test
	public void contextLoads() {
		SysRole sysRole = new SysRole();
		sysRole.setName("管理员");
		sysRole.setAvailable(1);
		sysRoleMapper.insert(sysRole);

		List<SysRole> sysRoleList = sysRoleMapper.selectAll();
		System.out.println("sysRoleList = " + sysRoleList.size());
	}

}
