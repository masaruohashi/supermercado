package com.supermercado.servlets.providers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supermercado.dao.ProvidersDAO;
import com.supermercado.models.Provider;

@WebServlet(name = "IndexProviderServlet", urlPatterns = { "/fornecedores" })
public class IndexServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

    public IndexServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      List<Provider> providers= null;
      providers = ProvidersDAO.getInstance().getAll();
      request.setAttribute("providers", providers);
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/providers/index.jsp");
      requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
}

}
