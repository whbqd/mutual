package com.dy.controller;
import com.dy.packageEntity.*;
import com.dy.service.UserService;
import com.dy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 登录
     * @param user
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestParam String user, @RequestParam String password) {
        User u = userService.login(user, password);
        System.out.println(u);
        if(u != null) {
            String token = UUID.randomUUID().toString().replaceAll("-","");
            redisTemplate.opsForValue().set(token, u, Duration.ofMinutes(30L));
            return new Result(token, "登录成功", 100);
        }
        return new Result(null, "登录失败", 104);
    }

    /**
     * 退出登录 删除redis的token
     * @param request
     * @return
     */
    @RequestMapping("/view/logout")
    public Result logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        Boolean delete = redisTemplate.delete(token);
        if(delete) {
            return new Result(delete, "退出成功", 100);
        }
        return new Result(null, "退出失败", 104);
    }
    /**
     * 通过token查询User信息
     * @param request
     * @return
     */
    @RequestMapping("/view/getUserOfLogin")
    public Result getUserOfLogin(HttpServletRequest request) {
        //获取token
        String token = request.getHeader("token");
        Object user = redisTemplate.opsForValue().get(token);
        if(user == null) {
            return new Result(null, "查询用户信息失败", 104);
        }
        return new Result(user, "查询用户信息成功", 100);
    }
    /**
     * 注册
     * @param user
     * @param password
     * @param email
     * @return
     */
    @RequestMapping("/view/register/{user}/{password}/{email}")
    public Result register(@PathVariable String user, @PathVariable String password, @PathVariable String email) {
        //查询用户名是否重复
        User isRepeat = userService.queryByUser(user);
        //重复
        if (isRepeat != null) {
            return new Result(null, "用户名重复", 104);
        }
        //不重复,执行注册方法
        Integer flag = userService.register(user, password, email);
        System.out.println(flag);
        isRepeat = userService.queryByUser(user);
        if (flag != 1) {
            return new Result(null, "注册失败", 104);
        }
        return new Result(isRepeat, "注册成功", 100);
    }

    /**
     * 判断用户名和邮箱是否正确
     * @param user
     * @param email
     * @return
     */
    @RequestMapping("/view/checking/{user}/{email}")
    public Result checking(@PathVariable String user, @PathVariable String email) {
        User u = userService.UserIsPwd(user, email);
        if(u == null) {
            return new Result(null, "用户名或邮箱不正确", 104);
        }
        return new Result(u, "用户名邮箱正确", 100);
    }

    /**
     * 修改密码
     * @param user
     * @param password
     * @return
     */
    @RequestMapping("/view/updatePwd/{user}/{password}")
    public Result updatePwd(@PathVariable String user, @PathVariable String password) {
        Integer flag = userService.updatePwd(user, password);
        if(flag != 1) {
            return new Result(null, "更新失败", 104);
        }
        return new Result(userService.queryByUser(user), "更新成功", 100);
    }

    /**
     * 全查
     * @return
     */
    @RequestMapping("/view/queryAll")
    public Result queryAll() {
        return new Result(userService.queryAll(), "全查成功", 100);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/view/del/{id}")
    public Result del(@PathVariable Integer id) {
        User user = userService.queryById(id);
        int flag = userService.del(id);
        if(flag != 1) {
            return new Result(null, "删除失败", 104);
        }
        return new Result(user, "删除成功", 100);
    }

}
