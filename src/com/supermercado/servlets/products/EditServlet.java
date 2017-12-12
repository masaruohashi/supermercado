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

@WebServlet("/produtos/editar")
public class EditServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Product product = new Product();

    try {
      product.setId((Integer.parseInt(request.getParameter("id"))));
      product.setName(request.getParameter("name"));
      product.setPrice(Double.parseDouble(request.getParameter("price").replace(" ", "")));
      product.setCategory(CategoriesDAO.getInstance().findById(Integer.parseInt(request.getParameter("category"))));
      product.setProvider(ProvidersDAO.getInstance().findById(Integer.parseInt(request.getParameter("provider"))));

      if (ProductsDAO.getInstance().edit(product)) {
        response.sendRedirect(request.getContextPath() + "/produtos?msg=Produto editado com sucesso!");
      }
      else {
        response.sendRedirect(request.getContextPath() + "/produtos/editar?msg=Falha ao editar produto.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Product selected_product = new Product();
    List<Category> categories = new ArrayList<Category>();
    List<Provider> providers = new ArrayList<Provider>();

    try {
      selected_product = ProductsDAO.getInstance().findById(Integer.parseInt(request.getParameter("id")));
      categories = CategoriesDAO.getInstance().getAll();
      providers = ProvidersDAO.getInstance().getAll();
      request.setAttribute("selected_product", selected_product);
      request.setAttribute("categories", categories);
      request.setAttribute("providers", providers);
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/products/edit.jsp");
      requestDispatcher.forward(request, response);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
