package com.src.view.manageBooks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.src.auth.DatabaseAccessManager;

public class ShelfManageTable {
    public void loadShelfData(JTable shelfManageTable) {
        String query = "SELECT s.Shelves_id, s.Shelf_number, " +
                "g.MainGenre_name, g.MainGenre_id, " +
                "m.First_name, m.Last_name, m.Manager_id, m.Email, m.Phone_number " +
                "FROM shelves s " +
                "LEFT JOIN genre g ON s.MainGenre_id = g.MainGenre_id " +
                "LEFT JOIN shelfmanager m ON s.Manager_id = m.Manager_id";

        try (Connection conn = DatabaseAccessManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) shelfManageTable.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                String genre = rs.getString("MainGenre_name");
                String genreDisplay = genre != null ? genre + " (ID: " + rs.getInt("MainGenre_id") + ")" : "";
                String managerName = (rs.getString("First_name") != null ? rs.getString("First_name") : "") + 
                                    " " + 
                                    (rs.getString("Last_name") != null ? rs.getString("Last_name") : "");
                String managerDisplay = managerName.trim() + (rs.getInt("Manager_id") != 0 ? " (ID: " + rs.getInt("Manager_id") + ")" : "");

                Object[] row = {
                        rs.getInt("Shelves_id"),
                        rs.getInt("Shelf_number"),
                        genreDisplay,
                        managerDisplay,
                        rs.getString("Email"),
                        rs.getString("Phone_number")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSearchResults(JTable table, List<Map<String, Object>> shelves) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Map<String, Object> shelf : shelves) {
            String genreDisplay = shelf.get("Genre") != null ? shelf.get("Genre") + " (ID: " + shelf.get("MainGenre_id") + ")" : "";
            String managerDisplay = shelf.get("Manager_Name") != null ? shelf.get("Manager_Name") + " (ID: " + shelf.get("Manager_id") + ")" : "";

            model.addRow(new Object[] {
                shelf.get("Shelf_ID"),
                shelf.get("Shelf_Number"),
                genreDisplay,
                managerDisplay,
                shelf.get("Email"),
                shelf.get("Phone")
            });
        }
    }

    
}