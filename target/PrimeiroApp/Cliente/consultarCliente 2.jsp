<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultar Clientes</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles2.css">
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400..700&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a4e795a207.js" crossorigin="anonymous"></script>
    <script src="js/funcoes.js"></script>
</head>
<body>
<div id="navbarContainer"></div>
<div class="background-image">
    <div class="container d-flex justify-content-center align-items-start min-vh-100 py-5">
        <div class="form-container p-4 glass">
            <h3 class="text-center mb-4">CONSULTAR CLIENTES CADASTRADOS</h3>
            <form action=CtrlClienteConsultar method="post" >
                <div class="form-row">
                    <div class="form-group col-md-2">
                    <label for="filtro">Filtrar por:</label>
                    </div>
                    <div class="form-group col-md-6">
                    <input type="text" id="filtro" name="filtro" class="form-control" required>
                </div>
                    <div class="form-group col-md-4">
                    <button type="submit" class="btn">Consultar</button></div>
                </div>
            </form>
            <br>
            <div class="main">
                <div class="listagem" id="listagemClientes">
                    <c:choose>
                        <c:when test="${not empty clientes}">
                            <ul>
                                <c:forEach var="cliente" items="${clientes}">
                                    <div class="row">
                                        <div class="col-md-8">${cliente.nome}</div>
                                        <div class="col-md-2">
                                        <button class="btn btn-menor" onclick="window.location.href='CtrlClienteAlterar?id=${cliente.id}'">Alterar</button>
                                        </div>
                                        <div class="col-md-2">
                                        <button class="btn btn-menor" onclick="window.location.href='CtrlClienteExcluir?id=${cliente.id}'">Excluir</button>
                                        </div>
                                    </div>
                                </c:forEach>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <p>Nenhum cliente encontrado.</p>
                        </c:otherwise>
                    </c:choose>
        </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        gerarNavbar();
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>