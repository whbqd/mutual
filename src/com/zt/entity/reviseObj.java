package com.zt.entity;

/**
 * Author: ZT
 * Date: 2020/4/21 20:12
 */
/*
* 修改
* msg 修改状态码
* */
public class reviseObj {
    private String user,
                    password;
    private boolean msg = false;

    public reviseObj (boolean msg, String user, String password) {
        this.user = user;
        this.password = password;
        this.msg = msg;
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

    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

}
