package com.src.view.viewRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.src.auth.DatabaseAccessManager;

public class BorrowingManageTable {
    public void loadBorrowingData(JTable borrowingTable) {
        String query = "SELECT Borrowing_id, ISBN, Borrower_id, BorrowerDate, DueDate, ReturnDay, Fine_AMOUNT " +
                      "FROM Borrowing";
        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) borrowingTable.getModel();
            model.setRowCount(0);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {
                Date issueDate = rs.getDate("BorrowerDate");
                Date dueDate = rs.getDate("DueDate");
                Date returnDate = rs.getDate("ReturnDay");
                long daysOverdue = 0;
                if (returnDate != null && returnDate.after(dueDate)) {
                    long diffInMillies = returnDate.getTime() - dueDate.getTime();
                    daysOverdue = diffInMillies / (1000 * 60 * 60 * 24);
                } else if (returnDate == null && dueDate.before(new Date())) {
                    long diffInMillies = new Date().getTime() - dueDate.getTime();
                    daysOverdue = diffInMillies / (1000 * 60 * 60 * 24);
                }

                Object[] row = {
                    rs.getInt("Borrowing_id"),
                    rs.getString("ISBN"),
                    rs.getInt("Borrower_id"),
                    issueDate != null ? dateFormat.format(issueDate) : "",
                    dueDate != null ? dateFormat.format(dueDate) : "",
                    returnDate != null ? dateFormat.format(returnDate) : "",
                    daysOverdue,
                    rs.getBigDecimal("Fine_AMOUNT") != null ? "$" + rs.getBigDecimal("Fine_AMOUNT").toString() : "$0"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSearchResults(JTable table, List<Map<String, Object>> borrowings) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (Map<String, Object> borrowing : borrowings) {
            Date issueDate = (Date) borrowing.get("BorrowerDate");
            Date returnDate = (Date) borrowing.get("ReturnDay");
            Date dueDate = (Date) borrowing.get("DueDate");

            long daysOverdue = 0;
            if (returnDate != null && returnDate.after(dueDate)) {
                long diffInMillies = returnDate.getTime() - dueDate.getTime();
                daysOverdue = diffInMillies / (1000 * 60 * 60 * 24);
            } else if (returnDate == null && dueDate.before(new Date())) {
                long diffInMillies = new Date().getTime() - dueDate.getTime();
                daysOverdue = diffInMillies / (1000 * 60 * 60 * 24);
            }

            Object[] row = {
                borrowing.get("Borrowing_id"),
                borrowing.get("ISBN"),
                borrowing.get("Borrower_id"),
                issueDate != null ? dateFormat.format(issueDate) : "",
                dueDate != null ? dateFormat.format(dueDate) : "",
                returnDate != null ? dateFormat.format(returnDate) : "",
                daysOverdue,
                borrowing.get("Fine_AMOUNT") != null ? "$" + borrowing.get("Fine_AMOUNT").toString() : "$0"
            };
            model.addRow(row);
        }
        table.revalidate();
        table.repaint();
    }
}
