<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    const nomeCliente = '${sessionScope.nomeCliente != null ? sessionScope.nomeCliente : "null"}';
    const idCliente = '${sessionScope.idCliente != null ? sessionScope.idCliente : "null"}';
</script>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Vinhos</title>
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
        <div class="form-container my-5 form-h-100">
            <div class="form-container barra-pesquisa glass">
                <form action="CtrlProdutoListar" method="post">
                    <div class="row">
                        <div class="col-md-2">
                            <label for="filtro">Pesquisar:</label>
                        </div>
                        <div class="form-group col-md-6">
                            <input type="text" id="filtro" name="filtro" class="form-control" required>
                        </div>
                        <div class="form-group col-md-2">
                            <button type="submit" class="btn btn-menor" id="btnConsultar">Consultar</button>
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-menor" id="btnFiltrar" disabled>Filtros</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="row">
                <c:forEach var="vinho" items="${vinhos}">
                    <div class="col-md-4 mb-4">
                        <div class="card glass">
                            <div class="row no-gutters">
                                <!-- Coluna da imagem -->
                                <div class="col-md-4">
                                    <img src="imagens/vinhos/${vinho.id-2}.png" class="card-img"
                                         alt="Imagem do vinho ${vinho.nome}">
                                </div>
                                <!-- Coluna do conteúdo -->
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title">${vinho.nome}</h5>
                                        <p class="card-text">
                                            Safra: ${vinho.safra}<br>
                                            Teor Alc.: ${vinho.teorAlc}%<br>
                                            Vinho ${vinho.tipoVinho}<br>
                                            Origem: ${vinho.pais}<br>

                                        </p>
                                        <h5 class="card-title">R$<fmt:formatNumber
                                                value="${vinho.preco}" pattern="#,##0.00"/></h5>
                                    </div>
                                </div>

                            </div>
                            <div class="card-footer text-center">
                                <a href="CtrlProdutoDetalhes?id=${vinho.id}" class="btn btn-primary btn-block">Ver
                                    Detalhes</a>
                            </div>
                        </div>
                    </div>

                </c:forEach>
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
