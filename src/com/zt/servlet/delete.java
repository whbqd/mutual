package com.zt.servlet;

import com.alibaba.fastjson.JSON;
import com.zt.entity.Msg;
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
 * Date: 2020/4/23 13:21
 */

/*
* url: http://47.102.223.209:8080/Login/delete
* 请求方式 get  post
* 请求参数  id
* 返回值 {
*   msg => 删除状态码
* }
*
* */
@WebServlet("/delete")
public class delete extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        //允许跨域
        resp.setHeader("Access-Control-Allow-Origin", "*");
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println(id);
        //调用服务层
        Service service = new ServiceImp();
        Msg msg = new Msg();
        if(service.delete(id) != 0) {
            msg.setMsg(true);
        }
        resp.getWriter().write(JSON.toJSONString(msg));
    }
}
