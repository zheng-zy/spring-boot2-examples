package com.zzy;

import com.zzy.db.entity.User;
import com.zzy.db.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Resource
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setStatus("1");
        User user2 = new User();
        user2.setStatus("2");
        User user3 = new User();
        user3.setStatus("3");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        userList.add(user3);
        userRepository.saveAll(userList);

    }

}

