<%@page import="com.supermercado.models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="com.supermercado.models.Category" %>
<%@ page import="com.supermercado.models.Provider" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>Supermercado - Novo Produto</title>
    <script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="../bower_components/jquery/dist/jquery.mask.min.js"></script>
    <script src="../bower_components/jquery/dist/masks.js"></script>
    <script src="../bower_components/bootstrap/dist/js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="../bower_components/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../bower_components/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="../app/assets/stylesheets/index.css">
</head>
<body>
<header class="container-fluid">
    <h1 class="logo">Supermercado</h1>
</header>
<nav>
    <ul class="nav-list">
        <li class="nav-item"><a href="../pedido">Novo Pedido</a></li>
        <li class="nav-item"><a href="../clientes">Cliente</a></li>
        <li class="nav-item"><a href="../vendedores">Vendedor</a></li>
        <li class="nav-item active"><a href="../produtos">Produto</a></li>
        <li class="nav-item"><a href="../lojas">Loja</a></li>
        <li class="nav-item"><a href="../categorias">Categoria</a></li>
        <li class="nav-item"><a href="../fornecedores">Fornecedor</a></li>
        <li class="nav-item"><a href="../pedidos">Pedido</a></li>
    </ul>
</nav>
<div class="content">
    <span class="content-header">Novo Produto</span>
    <hr class="content-line" />
    <div class="products">
        <div class="row">
            <form action="novo" method="POST" class="form-group">
                <div class="col-sm-6 col-sm-offset-3 js-user-seller">
                    <% List<Category> categories = (ArrayList<Category>) request.getAttribute("categories"); %>
                    <% List<Provider> providers = (List<Provider>) request.getAttribute("providers"); %>
                    <% if(categories.isEmpty() || providers.isEmpty()) { %>
                    <div class="col-sm-12 text-center">
                        <span>Não existem categorias ou provedores cadastrados!</span>
                    </div>
                    <% } else { %>
                    <label for="name">Nome:</label>
                    <input class="form-control" type="text" id="name" name="name" />
                    <br>
                    <label for="category">Categoria:</label>
                    <select class="form-control" id="category" name="category">
                        <% for(Category category : categories) { %>
                        <option value="<%= category.getId() %>">
                            <%= category.getName() %>
                        </option>
                        <% } %>
                    </select>
                    <br>
                    <label for="provider">Fornecedor:</label>
                    <select class="form-control" id="provider" name="provider">
                        <% for(Provider provider : providers) { %>
                        <option value="<%= provider.getId() %>">
                            <%= provider.getName() %>  - <%= provider.getAddress() %>
                        </option>
                        <% } %>
                    </select>
                    <br>
                    <label for="price">Preço (R$):</label>
                    <input class="form-control money" type="text" id="price" name="price" />
                    <% } %>
                    <br>
                    <div class="row form-buttons">
                        <div class="col-sm-6 col-sm-offset-6">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="../produtos" class="form-button btn btn-warning pull-right">Cancelar</a>
                                </div>
                                <div class="col-sm-6">
                                    <input type="submit" value="Salvar" class="form-button btn btn-primary pull-right" />
                                </div>
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
