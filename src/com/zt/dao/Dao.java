package com.zt.dao;

import com.zt.entity.User;

import java.sql.ResultSet;

/**
 * @Author: ZT
 * @Date: 2020/4/19 13:08
 */
public interface Dao {
    //登录
    User login(String user, String password);

    //注册
    int register(String user, String password, String email);

    //修改(密码)
    int revise(String user, String password);

    //通过id 删除数据
    int delete(int id);

    //信息表（全部）
    ResultSet UserAll();

    /////////////////////////////////////////////////////////////////////////////
    //判断账号是否重复或者是否正确  (重复： 重复=>true !=>false)  (正确 =>true !=>false)
    boolean judgeUser(String user);

    //判断邮箱及账号是否正确
    boolean judgeEmail(String user, String email);

//    通过id获取所有值
    User getIdByUser(int id);
    //通过id修改值
    int setIdByData(User user);
}
