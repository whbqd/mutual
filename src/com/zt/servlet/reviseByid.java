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
 * @Date: 2020/5/12 16:07
 */
@WebServlet("/reviseByid")
public class reviseByid extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        //允许跨域
        resp.setHeader("Access-Control-Allow-Origin", "*");
        int id = Integer.parseInt(req.getParameter("id"));
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        User u = new User(id, user, password, email);
        Service service = new ServiceImp();
        int flag = service.setIdByData(u);
        boolean msg = false;
        if(flag != 0) {
            msg = true;
        }
        resp.getWriter().write(JSON.toJSONString(msg));
    }
}
