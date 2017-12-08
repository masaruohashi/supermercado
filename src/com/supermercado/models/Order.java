package com.supermercado.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
  private int id;
  private String paymentMethod;
  private String deliveryMethod;
  private String status;
  private Date date;
  private Client client;
  private Seller seller;
  private Store store;
  private List<OrderItem> items;

  public Order() {
    items = new ArrayList<OrderItem>();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public String getDeliveryMethod() {
    return deliveryMethod;
  }

  public void setDeliveryMethod(String deliveryMethod) {
    this.deliveryMethod = deliveryMethod;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Seller getSeller() {
    return seller;
  }

  public void setSeller(Seller seller) {
    this.seller = seller;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public void addItem(Product product, int quantity) {
    OrderItem item = new OrderItem();
    item.setProduct(product);
    item.setQuantity(quantity);
    items.add(item);
  }

  public List<OrderItem> getItems() {
    return items;
  }
}
