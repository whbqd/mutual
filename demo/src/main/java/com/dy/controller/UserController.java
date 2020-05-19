package com.dy.controller;
import com.dy.service.UserService;
import com.dy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{id}")
    public User GetUser(@PathVariable int id) {
        return userService.queryById(id);
    }
    @RequestMapping("login/{user}/{password}")
    public User login(@PathVariable String user, @PathVariable String password) {
        return userService.login(user, password);
    }
}
