package com.dy.mapper;

import com.dy.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    /**
     * 登录
     * @param user
     * @param password
     * @return
     */
    User login(String user, String password);

    /**
     * 注册
     * @param user
     * @param password
     * @param email
     * @return
     */
    Integer register(String user, String password, String email);
    /**
     *查询用户名是否重复
     * @param user
     * @return
     */
    User queryByUser(String user);

}
