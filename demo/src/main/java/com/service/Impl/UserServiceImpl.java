package com.service.Impl;

import com.dal.entity.UserRole;
import com.dao.UserDao;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public UserRole getById(Integer id) {
        return  userDao.getById(id);
    }
}
