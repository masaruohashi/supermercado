package com.supermercado.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supermercado.models.Category;
import com.supermercado.models.Product;
import com.supermercado.models.Provider;

public class ProductsDAO extends BaseDAO{
  public ProductsDAO() {
    super();
  }

  public static ProductsDAO getInstance() {
    return new ProductsDAO();
  }

  public List<Product> getAll() {
    List<Product> products = new ArrayList<Product>();
    try {
      String sql = "SELECT products.*, categories.name AS category, providers.name AS provider, providers.address FROM products INNER JOIN categories ON products.category_id = categories.id INNER JOIN providers ON products.provider_id = providers.id ORDER BY products.name";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        Product product = new Product();
        Category category = new Category();
        Provider provider = new Provider();
        product.setId(result.getInt("id"));
        product.setName(result.getString("name"));
        product.setPrice(result.getDouble("price"));
        category.setId(result.getInt("category_id"));
        category.setName(result.getString("category"));
        product.setCategory(category);
        provider.setId(result.getInt("provider_id"));
        provider.setName(result.getString("provider"));
        provider.setAddress(result.getString("address"));
        product.setProvider(provider);
        products.add(product);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return products;
  }

  public Product findById(int id) {
    Product product = null;
    try {
      String sql = "SELECT products.*, categories.name AS category FROM products " +
        "INNER JOIN categories ON products.category_id = categories.id " +
        "INNER JOIN providers ON products.provider_id = providers.id " +
        "WHERE products.id=?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        product = new Product();
        product.setId(result.getInt("id"));
        product.setName(result.getString("name"));
        product.setPrice(result.getDouble("price"));
        product.setCategory(CategoriesDAO.getInstance().findById(result.getInt("category_id")));
        product.setProvider(ProvidersDAO.getInstance().findById(result.getInt("provider_id")));
        result.close();
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return product;
  }

  public boolean create(Product product) throws SQLException {
    String sql = "INSERT INTO products (name, price, provider_id, category_id)" +
            "VALUES (?, ?, ?, ?)";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setString(1, product.getName());
      statement.setDouble(2, product.getPrice());
      statement.setInt(3, product.getProvider().getId());
      statement.setInt(4, product.getCategory().getId());
      statement.execute();
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean delete(int id) throws SQLException {
    try {
      String sql = "DELETE FROM products WHERE id = ?";
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

  public boolean edit (Product product) throws SQLException {
    String sql = "UPDATE products " +
            "SET name = ?, price = ?, provider_id = ?, category_id = ? " +
            "WHERE  id = ?";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setString(1, product.getName());
      statement.setDouble(2, product.getPrice());
      statement.setInt(3, product.getProvider().getId());
      statement.setInt(4, product.getCategory().getId());
      statement.setInt(5, product.getId());
      statement.execute();
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
