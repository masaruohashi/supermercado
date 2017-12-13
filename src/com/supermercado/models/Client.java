package com.supermercado.models;

public class Client {
  private int id;
  private String name;
  private String cpf;
  private String address;
  
  public Client() {
	  
  }
  
  public Client(String name, String cpf, String address) {
	  this.name = name;
	  this.cpf = cpf;
	  this.address = address;
  }
  
  public Client(int id, String name, String cpf, String address) {
	  this.id = id;
	  this.name = name;
	  this.cpf = cpf;
	  this.address = address;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
