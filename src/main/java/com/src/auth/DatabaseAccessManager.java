package com.src.auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseAccessManager {
    private static final String URL = "jdbc:mysql://localhost:3306/libdb";
    private static final String USER = "root";//librarymanager
    private static final String PASSWORD = "giangmysql";//chiquoc123

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean checkLogin(String username, String password) {
        String query = "SELECT * FROM librarydb.manager WHERE Username = ? AND User_password = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); 

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}