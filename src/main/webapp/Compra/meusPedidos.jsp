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
    <title>Meus Pedidos</title>
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
            <h3 class="text-center mb-4">MEUS PEDIDOS</h3>

            <div class="main">
                <th class="listagem" id="listagemCompras">
                    <table class="table border-0">
                        <thead>
                        <tr>
                            <th>Data</th>
                            <th>Pedido</th>
                            <th>Status</th>
                            <th>Ação</th>
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
                                <td>${fn:replace(compra.status, '_', ' ')}</td>
                                <td>
                                    <form action="CtrlCompraTroca" method="get">
                                        <input type="hidden" id="compraId" name="compraId" value="${compra.id}">
                                        <c:if test="${compra.status == 'ENTREGUE'}">
                                            <button id="realizarTroca_${compra.id}" type="submit" class="btn btn-menor">SOLICITAR TROCA</button>
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
                                                        -R$<fmt:formatNumber value="${compra.valorFinal * -1}" pattern="#,##0.00"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        R$<fmt:formatNumber value="${compra.valorFinal}" pattern="#,##0.00"/>
                                                    </c:otherwise>
                                                </c:choose>
                                               </th>
                                        </tr>
                                        <c:if test="${compra.pagamentos != null}">
                                            <tr>
                                                <th>Pagamentos:</th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                            <c:forEach var="pagamento" items="${compra.pagamentos}">
                                                <tr>
                                                    <td>Cartão ${fn:replace(pagamento.cartao.bandeira, '_', ' ')} Final ${fn:substring(pagamento.cartao.numero, fn:length(pagamento.cartao.numero) - 4, fn:length(pagamento.cartao.numero))}</td>
                                                    <td></td>
                                                    <td>R$<fmt:formatNumber value="${pagamento.valor}"
                                                                            pattern="#,##0.00"/></td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
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
