package com.supermercado.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supermercado.models.Store;

public class StoresDAO extends BaseDAO{
  public StoresDAO() {
    super();
  }

  public static StoresDAO getInstance() {
    return new StoresDAO();
  }

  public List<Store> getAll() {
    List<Store> stores = new ArrayList<Store>();
    try {
      String sql = "SELECT * from stores";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        Store store = new Store();
        store.setId(result.getInt("id"));
        store.setName(result.getString("name"));
        store.setAddress(result.getString("address"));
        stores.add(store);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return stores;
  }

  public Store findById(int id) {
    Store store = null;
    try {
      String sql = "SELECT * FROM stores WHERE id=?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        store = new Store();
        store.setId(result.getInt("id"));
        store.setName(result.getString("name"));
        store.setAddress(result.getString("address"));
        result.close();
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return store;
  }
  
  public boolean create(Store store) {
    try {
      String sql = "INSERT INTO stores (name, address) " +
                   "VALUES (?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setString(1, store.getName());
      statement.setString(2, store.getAddress());
      statement.execute();
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  public boolean delete(int id) {
    try {
      String sql = "DELETE FROM stores WHERE id = ?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      statement.execute();
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  public boolean update(Store store) {
    try {
      String sql = "UPDATE stores " +
                   "SET name = ?, address = ? " +
                   "WHERE id = ?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setString(1, store.getName());
      statement.setString(2, store.getAddress());
      statement.setInt(3, store.getId());
      statement.execute();
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
