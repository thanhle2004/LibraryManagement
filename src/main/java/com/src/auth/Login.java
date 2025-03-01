package com.src.auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
   
    
    private boolean checkLogin(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/librarydb";
        String dbUser = "librarymanager";  
        String dbPass = "chiquoc123"; 
        String query = "SELECT * FROM librarydb.manager WHERE Username = ? AND User_password = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
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

    public boolean login(JTextField usernameField, JPasswordField passwordField) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (checkLogin(username, password)) {
            JOptionPane.showMessageDialog(null, "Đăng nhập thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }  
        JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu!", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
