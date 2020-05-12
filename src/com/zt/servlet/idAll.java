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
 * @Author: ZT
 * @Date: 2020/5/12 15:21
 */
@WebServlet("/idAll")
public class idAll extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        //允许跨域
        resp.setHeader("Access-Control-Allow-Origin", "*");

        int id = Integer.parseInt(req.getParameter("id"));
        Service service = new ServiceImp();
        User user = service.getIdByUser(id);
        resp.getWriter().write(JSON.toJSONString(user));
    }
}
