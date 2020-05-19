package com.dy.mapper;

import com.dy.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User queryById(int id);
    User login(String user, String password);
}
