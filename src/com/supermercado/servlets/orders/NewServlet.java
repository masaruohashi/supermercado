package com.supermercado.servlets.orders;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supermercado.dao.ClientsDAO;
import com.supermercado.dao.OrdersDAO;
import com.supermercado.dao.ProductsDAO;
import com.supermercado.dao.SellersDAO;
import com.supermercado.dao.StoresDAO;
import com.supermercado.models.Client;
import com.supermercado.models.Order;
import com.supermercado.models.Product;
import com.supermercado.models.Seller;
import com.supermercado.models.Store;

@WebServlet("/pedido")
public class NewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public NewServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Product> products = null;
    List<Client> clients = null;
    List<Seller> sellers = null;
    List<Store> stores = null;
    try {
      products = ProductsDAO.getInstance().getAll();
      clients = ClientsDAO.getInstance().getAll();
      sellers = SellersDAO.getInstance().getAll();
      stores = StoresDAO.getInstance().getAll();
      request.setAttribute("products", products);
      request.setAttribute("clients", clients);
      request.setAttribute("sellers", sellers);
      request.setAttribute("stores", stores);
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/orders/new.jsp");
      requestDispatcher.forward(request, response);
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Order order = new Order();
    String[] productsIds = request.getParameterValues("products");
    String clientId = request.getParameter("client");
    String sellerId = request.getParameter("seller");
    String storeId = request.getParameter("store");
    try {
      Client client = ClientsDAO.getInstance().findById(Integer.parseInt(clientId));
      Seller seller = SellersDAO.getInstance().findById(Integer.parseInt(sellerId));
      Store store = StoresDAO.getInstance().findById(Integer.parseInt(storeId));
      order.setClient(client);
      order.setSeller(seller);
      order.setStore(store);
      order.setDate(new Date());
      order.setDeliveryMethod(request.getParameter("delivery-method"));
      order.setPaymentMethod(request.getParameter("payment-method"));
      order.setStatus("processed");
      for(String productId : productsIds) {
        int productQuantity = Integer.parseInt(request.getParameter(productId));
        Product product = ProductsDAO.getInstance().findById(Integer.parseInt(productId));
        order.addItem(product, productQuantity);
      }
      if(OrdersDAO.getInstance().create(order)) {
        request.setAttribute("message", "Pedido Criado com sucesso!");
      } else {
        request.setAttribute("message", "Falha ao criar o pedido.");
      }
      doGet(request, response);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
