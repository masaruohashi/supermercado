package com.supermercado.dao;

import com.supermercado.models.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO extends BaseDAO {
  public CategoriesDAO () {
    super();
  }

  public static CategoriesDAO getInstance() {
    return new CategoriesDAO();
  }

  public List<Category> getAll() throws SQLException {
    List<Category> categories = new ArrayList<Category>();
    try {
      String sql = "SELECT * FROM categories";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();

      while(result.next()) {
        Category category = new Category();
        category.setId(result.getInt("id"));
        category.setName(result.getString("name"));
        categories.add(category);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return categories;
  }

  public Category findById(int id) throws SQLException {
    Category category = new Category();

    try {
      String sql = "SELECT * FROM categories WHERE id=?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        category.setId(result.getInt("id"));
        category.setName(result.getString("name"));
        result.close();
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return category;
  }

  public boolean create(Category category) throws SQLException {
    String sql = "INSERT INTO categories (name)" +
            "VALUES (?)";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setString(1, category.getName());
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
      String sql = "DELETE FROM categories WHERE id = ?";
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

  public boolean edit (Category category) throws SQLException {
    String sql = "UPDATE categories " +
            "SET name = ?" +
            "WHERE  id = ?";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setString(1, category.getName());
      statement.setInt(2, category.getId());
      statement.execute();
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

}
