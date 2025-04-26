package com.src.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StaffDAO extends AbstractGenericDAO<Map<String, Object>, Integer> {

    @Override
    protected String getTableName() {
        return "shelfmanager";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "Manager_id";
    }

    @Override
    protected Map<String, Object> mapResultSetToEntity(ResultSet rs) throws SQLException {
        Map<String, Object> staff = new HashMap<>();
        staff.put("Manager_id", rs.getInt("Manager_id"));
        staff.put("First_name", rs.getString("First_name"));
        staff.put("Last_name", rs.getString("Last_name"));
        staff.put("Email", rs.getString("Email"));
        staff.put("Phone_number", rs.getString("Phone_number"));
        staff.put("supervisor_username", rs.getString("supervisor_username"));
        return staff;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO shelfmanager (Manager_id, First_name, Last_name, Email, Phone_number, supervisor_username) " +
               "VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setInt(1, (Integer) entity.get("Manager_id"));
        stmt.setString(2, (String) entity.get("First_name"));
        stmt.setString(3, (String) entity.get("Last_name"));
        stmt.setString(4, (String) entity.get("Email"));
        stmt.setString(5, (String) entity.get("Phone_number"));
        stmt.setString(6, (String) entity.get("supervisor_username"));
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE shelfmanager SET First_name = ?, Last_name = ?, Email = ?, Phone_number = ?, " +
               "supervisor_username = ? WHERE Manager_id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("First_name"));
        stmt.setString(2, (String) entity.get("Last_name"));
        stmt.setString(3, (String) entity.get("Email"));
        stmt.setString(4, (String) entity.get("Phone_number"));
        stmt.setString(5, (String) entity.get("supervisor_username"));
        stmt.setInt(6, (Integer) entity.get("Manager_id"));
    }

    
    public Map<String, Object> getById(Integer id) {      
        return findById(id);
    }
}