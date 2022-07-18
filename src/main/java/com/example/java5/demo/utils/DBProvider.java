package com.example.java5.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBProvider {
    private static String DB_URL = "jdbc:mysql://localhost:3306/please_hu";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }

        return connection;
    }
}
