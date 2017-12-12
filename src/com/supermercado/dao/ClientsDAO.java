package com.supermercado.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supermercado.models.Client;

public class ClientsDAO extends BaseDAO{
  public ClientsDAO() {
    super();
  }

  public static ClientsDAO getInstance() {
    return new ClientsDAO();
  }

  public List<Client> getAll() {
    List<Client> clients = new ArrayList<Client>();
    try {
      String sql = "SELECT * FROM clients";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        Client client = new Client();
        client.setId(result.getInt("id"));
        client.setName(result.getString("name"));
        client.setCpf(result.getString("cpf"));
        client.setAddress(result.getString("address"));
        clients.add(client);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return clients;
  }

  public Client findById(int id) {
    Client client = null;
    try {
      String sql = "SELECT * FROM clients WHERE id=?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        client = new Client();
        client.setId(result.getInt("id"));
        client.setName(result.getString("name"));
        client.setCpf(result.getString("cpf"));
        client.setAddress(result.getString("address"));
        result.close();
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return client;
  }
  
  public void add(Client client) throws SQLException {
	  try {
		  String sql = "INSERT INTO clients (name, cpf, address) VALUES (?, ?, ?)";
	      PreparedStatement statement = this.connection.prepareStatement(sql);
	      statement.setString(1, client.getName());
	      statement.setString(2, client.getCpf());
	      statement.setString(3, client.getAddress());
	      statement.executeUpdate();	      
	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
  }
  
  public void update(Client client) throws SQLException {
	  try {
		  String sql = "UPDATE clients SET name = ?, cpf = ?, address = ? WHERE id = ?";
	      PreparedStatement statement = this.connection.prepareStatement(sql);
	      statement.setString(1, client.getName());
	      statement.setString(2, client.getCpf());
	      statement.setString(3, client.getAddress());
	      statement.setInt(4, client.getId());	      
	      statement.executeUpdate();	      
	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
  }
  
  public void delete(int id) throws SQLException {
	  try {
		  String sql = "DELETE FROM clients WHERE id = ?";
	      PreparedStatement statement = this.connection.prepareStatement(sql);
	      statement.setInt(1, id);	      
	      statement.executeUpdate();	      
	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
  }
}
