package com.src.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.src.auth.DatabaseAccessManager;

public class BookDAO extends AbstractGenericDAO<Map<String, Object>, String> {

    @Override
    protected String getTableName() {
        return "book";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "ISBN";
    }

    @Override
    protected Map<String, Object> mapResultSetToEntity(ResultSet rs) throws SQLException {
        Map<String, Object> book = new HashMap<>();
        book.put("ISBN", rs.getString("ISBN"));
        book.put("Title", rs.getString("Title"));
        book.put("Author_id", rs.getInt("Author_id"));
        book.put("MainGenre_id", rs.getInt("MainGenre_id"));
        book.put("published_day", rs.getDate("published_day"));
        book.put("Status", rs.getString("Status"));
        return book;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO book (ISBN, Title, Author_id, MainGenre_id, published_day, Status) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("ISBN"));
        stmt.setString(2, (String) entity.get("Title"));
        stmt.setInt(3, (Integer) entity.get("Author_id"));
        stmt.setInt(4, (Integer) entity.get("MainGenre_id"));
        stmt.setDate(5, (Date) entity.get("published_day"));
        stmt.setString(6, (String) entity.get("Status"));
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE book SET Title=?, Author_id=?, MainGenre_id=?, published_day=?, Status=? WHERE ISBN=?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("Title"));
        stmt.setInt(2, (Integer) entity.get("Author_id"));
        stmt.setInt(3, (Integer) entity.get("MainGenre_id"));
        stmt.setDate(4, (Date) entity.get("published_day"));
        stmt.setString(5, (String) entity.get("Status"));
        stmt.setString(6, (String) entity.get("ISBN"));
    }

    public List<Map<String, Object>> searchBooks(String isbn, String title) {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT b.ISBN, b.Title, CONCAT(a.First_name, ' ', a.Last_name) AS Author, g.MainGenre_name AS MainGenre, b.published_day, b.Status FROM book b INNER JOIN Author a ON b.Author_id = a.Author_id INNER JOIN Genre g ON b.MainGenre_id = g.MainGenre_id WHERE (? IS NULL OR b.ISBN = ?) AND (? IS NULL OR b.Title LIKE ?)";

        try (Connection conn = DatabaseAccessManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            stmt.setString(2, isbn);
            stmt.setString(3, title);
            stmt.setString(4, title);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> book = new HashMap<>();
                book.put("ISBN", rs.getString("ISBN"));
                book.put("Title", rs.getString("Title"));
                book.put("Author", rs.getString("Author"));
                book.put("MainGenre", rs.getString("MainGenre"));
                book.put("published_day", rs.getDate("published_day"));
                book.put("Status", rs.getString("Status"));
                results.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error searching books: " + e.getMessage());
        }
        return results;
    }

}
