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
      <li class="nav-item active"><a href="#">Novo Pedido</a></li>
      <li class="nav-item"><a href="#">Cliente</a></li>
      <li class="nav-item"><a href="#">Vendedor</a></li>
      <li class="nav-item"><a href="#">Produto</a></li>
      <li class="nav-item"><a href="#">Loja</a></li>
      <li class="nav-item"><a href="#">Categoria</a></li>
      <li class="nav-item"><a href="#">Fornecedores</a></li>
      <li class="nav-item"><a href="#">Pedido</a></li>
    </ul>
  </nav>
  <div class="content">
    <span class="content-header">Clientes</span>
    <hr class="content-line" />
    <div class="clients">
      <div class="row">
      	<form action="clientes" method="GET" class="form-group">
          <div class="col-sm-8 col-sm-offset-2 js-user-seller">
            <% List<Client> clients = (ArrayList<Client>) request.getAttribute("clients"); %>
            <% if(clients.isEmpty()) { %>
              <div class="col-sm-12 text-center">
                <span>Não existem clientes cadastrados!</span>
              </div>
            <% } %>
            <br>
            <div class="col-sm-12">
              <input class="hidden" name="acao" value="adicionar"/>
              <input class="btn btn-primary pull-right js-send-products" type="submit" value="Novo cliente" />
            </div>
          </div>
        </form>
      </div>
      <div class="row">
        <form action="clientes" method="POST" class="form-group" id="client-form">
          <div class="col-sm-10 col-sm-offset-1 js-products">
            <% if(!clients.isEmpty()) { %>
                <table class="table">
                  <thead>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Endereço</th>
                    <th>Atualizar</th>
                    <th>Excluir</th>             
                  </thead>
                  <tbody>
                    <% for(Client client : clients) { %>
                      <tr>                        
                        <td><%= client.getName() %></td>
                        <td><%= client.getCpf() %></td>
                        <td><%= client.getAddress() %></td>
                        <td>
                        	<a href="${pageContext.request.contextPath}/clientes?acao=atualizar&id=<%= client.getId() %>">
								<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
							</a>
                        </td>
                        <td>
                        	<a href="${pageContext.request.contextPath}/clientes?acao=excluir&id=<%= client.getId() %>">
								<img class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							</a>
                        </td>    
                      </tr>
                    <% } %>
                  </tbody>
                </table>
                <input class="hidden" name="acao" id="action"/>
                <input class="hidden" name="id" id="idbox"/>              
            <% } %>
          </div>          
        </form>
      </div>
    </div>
  </div>
  <% if(request.getAttribute("message") != null) { %>
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
            <%= request.getAttribute("message") %>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
          </div>
        </div>
      </div>
    </div>
    <script>$('#messageModal').modal({show: true})</script>
  <% } %>
  <script>
    function atualizar(id){
    	$("#idbox").val(id);
    	$("#action").val("atualizar");
    	$("#client-form").submit();
    }
    function excluir(id) {
    	$("#idbox").val(id);
    	$("#action").val("excluir");
    	$("#client-form").submit();
    }
    $(document).ready(function() {        
    	$(".js-select-all").click(function() {
        if($(this).is(":checked")) {
          $(".js-client-checkbox").prop('checked', true);
        }
        else {
          $(".js-client-checkbox").prop('checked', false);
        }
      });

      $(".js-send-user-seller").click(function() {
        $(".js-user-seller").addClass("hidden");
        $(".js-products").removeClass("hidden");
      });

      $(".js-send-products").click(function() {
          $(".js-products").addClass("hidden");
          $(".js-payment-information").removeClass("hidden");
        });

      $(".js-products-back").click(function() {
        $(".js-products").addClass("hidden");
        $(".js-user-seller").removeClass("hidden");
      });

      $(".js-payment-back").click(function() {
          $(".js-payment-information").addClass("hidden");
          $(".js-products").removeClass("hidden");
        });
    });
  </script>
</body>
</html>