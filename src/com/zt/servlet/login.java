package com.zt.servlet;

import com.alibaba.fastjson.JSON;
import com.zt.entity.User;
import com.zt.service.Service;
import com.zt.serviceImp.ServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.UUID;

/**
 * Author: ZT
 * Date: 2020/4/19 12:58
 */
/*
* 登录 API
* url:  http://47.102.223.209:8080/Login/login
* 请求方式  ： get post
* 请求参数 : {
* user => 用户名 ,
* password => 密码
* }
* 返回值 : {
*   msg: boolean => 登录状态
*        =>  true {
*               user,
*               password,
*               token
*           }
* }
*
* */
@WebServlet("/login")
public class login extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        //允许跨域
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //获取请求参数
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        System.out.println(user + password);
        //调用服务层
        Service service = new ServiceImp();
        //成功=>true 失败=>false
        User u = service.login(user, password);
        String token = null;
        boolean msg = false;
        if(u != null) {
            token = UUID.randomUUID() + "";
            msg = true;
            u.setToken(token);
            u.setMsg(msg);
        } else {
            u = new User(msg);
        }

        //User转换json并打印
        resp.getWriter().write(JSON.toJSONString(u));
    }
}
