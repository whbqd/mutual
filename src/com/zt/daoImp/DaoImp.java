package com.zt.daoImp;

import com.zt.dao.Dao;
import com.zt.entity.User;
import jdbc.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: ZT
 * @Date: 2020/4/19 13:11
 */
public class DaoImp implements Dao {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet set = null;
    @Override
    public User login(String user, String password) {
        conn = Jdbc.getConn();
        String Sql = "select * from user where user = ? and password = ?";
        User u = null;
        try {
            stmt = conn.prepareStatement(Sql);
            stmt.setString(1, user);
            stmt.setString(2, password);
            set = stmt.executeQuery();
            if (set.next()) {
                int id = set.getInt("id");
                System.out.println(id);
                String email = set.getString("email");
                u = new User(id, user, password, email);
            }
        } catch (Exception e) { e.printStackTrace(); }
        finally {
            Jdbc.closeDB(conn,stmt);
        }
        return u;
    }
    //注册
    @Override
    public int register(String user, String password, String email) {
        int flag = 0;
        conn = Jdbc.getConn();
        String Sql = "insert into user values(id, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(Sql);
            stmt.setString(1, user);
            stmt.setString(2, password);
            stmt.setString(3, email);
            flag = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    //修改密码
    @Override
    public int revise(String user, String password) {
        int flag = 0;
        conn = Jdbc.getConn();
        String Sql = "update user set password = ? where user = ?";
        try {
            stmt = conn.prepareStatement(Sql);
            stmt.setString(1, password);
            stmt.setString(2, user);
            flag = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    //信息表（全部）
    @Override
    public ResultSet UserAll() {
        conn = Jdbc.getConn();
        String Sql = "select * from user";
        try {
            stmt = conn.prepareStatement(Sql);
            set = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return set;
    }
    //通过id 删除数据


    @Override
    public int delete(int id) {
        conn = Jdbc.getConn();
        int flag = 0;
        String Sql = "delete from user where id = ?";
        try {
            stmt = conn.prepareStatement(Sql);
            stmt.setInt(1, id);
            flag = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //判断用户名是否重复
    @Override
    public boolean judgeUser(String user) {
        conn = Jdbc.getConn();
        String Sql = "select * from user where user = ?";
        try {
            stmt = conn.prepareStatement(Sql);
            stmt.setString(1, user);
            set = stmt.executeQuery();
            if(set.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //判断账号,邮箱正错
    @Override
    public boolean judgeEmail(String user, String email) {
        conn = Jdbc.getConn();
        String Sql = "select * from user where email = ? and user = ?";
        try {
            stmt = conn.prepareStatement(Sql);
            stmt.setString(1, email);
            stmt.setString(2, user);
            set = stmt.executeQuery();
            if(set.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
