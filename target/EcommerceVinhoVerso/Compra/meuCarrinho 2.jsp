<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    const nomeCliente = '${sessionScope.nomeCliente != null ? sessionScope.nomeCliente : "null"}';
    const idCliente = '${sessionScope.idCliente != null ? sessionScope.idCliente : "null"}';
    const cartoes = [
        <c:forEach var="cartao" items="${cliente.cartoes}">
        {
            id: ${cartao.id},
            nome: '${cartao.nome}',
            bandeira: '${cartao.bandeira}',
            numero: '${cartao.numero}',
            codseg: '${cartao.cod}',
            principal: ${cartao.principal}
        }<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];
    const cupons = [
        <c:forEach var="cupom" items="${cliente.cupons}">
        {
            id: ${cupom.id},
            codigo: '${cupom.codigo}',
            valor: ${cupom.valor},
            utilizado: ${cupom.utilizado}
        }<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];

</script>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meu Carrinho</title>
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
            <div class="form-row">
                <div class="form-group col-md-8">
                    <h3 class="text-center mb-4">Meu Carrinho</h3>
                </div>
                <div class="form-group col-md-4">
                    <div id="contador"></div>
                </div>
            </div>
            <form id="carrinhoForm" method="POST">
                <table class="table border-0">
                    <thead>
                    <tr>
                        <th>Produto</th>
                        <th>Qtd.</th>
                        <th>Preço</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${carrinho.itens}">
                    <tr id="linha_${item.produto.id}">
                        <td>${item.produto.nome}</td>
                        <td>
                            <select name="quantidade_${item.produto.id}" class="form-control"
                                    onchange="atualizarPreco(${item.produto.id}, ${item.produto.preco}, this.value)">
                                <c:forEach var="i" begin="1" end="${item.produto.qtdeEstoque + item.quantidade}">
                                    <option value="${i}"
                                            <c:if test="${i == item.quantidade}">selected</c:if>>${i}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td id="preco_${item.produto.id}">R$<fmt:formatNumber
                                value="${item.produto.preco * item.quantidade}" pattern="#,##0.00"/></td>
                        <td>
                            <button id="remover_${item.produto.id}" type="button" class="btn btn-danger btn-menor"
                                    onclick="removerItem(${item.produto.id})">Remover
                            </button>
                        </td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <th id="prazoFrete" colspan="2"></th>
                        <td id="valorFrete" name="valorFrete"></td>
                        <input type="hidden" id="valorFreteHidden" name="valorFreteHidden" value="0">
                        <input type="hidden" id="prazoFreteHidden" name="prazoFreteHidden" value="">

                        <td></td>
                    </tr>
                    <tbody id="cuponsTabela">
                <input type="hidden" id="numCuponsHidden" name="numCuponsHidden" value="0">
                    </tbody>
                    <tr>
                        <td colspan="2"><strong>Total</strong></td>
                        <td id="totalCarrinho">R$<fmt:formatNumber
                                value="${carrinho.total}" pattern="#,##0.00"/></td>
                        <input type="hidden" id="totalCarrinhoHidden" name="totalCarrinhoHidden" value="<fmt:formatNumber
                                value="${carrinho.total}" pattern="#,##0.00"/>">
                        <td></td>
                    </tr>
                    </tbody>
                </table>
                <div class="d-flex justify-content-start">
                    <label>Endereço de Entrega:</label>
                </div>
                <div class="form-row">
                    <div class="form-group d-flex col-md-9">
                        <select id="enderecoEntrega" name="enderecoEntrega" class="form-control">
                            <c:set var="contador" value="0"/>
                            <c:forEach var="end" items="${cliente.endEnt}">
                                <option value="${contador}">${end.tipoLograd} ${end.logradouro}, ${end.numero} -
                                        ${end.bairro.nome}, ${end.bairro.cidade.nome}-${end.bairro.cidade.estado.nome} ${end.bairro.cidade.estado.pais.nome}
                                    CEP ${end.cep}</option>
                                <c:set var="contador" value="${contador + 1}"/>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-3">
                        <button id="calcularFreteBtn" type="button" class="btn btn-primary btn-menor"
                                onclick="calcularFrete()">Calcular Frete
                        </button>
                    </div>
                </div>
                <div class="form-row d-flex">
                    <div class="form-group col-md-3">
                        <button id="novoEndereco" type="button" class="btn btn-menor"
                                onclick="submitFormGeral('CtrlClienteNovoEndEnt','GET','carrinhoForm')">Novo Endereço
                        </button>
                    </div>
                </div>
                <div class="d-flex justify-content-start">
                    <label>Forma de Pagamento:</label>
                </div>
                <div id="cuponsContainer" name="cuponsContainer">
                    <div class="form-row">
                        <div class="form-group col-md-3">
                            <label>Inserir Cupom:</label>
                        </div>
                        <div class="form-group col-md-6">
                            <input type="text" id="cupom_0" name="cupom_0" class="form-control"
                                   placeholder="Digite seu cupom">
                        </div>
                        <div class="form-group col-md-3">
                            <button type="button" id="botaoCupom_0" class="btn btn-primary btn-menor" onclick="aplicarCupom(0)">Aplicar
                                Cupom
                            </button>
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <button type="button" id="adicionarCupomBtn" class="btn btn-menor"
                                onclick="adicionarCupom()" disabled>Usar Outro Cupom
                        </button>
                    </div>
                </div>
                <div id="cartoesContainer">
                    <input type="hidden" id="numCartoesHidden" name="numCartoesHidden" value="1">
                    <div class="form-row">
                        <div class="form-group col-md-3">
                            <label>Selecionar Cartão:</label>
                        </div>
                        <div class="form-group col-md-6">
                            <select id="cartao_0" name="cartao_0" class="form-control">
                                <c:forEach var="cartao" items="${cliente.cartoes}">
                                    <option value="${cartao.id}">${cartao.bandeira}
                                        Final ${fn:substring(cartao.numero, fn:length(cartao.numero) - 4, fn:length(cartao.numero))}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-3">
                            <input type="number" id="valor_cartao_0" name="valor_cartao_0" class="form-control"
                                   placeholder="Valor">
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <button type="button" id="adicionarCartaoBtn" class="btn btn-menor"
                                onclick="adicionarCartaoCompra(cartoes,maxCartoes)">Usar Outro Cartao
                        </button>
                    </div>
                </div>
                <div class="form-row d-flex">
                    <div class="form-group col-md-3">
                        <button id="novoCartaoBtn" type="button" class="btn btn-menor"
                                onclick="submitFormGeral('CtrlClienteNovoCartao','GET','carrinhoForm')">Novo Cartão
                        </button>
                    </div>
                </div>
                <div class="form-row d-flex justify-content-between">
                    <div class="form-group col-md-5">
                        <button id="continuarComprando" type="button" class="btn"
                                onclick="submitFormGeral('CtrlCompraAtualizarCarrinho', 'GET','carrinhoForm')">Continuar Comprando
                        </button>
                    </div>
                    <div class="form-group col-md-4">
                        <button id="finalizarCompra" type="button" class="btn" onclick="submitFormGeral('CtrlCompraFinalizar', 'POST','carrinhoForm')">
                            Finalizar Compra
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        gerarNavbar();
        const tempoInicialString = "${carrinho.tempo}"; // Formato "hh:mm:ss"
        const tempoInicial = converterParaSegundos(tempoInicialString);
        const tempoFinal = tempoInicial + 600;
        const contadorElement = document.getElementById('contador');
        const intervalo = setInterval(() => {

            const agora = new Date();
            const horas = agora.getHours();
            const minutos = agora.getMinutes();
            const segundos = agora.getSeconds();
            const tempoAtual = horas * 3600 + minutos * 60 + segundos;

            const tempoRestante = Math.max(0, tempoFinal - tempoAtual);

            const minutosRestantes = Math.floor(tempoRestante / 60);
            const segundosRestantes = tempoRestante % 60;

            if (tempoInicialString !== "") {
                contadorElement.textContent = `Seu carrinho expira em ` + minutosRestantes + `m ` + segundosRestantes + `s`;
            }
            if (tempoRestante <= 0) {
                clearInterval(intervalo);
                contadorElement.textContent = 'Tempo esgotado!';
            }
        }, 1000);
    });
    const botaoAdicionarCartao = document.getElementById('adicionarCartaoBtn');
    const cartoesContainer = document.getElementById('cartoesContainer');
    let numCartao = 1;
    const maxCartoes = cartoes.length;
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
