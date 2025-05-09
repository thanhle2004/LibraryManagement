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
        return "UPDATE book SET Title=?, Author_id=?, MainGenre_id=?, published_day=? WHERE ISBN=?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("Title"));
        stmt.setInt(2, (Integer) entity.get("Author_id"));
        stmt.setInt(3, (Integer) entity.get("MainGenre_id"));
        stmt.setDate(4, (Date) entity.get("published_day"));
        stmt.setString(5, (String) entity.get("ISBN"));
    }

    public List<Map<String, Object>> searchBooks(String keyword) {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT b.ISBN, b.Title, CONCAT(a.First_name, ' ', a.Last_name) AS Author, g.MainGenre_name AS MainGenre, b.published_day, b.Status "
                +
                "FROM book b " +
                "INNER JOIN Author a ON b.Author_id = a.Author_id " +
                "INNER JOIN Genre g ON b.MainGenre_id = g.MainGenre_id " +
                "WHERE b.ISBN LIKE ? " +
                "OR b.Title LIKE ? " +
                "OR CONCAT(a.First_name, ' ', a.Last_name) LIKE ? " +
                "OR g.MainGenre_name LIKE ? " +
                "OR CAST(b.published_day AS CHAR) LIKE ? " +
                "OR b.Status LIKE ?";

        try (Connection conn = DatabaseAccessManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);
            stmt.setString(4, searchPattern);
            stmt.setString(5, searchPattern);
            stmt.setString(6, searchPattern);

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

    public Map<String, Object> getById(String isbn) {
        String sql = "SELECT ISBN, Title, Author_id, MainGenre_id, published_day FROM Book WHERE ISBN = ?";
        try (Connection conn = DatabaseAccessManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Map<String, Object> book = new HashMap<>();
                book.put("ISBN", rs.getString("ISBN"));
                book.put("Title", rs.getString("Title"));
                book.put("Author_id", rs.getInt("Author_id"));
                book.put("MainGenre_id", rs.getInt("MainGenre_id"));
                book.put("published_day", rs.getDate("published_day"));
                return book;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error" + e.getMessage());
        }
    }

    public Map<String, Object> getAuthorID(String isbn) {
        String sql = "SELECT Author_id FROM Book WHERE ISBN = ?";
        try (Connection conn = DatabaseAccessManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Map<String, Object> book = new HashMap<>();
                book.put("Author_id", rs.getInt("Author_id"));
                return book;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error" + e.getMessage());
        }
    }

    public Map<String, Object> getGenreID(String isbn) {
        String sql = "SELECT MainGenre_id FROM Book WHERE ISBN = ?";
        try (Connection conn = DatabaseAccessManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Map<String, Object> book = new HashMap<>();
                book.put("MainGenre_id", rs.getInt("MainGenre_id"));
                return book;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error" + e.getMessage());
        }
    }

    public boolean isGenreUsed(String genreId) throws SQLException {
        String query = "SELECT * FROM book WHERE MainGenre_id = ?";
        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, genreId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); 
            }
        }
    }
    

}
