package com.src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.src.auth.DatabaseAccessManager;

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
        borrower.put("Last_name", rs.getString("Last_name"));
        borrower.put("Email", rs.getString("Email"));
        borrower.put("Address", rs.getString("Address"));
        borrower.put("Phone_number", rs.getString("Phone_number"));
        borrower.put("born_year", rs.getInt("born_year"));
        return borrower;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO borrower (Borrower_id, First_name, Last_name, Email, Address, Phone_number, born_year) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("Borrower_id"));
        stmt.setString(2, (String) entity.get("First_name"));
        stmt.setString(3, (String) entity.get("Last_name"));
        stmt.setString(4, (String) entity.get("Email"));
        stmt.setString(5, (String) entity.get("Address"));
        stmt.setString(6, (String) entity.get("Phone_number"));
        stmt.setInt(7, (Integer) entity.get("born_year"));
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE borrower SET First_name = ?, Last_name = ?, Email = ?, Address = ?, Phone_number = ?, born_year = ? WHERE Borrower_id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("First_name"));
        stmt.setString(2, (String) entity.get("Last_name"));
        stmt.setString(3, (String) entity.get("Email"));
        stmt.setString(4, (String) entity.get("Address"));
        stmt.setString(5, (String) entity.get("Phone_number"));
        stmt.setInt(6, (Integer) entity.get("born_year"));
        stmt.setString(7, (String) entity.get("Borrower_id"));
    }

    public Map<String, Object> getById(String borrowerId) throws SQLException {
        return findById(borrowerId);
    }

    public List<Map<String, Object>> searchBorrowers(String searchTerm) {
        List<Map<String, Object>> borrowers = new ArrayList<>();

        String query = "SELECT * FROM borrower WHERE Borrower_id LIKE ? " +
                      "OR CONCAT(UPPER(First_name), ' ', UPPER(Last_name)) LIKE ? " +
                      "OR Email LIKE ? " +
                      "OR Address LIKE ? " +
                      "OR Phone_number LIKE ? " +
                      "OR CAST(born_year AS CHAR) LIKE ?";

        try (Connection connection = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            String likeTerm = "%" + searchTerm + "%";
            String upperLikeTerm = "%" + searchTerm.toUpperCase() + "%";

            stmt.setString(1, likeTerm); 
            stmt.setString(2, upperLikeTerm); 
            stmt.setString(3, likeTerm); 
            stmt.setString(4, likeTerm); 
            stmt.setString(5, likeTerm); 
            stmt.setString(6, likeTerm); 

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    borrowers.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching borrowers: " + e.getMessage());
            e.printStackTrace();
        }
        return borrowers;
    }
}