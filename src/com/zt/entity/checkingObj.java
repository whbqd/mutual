package com.zt.entity;

/**
 * @Author: ZT
 * @Date: 2020/4/21 20:52
 */

/*
* 验证 账号 邮箱
* msg 验证状态码
*
* */
public class checkingObj {
    private String user,
                    email;
    boolean msg = false;

    public checkingObj(String user, String email, boolean msg) {
        this.user = user;
        this.email = email;
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "checkingObj{" +
                "user='" + user + '\'' +
                ", email='" + email + '\'' +
                ", msg=" + msg +
                '}';
    }
}
