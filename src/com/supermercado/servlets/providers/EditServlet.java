package com.supermercado.servlets.providers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supermercado.dao.ProvidersDAO;
import com.supermercado.models.Provider;

@WebServlet(name = "EditProviderServlet", urlPatterns = { "/fornecedores/editar" })
public class EditServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public EditServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Provider provider = new Provider();
    int id = Integer.parseInt(request.getParameter("id"));
    provider = ProvidersDAO.getInstance().findById(id);
    request.setAttribute("provider", provider);
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/providers/edit.jsp");
    requestDispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String address = request.getParameter("address");
    Provider provider = new Provider();
    provider.setId(id);
    provider.setName(name);
    provider.setAddress(address);
    if(name == null || name.matches("") || address == null || address.matches("")) {
      response.sendRedirect(request.getContextPath() + "/fornecedores/editar?id=" + id + "&msg=Insira todos os campos");
    }
    else {
      if(ProvidersDAO.getInstance().update(provider)) {
        response.sendRedirect(request.getContextPath() + "/fornecedores?msg=Fornecedor editado com sucesso");
      }
      else {
        response.sendRedirect(request.getContextPath() + "/fornecedores/editar?id=" + id + "&msg=Falha ao editar o fornecedor");
      }
    }
  }

}
