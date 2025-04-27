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

public class ManagerDAO extends AbstractGenericDAO<Map<String, Object>, String> {

    @Override
    protected String getTableName() {
        return "manager";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "Username";
    }

    @Override
    protected Map<String, Object> mapResultSetToEntity(ResultSet rs) throws SQLException {
        Map<String, Object> manager = new HashMap<>();
        manager.put("Username", rs.getString("Username"));
        manager.put("User_password", rs.getString("User_password"));
        return manager;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO manager (Username, User_password) VALUES (?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("Username"));
        stmt.setString(2, (String) entity.get("User_password"));
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE manager SET User_password = ? WHERE Username = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("User_password"));
        stmt.setString(2, (String) entity.get("Username"));
    }

    public Map<String, Object> getById(String id) {
        return findById(id);
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        String query = "SELECT Username FROM manager";
        
        try (Connection connection = DatabaseAccessManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usernames.add(rs.getString("Username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usernames;
    }
}