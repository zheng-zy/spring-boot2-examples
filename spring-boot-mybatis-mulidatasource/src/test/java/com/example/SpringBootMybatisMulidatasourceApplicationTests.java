package com.example;

import com.example.dao.db1.UserDB1Dao;
import com.example.dao.db2.UserDB2Dao;
import com.example.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisMulidatasourceApplicationTests {
    @Autowired
    private UserDB1Dao userDB1Dao;
    @Autowired
    private UserDB2Dao userDB2Dao;

    @Test
    public void contextLoads() {
        List<User> user1List = userDB1Dao.getAll();
        List<User> user2List = userDB2Dao.getAll();
        System.out.println(user1List.size());
        System.out.println(user2List.size());
    }

}
