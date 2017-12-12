package com.supermercado.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
      PreparedStatement statement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
        OrderItemDAO.getInstance().create(item, orderId);
      }
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public List<Order> findWithFilters(Integer sellerId, Integer minimumPrice) {
    List<Order> orders = null;
    String sql = "SELECT orders.*, SUM(products.price * order_items.quantity) AS total_price " +
                 "FROM orders, products, order_items " +
                 "WHERE order_items.product_id = products.id "+
                 "AND order_items.order_id = orders.id " +
                 "AND orders.seller_id = " + getSellerId(sellerId) + " " +
                 "GROUP BY order_items.order_id " +
                 "HAVING total_price >= " + getMinimumPrice(minimumPrice);
    try {
      orders = new ArrayList<Order>();
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        Order order = new Order();
        order.setId(result.getInt("id"));
        order.setPaymentMethod(result.getString("payment_method"));
        order.setDeliveryMethod(result.getString("delivery_method"));
        order.setStatus(result.getString("status"));
        order.setDate(result.getDate("date"));
        order.setTotalPrice(result.getDouble("total_price"));
        order.setClient(ClientsDAO.getInstance().findById(result.getInt("client_id")));
        order.setSeller(SellersDAO.getInstance().findById(result.getInt("seller_id")));
        order.setStore(StoresDAO.getInstance().findById(result.getInt("store_id")));
        orders.add(order);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orders;
  }

  public Order findById(int id) {
    Order order = null;
    String sql = "SELECT * FROM orders WHERE id = ?";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        order = new Order();
        order.setId(id);
        order.setPaymentMethod(result.getString("payment_method"));
        order.setDeliveryMethod(result.getString("delivery_method"));
        order.setStatus(result.getString("status"));
        order.setDate(result.getDate("date"));
        order.setClient(ClientsDAO.getInstance().findById(result.getInt("client_id")));
        order.setSeller(SellersDAO.getInstance().findById(result.getInt("seller_id")));
        order.setStore(StoresDAO.getInstance().findById(result.getInt("store_id")));
        order.setItems(OrderItemDAO.getInstance().findByOrderId(id));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return order;
  }
  
  private String getSellerId(Integer sellerId) {
    if(sellerId == null) {
      return "orders.seller_id";
    }
    return sellerId.toString();
  }
  
  private Integer getMinimumPrice(Integer minimumPrice) {
    if(minimumPrice == null) {
      return 0;
    }
    return minimumPrice;
  }
}
