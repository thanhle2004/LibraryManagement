package com.src.view.manageBooks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.src.auth.DatabaseAccessManager;

public class BookManageTable {
    public void loadBookData(JTable BookManageTable) {
        String query = "SELECT b.ISBN, b.Title, " +
                       "a.First_name, a.Last_name, " +
                       "g.MainGenre_name, " +
                       "b.published_day, b.Status " +
                       "FROM book b " +
                       "JOIN author a ON b.Author_id = a.Author_id " +
                       "JOIN genre g ON b.MainGenre_id = g.MainGenre_id";
        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) BookManageTable.getModel();
            model.setRowCount(0); 
    
            while (rs.next()) {
                Object[] row = {
                    rs.getString("ISBN"),
                    rs.getString("Title"),
                    rs.getString("First_name") + " " + rs.getString("Last_name"),
                    rs.getString("MainGenre_name"),
                    rs.getDate("published_day"),
                    rs.getString("Status")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSearchResults(JTable table, List<Map<String, Object>> books) {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0);
    for (Map<String, Object> book : books) {
        model.addRow(new Object[]{
                book.get("ISBN"),
                book.get("Title"),
                book.get("Author"),
                book.get("MainGenre"),
                book.get("published_day"),
                book.get("Status")
        });
    }
}
    
}
