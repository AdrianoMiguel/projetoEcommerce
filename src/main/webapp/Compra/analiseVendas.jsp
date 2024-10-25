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
                    <label for="dataInicio">Data Início:</label>
                    <input type="date" id="dataInicio">

                    <label for="dataFim">Data Fim:</label>
                    <input type="date" id="dataFim">

                    <label for="produto">Vinho:</label>
                    <select id="produto">
                        <option value="">Todos</option>
                        <c:forEach var="vinho" items="${vinhos}">
                            <option value="${vinho.nome}">${vinho.nome}</option>
                        </c:forEach>
                    </select>

                    <label for="tipoVinho">Tipo de Vinho:</label>
                    <select id="tipoVinho">
                        <option value="">Todos</option>
                        <c:forEach var="tipoVinho" items="${tiposVinho}">
                            <option value="${tipoVinho}">${tipoVinho}</option>
                        </c:forEach>
                    </select>

                    <label for="tipoUva">Tipo de Uva:</label>
                    <select id="tipoUva">
                        <option value="">Todos</option>
                        <c:forEach var="tipoUva" items="${tiposUva}">

                            <option value="${tipoUva}">${fn:replace(tipoUva, '_', ' ')}</option>
                         
                        </c:forEach>
                    </select>

                    <label for="pais">País:</label>
                    <select id="pais">
                        <option value="">Todos</option>
                        <c:forEach var="pais" items="${paises}">
                            <option value="${pais}">${pais}</option>
                        </c:forEach>
                    </select>

                    <button type="button" onclick="filtrarDados()">Filtrar</button>
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
    const compras = [
        <c:forEach var="compra" items="${compras}">
        {
            id: ${compra.id},
            clienteId: ${compra.clienteId},
            dataHora: '${compra.dataHora}',
            valorFinal: ${compra.valorFinal},
            itens: [
                <c:forEach var="item" items="${compra.carrinho.itens}">
                {
                    quantidade: ${item.quantidade},
                    produto: {
                        nome: '${item.produto.nome}',
                        safra: ${item.produto.safra},
                        preco: ${item.produto.preco},
                        tipoVinho: '${item.produto.tipoVinho}',
                        tipoUva: [
                            <c:forEach var="uva" items="${item.produto.tipoUva}">
                            '${uva}'<c:if test="${!status.last}">, </c:if>
                            </c:forEach>
                        ],
                        pais: '${item.produto.pais}',
                        volume: ${item.produto.volume}
                    }
                }<c:if test="${!status.last}">, </c:if>
                </c:forEach>
            ]
        }<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];

    // Função para renderizar o gráfico
    function renderizarGrafico(filtrados) {
        const ctx = document.getElementById('compraChart').getContext('2d');
        const datasets = [];

        // Organizando os dados por produto
        const dadosPorProduto = {};

        filtrados.forEach(compra => {
            compra.itens.forEach(item => {
                const produtoNome = item.produto.nome;
                if (!dadosPorProduto[produtoNome]) {
                    dadosPorProduto[produtoNome] = [];
                }
                dadosPorProduto[produtoNome].push({
                    x: new Date(compra.dataHora),
                    y: item.quantidade
                });
            });
        });

        // Criar datasets para cada produto
        for (const produto in dadosPorProduto) {
            datasets.push({
                label: produto,
                data: dadosPorProduto[produto],
                fill: false,
                borderColor: getRandomColor(),
                tension: 0.1
            });
        }

        // Se já houver um gráfico, destrua-o para evitar duplicação
        if (chart) {
            chart.destroy();
        }

        chart = new Chart(ctx, {
            type: 'line',
            data: {
                datasets: datasets
            },
            options: {
                scales: {
                    x: {
                        type: 'time',
                        time: {
                            unit: 'day'
                        }
                    },
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }

    // Função para gerar uma cor aleatória
    function getRandomColor() {
        const letters = '0123456789ABCDEF';
        let color = '#';
        for (let i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }

    // Função para filtrar dados com base no intervalo de datas e categorias
    function filtrarDados() {
        const dataInicio = new Date(document.getElementById('dataInicio').value);
        const dataFim = new Date(document.getElementById('dataFim').value);
        const produtoSelecionado = document.getElementById('produto').value;
        const tipoVinhoSelecionado = document.getElementById('tipoVinho').value;
        const tipoUvaSelecionado = document.getElementById('tipoUva').value;
        const paisSelecionado = document.getElementById('pais').value;

        const filtrados = compras.filter(compra => {
            const dataCompra = new Date(compra.dataHora);

            return (!isNaN(dataInicio) ? dataCompra >= dataInicio : true) &&
                (!isNaN(dataFim) ? dataCompra <= dataFim : true) &&
                (produtoSelecionado ? compra.itens.some(item => item.produto.nome === produtoSelecionado) : true) &&
                (tipoVinhoSelecionado ? compra.itens.some(item => item.produto.tipoVinho === tipoVinhoSelecionado) : true) &&
                (tipoUvaSelecionado ? compra.itens.some(item => item.produto.tipoUva.includes(tipoUvaSelecionado)) : true) &&
                (paisSelecionado ? compra.itens.some(item => item.produto.pais === paisSelecionado) : true);
        });

        renderizarGrafico(filtrados);
    }

    // Renderiza o gráfico inicial com todos os dados
    // renderizarGrafico(compras);
</script>

</body>
</html>
