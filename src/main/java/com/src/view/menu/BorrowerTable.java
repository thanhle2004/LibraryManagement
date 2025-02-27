package com.src.view.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.src.auth.DatabaseAccessManager;

public class BorrowerTable {
    public void loadBorrowerData(JTable BorrowerTable) {
    String query = "SELECT Borrower_id, First_name, Email, Phone_number FROM borrower";  
        try (Connection conn = DatabaseAccessManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ) {
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) BorrowerTable.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            while (rs.next()) {
                Object[] row = {
                    rs.getString("Borrower_id"),
                    rs.getString("First_name"),
                    rs.getString("Email"),
                    rs.getString("Phone_number")
            };
            model.addRow(row);
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

}
