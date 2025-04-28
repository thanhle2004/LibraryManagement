package com.src.view.manageStaff;

import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.src.dao.StaffDAO;

public class StaffManageTable {

    public void loadStaffData(JTable manageTable) {
        DefaultTableModel model = (DefaultTableModel) manageTable.getModel();
        model.setRowCount(0); 
        try {
            StaffDAO staffDAO = new StaffDAO();
            Object staffData = staffDAO.findAll();
            if (staffData == null || !(staffData instanceof List)) {
                System.out.println("No staff data found or invalid data type: " + staffData);
                return;
            }
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> staffList = (List<Map<String, Object>>) staffData;
            for (Map<String, Object> staff : staffList) {
                Object[] row = new Object[8]; 
                row[0] = staff.get("Manager_id");
                row[1] = staff.get("First_name");
                row[2] = staff.get("Last_name");
                row[3] = staff.get("Email");
                row[4] = staff.get("Phone_number");
                row[5] = staff.get("supervisor_username");
                row[6] = staff.get("birthday");
                row[7] = staff.get("address");
                model.addRow(row);
            }
            manageTable.revalidate();
            manageTable.repaint();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading staff data: " + e.getMessage());
        }
    }

    public void loadSearchResults(JTable manageTable, Object searchStaff) {
        DefaultTableModel model = (DefaultTableModel) manageTable.getModel();
        model.setRowCount(0);
        if (searchStaff == null || !(searchStaff instanceof List)) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> staffList = (List<Map<String, Object>>) searchStaff;
        for (Map<String, Object> staff : staffList) {
            Object[] row = new Object[8]; 
            row[0] = staff.get("Manager_id");
            row[1] = staff.get("First_name");
            row[2] = staff.get("Last_name");
            row[3] = staff.get("Email");
            row[4] = staff.get("Phone_number");
            row[5] = staff.get("supervisor_username");
            row[6] = staff.get("birthday");
            row[7] = staff.get("address");
            model.addRow(row);
        }
        manageTable.revalidate();
        manageTable.repaint();
    }
}