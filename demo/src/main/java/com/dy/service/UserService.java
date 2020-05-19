package com.dy.service;

import com.dy.entity.User;
import com.dy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    @Autowired
    UserMapper userMapper;
    public User queryById(int id) {
        return userMapper.queryById(id);
    }
    public User login(String user, String password) {
        return userMapper.login(user, password);
    }
}
