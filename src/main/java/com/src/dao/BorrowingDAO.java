package com.src.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BorrowingDAO extends AbstractGenericDAO<Map<String, Object>, Integer> {

    @Override
    protected String getTableName() {
        return "Borrowing";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "Borrowing_id";
    }

    @Override
    protected Map<String, Object> mapResultSetToEntity(ResultSet rs) throws SQLException {
        Map<String, Object> borrowing = new HashMap<>();
        borrowing.put("Borrowing_id", rs.getInt("Borrowing_id"));
        borrowing.put("ISBN", rs.getString("ISBN"));
        borrowing.put("Borrower_id", rs.getInt("Borrower_id"));
        borrowing.put("BorrowerDate", rs.getDate("BorrowerDate"));
        borrowing.put("DueDate", rs.getDate("DueDate"));
        borrowing.put("ReturnDay", rs.getDate("ReturnDay"));
        borrowing.put("Fine_AMOUNT", rs.getBigDecimal("Fine_AMOUNT"));
        borrowing.put("Status", rs.getString("Status"));
        borrowing.put("Renewed", rs.getBoolean("Renewed"));
        return borrowing;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO Borrowing (Borrowing_id, ISBN, Borrower_id, BorrowerDate, DueDate, ReturnDay, Fine_AMOUNT, Status, Renewed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setInt(1, (Integer) entity.get("Borrowing_id"));
        stmt.setString(2, (String) entity.get("ISBN"));
        stmt.setInt(3, (Integer) entity.get("Borrower_id"));
        stmt.setDate(4, (java.sql.Date) entity.get("BorrowerDate"));
        stmt.setDate(5, (java.sql.Date) entity.get("DueDate"));
        stmt.setDate(6, (java.sql.Date) entity.get("ReturnDay"));
        stmt.setBigDecimal(7, (java.math.BigDecimal) entity.get("Fine_AMOUNT"));
        stmt.setString(8, (String) entity.get("Status"));
        stmt.setBoolean(9, (Boolean) entity.get("Renewed"));
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Borrowing SET ISBN = ?, Borrower_id = ?, BorrowerDate = ?, DueDate = ?, ReturnDay = ?, Fine_AMOUNT = ?, Status = ?, Renewed = ? WHERE Borrowing_id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("ISBN"));
        stmt.setInt(2, (Integer) entity.get("Borrower_id"));
        stmt.setDate(3, (java.sql.Date) entity.get("BorrowerDate"));
        stmt.setDate(4, (java.sql.Date) entity.get("DueDate"));
        stmt.setDate(5, (java.sql.Date) entity.get("ReturnDay"));
        stmt.setBigDecimal(6, (java.math.BigDecimal) entity.get("Fine_AMOUNT"));
        stmt.setString(7, (String) entity.get("Status"));
        stmt.setBoolean(8, (Boolean) entity.get("Renewed"));
        stmt.setInt(9, (Integer) entity.get("Borrowing_id"));
    }
}