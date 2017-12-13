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

@WebServlet("/categorias/editar")
public class EditServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Category category = new Category();

    category.setId((Integer.parseInt(request.getParameter("id"))));
    category.setName(request.getParameter("name"));

    try {
      if (CategoriesDAO.getInstance().edit(category)) {
        response.sendRedirect(request.getContextPath() + "/categorias?msg=Categoria editada com sucesso!");
      }
      else {
        response.sendRedirect(request.getContextPath() + "/categorias/editar?msg=Falha ao editar categoria.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Category selected_category = new Category();

    try {
      selected_category = CategoriesDAO.getInstance().findById(Integer.parseInt(request.getParameter("id")));
      request.setAttribute("selected_category", selected_category);
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/categories/edit.jsp");
      requestDispatcher.forward(request, response);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
