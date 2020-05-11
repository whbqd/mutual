package com.zt.entity;

import java.util.UUID;

/**
 * Author: ZT
 * Date: 2020/4/22 15:37
 */

/*
* 查询全部信息
* msg => 查询状态码
* */
public class userAll {
    private int id;
    private String user,
                    password,
                    email;
    public userAll(int id, String user, String password, String email) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.email = email;
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


    @Override
    public String toString() {
        return "userAll{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
