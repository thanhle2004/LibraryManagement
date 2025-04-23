package com.src.view.manageBooks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.src.auth.DatabaseAccessManager;

public class AuthorManageTable {
    public void loadAuthorData(JTable AuthorManageTable) {
        String query = "SELECT Author_id, First_name, Last_name, BirthDate, Nationality, Bio " +
                "FROM Author";
        try (Connection conn = DatabaseAccessManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) AuthorManageTable.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("Author_id"),
                        rs.getString("First_name") + " " + rs.getString("Last_name"),
                        rs.getDate("BirthDate"),
                        rs.getString("Nationality"),
                        rs.getString("Bio")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSearchResults(JTable table, List<Map<String, Object>> authors) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Map<String, Object> author : authors) {
            String fullName = author.get("First_name") + " " + author.get("Last_name");
            model.addRow(new Object[] {
                author.get("Author_id"),
                fullName,  
                author.get("BirthDate"),
                author.get("Nationality"),
                author.get("Bio")
            });
        }
        table.revalidate();
        table.repaint();
    }

}
