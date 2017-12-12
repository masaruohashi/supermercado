<%@page import="com.supermercado.models.OrderItem"%>
<%@page import="com.supermercado.models.Order"%>
<%@page import="com.supermercado.decorators.OrderDecorator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Supermercado - Pedido</title>
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
      <li class="nav-item"><a href="pedido">Novo Pedido</a></li>
      <li class="nav-item"><a href="clientes">Cliente</a></li>
      <li class="nav-item"><a href="vendedores">Vendedor</a></li>
      <li class="nav-item"><a href="produtos">Produto</a></li>
      <li class="nav-item"><a href="lojas">Loja</a></li>
      <li class="nav-item"><a href="categorias">Categoria</a></li>
      <li class="nav-item"><a href="fornecedores">Fornecedor</a></li>
      <li class="nav-item active"><a href="pedidos">Pedido</a></li>
    </ul>
  </nav>
  <div class="content">
    <% Order order = (Order) request.getAttribute("order"); %>
    <span class="content-header">Detalhes do Pedido <%= order.getId() %></span>
    <hr class="content-line" />
    <div class="products">
      <div class="row">
        <% if(order == null) { %>
          <div class="col-sm-12 text-center">
            <span class="h4">O pedido não existe!</span>
          </div>
        <% } else { %>
          <div class="col-sm-12">
            <table class="table">
              <thead>
                <tr>
                  <th>Nome</th>
                  <th>Categoria</th>
                  <th>Preço</th>
                  <th>Quantidade</th>
                </tr>
              </thead>
              <tbody>
                <% for(OrderItem item : order.getItems()) { %>
                  <tr>
                    <td><%= item.getProduct().getName() %></td>
                    <td><%= item.getProduct().getCategory().getName() %></td>
                    <td><%= item.getProduct().getFormattedPrice() %></td>
                    <td><%= item.getQuantity() %></td>
                  </tr>
                <% } %>
              </tbody>
            </table>
          </div>
        <% } %>
      </div>
    </div>
  </div>
</body>
</html>