package com.supermercado.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.supermercado.models.Order;
import com.supermercado.models.OrderItem;

public class OrdersDAO extends BaseDAO{
  public OrdersDAO() {
    super();
  }

  public static OrdersDAO getInstance() {
    return new OrdersDAO();
  }

  public boolean create(Order order) {
    String sql = "INSERT INTO orders (date, payment_method, delivery_method, status, client_id, seller_id, store_id) " +
      "VALUES (?, ?, ?, ?, ?, ?, ?)";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setDate(1, new Date(order.getDate().getTime()));
      statement.setString(2, order.getPaymentMethod());
      statement.setString(3, order.getDeliveryMethod());
      statement.setString(4, order.getStatus());
      statement.setInt(5, order.getClient().getId());
      statement.setInt(6, order.getSeller().getId());
      statement.setInt(7, order.getStore().getId());
      statement.execute();
      ResultSet result = statement.getGeneratedKeys();
      result.next();
      int orderId = result.getInt(1);
      for(OrderItem item : order.getItems()) {
        sql = "INSERT INTO order_items (quantity, order_id, product_id) VALUES (?, ?, ?)";
        statement = this.connection.prepareStatement(sql);
        statement.setInt(1, item.getQuantity());
        statement.setInt(2, orderId);
        statement.setInt(3, item.getProduct().getId());
        statement.execute();
      }
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
