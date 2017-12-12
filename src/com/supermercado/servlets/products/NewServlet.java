package com.supermercado.servlets.products;

import com.supermercado.dao.CategoriesDAO;
import com.supermercado.dao.ProductsDAO;
import com.supermercado.dao.ProvidersDAO;
import com.supermercado.models.Category;
import com.supermercado.models.Product;
import com.supermercado.models.Provider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/produtos/novo")
public class NewServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Product product = new Product();

    try {
      product.setName(request.getParameter("name"));
      product.setPrice(Double.parseDouble(request.getParameter("price")));
      product.setCategory(CategoriesDAO.getInstance().findById(Integer.parseInt(request.getParameter("category"))));
      product.setProvider(ProvidersDAO.getInstance().findById(Integer.parseInt(request.getParameter("provider"))));

      if(ProductsDAO.getInstance().create(product)) {
        response.sendRedirect(request.getContextPath() + "/produtos?msg=Produto criado com sucesso!");
      }
      else {
        response.sendRedirect(request.getContextPath() + "/produtos/novo?msg=Falha na criacao do produto.");
//        doGet(request, response);
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Category> categories = new ArrayList<Category>();
    List<Provider> providers = new ArrayList<Provider>();

    try {
      categories = CategoriesDAO.getInstance().getAll();
      providers = ProvidersDAO.getInstance().getAll();
      request.setAttribute("categories", categories);
      request.setAttribute("providers", providers);
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/products/new.jsp");
      requestDispatcher.forward(request, response);
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }
}
