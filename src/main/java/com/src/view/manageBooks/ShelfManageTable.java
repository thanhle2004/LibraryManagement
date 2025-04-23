package com.src.view.manageBooks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.src.auth.DatabaseAccessManager;

public class ShelfManageTable {
    public void loadShelfData(JTable shelfManageTable) {
        String query = "SELECT s.Shelves_id, s.Shelf_number, " +
                "g.MainGenre_name, " +
                "m.First_name, m.Last_name, m.Email, m.Phone_number " +
                "FROM Shelves s " +
                "LEFT JOIN Genre g ON s.MainGenre_id = g.MainGenre_id " +
                "LEFT JOIN ShelfManager m ON s.Manager_id = m.Manager_id";

        try (Connection conn = DatabaseAccessManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) shelfManageTable.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("Shelves_id"),
                        rs.getInt("Shelf_number"),
                        rs.getString("MainGenre_name"),
                        rs.getString("First_name") + " " + rs.getString("Last_name"),
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
