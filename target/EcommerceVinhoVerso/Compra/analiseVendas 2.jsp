<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.google.gson.Gson" %>
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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gráfico de Compras</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/estilos.css">
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400..700&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a4e795a207.js" crossorigin="anonymous"></script>
    <script src="js/funcoes.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns"></script>
</head>
<body>
<div id="navbarContainer"></div>
<div class="background-image">
    <div class="container d-flex justify-content-center align-items-start min-vh-100 py-5">
        <div class="form-container p-4 glass">
            <h3 class="text-center mb-4">Gráfico de Compras por Produto</h3>

            <div class="main">
                <!-- Formulário para seleção de intervalo de datas e filtros -->
                <form id="filtroForm">
                    <div class="form-row">
                        <div class="form-group col-md-6 d-flex">
                            <label for="dataInicio" style="white-space: nowrap">Data Inicial:</label>
                            <input type="date" class="form-control" id="dataInicio">
                        </div>

                        <div class="form-group col-md-6 d-flex">
                            <label for="dataFim" style="white-space: nowrap">Data Final:</label>
                            <input type="date" class="form-control" id="dataFim">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12 d-flex">
                    <label for="produto">Vinho:</label>
                    <select id="produto" class="form-control">
                        <option value="">Todos</option>
                        <c:forEach var="vinho" items="${vinhos}">
                            <option value="${vinho.nome}">${vinho.nome}</option>
                        </c:forEach>
                    </select>
                    </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-4 d-flex">
                    <label for="tipoVinho" style="white-space: nowrap">Tipo de Vinho:</label>
                    <select class="form-control" id="tipoVinho">
                        <option value="">Todos</option>
                        <c:forEach var="tipoVinho" items="${tiposVinho}">
                            <c:if test="${not (tipoVinho == 'TIPO_DE_VINHO')}">
                                <option value="${tipoVinho}">${tipoVinho}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                        </div>
                        <div class="form-group col-md-5 d-flex">
                    <label for="tipoUva" style="white-space: nowrap">Tipo de Uva:</label>
                    <select class="form-control" id="tipoUva">
                        <option value="">Todos</option>
                        <c:forEach var="tipoUva" items="${tiposUva}">

                            <option value="${tipoUva}">${fn:replace(tipoUva, '_', ' ')}</option>

                        </c:forEach>
                    </select>
                        </div>
                        <div class="form-group col-md-3 d-flex">
                    <label for="pais">País:</label>
                    <select class="form-control" id="pais">
                        <option value="">Todos</option>
                        <c:forEach var="pais" items="${paises}">
                            <c:if test="${not (pais == 'PAÍS')}">
                                <option value="${pais}">${fn:replace(pais, '_', ' ')}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                        </div>
                        </div>
                    <div class="form-row">
                        <div class="form-group col-md-6 d-flex">
                    <button type="button" class="btn" onclick="renderizarGrafico(filtrarDados())">Filtrar por Qtd.</button>
                        </div>
                        <div class="form-group col-md-6 d-flex">
                    <button type="button" class="btn" onclick="renderizarGraficoValor(filtrarDados())">Filtrar por Valor</button>
                        </div>
                    </div>
                </form>

                <!-- Área onde o gráfico será renderizado -->
                <canvas id="compraChart"></canvas>
            </div>
            <button class="btn" type="button" onclick="javascript:window.history.go(-1);">Voltar</button>
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

<script>
    // Declarar a variável chart no escopo global
    let chart;


    // Dados de compras vindos do servidor
    const vinhosPorData = [
        <c:forEach var="dataCompraEntry" items="${vinhosPorData}">
        {
            dataCompra: new Date('${dataCompraEntry.key}'),
            itens: [
                <c:forEach var="vinhoEntry" items="${dataCompraEntry.value}">
                {
                    quantidade: ${vinhoEntry.value},
                    vinho: {
                        nome: '${vinhoEntry.key.nome}',
                        preco: ${vinhoEntry.key.preco},
                        tipoVinho: '${vinhoEntry.key.tipoVinho}',
                        tipoUva: '${vinhoEntry.key.tipoUva}',
                        pais: '${vinhoEntry.key.pais}'
                    }
                },
                </c:forEach>
            ]
        },
        </c:forEach>
    ];

    // Renderiza o gráfico inicial com todos os dados
    // renderizarGrafico(compras);
</script>

</body>
</html>
