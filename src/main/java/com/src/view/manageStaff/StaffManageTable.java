package com.src.view.manageStaff;

import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.src.dao.StaffDAO;

public class StaffManageTable {

    public void loadStaffData(JTable table) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows

            StaffDAO staffDAO = new StaffDAO();
            List<Map<String, Object>> staffList = staffDAO.findAll();

            for (Map<String, Object> staff : staffList) {
                Object[] row = new Object[] {
                    staff.get("Manager_id"),
                    staff.get("First_name"),
                    staff.get("Last_name"),
                    staff.get("Email"),
                    staff.get("Phone_number"),
                    staff.get("supervisor_username")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}