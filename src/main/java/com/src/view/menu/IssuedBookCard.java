package com.src.view.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.src.auth.DatabaseAccessManager;

public class IssuedBookCard implements MenuCards{

    @Override
    public String showupDB() {
       String query = "SELECT COUNT(*) AS TotalIssuedBooks FROM book where status = ?";
        try(Connection conn = DatabaseAccessManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {    
            stmt.setString(1, "borrowed");
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return String.valueOf(rs.getInt("TotalIssuedBooks"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

}
