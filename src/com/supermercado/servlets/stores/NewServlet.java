package com.supermercado.servlets.stores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supermercado.dao.StoresDAO;
import com.supermercado.models.Store;

@WebServlet(name = "NewStoreServlet", urlPatterns = { "/lojas/novo" })
public class NewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public NewServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/stores/new.jsp");
    requestDispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name  = request.getParameter("name");
    String address = request.getParameter("address");
    try {
      Store store = new Store();
      store.setName(name);
      store.setAddress(address);
      if(name == null || address == null) {
        response.sendRedirect(request.getContextPath() + "/lojas/novo?msg=Insira todos os campos");
      }
      else {
        if(StoresDAO.getInstance().create(store)) {
          response.sendRedirect(request.getContextPath() + "/lojas?msg=Loja criada com sucesso");
        }
        else {
          response.sendRedirect(request.getContextPath() + "/lojas/novo?msg=Falha ao criar a loja");
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
