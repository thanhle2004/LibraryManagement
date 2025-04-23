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

public class ShelfDAO extends AbstractGenericDAO<Map<String, Object>, Integer> {

    @Override
    protected String getTableName() {
        return "shelves";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "Shelves_id";
    }

    @Override
    protected Map<String, Object> mapResultSetToEntity(ResultSet rs) throws SQLException {
        Map<String, Object> shelf = new HashMap<>();
        shelf.put("Shelves_id", rs.getInt("Shelves_id"));
        shelf.put("Shelf_number", rs.getInt("Shelf_number"));
        shelf.put("MainGenre_id", rs.getInt("MainGenre_id"));
        shelf.put("Manager_id", rs.getObject("Manager_id"));
        return shelf;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO shelves (Shelf_number, MainGenre_id, Manager_id) VALUES (?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setInt(1, (Integer) entity.get("Shelf_number"));
        stmt.setInt(2, (Integer) entity.get("MainGenre_id"));
        if (entity.get("Manager_id") != null) {
            stmt.setInt(3, (Integer) entity.get("Manager_id"));
        } else {
            stmt.setNull(3, java.sql.Types.INTEGER);
        }
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE shelves SET Shelf_number=?, MainGenre_id=?, Manager_id=? WHERE Shelves_id=?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setInt(1, (Integer) entity.get("Shelf_number"));
        stmt.setInt(2, (Integer) entity.get("MainGenre_id"));
        if (entity.get("Manager_id") != null) {
            stmt.setInt(3, (Integer) entity.get("Manager_id"));
        } else {
            stmt.setNull(3, java.sql.Types.INTEGER);
        }
        stmt.setInt(4, (Integer) entity.get("Shelves_id"));
    }

    public List<Map<String, Object>> getShelfDetails() {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT s.Shelves_id, s.Shelf_number, g.MainGenre_name, " +
                     "CONCAT(m.First_name, ' ', m.Last_name) AS Manager_Name, " +
                     "m.Email, m.Phone_number " +
                     "FROM shelves s " +
                     "LEFT JOIN Genre g ON s.MainGenre_id = g.Genre_id " +
                     "LEFT JOIN shelfmanager m ON s.Manager_id = m.Manager_id";
    
        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> shelf = new HashMap<>();
                shelf.put("Shelves_id", rs.getInt("Shelves_id"));
                shelf.put("Shelf_number", rs.getInt("Shelf_number"));
                shelf.put("MainGenre", rs.getString("MainGenre_name"));
    
                String managerName = rs.getString("Manager_Name");  
                shelf.put("Manager_Name", managerName); 
    
                shelf.put("Email", rs.getString("Email"));
                shelf.put("Phone_number", rs.getString("Phone_number"));
                results.add(shelf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching shelf details: " + e.getMessage());
        }
    
        return results;
    }
    
}
