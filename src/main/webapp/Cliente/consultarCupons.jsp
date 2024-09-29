<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <title>Consultar Cupons</title>
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
            <h3 class="text-center mb-4">CUPONS</h3>

            <br>
            <div class="main">
                <div class="listagem" id="listagemLogs">
                    <ul>
                        <c:choose>
                            <c:when test="${not empty cliente.cupons}">
                                <div class="form-row justify-content-between">
                                    <div class="form-group col-md-6">
                                        <strong>Codigo</strong>
                                    </div>
                                    <div class="form-group col-md-3">
                                        <strong>Valor</strong>
                                    </div>
                                    <div class="form-group col-md-3">
                                        <strong>Utilizado</strong>
                                    </div>
                                </div>
                                <c:forEach var="cupom" items="${cliente.cupons}">
                                    <div class="form-row justify-content-between">
                                        <div class="form-group col-md-6">
                                                ${cupom.codigo}
                                        </div>
                                        <div class="form-group col-md-3">
                                            <fmt:formatNumber
                                                    value="${cupom.valor}" pattern="#,##0.00"/>
                                        </div>
                                        <div class="form-group col-md-3">
                                            <c:if test="${cupom.utilizado}">
                                                <span class="badge badge-secondary justify-content-center">SIM</span>
                                            </c:if>
                                            <c:if test="${!cupom.utilizado}">
                                                <span class="badge badge-success justify-content-center">NAO</span>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <p>Nenhum Cupom encontrado.</p>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
                <br><br>
                <div class="row justify-content-between">
                    <div class="col-md-4">
                        <button class="btn" type="button" onclick="javascript:window.history.go(-1);">Voltar</button>
                    </div>
                    <div class="col-md-6">
                        <form action=CtrlClienteCupom method="get">
                            <input type="hidden" name="id" value="${cliente.id}">
                            <button class="btn" type="submit">Novo Cupom</button>
                        </form>
                    </div>
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