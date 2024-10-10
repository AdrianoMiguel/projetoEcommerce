<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    const nomeCliente = '${sessionScope.nomeCliente != null ? sessionScope.nomeCliente : "null"}';
    const idCliente = '${sessionScope.idCliente != null ? sessionScope.idCliente : "null"}';
    const vinhoTiposUva = [
        <c:forEach items="${vinho.tipoUva}" var="tipoUva" varStatus="status">
        '${tipoUva.toString()}'<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];
</script>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${vinho.nome}</title>
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
            <h3 class="text-center mb-4">${vinho.nome}</h3>
            <form action=CtrlCompraAdicionarCarrinho method="post" id="formularioSalvar" name="formularioSalvar">
                <input type="hidden" id="clienteId" name="clienteId" value="${sessionScope.idCliente}">
                <input type="hidden" id="vinhoId" name="vinhoId" value="${vinho.id}">
                <div class="form-row">
                    <div class="col-md-4 mb-4 text-center">
                        <img src="imagens/vinhos/${vinho.id-2}.png" alt="Imagem do vinho ${vinho.nome}"
                             style=" max-height: 350px; object-fit: cover;">
                    </div>
                    <!-- Coluna do conteúdo -->
                    <div class="col-md-8">
                        <div class="form-row">
                            <div class="col-md-8">
                                <h5>
                                    Vinho ${vinho.tipoVinho}<br><br>
                                    Safra: </h5>${vinho.safra}<br>
                                <h5> Teor Alcoólico: </h5>${vinho.teorAlc}%<br>
                                <h5> Origem: </h5>${vinho.pais}<br>
                                <h5> Volume:</h5> ${vinho.volume}ml<br>
                            </div>
                            <div class="col-md-4 d-flex">
                                <h5 class="valor">VALOR R$<fmt:formatNumber
                                        value="${vinho.preco}" pattern="#,##0.00"/></h5>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-12">
                                <h5> Tipos de Uva:</h5>
                                <div id="tiposDeUva"></div>
                                <br>
                            </div>
                            <div class="form-row">
                                <div class="col-md-12">
                                    <h5> Descrição:</h5>
                                    ${vinho.descricao}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12 d-flex">

                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4 d-flex text-right">
                    </div>
                    <div class="form-group col-md-8 d-flex">
                        <label for="quantidadeVinho" class="mr-2">QUANTIDADE:</label>
                        <select id="quantidadeVinho" name="quantidade" class="form-control w-auto mr-5"
                                <c:if test="${vinho.qtdeEstoque == 0}">
                                    disabled
                                </c:if>
                        >
                            <c:choose>
                                <c:when test="${vinho.qtdeEstoque == 0}">
                                    <option value="0">Esgotado</option>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="i" begin="1" end="${vinho.qtdeEstoque}">
                                        <option value="${i}">${i}</option>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                </div>
                    <div class="form-row justify-content-between">
                        <div class="form-group col-md-3">
                            <button type="button" class="btn" id="voltar"
                                    onclick="submitFormGeral('CtrlProdutoListar', 'GET','formularioSalvar')">Voltar
                            </button>
                        </div>
                        <div class="form-group col-md-4">
                            <button type="submit" class="btn" id="cadastrar">Adicionar ao Carrinho</button>
                        </div>
                    </div>

            </form>
        </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        gerarNavbar();
        document.getElementById('tiposDeUva').innerHTML = vinhoTiposUva;
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
