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
    <span class="content-header">Vendedores</span>
    <hr class="content-line" />
    <div class="sellers">
      <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
          <form action="vendedores" method="POST" class="form-group">      	    
            <%Seller seller = (Seller) request.getAttribute("seller"); %>
            <div class="row">
              <% if (seller == null) { %>
                <label>Nome:</label>
                <input class="form-control" type="text" name="name"/>
                <br>
                <label>CPF:</label>
                <input class="form-control" type="text" name="cpf"/>
                <input class="hidden" type="text" name="operation" value="add"/>
              <% } else { %>
                <label>Nome:</label>
                <input class="form-control" type="text" name="name" value="<%=seller.getName()%>"/>
                <br>
                <label>CPF:</label>
                <input class="form-control" type="text" name="cpf" value="<%=seller.getCpf()%>"/>
                <br>
                <input class="hidden" type="text" name="id" value="<%=seller.getId()%>"/>
                <input class="hidden" type="text" name="operation" value="update"/>
              <% } %>
            </div>
            <br>
            <div class="row">
              <div class="col-sm-6 col-sm-offset-6">
                <div class="col-sm-6">
                  <a class="btn btn-warning pull-right" href="vendedores">Cancelar</a>
                </div>
                <div class="col-sm-6">
                  <input class="btn btn-primary pull-right" type="submit" value="Salvar" />
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>     
</body>
</html>