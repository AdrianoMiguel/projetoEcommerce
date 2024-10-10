<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    const nomeCliente = '${sessionScope.nomeCliente != null ? sessionScope.nomeCliente : "null"}';
    const idCliente = '${sessionScope.idCliente != null ? sessionScope.idCliente : "null"}';
    const status = [
        <c:forEach items="${status}" var="tpStatus" varStatus="status">
        '${tpStatus.toString()}'<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];

</script>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Realizar Troca</title>
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
            <h3 class="text-center mb-4">REALIZAR TROCA</h3>

            <div class="main">
                <form id="trocaContainer" name="trocaContainer">
                    <input type="hidden" name="id" value="${compra.id}">
                    <input type="hidden" name="proximoStatus_${compra.id}" value="${compra.proximoStatus}">
                    <th class="listagem" id="listagem">
                        <table class="table border-0">
                            <thead>
                            <tr>
                                <th>Data</th>
                                <th>Pedido</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="d-flex">
                                    <fmt:formatDate value="${compra.dataHora}" pattern="dd/MM/yyyy HH:mm:ss"/>
                                </td>
                                <td>${compra.id}</td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <table class="table">
                                        <tr>
                                            <th>Trocar</th>
                                            <th>Produto</th>
                                            <th>Qtd.</th>
                                            <th>Valor</th>
                                        </tr>
                                        <c:forEach var="item" items="${compra.carrinho.itens}">
                                            <tr>
                                                <td><input type="checkbox" name="itensSelecionados" id="item_${item.id}" value="${item.id}" onchange="verificarSelecaoParcial()">
                                                </td>
                                                <td>${item.produto.nome}</td>
                                                <td>${item.quantidade}</td>
                                                <td>R$<fmt:formatNumber value="${item.produto.preco * item.quantidade}"
                                                                        pattern="#,##0.00"/></td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                        <div class="form-row col-md-12 justify-content-between d-flex">
                            <div class="form-group col-md-4">
                                <button id="trocaParcialBtn" type="button"
                                        onclick="submitFormGeral('CtrlCompraTroca','post','trocaContainer')"
                                        class="btn btn-menor" disabled>TROCA PARCIAL
                                </button>
                            </div>
                            <div class="form-group col-md-4">
                                <button id="trocaTotalBtn" type="button"
                                        onclick="submitFormGeral('CtrlCompraPedidosDeTroca','post','trocaContainer')"
                                        class="btn btn-menor">TROCA TOTAL
                                </button>
                            </div>
                        </div>
                </form>
            </div>
            <button class="btn" type="button" onclick="javascript:window.history.go(-1);">Voltar</button>
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
