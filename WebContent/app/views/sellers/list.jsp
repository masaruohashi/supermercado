<%@page import="com.supermercado.models.Seller"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Supermercado - Vendedores</title>
  <script src="bower_components/jquery/dist/jquery.min.js"></script>
  <script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="app/assets/stylesheets/index.css">
</head>
<body>
  <header class="container-fluid">
    <h1 class="logo">Supermercado</h1>
  </header>
  <nav>
    <ul class="nav-list">
      <li class="nav-item active"><a href="#">Novo Pedido</a></li>
      <li class="nav-item"><a href="#">Cliente</a></li>
      <li class="nav-item"><a href="#">Vendedor</a></li>
      <li class="nav-item"><a href="#">Produto</a></li>
      <li class="nav-item"><a href="#">Loja</a></li>
      <li class="nav-item"><a href="#">Categoria</a></li>
      <li class="nav-item"><a href="#">Fornecedores</a></li>
      <li class="nav-item"><a href="#">Pedido</a></li>
    </ul>
  </nav>
  <div class="content">
    <span class="content-header">Vendedores</span>
    <hr class="content-line" />
    <div class="sellers">
      <div class="row">
      	<form action="vendedores" method="GET" class="form-group">
          <div class="col-sm-8 col-sm-offset-2 js-user-seller">
            <% List<Seller> sellers = (ArrayList<Seller>) request.getAttribute("sellers"); %>
            <% if(sellers.isEmpty()) { %>
              <div class="col-sm-12 text-center">
                <span>Não existem clientes cadastrados!</span>
              </div>
            <% } %>
            <br>
            <div class="col-sm-12">
              <input class="hidden" name="acao" value="adicionar"/>
              <input class="btn btn-primary pull-right" type="submit" value="Novo vendedor" />
            </div>
          </div>
        </form>
      </div>
      <div class="row">
        <form action="vendedores" method="POST" class="form-group" id="seller-form">
          <div class="col-sm-10 col-sm-offset-1 js-products">
            <% if(!sellers.isEmpty()) { %>
                <table class="table">
                  <thead>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Atualizar</th>
                    <th>Excluir</th>             
                  </thead>
                  <tbody>
                    <% for(Seller seller : sellers) { %>
                      <tr>                        
                        <td><%= seller.getName() %></td>
                        <td><%= seller.getCpf() %></td>
                        <td>
                        	<a href="${pageContext.request.contextPath}/vendedores?acao=atualizar&id=<%= seller.getId() %>">
								<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
							</a>
                        </td>
                        <td>
                        	<a href="${pageContext.request.contextPath}/vendedores?acao=excluir&id=<%= seller.getId() %>">
								<img class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							</a>
                        </td>    
                      </tr>
                    <% } %>
                  </tbody>
                </table>
                <input class="hidden" name="acao" id="action"/>
                <input class="hidden" name="id" id="idbox"/>              
            <% } %>
          </div>          
        </form>
      </div>
    </div>
  </div>
</body>
</html>