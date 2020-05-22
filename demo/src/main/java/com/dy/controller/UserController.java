package com.dy.controller;
import com.dy.packageEntity.CheckingUser;
import com.dy.packageEntity.LoginUser;
import com.dy.packageEntity.RegisterUser;
import com.dy.packageEntity.UpdatePwd;
import com.dy.service.UserService;
import com.dy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
        if(u != null) {
            String token = UUID.randomUUID().toString().replaceAll("-","");
            return new LoginUser(u, token, true);
        }
        return new LoginUser(u, null, false);
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

    /**
     * 判断用户名和邮箱是否正确
     * @param user
     * @param email
     * @return
     */
    @RequestMapping("/checking/{user}/{email}")
    public CheckingUser checking(@PathVariable String user, @PathVariable String email) {
        User u = userService.UserIsPwd(user, email);
        CheckingUser checkingUser = new CheckingUser(u, false);
        if(u == null) {
            return checkingUser;
        }
        checkingUser.setMsg(true);
        return checkingUser;
    }

    /**
     * 修改密码
     * @param user
     * @param password
     * @return
     */
    @RequestMapping("/updatePwd/{user}/{password}")
    public UpdatePwd updatePwd(@PathVariable String user, @PathVariable String password) {
        Integer flag = userService.updatePwd(user, password);
        UpdatePwd updatePwd = new UpdatePwd(null, false);
        if(flag != 1) {
            return updatePwd;
        }
        return new UpdatePwd(userService.queryByUser(user), true);
    }

    /**
     * 全查
     * @return
     */
    @RequestMapping("/queryAll")
    public List<User> queryAll() {
        return userService.queryAll();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/del/{id}")
    public CheckingUser del(@PathVariable Integer id) {
        CheckingUser checkingUser = new CheckingUser(userService.queryById(id), true);
        int flag = userService.del(id);
        if(flag == 1) {
            return checkingUser;
        }
        checkingUser = new CheckingUser(null, false);
        return checkingUser;
    }
}
