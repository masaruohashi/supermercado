package com.supermercado.servlets.orders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supermercado.dao.OrdersDAO;
import com.supermercado.decorators.OrderDecorator;
import com.supermercado.models.Order;

@WebServlet("/pedidos")
public class ShowServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ShowServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String orderId = request.getParameter("id");
    System.out.println(orderId);
    if(orderId != null) {
      showOrder(request, response);
    } else {
      listOrders(request, response);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  private void listOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<OrderDecorator> orders = new ArrayList<OrderDecorator>();
    for(Order order : OrdersDAO.getInstance().findAll()) {
      orders.add(new OrderDecorator(order));
    }
    request.setAttribute("orders", orders);
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/orders/list.jsp");
    requestDispatcher.forward(request, response);
  }

  private void showOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int orderId = Integer.parseInt(request.getParameter("id"));
    Order order = OrdersDAO.getInstance().findById(orderId);
    request.setAttribute("order", order);
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/orders/show.jsp");
    requestDispatcher.forward(request, response);
  }
}
