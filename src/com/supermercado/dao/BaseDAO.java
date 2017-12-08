package com.supermercado.dao;

import java.sql.Connection;

import com.supermercado.jdbc.ConnectionFactory;


public class BaseDAO {
  protected Connection connection;

  public BaseDAO() {
    this.connection = ConnectionFactory.getConnection();
  }
}
