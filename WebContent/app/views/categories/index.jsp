<%@ page import="com.supermercado.models.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Supermercado - Categorias</title>
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
      <li class="nav-item active"><a href="categorias">Categoria</a></li>
      <li class="nav-item"><a href="fornecedores">Fornecedor</a></li>
      <li class="nav-item"><a href="pedidos">Pedido</a></li>
    </ul>
  </nav>
  <div class="content">
    <div class="row">
      <div class="col-sm-12">
        <span class="content-header">Categorias</span>
        <a class="btn btn-primary pull-right add-button" href="categorias/novo">
          <i class="fa fa-plus" aria-hidden="true"></i>
          <span>Adicionar</span>
        </a>
      </div>
    </div>
    <hr class="content-line" />
    <div class="products">
      <div class="row">
        <div class="content-table col-sm-10 col-sm-offset-1">
          <table class="table">
            <thead>
            <tr>
              <th>Nome</th>
              <th class="text-center col-sm-3">Ações</th>
            </tr>
            </thead>
            <tbody>
            <% List<Category> categories = (ArrayList<Category>) request.getAttribute("categories"); %>
            <% if(request.getParameter("msg") != null) System.out.println(request.getParameter("msg")); %>
            <% for(Category category: categories) { %>
            <tr>
              <td><%= category.getName() %></td>
              <td class="text-center">
                <a href="categorias/editar?id=<%=category.getId() %>" class="table-button">
                  <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                </a>
                <a href="categorias/deletar?id=<%=category.getId() %>" class="table-button">
                  <i class="fa fa-times" aria-hidden="true"></i>
                </a>
              </td>
            </tr>
            <% } %>
            <br>
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
