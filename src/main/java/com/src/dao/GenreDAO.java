package com.src.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GenreDAO extends AbstractGenericDAO<Map<String, Object>, String> {

    public Map<String, Object> getById(int genreId) throws SQLException {
        String idAsString = String.valueOf(genreId);
        return findById(idAsString);
    }

    @Override
    protected String getTableName() {
        return "genre";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "MainGenre_id";
    }

    @Override
    protected Map<String, Object> mapResultSetToEntity(ResultSet rs) throws SQLException {
        Map<String, Object> genre = new HashMap<>();
        genre.put("MainGenre_id", rs.getString("MainGenre_id"));
        genre.put("MainGenre_name", rs.getString("MainGenre_name"));
        return genre;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO genre (MainGenre_id, MainGenre_name) VALUES (?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("MainGenre_id"));
        stmt.setString(2, (String) entity.get("MainGenre_name"));
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE genre SET MainGenre_name = ? WHERE MainGenre_id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement stmt, Map<String, Object> entity) throws SQLException {
        stmt.setString(1, (String) entity.get("MainGenre_name"));
        stmt.setString(2, (String) entity.get("MainGenre_id"));
    }
}