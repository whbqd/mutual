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

/**
 * Author: ZT
 * Date: 2020/4/20 19:38
 */
/*
* 注册api
* url:  http://47.102.223.209:8080/Login/register
*请求方式  get,post
*请求参数 {
*   user,
*   password,
*   email
* }
*返回值 {
*   user => 账号,
*   password => 密码,
*   email => 邮箱,
*   msg => 注册状态（true, false）,
*   repeatUsername => 重复账号（true, false）
* }
* 返回格式  json
* */
@WebServlet("/register")
public class register extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        //允许跨域
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //获取参数
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        //调用服务层
        Service service = new ServiceImp();
        boolean msg = false;  //注册是否成功
        boolean repeatUsername = service.judgeUser(user); //用户名是否重复
        if(!repeatUsername) {
            if (service.register(user, password, email) != 0) {
                msg = true;
            }
        }
        User u = new User(user, password, email, msg, repeatUsername);
        resp.getWriter().write(JSON.toJSONString(u));
    }
}
