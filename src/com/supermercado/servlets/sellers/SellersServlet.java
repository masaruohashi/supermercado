package com.supermercado.servlets.sellers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supermercado.dao.SellersDAO;
import com.supermercado.models.Seller;

@WebServlet("/vendedores")
public class SellersServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
  public SellersServlet() {
        super();
    }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("acao");
      List<Seller> sellers = null;
      if(action == null) {
        sellers = SellersDAO.getInstance().getAll();
        request.setAttribute("sellers", sellers);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/sellers/list.jsp");
        requestDispatcher.forward(request, response);       
    }
      else if(action.equals("adicionar")) {    
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/sellers/form.jsp");
        requestDispatcher.forward(request, response);
    }
    else if(action.equals("atualizar")) {
      int id = Integer.parseInt(request.getParameter("id"));
      Seller seller = null;
      seller = SellersDAO.getInstance().findById(id);
      request.setAttribute("id", id);
      request.setAttribute("seller", seller);
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/sellers/form.jsp");
        requestDispatcher.forward(request, response);        
    }
    else if(action.equals("excluir")) {
      int id = Integer.parseInt(request.getParameter("id"));
      try {
        SellersDAO.getInstance().delete(id);
        sellers = SellersDAO.getInstance().getAll();
          request.setAttribute("sellers", sellers);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/sellers/list.jsp");
          requestDispatcher.forward(request, response);
      } catch(SQLException e) {
            e.printStackTrace();
      }
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
    String operation = request.getParameter("operation");
    List<Seller> sellers = null;
        
    if(operation.equals("add")) {
      String name = request.getParameter("name");
      String cpf = request.getParameter("cpf");      
      try {
        SellersDAO.getInstance().add(new Seller(name, cpf));
        sellers = SellersDAO.getInstance().getAll();
          request.setAttribute("sellers", sellers);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/sellers/list.jsp");
          requestDispatcher.forward(request, response);
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
    else if(operation.equals("update")) {
      int id = Integer.parseInt(request.getParameter("id"));
      String name = request.getParameter("name");
      String cpf = request.getParameter("cpf");      
      try {
        SellersDAO.getInstance().update(new Seller(id, name, cpf));
        sellers = SellersDAO.getInstance().getAll();
          request.setAttribute("sellers", sellers);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/sellers/list.jsp");
          requestDispatcher.forward(request, response);
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
