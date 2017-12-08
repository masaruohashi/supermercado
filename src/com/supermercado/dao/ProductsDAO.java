package com.supermercado.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supermercado.models.Category;
import com.supermercado.models.Product;

public class ProductsDAO extends BaseDAO{
  public ProductsDAO() {
    super();
  }

  public static ProductsDAO getInstance() {
    return new ProductsDAO();
  }

  public List<Product> getAll() throws SQLException {
    List<Product> products = new ArrayList<Product>();
    try {
      String sql = "SELECT products.*, categories.name AS category FROM products INNER JOIN categories ON products.category_id = categories.id";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        Product product = new Product();
        Category category = new Category();
        product.setId(result.getInt("id"));
        product.setName(result.getString("name"));
        product.setPrice(result.getDouble("price"));
        category.setId(result.getInt("category_id"));
        category.setName(result.getString("category"));
        product.setCategory(category);
        products.add(product);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return products;
  }

  public Product findById(int id) throws SQLException {
    Product product = null;
    try {
      String sql = "SELECT products.*, categories.name AS category FROM products " +
        "INNER JOIN categories ON products.category_id = categories.id WHERE products.id=?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        product = new Product();
        Category category = new Category();
        product.setId(result.getInt("id"));
        product.setName(result.getString("name"));
        product.setPrice(result.getDouble("price"));
        category.setId(result.getInt("category_id"));
        category.setName(result.getString("category"));
        product.setCategory(category);
        result.close();
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return product;
  }
}
