package com.example.bibliotekdb3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static Connection conn;

    static {
        String url = "jdbc:mysql://localhost:3306/bibliotek";
        String user = "root";
        String password = "Alex1990";
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Database connection failed");
            throw new RuntimeException(ex);
        }
    }

    public static Connection getConnection() {
        return conn;
    }

}
