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
        staff.put("birthday", rs.getString("birthday"));
        staff.put("address", rs.getString("address"));
        return staff;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO shelfmanager (Manager_id, First_name, Last_name, Email, Phone_number, supervisor_username, birthday, address) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setInt(1, (Integer) entity.get("Manager_id"));
        stmt.setString(2, (String) entity.get("First_name"));
        stmt.setString(3, (String) entity.get("Last_name"));
        stmt.setString(4, (String) entity.get("Email"));
        stmt.setString(5, (String) entity.get("Phone_number"));
        stmt.setString(6, (String) entity.get("supervisor_username"));
        String birthday = (String) entity.get("birthday");
        stmt.setString(7, birthday != null && !birthday.isEmpty() ? birthday : null);
        String address = (String) entity.get("address");
        stmt.setString(8, address != null && !address.isEmpty() ? address : null);
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE shelfmanager SET First_name = ?, Last_name = ?, Email = ?, Phone_number = ?, " +
                "supervisor_username = ?, birthday = ?, address = ? WHERE Manager_id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("First_name"));
        stmt.setString(2, (String) entity.get("Last_name"));
        stmt.setString(3, (String) entity.get("Email"));
        stmt.setString(4, (String) entity.get("Phone_number"));
        stmt.setString(5, (String) entity.get("supervisor_username"));
        String birthday = (String) entity.get("birthday");
        stmt.setString(6, birthday != null && !birthday.isEmpty() ? birthday : null);
        String address = (String) entity.get("address");
        stmt.setString(7, address != null && !address.isEmpty() ? address : null);
        stmt.setInt(8, (Integer) entity.get("Manager_id"));
    }

    public Map<String, Object> getById(Integer id) {
        return findById(id);
    }

    public Object searchStaff(String searchText) {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT " +
                     "    Manager_id, " +
                     "    First_name, " +
                     "    Last_name, " +
                     "    Email, " +
                     "    Phone_number, " +
                     "    supervisor_username, " +
                     "    birthday, " +
                     "    address " +
                     "FROM " +
                     "    shelfmanager " +
                     "WHERE " +
                     "    CAST(Manager_id AS CHAR) LIKE ? " +
                     "    OR CONCAT(First_name, ' ', Last_name) LIKE ? " +
                     "    OR Email LIKE ? " +
                     "    OR Phone_number LIKE ? " +
                     "    OR supervisor_username LIKE ? " +
                     "    OR birthday LIKE ? " +
                     "    OR address LIKE ?";

        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + searchText + "%";
            for (int i = 1; i <= 7; i++) {
                stmt.setString(i, searchPattern);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> staff = mapResultSetToEntity(rs);
                results.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error searching staff: " + e.getMessage());
        }
        System.out.println("Found staff: " + results.size());
        return results;
    }
}