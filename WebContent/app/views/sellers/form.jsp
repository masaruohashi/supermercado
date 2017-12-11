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
      <li class="nav-item"><a href="#">Produto</a></li>
      <li class="nav-item"><a href="#">Loja</a></li>
      <li class="nav-item"><a href="#">Categoria</a></li>
      <li class="nav-item"><a href="#">Fornecedores</a></li>
      <li class="nav-item"><a href="pedidos">Pedido</a></li>
    </ul>
  </nav>
  <div class="content">
    <span class="content-header">Vendedores</span>
    <hr class="content-line" />
    <div class="sellers">
      <div class="row">
        <div class="col-sm-8 col-sm-offset-2 js-user-seller">
      	  <form action="vendedores" method="POST" class="form-group">      	    
      	    <%Seller seller = (Seller) request.getAttribute("seller"); %>
            <div class="row">
              <% if (seller == null) { %>
              <div class="col-sm-4 col-sm-offset-2 js-user-seller">
                <label>Nome:</label>
                <input type="text" name="name"/>
              </div>
              <div class="col-sm-4 col-sm-offset-2 js-user-seller">
                <label>CPF:</label>
                <input type="text" name="cpf"/>
               </div>
              <div class="col-sm-4 col-sm-offset-2 js-user-seller">
                <input class="hidden" type="text" name="operation" value="add"/>
              </div>
              <% } else { %>
              <div class="col-sm-4 col-sm-offset-2 js-user-seller">
                <label>Nome:</label>
                <input type="text" name="name" value="<%=seller.getName()%>"/>
              </div>
              <div class="col-sm-4 col-sm-offset-2 js-user-seller">
                <label>CPF:</label>
                <input type="text" name="cpf" value="<%=seller.getCpf()%>"/>
               </div>
              <div class="col-sm-4 col-sm-offset-2 js-user-seller">
                <input class="hidden" type="text" name="id" value="<%=seller.getId()%>"/>
              </div>
              <div class="col-sm-4 col-sm-offset-2 js-user-seller">
                <input class="hidden" type="text" name="operation" value="update"/>
              </div>
              <% } %>
            </div>
            <br>
            <div class="row">            
              <div class="col-sm-5 col-sm-offset-7">
                  <div class="col-sm-6 pull-right">          
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