package com.zt.service;

import com.zt.entity.User;

import java.sql.ResultSet;

/**
 * Author: ZT
 * Date: 2020/4/19 13:31
 */
public interface Service {
    //登录
    User login(String user, String password);
    //注册
    int register(String user, String password, String email);

    //修改（密码）
    int revise (String user, String password);
    //信息表（全部）
    ResultSet UserAll();
    //通过id 删除数据
    int delete (int id);
    //判断用户名是否重复
    boolean judgeUser (String user);
    //判断账号,邮箱正误
    boolean judgeEmail (String user, String email);

}
