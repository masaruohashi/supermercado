package com.supermercado.servlets.stores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supermercado.dao.StoresDAO;
import com.supermercado.models.Store;

@WebServlet(name = "IndexStoreServlet", urlPatterns = { "/lojas" })
public class IndexServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

    public IndexServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
        List<Store> stores= null;
        stores = StoresDAO.getInstance().getAll();
        request.setAttribute("stores", stores);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/stores/index.jsp");
        requestDispatcher.forward(request, response);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
}

}
