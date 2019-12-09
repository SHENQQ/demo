package com.dao;

import com.dal.entity.UserRole;
import org.springframework.stereotype.Repository;

public interface UserDao {
    UserRole getById (Integer id);
}
