package com.src.view.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.src.auth.DatabaseAccessManager;

public class BookTable {
    public void loadBookData(JTable BookTable) {
    String query = "SELECT ISBN, Title, Author_id, published_day FROM book";  
        try (Connection conn = DatabaseAccessManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ) {
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) BookTable.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            while (rs.next()) {
                Object[] row = {
                    rs.getString("ISBN"),
                    rs.getString("Title"),
                    rs.getInt("Author_id"),
                    rs.getDate("published_day")
            };
            model.addRow(row);
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
}
