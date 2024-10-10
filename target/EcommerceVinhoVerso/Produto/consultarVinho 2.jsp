<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script>
    const nomeCliente = '${sessionScope.nomeCliente != null ? sessionScope.nomeCliente : "null"}';
    const idCliente = '${sessionScope.idCliente != null ? sessionScope.idCliente : "null"}';
</script>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultar Vinhos</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/estilos.css">
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400..700&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a4e795a207.js" crossorigin="anonymous"></script>
    <script src="js/funcoes.js"></script>
</head>
<body>
<div id="navbarContainer"></div>
<div class="background-image">
    <div class="container d-flex justify-content-center align-items-start min-vh-100 py-5">
        <div class="form-container p-4 glass">
            <h3 class="text-center mb-4">CONSULTAR VINHOS CADASTRADOS</h3>
            <form action=CtrlProdutoConsultar method="post" >
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <label for="filtro">Filtrar por:</label>
                    </div>
                    <div class="form-group col-md-8">
                        <input type="text" id="filtro" name="filtro" class="form-control" required>
                    </div>
                    <div class="form-group col-md-2">
                        <button type="submit" class="btn btn-menor" id="btnConsultar">Consultar</button></div>
                </div>
            </form>
            <br>
            <div class="main">
                <div class="listagem" id="listagemVinhos">
                    <c:choose>
                        <c:when test="${not empty vinhos}">
                            <ul>
                                <div class="form-row d-flex align-items-center">
                                    <div class="col-md-6">NOME</div>
                                    <div class="col-md-2">PAÍS</div>
                                    <div class="col-md-1">SAFRA</div>
                                    <div class="col-md-1">PREÇO</div>
                                    <div class="col-md-1">STATUS</div>
                                    <div class="col-md-1">AÇÕES</div>
                                </div>
                                <c:forEach var="vinho" items="${vinhos}">
                                    <div class="form-row d-flex align-items-center">
                                        <div class="col-md-6">${vinho.nome}</div>
                                        <div class="col-md-2">${vinho.pais}</div>
                                        <div class="col-md-1">${vinho.safra}</div>
                                        <div class="col-md-1">${vinho.preco}</div>
                                        <div class="col-md-1 d-flex text-center justify-content-center">
                                            <c:if test="${vinho.status}">
                                                <span class="badge badge-success">ATIVO</span>
                                            </c:if>
                                            <c:if test="${!vinho.status}">
                                                <span class="badge badge-secondary justify-content-center">INATIVO</span>
                                            </c:if>
                                        </div>

                                        <div class="col-md-1 d-flex">
                                            <button class="btn btn-menor" onclick="window.location.href='CtrlProdutoAlterar?id=${vinho.id}'"><i class="fa-solid fa-pen-to-square"></i></button>
                                            <button class="btn btn-menor ml-1" onclick="window.location.href='CtrlProdutoExcluir?id=${vinho.id}'"><i class="fa-solid fa-trash"></i></button>
                                        </div>
                                    </div>
                                </c:forEach>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <p>Nenhum vinho encontrado.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
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