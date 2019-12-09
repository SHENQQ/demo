package com.dao;

import com.dal.entity.UserRole;
import com.dal.entity.UserRoleExample;
import com.dal.mapper.UserRoleMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    UserRoleMapper userMapper;

    @Override
    public UserRole getById (Integer id) {
        UserRoleExample userExample = new UserRoleExample();
        userExample.createCriteria().andIdEqualTo(1);
        return  userMapper.selectByExample(userExample).get(0);
    }
}
