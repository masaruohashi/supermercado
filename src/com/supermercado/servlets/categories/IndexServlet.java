package com.supermercado.servlets.categories;

import com.supermercado.dao.CategoriesDAO;
import com.supermercado.models.Category;

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

@WebServlet("/categoria")
public class IndexServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Category> categories = new ArrayList<Category>();

    try {
      categories = CategoriesDAO.getInstance().getAll();
      request.setAttribute("categories", categories);
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/categories/index.jsp");
      requestDispatcher.forward(request, response);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
