package com.zt.entity;

/**
 * @Author: ZT
 * @Date: 2020/4/22 17:58
 */
public class Msg {
    private boolean msg = false;

    public Msg() {
    }

    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg=" + msg +
                '}';
    }
}
