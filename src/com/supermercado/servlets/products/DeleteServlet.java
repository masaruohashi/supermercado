package com.supermercado.servlets.products;

import com.supermercado.dao.ProductsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/produto/deletar")
public class DeleteServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      if (ProductsDAO.getInstance().delete(Integer.parseInt(request.getParameter("id")))) {
        response.sendRedirect(request.getContextPath() + "/produto?msg=Produto excluido com sucesso!");
      }
      else {
        response.sendRedirect(request.getContextPath() + "/produto?msg=Falha em excluir o produto.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
