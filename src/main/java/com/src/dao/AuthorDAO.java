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
        stmt.setInt(1, (Integer) entity.get("Author_id")); // Dòng này gây lỗi
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

    public List<Map<String, Object>> searchAuthors(String keyword) {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT Author_id, First_name, Last_name, BirthDate, Nationality, Bio FROM Author " +
                "WHERE CAST(Author_id AS CHAR) LIKE ? " +
                "OR CONCAT(First_name, ' ', Last_name )LIKE ? " +
                "OR CAST(BirthDate AS CHAR) LIKE ? " +
                "OR Nationality LIKE ? " +
                "OR Bio LIKE ?";

        try (Connection conn = DatabaseAccessManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);
            stmt.setString(4, searchPattern);
            stmt.setString(5, searchPattern);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> author = new HashMap<>();
                author.put("Author_id", rs.getInt("Author_id"));
                author.put("First_name", rs.getString("First_name"));
                author.put("Last_name", rs.getString("Last_name"));
                author.put("BirthDate", rs.getDate("BirthDate"));
                author.put("Nationality", rs.getString("Nationality"));
                author.put("Bio", rs.getString("Bio"));
                results.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error searching authors: " + e.getMessage());
        }
        System.out.println("Found authors: " + results.size());
        return results;
    }

    public Map<String, Object> getById(int authorId) {
        String sql = "SELECT Author_id, First_name, Last_name, BirthDate, Nationality, Bio FROM Author WHERE Author_id = ?";
        try (Connection conn = DatabaseAccessManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, authorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Map<String, Object> author = new HashMap<>();
                author.put("Author_id", rs.getInt("Author_id"));
                author.put("First_name", rs.getString("First_name"));
                author.put("Last_name", rs.getString("Last_name"));
                author.put("BirthDate", rs.getDate("BirthDate"));
                author.put("Nationality", rs.getString("Nationality"));
                author.put("Bio", rs.getString("Bio"));
                return author;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving author: " + e.getMessage());
        }
    }
}