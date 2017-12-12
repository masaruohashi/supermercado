package com.supermercado.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supermercado.models.Provider;

public class ProvidersDAO extends BaseDAO {
  public ProvidersDAO() {
    super();
  }
  
  public static ProvidersDAO getInstance() {
    return new ProvidersDAO();
  }
  
  public List<Provider> getAll() {
    List<Provider> providers = new ArrayList<Provider>();
    try {
      String sql = "SELECT * from providers";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        Provider provider = new Provider();
        provider.setId(result.getInt("id"));
        provider.setName(result.getString("name"));
        provider.setAddress(result.getString("address"));
        providers.add(provider);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return providers;
  }

  public Provider findById(int id) {
    Provider provider = null;
    try {
      String sql = "SELECT * FROM providers WHERE id=?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        provider = new Provider();
        provider.setId(result.getInt("id"));
        provider.setName(result.getString("name"));
        provider.setAddress(result.getString("address"));
        result.close();
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return provider;
  }
  
  public boolean create(Provider provider) {
    try {
      String sql = "INSERT INTO providers (name, address) " +
                   "VALUES (?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setString(1, provider.getName());
      statement.setString(2, provider.getAddress());
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
      String sql = "DELETE FROM providers WHERE id = ?";
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
  
  public boolean update(Provider provider) {
    try {
      String sql = "UPDATE providers " +
                   "SET name = ?, address = ? " +
                   "WHERE id = ?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setString(1, provider.getName());
      statement.setString(2, provider.getAddress());
      statement.setInt(3, provider.getId());
      statement.execute();
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
