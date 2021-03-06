<%@page import="com.supermercado.models.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Supermercado - Clientes</title>
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
      <li class="nav-item active"><a href="clientes">Cliente</a></li>
      <li class="nav-item"><a href="vendedores">Vendedor</a></li>
      <li class="nav-item"><a href="produtos">Produto</a></li>
      <li class="nav-item"><a href="lojas">Loja</a></li>
      <li class="nav-item"><a href="categorias">Categoria</a></li>
      <li class="nav-item"><a href="fornecedores">Fornecedor</a></li>
      <li class="nav-item"><a href="pedidos">Pedido</a></li>
    </ul>
  </nav>
  <div class="content">
    <div class="row">
      <div class="col-sm-10">
        <span class="content-header">Clientes</span>
      </div>
      <div class="col-sm-2">
        <a class="btn btn-primary pull-right" href="clientes?acao=adicionar">
          <i class="fa fa-user-plus" aria-hidden="true"></i>
          <span>Adicionar</span>
        </a>
      </div>
    </div>
    <hr class="content-line" />
    <div class="clients">
      <% List<Client> clients = (ArrayList<Client>) request.getAttribute("clients"); %>
      <% if(clients.isEmpty()) { %>
        <div class="col-sm-8 col-sm-offset-2 js-user-seller">
          <div class="col-sm-12 text-center">
            <span>Não existem clientes cadastrados!</span>
          </div>
        </div>
      <% } else { %>
        <div class="row">
          <div class="col-sm-10 col-sm-offset-1 js-products">
            <table class="table">
              <thead>
                <th class="col-sm-3">Nome</th>
                <th class="col-sm-3">CPF</th>
                <th class="col-sm-4">Endereço</th>
                <th class="text-center col-sm-2">Ações</th>
              </thead>
              <tbody>
                <% for(Client client : clients) { %>
                  <tr>
                    <td><%= client.getName() %></td>
                    <td><%= client.getCpf() %></td>
                    <td><%= client.getAddress() %></td>
                    <td class="text-center">
                      <a class="table-button" href="${pageContext.request.contextPath}/clientes?acao=atualizar&id=<%= client.getId() %>">
                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                      </a>
                      <a class="table-button" href="${pageContext.request.contextPath}/clientes?acao=excluir&id=<%= client.getId() %>">
                        <i class="fa fa-times" aria-hidden="true"></i>
                      </a>
                    </td>
                  </tr>
                <% } %>
              </tbody>
            </table>
          </div>
        </div>
      <% } %>
    </div>
  </div>
</body>
</html>