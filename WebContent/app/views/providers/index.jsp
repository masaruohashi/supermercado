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
      <li class="nav-item"><a href="#">Novo Pedido</a></li>
      <li class="nav-item"><a href="#">Cliente</a></li>
      <li class="nav-item"><a href="#">Vendedor</a></li>
      <li class="nav-item"><a href="#">Produto</a></li>
      <li class="nav-item"><a href="/supermercado/lojas/novo">Nova Loja</a></li>
      <li class="nav-item"><a href="/supermercado/lojas">Loja</a></li>
      <li class="nav-item"><a href="#">Categoria</a></li>
      <li class="nav-item"><a href="/supermercado/fornecedores/novo">Novo Fornecedor</a></li>
      <li class="nav-item active"><a href="/supermercado/fornecedores">Fornecedor</a></li>
      <li class="nav-item"><a href="#">Pedido</a></li>
    </ul>
  </nav>
  <div class="content">
    <span class="content-header">Listagem de Fornecedores</span>
      <hr class="content-line" />
        <div class="content-table">
          <table class="table">
            <thead>
              <tr>
                <th>Nome</th>
                <th>Endereço</th>
              </tr>
            </thead>
            <tbody>
              <% List<Provider> providers = (ArrayList<Provider>) request.getAttribute("providers"); %>
              <% for(Provider provider: providers) { %>
                <tr>
                  <td><%= provider.getName() %></td>
                  <td><%= provider.getAddress() %></td>
                  <td>
                    <a href="lojas/editar?id=<%=provider.getId() %>" class="btn btn-warning">Editar</a>
                    <a href="lojas/deletar?id=<%=provider.getId() %>" class="btn btn-danger">Excluir</a>
                  </td>
                </tr>
              <% } %>
            </tbody>
          </table>
        </div>
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