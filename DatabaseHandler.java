package com.itep.VisitorManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    public Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/visitor_management", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

