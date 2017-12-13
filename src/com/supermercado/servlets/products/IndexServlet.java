package com.supermercado.servlets.products;

import com.supermercado.dao.ProductsDAO;
import com.supermercado.models.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/produtos")
public class IndexServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Product> products = new ArrayList<Product>();
    products = ProductsDAO.getInstance().getAll();
    request.setAttribute("products", products);
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/products/index.jsp");
    requestDispatcher.forward(request, response);
  }
}
