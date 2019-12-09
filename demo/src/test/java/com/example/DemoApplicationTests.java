package com.example;
import com.dal.entity.UserRole;
import com.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Resource
    UserDao userDao;

    @Test
    public void contextLoads() {
        UserRole user = userDao.getById(1);
        System.out.println(user.getId());
        System.out.println(user.getName());
    }

}
