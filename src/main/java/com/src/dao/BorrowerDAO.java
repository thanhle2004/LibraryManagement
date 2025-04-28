package com.src.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BorrowerDAO extends AbstractGenericDAO<Map<String, Object>, String> {

    @Override
    protected String getTableName() {
        return "borrower";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "Borrower_id";
    }

    @Override
    protected Map<String, Object> mapResultSetToEntity(ResultSet rs) throws SQLException {
        Map<String, Object> borrower = new HashMap<>();
        borrower.put("Borrower_id", rs.getString("Borrower_id"));
        borrower.put("First_name", rs.getString("First_name"));
        borrower.put("Last_name", rs.getString("Last_name"));   ////////
        borrower.put("born_year", rs.getInt("born_year"));
        borrower.put("Address", rs.getString("Address"));
        borrower.put("Email", rs.getString("Email"));
        borrower.put("Phone_number", rs.getString("Phone_number"));
        return borrower;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO borrower (First_name, Last_name, born_year, Address, Email, Phone_number) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("First_name"));
        stmt.setString(2, (String) entity.get("Last_name"));
        stmt.setInt(3, (Integer) entity.get("born_year"));
        stmt.setString(4, (String) entity.get("Address"));
        stmt.setString(5, (String) entity.get("Email"));
        stmt.setString(6, (String) entity.get("Phone_number"));
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE borrower SET First_name = ?, Last_name = ?, born_year = ?, Address = ?, Email = ?, Phone_number = ? WHERE Borrower_id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("First_name"));
        stmt.setString(2, (String) entity.get("Last_name"));
        stmt.setInt(3, (Integer) entity.get("born_year"));
        stmt.setString(4, (String) entity.get("Address"));
        stmt.setString(5, (String) entity.get("Email"));
        stmt.setString(6, (String) entity.get("Phone_number"));
        stmt.setString(7, (String) entity.get("Borrower_id"));
    }
}
