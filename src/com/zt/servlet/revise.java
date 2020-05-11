package com.zt.servlet;

import com.alibaba.fastjson.JSON;
import com.zt.entity.reviseObj;
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
 * Date: 2020/4/21 20:25
 */
/*
* 修改api
* url:    http://47.102.223.209:8080/Login/revise
* 请求方式 get,post
* 请求参数 {
*   user,
*   password
* }
* 返回值 {
*   user,
*   password,
*   msg => 修改状态码
* }
*
*
* */
@WebServlet("/revise")
public class revise extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        //允许跨域
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //获取请求参数
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        boolean msg = false;
        //调用服务层
        Service service = new ServiceImp();
        int flag = service.revise(user, password);
        if(flag != 0) {
            msg = true;
        }
        reviseObj reviseObj = new reviseObj(msg, user,password);
        resp.getWriter().write(JSON.toJSONString(reviseObj));
    }
}
