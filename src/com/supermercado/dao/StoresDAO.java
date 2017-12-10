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
}
