package com.geekuniversity.java.week05.springboot.homework06;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnectionUtil {

    private static final String DRIVER_MANAGER = "com.mysql.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncode=true&characterEncoding=utf-8";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "password";

    private static List<Connection> connectionPools = new ArrayList<>();

    public static Connection getConnection() {
        // 实例化 JDBC Driver 中指定的驱动类实例
        Driver driver = null;
        try {
            driver = (Driver) (Class.forName(DRIVER_MANAGER).newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 注册 JDBC 驱动程序
        try {
            DriverManager.registerDriver(driver);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}