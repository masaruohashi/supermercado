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
      <li class="nav-item"><a href="pedido">Novo Pedido</a></li>
      <li class="nav-item"><a href="clientes">Cliente</a></li>
      <li class="nav-item active"><a href="vendedores">Vendedor</a></li>
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
        <span class="content-header">Vendedores</span>
      </div>
      <div class="col-sm-2">
        <a class="btn btn-primary pull-right" href="vendedores?acao=adicionar">
          <i class="fa fa-user-plus" aria-hidden="true"></i>
          <span>Adicionar</span>
        </a>
      </div>
    </div>
    <hr class="content-line" />
    <div class="sellers">
      <% List<Seller> sellers = (ArrayList<Seller>) request.getAttribute("sellers"); %>
      <% if(sellers.isEmpty()) { %>
        <div class="col-sm-8 col-sm-offset-2">
            <div class="col-sm-12 text-center">
              <span>Não existem clientes cadastrados!</span>
            </div>
        </div>
      <% } else {%>
        <div class="row">
          <form action="vendedores" method="POST" class="form-group" id="seller-form">
            <div class="col-sm-10 col-sm-offset-1">
              <% if(!sellers.isEmpty()) { %>
                <table class="table">
                  <thead>
                    <th class="col-sm-5">Nome</th>
                    <th class="col-sm-5">CPF</th>
                    <th class="text-center col-sm-2">Ações</th>
                  </thead>
                  <tbody>
                    <% for(Seller seller : sellers) { %>
                      <tr>
                        <td><%= seller.getName() %></td>
                        <td><%= seller.getCpf() %></td>
                        <td class="text-center">
                          <a class="table-button" href="${pageContext.request.contextPath}/vendedores?acao=atualizar&id=<%= seller.getId() %>">
                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                          </a>
                          <a class="table-button" href="${pageContext.request.contextPath}/vendedores?acao=excluir&id=<%= seller.getId() %>">
                            <i class="fa fa-times" aria-hidden="true"></i>
                          </a>
                        </td>
                      </tr>
                    <% } %>
                  </tbody>
                </table>
              <% } %>
            </div>
          </form>
        </div>
      <% } %>
    </div>
  </div>
</body>
</html>