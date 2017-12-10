package com.supermercado.servlets.clients;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supermercado.dao.ClientsDAO;
import com.supermercado.dao.ProductsDAO;
import com.supermercado.dao.SellersDAO;
import com.supermercado.dao.StoresDAO;
import com.supermercado.models.Client;
import com.supermercado.models.Product;
import com.supermercado.models.Seller;
import com.supermercado.models.Store;

@WebServlet("/clientes")
public class clientsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public clientsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("acao");
	    List<Client> clients = null;
	    if(action == null) {
			try {
			      clients = ClientsDAO.getInstance().getAll();
			      request.setAttribute("clients", clients);
			      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/clients/listar.jsp");
			      requestDispatcher.forward(request, response);
			    } catch(SQLException e) {
			      e.printStackTrace();
			    }
		}
	    else if(action.equals("adicionar")) {		
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/clients/cadastrar.jsp");
		    requestDispatcher.forward(request, response);
		}
		else if(action.equals("atualizar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Client client = null;
			try {
				client = ClientsDAO.getInstance().findById(id);
				request.setAttribute("id", id);
				request.setAttribute("client", client);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/clients/cadastrar.jsp");
			    requestDispatcher.forward(request, response);
			} catch(SQLException e) {
			      e.printStackTrace();
			}
		}
		else if(action.equals("excluir")) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				ClientsDAO.getInstance().delete(id);
				clients = ClientsDAO.getInstance().getAll();
			    request.setAttribute("clients", clients);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/clients/listar.jsp");
			    requestDispatcher.forward(request, response);
			} catch(SQLException e) {
			      e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String operation = request.getParameter("operation");
		List<Client> clients = null;
				
		if(operation.equals("add")) {
			String name = request.getParameter("name");
			String cpf = request.getParameter("cpf");
			String address = request.getParameter("address");
			try {
				ClientsDAO.getInstance().add(new Client(name, cpf, address));
				clients = ClientsDAO.getInstance().getAll();
			    request.setAttribute("clients", clients);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/clients/listar.jsp");
			    requestDispatcher.forward(request, response);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else if(operation.equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String cpf = request.getParameter("cpf");
			String address = request.getParameter("address");
			try {
				ClientsDAO.getInstance().update(new Client(id, name, cpf, address));
				clients = ClientsDAO.getInstance().getAll();
			    request.setAttribute("clients", clients);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/clients/listar.jsp");
			    requestDispatcher.forward(request, response);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
