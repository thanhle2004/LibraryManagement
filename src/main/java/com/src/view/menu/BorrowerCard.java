package com.src.view.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.src.auth.DatabaseAccessManager;

public class BorrowerCard implements MenuCards{

    @Override
    public String showupDB() {
       String query = "SELECT COUNT(*) AS TotalBorrowers FROM borrower";
        try(Connection conn = DatabaseAccessManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {    
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return String.valueOf(rs.getInt("TotalBorrowers"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

}
