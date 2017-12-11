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
    <script src="../bower_components/jquery/dist/jquery.min.js"></script>
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
        <li class="nav-item"><a href="/pedido">Novo Pedido</a></li>
        <li class="nav-item"><a href="#">Cliente</a></li>
        <li class="nav-item"><a href="#">Vendedor</a></li>
        <li class="nav-item"><a href="/produto">Produtos</a></li>
        <li class="nav-item"><a href="#">Loja</a></li>
        <li class="nav-item active"><a href="/categoria">Categorias</a></li>
        <li class="nav-item"><a href="#">Fornecedores</a></li>
        <li class="nav-item"><a href="#">Pedido</a></li>
    </ul>
</nav>
<div class="content">
    <span class="content-header">Categorias</span>
    <hr class="content-line" />
    <div class="col-sm-12">
        <a href="/categoria/novo" class="form-button btn btn-primary pull-right">Criar categoria</a>
    </div>
    <div class="products">
        <div class="row">
            <div class="col-sm-8 col-sm-offset-2 js-user-seller">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% List<Category> categories = (ArrayList<Category>) request.getAttribute("categories"); %>
                    <% if(request.getParameter("msg") != null) System.out.println(request.getParameter("msg")); %>
                    <% for(Category category: categories) { %>
                    <tr>
                        <td><%= category.getName() %></td>
                        <td>
                            <a href="categoria/editar?id=<%=category.getId() %>" class="btn btn-warning">Editar</a>
                            <a href="categoria/deletar?id=<%=category.getId() %>" class="btn btn-danger">Excluir</a>
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
