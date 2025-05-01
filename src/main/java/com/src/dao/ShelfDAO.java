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
        return "INSERT INTO shelves (Shelves_id, Shelf_number, MainGenre_id, Manager_id) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        Integer shelvesId = (Integer) entity.get("Shelves_id");
        if (shelvesId == null) {
            throw new IllegalArgumentException("Shelves_id is required but was not provided.");
        }
        stmt.setInt(1, shelvesId);

        Integer shelfNumber = (Integer) entity.get("Shelf_number");
        if (shelfNumber == null) {
            throw new IllegalArgumentException("Shelf_number is required but was not provided.");
        }
        stmt.setInt(2, shelfNumber);

        Integer mainGenreId = (Integer) entity.get("MainGenre_id");
        if (mainGenreId == null) {
            throw new IllegalArgumentException("MainGenre_id is required but was not provided.");
        }
        stmt.setInt(3, mainGenreId);

        if (entity.get("Manager_id") != null) {
            stmt.setInt(4, (Integer) entity.get("Manager_id"));
        } else {
            stmt.setNull(4, java.sql.Types.INTEGER);
        }
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE shelves SET Shelf_number = ?, MainGenre_id = ?, Manager_id = ? WHERE Shelves_id = ?";
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

    public List<Map<String, Object>> searchShelves(String keyword) {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT " +
                     "    s.Shelves_id AS Shelf_ID, " +
                     "    s.Shelf_number AS Shelf_Number, " +
                     "    g.MainGenre_name AS Genre, " +
                     "    g.MainGenre_id, " +
                     "    CONCAT(m.First_name, ' ', m.Last_name) AS Manager_Name, " +
                     "    m.Manager_id, " +
                     "    m.Email AS Email, " +
                     "    m.Phone_number AS Phone " +
                     "FROM " +
                     "    shelves s " +
                     "LEFT JOIN " +
                     "    genre g ON s.MainGenre_id = g.MainGenre_id " +
                     "LEFT JOIN " +
                     "    shelfmanager m ON s.Manager_id = m.Manager_id " +
                     "WHERE " +
                     "    CAST(s.Shelves_id AS CHAR) LIKE ? " +
                     "    OR CAST(s.Shelf_number AS CHAR) LIKE ? " +
                     "    OR g.MainGenre_name LIKE ? " +
                     "    OR m.First_name LIKE ? " +
                     "    OR m.Last_name LIKE ? " +
                     "    OR m.Email LIKE ? " +
                     "    OR m.Phone_number LIKE ?";

        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + keyword + "%";
            for (int i = 1; i <= 7; i++) {
                stmt.setString(i, searchPattern);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> shelf = new HashMap<>();
                shelf.put("Shelf_ID", rs.getInt("Shelf_ID"));
                shelf.put("Shelf_Number", rs.getInt("Shelf_Number"));
                shelf.put("Genre", rs.getString("Genre"));
                shelf.put("MainGenre_id", rs.getInt("MainGenre_id"));
                shelf.put("Manager_Name", rs.getString("Manager_Name"));
                shelf.put("Manager_id", rs.getObject("Manager_id"));
                shelf.put("Email", rs.getString("Email"));
                shelf.put("Phone", rs.getString("Phone"));
                results.add(shelf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error searching shelves: " + e.getMessage());
        }
        System.out.println("Found shelves: " + results.size());
        return results;
    }

    public Map<String, Object> getById(int shelfId) {
        String sql = "SELECT Shelves_id, Shelf_number, MainGenre_id, Manager_id FROM shelves WHERE Shelves_id = ?";
        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, shelfId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Map<String, Object> shelf = new HashMap<>();
                shelf.put("Shelves_id", rs.getInt("Shelves_id"));
                shelf.put("Shelf_number", rs.getInt("Shelf_number"));
                shelf.put("MainGenre_id", rs.getInt("MainGenre_id"));
                shelf.put("Manager_id", rs.getObject("Manager_id"));
                return shelf;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving shelf with ID " + shelfId + ": " + e.getMessage());
        }
    }


    public Map<String, Object> getMainGenreId(int shelfId) {
        String sql = "SELECT MainGenre_id FROM shelves WHERE Shelves_id = ?";
        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, shelfId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Map<String, Object> shelf = new HashMap<>();
                shelf.put("MainGenre_id", rs.getInt("MainGenre_id"));
                return shelf;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving shelf with ID " + shelfId + ": " + e.getMessage());
        }
    }

    public Map<String, Object> getManager_id(int shelfId) {
        String sql = "SELECT Manager_id FROM shelves WHERE Shelves_id = ?";
        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, shelfId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Map<String, Object> shelf = new HashMap<>();
                shelf.put("Manager_id", rs.getObject("Manager_id"));
                return shelf;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving shelf with ID " + shelfId + ": " + e.getMessage());
        }
    }
}