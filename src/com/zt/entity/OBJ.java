package com.zt.entity;

import java.util.List;

/**
 * @Author: ZT
 * @Date: 2020/4/22 18:19
 */

/*
* msg => 状态码
* UserList (封装)=>  userAll数组
*
*
* */
public class OBJ {
    private boolean msg = true;
    private List<userAll> UserList;

    public OBJ(boolean msg, List<userAll> userList) {
        this.msg = msg;
        UserList = userList;
    }

    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

    public List<userAll> getUserList() {
        return UserList;
    }

    public void setUserList(List<userAll> userList) {
        UserList = userList;
    }

    @Override
    public String toString() {
        return "OBJ{" +
                "msg=" + msg +
                ", UserList=" + UserList +
                '}';
    }
}
