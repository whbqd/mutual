package com.dy.service;

import com.dy.entity.User;
import com.dy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 判断用户名和邮箱是否正确
     * @param user
     * @param password
     * @return
     */
    public User UserIsPwd(String user, String password) {
        return userMapper.UserIsPwd(user, password);
    }

    /**
     * 修改密码
     * @param password
     * @return
     */
    public Integer updatePwd(String user, String password) {
        return userMapper.updatePwd(user, password);
    }

    /**
     * 全查
     * @return
     */
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public Integer del(Integer id) {
        return userMapper.del(id);
    }

    public User queryById(Integer id) {
        return userMapper.queryById(id);
    }
}