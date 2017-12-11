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

@WebServlet("/categoria/novo")
public class NewServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Category category = new Category();

    try {
      category.setName(request.getParameter("name"));

      if(CategoriesDAO.getInstance().create(category)) {
        response.sendRedirect(request.getContextPath() + "/categoria?msg=Categoria criada com sucesso!");
      }
      else {
        response.sendRedirect(request.getContextPath() + "/categoria/novo?msg=Falha na criacao da categoria.");
//        doGet(request, response);
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/categories/new.jsp");
    requestDispatcher.forward(request, response);
  }
}
