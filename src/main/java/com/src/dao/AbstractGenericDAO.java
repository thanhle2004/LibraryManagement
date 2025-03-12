package com.src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.src.auth.DatabaseAccessManager;

public abstract class AbstractGenericDAO<T, K> implements GenericDAO<T, K> {

   
    protected abstract String getTableName();

    
    protected abstract String getPrimaryKeyColumn();

   
    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;

 
    protected abstract String getInsertQuery();


    protected abstract void setInsertParameters(PreparedStatement stmt, T entity) throws SQLException;

   
    protected abstract String getUpdateQuery();

  
    protected abstract void setUpdateParameters(PreparedStatement stmt, T entity) throws SQLException;

    @Override
    public T findById(K key) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + getPrimaryKeyColumn() + " = ?";
        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, key);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToEntity(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName();
        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void insert(T entity) {
        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(getInsertQuery())) {
            setInsertParameters(stmt, entity);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T entity) {
        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(getUpdateQuery())) {
            setUpdateParameters(stmt, entity);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(K key) {
        String sql = "DELETE FROM " + getTableName() + " WHERE " + getPrimaryKeyColumn() + " = ?";
        try (Connection conn = DatabaseAccessManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, key);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
