package com.supermercado.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supermercado.models.Seller;

public class SellersDAO extends BaseDAO{
  public SellersDAO() {
    super();
  }

  public static SellersDAO getInstance() {
    return new SellersDAO();
  }

  public List<Seller> getAll() throws SQLException {
    List<Seller> sellers = new ArrayList<Seller>();
    try {
      String sql = "SELECT * FROM sellers";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        Seller seller = new Seller();
        seller.setId(result.getInt("id"));
        seller.setName(result.getString("name"));
        seller.setCpf(result.getString("cpf"));
        sellers.add(seller);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return sellers;
  }

  public Seller findById(int id) throws SQLException {
    Seller seller = null;
    try {
      String sql = "SELECT * FROM sellers WHERE id=?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        seller = new Seller();
        seller.setId(result.getInt("id"));
        seller.setName(result.getString("name"));
        seller.setCpf(result.getString("cpf"));
        result.close();
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return seller;
  }
}