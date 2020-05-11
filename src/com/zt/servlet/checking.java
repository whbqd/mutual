package com.zt.servlet;

import com.alibaba.fastjson.JSON;
import com.zt.entity.checkingObj;
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
 * Date: 2020/4/21 20:08
 */
/*Backend
*url:   http://47.102.223.209:8080/Login/checking
* 判断用户名及邮箱Api
*请求方式  get,post
*请求参数 {
*   user,
*   email
* }
* 返回值 {
*   user,
*   email,
*   msg => 判断状态码
* }
* */
@WebServlet("/checking")
public class checking extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        //允许跨域
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //获取请求参数
        String user = req.getParameter("user");
        String email = req.getParameter("email");
        System.out.println(user + email);
        //调用服务层
        Service service = new ServiceImp();
        boolean msg = service.judgeEmail(user, email);
        checkingObj check = new checkingObj(user, email, msg);
        resp.getWriter().write(JSON.toJSONString(check));
    }
}
