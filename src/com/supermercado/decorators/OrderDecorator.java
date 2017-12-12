package com.supermercado.decorators;

import java.text.SimpleDateFormat;

import com.supermercado.models.Order;

public class OrderDecorator {
  private Order order;

  public OrderDecorator(Order order) {
    this.order = order;
  }

  public String client() {
    return order.getClient().getName();
  }

  public String seller() {
    return order.getSeller().getName();
  }

  public String store() {
    return order.getStore().getName();
  }

  public int id() {
    return order.getId();
  }

  public String date() {
    String formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(order.getDate());
    return formattedDate;
  }

  public String paymentMethod() {
    if(order.getPaymentMethod().equals("cash")) {
      return "Dinheiro";
    } else {
      return "Cartão";
    }
  }
}
