<%@page import="com.supermercado.models.Store"%>
<%@page import="com.supermercado.models.Seller"%>
<%@page import="com.supermercado.models.Client"%>
<%@page import="com.supermercado.models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Supermercado - Novo Pedido</title>
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
    </ul>
  </nav>
  <div class="content">
    <span class="content-header">Novo Pedido</span>
    <hr class="content-line" />
    <div class="products">
      <div class="row">
        <form action="pedido" method="POST" class="form-group">
          <div class="col-sm-8 col-sm-offset-2 js-user-seller">
            <% List<Client> clients = (ArrayList<Client>) request.getAttribute("clients"); %>
            <% List<Seller> sellers = (ArrayList<Seller>) request.getAttribute("sellers"); %>
            <% List<Store> stores = (ArrayList<Store>) request.getAttribute("stores"); %>
            <% if(clients.isEmpty() || sellers.isEmpty()) { %>
              <div class="col-sm-12 text-center">
                <span>Não existem clientes ou vendedores cadastrados!</span>
              </div>
            <% } else { %>
              <label for="seller">Vendedor:</label>
              <select class="form-control" id="seller" name="seller">
                <% for(Seller seller : sellers) { %>
                  <option value="<%= seller.getId() %>">
                    <%= seller.getName() %> - <%= seller.getCpf() %> 
                  </option>
                <% } %>
              </select>
              <br>
              <label for="client">Cliente:</label>
              <select class="form-control" id="client" name="client">
                <% for(Client client : clients) { %>
                  <option value="<%= client.getId() %>">
                    <%= client.getName() %>  - <%= client.getCpf() %>
                  </option>
                <% } %>
              </select>
              <br>
              <label for="store">Loja:</label>
              <select class="form-control" id="store" name="store">
                <% for(Store store : stores) { %>
                  <option value="<%= store.getId() %>">
                    <%= store.getName() %> 
                  </option>
                <% } %>
              </select>
            <% } %>
            <br>
            <div class="col-sm-12">
              <span class="btn btn-primary pull-right js-send-user-seller">Enviar</span>
            </div>
          </div>
          <div class="col-sm-10 col-sm-offset-1 hidden js-products">
            <% List<Product> products = (ArrayList<Product>) request.getAttribute("products"); %>
            <% if(products.isEmpty()) { %>
              <div class="col-sm-12 text-center">
                <span>Não existem produtos cadastrados!</span>
              </div>
            <% } else { %>
                <table class="table">
                  <thead>
                    <th><input type="checkbox" class="js-select-all" /></th>
                    <th>Categoria</th>
                    <th>Nome</th>
                    <th>Preço</th>
                    <th class="col-sm-2">Quantidade</th>
                  </thead>
                  <tbody>
                    <% for(Product product : products) { %>
                      <tr>
                        <td><input class="js-product-checkbox" type="checkbox" name="products" value="<%= product.getId() %>" /></td>
                        <td><%= product.getCategory().getName() %></td>
                        <td><%= product.getName() %></td>
                        <td>R$<%= product.getFormattedPrice() %></td>
                        <td><input class="js-product-quantity-input form-control" type="number" min="1" value="1" name="<%= product.getId() %>" /></td>
                      </tr>
                    <% } %>
                  </tbody>
                </table>
                <div class="col-sm-5 col-sm-offset-7">
                  <div class="col-sm-6 pull-right">
                    <span class="btn btn-primary pull-right js-send-products">Enviar</span>
                  </div>
                  <div class="col-sm-6 pull-right">
                    <span class="btn btn-warning pull-right js-products-back">Voltar</span>
                  </div>
                </div>
            <% } %>
          </div>
          <div class="col-sm-8 col-sm-offset-2 js-payment-information hidden">
            <label for="payment-method">Pagamento:</label>
            <select class="form-control" id="payment-method" name="payment-method">
              <option value="cash">Dinheiro</option>
              <option value="card">Cartão</option>
            </select>
            <br>
            <label for="delivery-method">Entrega:</label>
            <input class="form-control" type="text" name="delivery-method" />
            <br>
	          <div class="col-sm-5 col-sm-offset-7">
	            <div class="col-sm-6 pull-right">
	              <input type="submit" class="btn btn-primary" value="Enviar" />
	            </div>
	            <div class="col-sm-6 pull-right">
	              <span class="btn btn-warning pull-right js-payment-back">Voltar</span>
	            </div>
	          </div>
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
    $(document).ready(function() {
      $(".js-select-all").click(function() {
        if($(this).is(":checked")) {
          $(".js-product-checkbox").prop('checked', true);
        }
        else {
          $(".js-product-checkbox").prop('checked', false);
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