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
    <title>Transacoes</title>
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
            <h3 class="text-center mb-4">PEDIDOS DE TROCA</h3>

            <div class="main">
                <th class="listagem" id="listagemCompras">
                    <table class="table border-0">
                        <thead>
                        <tr>
                            <th>Data</th>
                            <th>Pedido</th>
                            <th>Status</th>
                            <th>Alterar Status para:</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="compra" items="${compras}">
                            <tr>
                                <td class="d-flex">
                                    <button type="button" class="btn btn-menor btn-alternar mr-5"
                                            id="expandir${compra.id}"
                                            onclick="alternarExibicao(${compra.id})">
                                        <i class="fa-solid fa-caret-down"></i>
                                    </button>
                                    <fmt:formatDate value="${compra.dataHora}" pattern="dd/MM/yyyy HH:mm:ss"/>
                                </td>
                                <td>${compra.id}</td>
                                <td id="tpStatusContainer_${compra.id}"
                                    name="tpStatusContainer_${compra.id}">${fn:replace(compra.status, '_', ' ')}</td>
                                <td class="d-flex justify-content-center">
                                    <form class="d-flex align-items-center" name="statusContainer_${compra.id}" id="statusContainer_${compra.id}">
                                        <input type="hidden" name="id" value="${compra.id}">
                                        <input type="hidden" name="proximoStatus_${compra.id}" value="${compra.proximoStatus}">
                                        <input type="hidden" name="reporEstoque_${compra.id}" id="reporEstoque_${compra.id}" value="">
                                        <c:if test="${compra.status == 'PAGAMENTO_APROVADO' || compra.status == 'EM_TRANSPORTE'}">
                                            <button type="button"
                                                    class="btn btn-menor" onclick="submitFormGeral('CtrlCompraTransacoes','POST','statusContainer_${compra.id}')">${fn:replace(compra.proximoStatus, '_', ' ')}</button>
                                        </c:if>
                                        <c:if test="${compra.proximoStatus == 'TROCADO'}">TROCADO, REPOR?
                                            <button type="button" class="btn btn-menor ml-1" id="reporSimBtn_${compra.id}" onclick="Repor(${compra.id}); submitFormGeral('CtrlCompraPedidosDeTroca','post','statusContainer_${compra.id}')">
                                                SIM
                                            </button>
                                            <button type="button" class="btn btn-menor ml-1" id="reporNaoBtn_${compra.id}" onclick="naoRepor(${compra.id}); submitFormGeral('CtrlCompraPedidosDeTroca','post','statusContainer_${compra.id}')">NÃO

                                            </button>
                                        </c:if>
                                    </form>
                                </td>
                            </tr>
                            <tr id="detalhes${compra.id}" class="collapse">
                                <!-- A classe collapse é aplicada inicialmente -->
                                <td colspan="3">
                                    <table class="table">
                                        <tr>
                                            <th>Produto</th>
                                            <th>Qtd.</th>
                                            <th>Valor</th>
                                        </tr>
                                        <c:forEach var="item" items="${compra.carrinho.itens}">
                                            <tr>
                                                <td>${item.produto.nome}</td>
                                                <td>${item.quantidade}</td>
                                                <td>R$<fmt:formatNumber value="${item.produto.preco * item.quantidade}"
                                                                        pattern="#,##0.00"/></td>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${compra.cupons != null}">

                                            <c:forEach var="cupom" items="${compra.cupons}">
                                                <tr>
                                                    <th>Cupom Aplicado: ${cupom.codigo}</th>
                                                    <td></td>
                                                    <td>-R$<fmt:formatNumber value="${cupom.valor}"
                                                                             pattern="#,##0.00"/></td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                        <tr>
                                            <th>Frete: ${compra.frete.prazo}</th>
                                            <th></th>
                                            <td>R$<fmt:formatNumber value="${compra.frete.valor}"
                                                                    pattern="#,##0.00"/></td>
                                        </tr>
                                        <tr>
                                            <th>Total:</th>
                                            <th></th>
                                            <th>
                                                <c:choose>
                                                    <c:when test="${compra.valorFinal < 0}">
                                                        -R$<fmt:formatNumber value="${compra.valorFinal * -1}"
                                                                             pattern="#,##0.00"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        R$<fmt:formatNumber value="${compra.valorFinal}"
                                                                            pattern="#,##0.00"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th>Endereço de Entrega:</th>
                                        </tr>
                                        <tr>
                                            <th>
                                                    ${compra.enderecoEntrega.tipoLograd} ${compra.enderecoEntrega.logradouro}, ${compra.enderecoEntrega.numero}
                                                - ${compra.enderecoEntrega.bairro.nome}, ${compra.enderecoEntrega.bairro.cidade.nome}-${compra.enderecoEntrega.bairro.cidade.estado.nome}
                                                CEP ${compra.enderecoEntrega.cep}<br>
                                            </th>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
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
