package com.zt.serviceImp;

import com.zt.dao.Dao;
import com.zt.daoImp.DaoImp;
import com.zt.entity.User;
import com.zt.service.Service;

import java.sql.ResultSet;

/**
 * Author: ZT
 * Date: 2020/4/19 13:32
 */
public class ServiceImp implements Service {
    Dao dao = new DaoImp();
    //登录
    @Override
    public User login(String user, String password) {
        return dao.login(user, password);
    }
    //注册
    @Override
    public int register(String user, String password, String email) {
        return dao.register(user, password, email);
    }
    //修改密码
    @Override
    public int revise(String user, String password) {
        return dao.revise(user, password);
    }
    //信息表（全部）
    public ResultSet UserAll() {
        return dao.UserAll();
    }
    //通过id 删除数据
    public int delete (int id) {
        return dao.delete(id);
    };
    //判断用户名是否重复
    @Override
    public boolean judgeUser (String user) {
        return dao.judgeUser(user);
    }
    //判断账号,邮箱正误
    @Override
    public boolean judgeEmail(String user, String email) {
        return dao.judgeEmail(user, email);
    }
}
