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

@WebServlet(name = "NewProviderServlet", urlPatterns = { "/fornecedores/novo" })
public class NewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public NewServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/providers/new.jsp");
    requestDispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name  = request.getParameter("name");
    String address = request.getParameter("address");
    Provider provider = new Provider();
    provider.setName(name);
    provider.setAddress(address);
    if(name == null || address == null) {
      response.sendRedirect(request.getContextPath() + "/fornecedores/novo?msg=Insira todos os campos");
    }
    else {
      if(ProvidersDAO.getInstance().create(provider)) {
        response.sendRedirect(request.getContextPath() + "/fornecedores?msg=Fornecedor criado com sucesso");
      }
      else {
        response.sendRedirect(request.getContextPath() + "/fornecedores/novo?msg=Falha ao criar o fornecedor");
      }
    }
  }

}
