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

public class AuthorDAO extends AbstractGenericDAO<Map<String, Object>, Integer> {

    @Override
    protected String getTableName() {
        return "Author";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "Author_id";
    }

    @Override
    protected Map<String, Object> mapResultSetToEntity(ResultSet rs) throws SQLException {
        Map<String, Object> author = new HashMap<>();
        author.put("Author_id", rs.getInt("Author_id"));
        author.put("First_name", rs.getString("First_name"));
        author.put("Last_name", rs.getString("Last_name"));
        author.put("BirthDate", rs.getDate("BirthDate"));
        author.put("Nationality", rs.getString("Nationality"));
        return author;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO Author (Author_id, First_name, Last_name, BirthDate, Nationality) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setInt(1, (Integer) entity.get("Author_id"));
        stmt.setString(2, (String) entity.get("First_name"));
        stmt.setString(3, (String) entity.get("Last_name"));
        stmt.setDate(4, (java.sql.Date) entity.get("BirthDate"));
        stmt.setString(5, (String) entity.get("Nationality"));
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Author SET First_name=?, Last_name=?, BirthDate=?, Nationality=? WHERE Author_id=?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("First_name"));
        stmt.setString(2, (String) entity.get("Last_name"));
        stmt.setDate(3, (java.sql.Date) entity.get("BirthDate"));
        stmt.setString(4, (String) entity.get("Nationality"));
        stmt.setInt(5, (Integer) entity.get("Author_id"));
    }

    public List<Map<String, Object>> searchAuthors(Integer authorId, String name) {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT Author_id, First_name, Last_name, BirthDate, Nationality FROM Author WHERE (? IS NULL OR Author_id = ?) AND (? IS NULL OR CONCAT(First_name, ' ', Last_name) LIKE ?)";

        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (authorId == null) {
                stmt.setNull(1, java.sql.Types.INTEGER);
                stmt.setNull(2, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(1, authorId);
                stmt.setInt(2, authorId);
            }
            stmt.setString(3, name);
            stmt.setString(4, name);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> author = new HashMap<>();
                author.put("Author_id", rs.getInt("Author_id"));
                author.put("First_name", rs.getString("First_name"));
                author.put("Last_name", rs.getString("Last_name"));
                author.put("BirthDate", rs.getDate("BirthDate"));
                author.put("Nationality", rs.getString("Nationality"));
                results.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error searching authors: " + e.getMessage());
        }
        return results;
    }
}