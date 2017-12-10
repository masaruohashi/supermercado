package com.supermercado.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supermercado.models.OrderItem;

public class OrderItemDAO extends BaseDAO{
  public OrderItemDAO() {
    super();
  }

  public static OrderItemDAO getInstance() {
    return new OrderItemDAO();
  }

  public boolean create(OrderItem orderItem, int orderId) {
    String sql = "INSERT INTO order_items (quantity, order_id, product_id) VALUES (?, ?, ?)";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement = this.connection.prepareStatement(sql);
      statement.setInt(1, orderItem.getQuantity());
      statement.setInt(2, orderId);
      statement.setInt(3, orderItem.getProduct().getId());
      statement.execute();
      statement.close();
      return true;
    } catch(SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public List<OrderItem> findByOrderId(int orderId) {
    List<OrderItem> orderItems = new ArrayList<OrderItem>();
    String sql = "SELECT * FROM order_items WHERE order_id = ?";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, orderId);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(result.getInt("id"));
        orderItem.setQuantity(result.getInt("quantity"));
        orderItem.setProduct(ProductsDAO.getInstance().findById(result.getInt("product_id")));
        orderItems.add(orderItem);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orderItems;
  }
}
