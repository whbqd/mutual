package com.dy.service;

import com.dy.entity.User;
import com.dy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    @Autowired
    UserMapper userMapper;

    /**
     * 登录
     * @param user
     * @param password
     * @return
     */
    public User login(String user, String password) {
        return userMapper.login(user, password);
    }

    /**
     * 注册
     * @param user
     * @param password
     * @param email
     * @return
     */
    public Integer register(String user, String password, String email) {
        return userMapper.register(user, password, email);
    }

    /**
     * 判断用户名是否重复
     * @param user
     * @return
     */
    public User queryByUser(String user) {
        return userMapper.queryByUser(user);
    }
}
