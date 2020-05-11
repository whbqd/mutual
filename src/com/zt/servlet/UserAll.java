package com.zt.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zt.entity.Msg;
import com.zt.entity.OBJ;
import com.zt.entity.userAll;
import com.zt.service.Service;
import com.zt.serviceImp.ServiceImp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: ZT
 * Date: 2020/4/22 15:46
 */

/*
* 查询全部信息api
 * url:  http://47.102.223.209:8080/Login/UserAll
 * 请求方式： get, post
 * 请求参数： null
 * 返回值： {
 *   userList => 查询结果对象
 *   msg => 查询状态码
 * }
*
*
* */
@WebServlet("/UserAll")
public class UserAll extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        //允许跨域
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //调用服务层
        Service service = new ServiceImp();
        ResultSet set = service.UserAll();
        List<userAll> list = new ArrayList<>();
        while (true) {
            try {
                if (!set.next()) break;
                int id = set.getInt(1);
                String user = set.getString(2);
                String password = set.getString(3);
                String email = set.getString(4);
                userAll userAll = new userAll(id, user, password, email);
                list.add(userAll);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // msg 状态码
        boolean msg = false;
        // 判断查询结果是否为空
        if(list.size() != 0) {
            msg = true;
        }
        //封装数组(呈现数据匿名),可以加入其他属性(这里是msg => 状态码)
        OBJ obj = new OBJ(msg, list);
//        resp.getWriter().write(JSON.toJSONString(list) + JSON.toJSONString(msg));
//        resp.getWriter().write(JSONArray.toJSONString("{"+msg+","+list+"}"));
//        resp.getWriter().write(JSON.toJSONString(msg));
        resp.getWriter().write(JSONArray.toJSONString(obj));
    }
}
