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
  <script src="../bower_components/jquery/dist/jquery.min.js"></script>
  <script src="../bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <link rel="stylesheet" type="text/css" href="../bower_components/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="../bower_components/font-awesome/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="../app/assets/stylesheets/index.css">
  <link rel="stylesheet" type="text/css" href="../app/assets/stylesheets/registration.css">
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
      <li class="nav-item"><a href="/supermercado/lojas">Loja</a></li>
      <li class="nav-item"><a href="#">Categoria</a></li>
      <li class="nav-item active"><a href="/supermercado/fornecedores">Fornecedor</a></li>
      <li class="nav-item"><a href="#">Pedido</a></li>
    </ul>
  </nav>
  <div class="content">
    <span class="content-header">Cadastro de Fornecedores</span>
    <hr class="content-line" />
      <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
          <form class="user-form" action="novo" method="post">
            <div class="row">
              <div class="col-sm-3">
                <label>Nome: </label>
              </div>
              <div class="col-sm-9">
                <% if(request.getParameter("name") != null) { %>
                  <input class="user-input pull-right" type="text" name="name" value="<%=request.getParameter("name")%>"/>
                <% } else { %>
                  <input class="user-input pull-right" type="text" name="name" />
                <% } %>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <label>Endereço: </label>
              </div>
              <div class="col-sm-9">
                <% if(request.getParameter("address") != null) { %>
                  <input class="user-input pull-right" type="text" name="address" value="<%=request.getParameter("address")%>"/>
                <% } else { %>
                  <input class="user-input pull-right" type="text" name="address" />
                <% } %>
              </div>
            </div>
            <div class="row form-buttons">
              <div class="col-sm-5 col-sm-offset-7">
                <div class="row">
                  <div class="col-sm-6">
                    <a href="${pageContext.request.contextPath }/lojas" class="form-button btn btn-warning pull-right">Cancelar</a>
                  </div>
                  <div class="col-sm-6">
                    <input type="submit" value="Enviar" class="form-button btn btn-primary pull-right" />
                  </div>
                </div>
              </div>
            </div>
          </form>
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