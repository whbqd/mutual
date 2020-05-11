package jdbc;

import java.sql.*;

/**
 * Author: ZT
 * Date: 2020/4/19 13:14
 */
public class Jdbc {
    private static Connection conn = null;
    private static String Driver = "com.mysql.cj.jdbc.Driver",
                    url = "jdbc:mysql://localhost:3306/servlet_db?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&useSSL=false&verifyServerCertificate=false&autoReconnct=true&autoReconnectForPools=true&allowMultiQueries=true",
                    user = "root",
                    password = "907Ztmlda.";
    public static Connection getConn(){
        try {
            Class.forName(Driver);
            conn = DriverManager.getConnection(url, user, password);
            if(conn == null) {
                System.out.println("连接失败");
            }
        }
        catch (Exception ignored) {}
        return conn;
    }
    public static void closeDB(Connection conn, PreparedStatement stmt) {
        try {
            if(conn != null) {
                conn.close();
            }
            if(stmt != null) {
                stmt.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeDB(Connection conn, PreparedStatement stmt, ResultSet set) {
        try {
            if(conn != null) {
                conn.close();
            }
            if(stmt != null) {
                stmt.close();
            }
            if(set != null) {
                set.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
