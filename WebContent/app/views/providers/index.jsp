<%@page import="java.util.ArrayList"%>
<%@page import="com.supermercado.models.Provider"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Supermercado - Lojas</title>
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
      <li class="nav-item active"><a href="fornecedores">Fornecedor</a></li>
      <li class="nav-item"><a href="pedidos">Pedido</a></li>
    </ul>
  </nav>
  <div class="content">
    <div class="row">
      <div class="col-sm-12">
        <span class="content-header">Listagem de Fornecedores</span>
        <a class="btn btn-primary pull-right add-button" href="/supermercado/fornecedores/novo">
          <i class="fa fa-plus" aria-hidden="true"></i>
          <span>Adicionar</span>
        </a>
      </div>
    </div>
    <hr/>
      <div class="content-table col-sm-10 col-sm-offset-1">
        <table class="table">
          <thead>
            <tr>
              <th class="col-sm-5">Nome</th>
              <th class="col-sm-5">Endereço</th>
              <th class="text-center col-sm-2">Ações</th>
            </tr>
          </thead>
          <tbody>
            <% List<Provider> providers = (ArrayList<Provider>) request.getAttribute("providers"); %>
            <% for(Provider provider: providers) { %>
              <tr>
                <td><%= provider.getName() %></td>
                <td><%= provider.getAddress() %></td>
                <td>
                  <a href="fornecedores/editar?id=<%=provider.getId() %>" class="table-button">
                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                  </a>
                  <a href="fornecedores/deletar?id=<%=provider.getId() %>" class="table-button">
                    <i class="fa fa-times" aria-hidden="true"></i>
                  </a>
                </td>
              </tr>
            <% } %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <% if(request.getParameter("msg") != null) { %>
    <div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="messageModalLabel">Mensagem</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <%= request.getParameter("msg") %>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
          </div>
        </div>
      </div>
    </div>
    <script>$('#messageModal').modal({show: true})</script>
  <% } %>
</body>
</html>