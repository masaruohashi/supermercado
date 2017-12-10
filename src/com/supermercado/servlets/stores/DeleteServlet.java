package com.supermercado.servlets.stores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supermercado.dao.StoresDAO;

@WebServlet(name = "DeleteStoreServlet", urlPatterns = { "/lojas/deletar" })
public class DeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public DeleteServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    try {
      if(StoresDAO.getInstance().delete(id)) {
        response.sendRedirect(request.getContextPath() + "/lojas?msg=Loja deletada com sucesso");
      }
      else {
        response.sendRedirect(request.getContextPath() + "/lojas?msg=Falha ao deletar a loja");
      }
    } catch (SQLException e) {
    e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
