package com.zt.entity;

/**
 * @Author: ZT
 * @Date: 2020/4/19 13:02
 */
/*
* 登录 注册
* msg => 注册 ， 登录状态码
* repeatUser 注册重复状态码
* */

public class User {
    private int id;
    private String user,
                    password,
                    email,
                    token;
    private boolean msg = false,
                    repeatUser = false;
    public User(String token, boolean msg) {
        this.token = token;
        this.msg = msg;
    }

    public User(boolean msg) {
        this.msg = msg;
    }

    public User(String user, String password, String email, boolean msg, boolean repeatUser) {
        this.user = user;
        this.password = password;
        this.email = email;
        this.msg = msg;
        this.repeatUser = repeatUser;
    }

    public User(int id, String user, String password, boolean msg) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.msg = msg;
    }

    public User(String user, String password, String email) {
        this.user = user;
        this.password = password;
        this.email = email;
    }

    public User(int id, String user, String password, String email) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.email = email;
    }

    public boolean isRepeatUser() {
        return repeatUser;
    }

    public void setRepeatUser(boolean repeatUser) {
        this.repeatUser = repeatUser;
    }

    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", msg=" + msg +
                ", repeatUser=" + repeatUser +
                '}';
    }
}
