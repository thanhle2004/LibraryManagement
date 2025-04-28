package com.src.view.manageBorrower;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.src.auth.DatabaseAccessManager;

public class BorrowerManageTable {
    public void loadBorrowerData(JTable BorrowerTable) {
        String query = "SELECT Borrower_id, CONCAT(First_name, ' ', Last_Name) AS Name, birthday, Address, Email, Phone_number FROM borrower";
 
            try (Connection conn = DatabaseAccessManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
                ResultSet rs = stmt.executeQuery();
                DefaultTableModel model = (DefaultTableModel) BorrowerTable.getModel();
                model.setRowCount(0); 
    
                while (rs.next()) {
                    Object[] row = {
                        rs.getString("Borrower_id"),
                        rs.getString("Name"),
                        rs.getString("birthday"),
                        rs.getString("Address"),
                        rs.getString("Email"),
                        rs.getString("Phone_number")
                };
                model.addRow(row);
                }
            } catch (Exception e) {
            e.printStackTrace();
            }
        }
    

    public void loadSearchResults(JTable table, List<Map<String, Object>> borrowers) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 

        for (Map<String, Object> borrower : borrowers) {
            
            String fullName = (borrower.get("First_name") != null ? borrower.get("First_name") : "") + 
                             " " + 
                             (borrower.get("Last_name") != null ? borrower.get("Last_name") : "");

            model.addRow(new Object[]{
                borrower.get("Borrower_id"),
                fullName.trim(),
                borrower.get("birthday"),
                borrower.get("Address"),
                borrower.get("Email"),
                borrower.get("Phone_number")
            });
        }
    }
}
