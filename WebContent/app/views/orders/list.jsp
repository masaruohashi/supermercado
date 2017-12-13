<%@page import="com.supermercado.models.Seller"%>
<%@page import="com.supermercado.decorators.OrderDecorator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Supermercado - Pedidos</title>
  <script src="bower_components/jquery/dist/jquery.min.js"></script>
  <script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="app/assets/stylesheets/index.css">
  <link rel="stylesheet" type="text/css" href="app/assets/stylesheets/orders.css">
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
    <span class="content-header">Pedidos Realizados</span>
    <hr class="content-line" />
    <div class="products">
      <div class="row">
        <% List<OrderDecorator> orders = (ArrayList<OrderDecorator>) request.getAttribute("orders"); %>
        <% List<Seller> sellers = (ArrayList<Seller>) request.getAttribute("sellers"); %>
        <% if(orders.isEmpty()) { %>
          <div class="col-sm-12 text-center">
            <span class="h4">Não existem pedidos cadastrados!</span>
          </div>
        <% } else { %>
          <div class="row">
            <form class="form-group" action="pedidos" method="GET">
              <div class="col-sm-10 col-sm-offset-1">
                  <div class="col-sm-5">
                    <label for="seller_id">Vendedor:</label>
                    <select class="form-control" id="seller_id" name="seller_id">
                      <option value="">Selecione</option>
                      <% for(Seller seller : sellers) { %>
                        <% if(Integer.toString(seller.getId()).equals(request.getParameter("seller_id"))) { %>
                          <option value="<%= seller.getId() %>" selected>
                        <% } else { %>
                          <option value="<%= seller.getId() %>">
                        <% } %>
                          <%= seller.getName() %> - CPF: <%= seller.getCpf() %> 
                        </option>
                      <% } %>
                    </select>
                  </div>
                  <div class="col-sm-5">
                    <label for="minimum_price">Total Mínimo:</label>
                    <% String minimumPrice = request.getParameter("minimum_price"); %>
                    <% if(minimumPrice != null) { %>
                      <input class="form-control" name="minimum_price" type="number" min="0" value="<%= minimumPrice %>" />
                    <% } else { %>
                      <input class="form-control" name="minimum_price" type="number" min="0" value="0" />
                    <% } %>
                  </div>
                  <div class="col-sm-2">
                    <input class="btn btn-primary filter-submit" type="submit" value="Filtrar" />
                  </div>
              </div>
            </form>
          </div>
          <br>
          <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
              <table class="table">
                <thead>
                  <tr>
                    <th>#</th>
                    <th>Cliente</th>
                    <th>Vendedor</th>
                    <th>Loja</th>
                    <th>Data</th>
                    <th>Pagamento</th>
                    <th>Total</th>
                  </tr>
                </thead>
                <tbody>
                  <% for(OrderDecorator order : orders) { %>
                    <tr>
                      <td><a href="pedidos?id=<%= order.id() %>"><%= order.id() %></a></td>
                      <td><%= order.client() %></td>
                      <td><%= order.seller() %></td>
                      <td><%= order.store() %></td>
                      <td><%= order.date() %></td>
                      <td><%= order.paymentMethod() %></td>
                      <td>R$ <%= order.totalPrice() %></td>
                    </tr>
                  <% } %>
                </tbody>
              </table>
            </div>
          </div>
        <% } %>
      </div>
    </div>
  </div>
</body>
</html>