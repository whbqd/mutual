package com.dy.controller;
import com.dy.packageEntity.LoginUser;
import com.dy.packageEntity.RegisterUser;
import com.dy.service.UserService;
import com.dy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @param password
     * @return
     */
    @RequestMapping("login/{user}/{password}")
    public LoginUser login(@PathVariable String user, @PathVariable String password) {
        User u = userService.login(user, password);
        LoginUser loginUser = new LoginUser(u, null, false);
        if(u != null) {
            String token = String.valueOf(UUID.randomUUID());
            loginUser = new LoginUser(u, token, true);
        }
        return loginUser;
    }

    /**
     * 注册
     * @param user
     * @param password
     * @param email
     * @return
     */
    @RequestMapping("/register/{user}/{password}/{email}")
    public RegisterUser register(@PathVariable String user, @PathVariable String password, @PathVariable String email) {
        //查询用户名是否重复
        User isRepeat = userService.queryByUser(user);
        RegisterUser registerUser = new RegisterUser();
        //重复
        if (isRepeat != null) {
            registerUser.setMsg(false);
            registerUser.setRepeat(true);
            return registerUser;
        }
        //不重复,执行注册方法
        Integer flag = userService.register(user, password, email);
        isRepeat = userService.queryByUser(user);
        registerUser.setData(isRepeat);
        registerUser.setMsg(flag == 1);
        registerUser.setRepeat(false);
        return registerUser;
    }
}
