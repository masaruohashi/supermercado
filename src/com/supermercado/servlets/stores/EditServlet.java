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

@WebServlet(name = "EditStoreServlet", urlPatterns = { "/lojas/editar" })
public class EditServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public EditServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Store store = new Store();
    int id = Integer.parseInt(request.getParameter("id"));
    store = StoresDAO.getInstance().findById(id);
    request.setAttribute("store", store);
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/stores/edit.jsp");
    requestDispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String address = request.getParameter("address");
    Store store = new Store();
    store.setId(id);
    store.setName(name);
    store.setAddress(address);
    if(name == null || name.matches("") || address == null || address.matches("")) {
      response.sendRedirect(request.getContextPath() + "/lojas/editar?id=" + id + "&msg=Insira todos os campos");
    }
    else {
      if(StoresDAO.getInstance().update(store)) {
        response.sendRedirect(request.getContextPath() + "/lojas?msg=Loja editada com sucesso");
      }
      else {
        response.sendRedirect(request.getContextPath() + "/lojas/editar?id=" + id + "&msg=Falha ao editar a loja");
      }
    }
  }

}
