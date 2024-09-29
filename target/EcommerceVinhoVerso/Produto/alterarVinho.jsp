<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    const nomeCliente = '${sessionScope.nomeCliente != null ? sessionScope.nomeCliente : "null"}';
    const idCliente = '${sessionScope.idCliente != null ? sessionScope.idCliente : "null"}';
    const vinhoTiposUva = [
        <c:forEach items="${vinho.tipoUva}" var="tipoUva" varStatus="status">
        '${tipoUva.toString()}'<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];
    const tiposUva = [
        <c:forEach items="${tiposUva}" var="tipoUva" varStatus="status">
        '${tipoUva.toString()}'<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];
    const tiposVinho = [
        <c:forEach items="${tiposVinho}" var="tipoVinho" varStatus="status">
        '${tipoVinho.toString()}'<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];
    const paises = [
        <c:forEach items="${paises}" var="pais" varStatus="status">
        '${pais.toString()}'<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];
    const gruposPrecif = [
        <c:forEach items="${gruposPrecif}" var="grupoPrec" varStatus="status">
        '${grupoPrec.toString()}'<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];
    const motivos = [
        <c:forEach items="${motivos}" var="motivo" varStatus="status">
        '${motivo.toString()}'<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];

</script>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alteracao de cadastro de Vinho</title>
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
            <h3 class="text-center mb-4">Alteracao de Cadastro de Vinho</h3>
            <form action=CtrlProdutoAtualizar method="post" id="formularioSalvar" name="formularioSalvar" onsubmit="obterTiposUvaSelecionadas()">
                <div class="form-row">
                    <div class="form-group col-md-9 d-flex">NOME:
                        <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome" value='${vinho.nome}'>
                    </div>
                    <div class="form-group col-md-3 d-flex">SAFRA:
                        <input type="text" class="form-control" id="safra" name="safra" placeholder="Safra (Ano)" value='${vinho.safra}'>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 d-flex">TIPO:
                        <div id="tiposVinhoContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-3 d-flex">PAÍS:
                        <div id="paisesContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-3 d-flex">VOLUME(ML):
                        <input type="text" class="form-control" id="volume" name="volume" placeholder="volume da garrafa em ml" value='${vinho.volume}'>
                    </div>
                    <div class="form-group col-md-3 d-flex"> TEOR ALC.:
                        <input type="text" class="form-control" id="teorAlc" name="teorAlc" placeholder="Teor Alcoolico" value='${vinho.teorAlc}'>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 d-flex">GRUPO PRECIFICAÇÃO:</div>
                    <div class="form-group col-md-2 d-flex">
                        <div id="gruposPrecificacaoContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-3 d-flex">COD. DE BARRAS:</div>
                    <div class="form-group col-md-4 d-flex">
                        <input type="text" class="form-control" id="codBarras" name="codBarras" placeholder="Codigo de Barras" value='${vinho.codBarras}'>
                    </div>
                </div>
                <p>TIPOS DE UVA:</p>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <div id="tiposUvaContainer" class="form-group"></div>
                    </div>
                </div>

                <div class="form-row">
                    <P>DESCRIÇÃO:</P>
                    <div class="form-group col-md-12">
                        <textarea id="descricao" name="descricao" placeholder="descricao" class="form-control">${vinho.descricao}</textarea>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4 d-flex">MAIOR CUSTO:
                        <input type="text" class="form-control" id="maiorCusto" name="maiorCusto" placeholder="Custo Unit."value='${vinho.maiorCusto}'>
                    </div>
                    <div class="form-group col-md-4 d-flex">PRECO:
                        <input type="text" class="form-control" id="preco" name="preco" placeholder="Preço de Venda" value='${vinho.preco}'>
                    </div>
                    <div class="form-group col-md-4 d-flex">ESTOQUE:
                        <input type="text" class="form-control" id="qtdeEstoque" name="qtdeEstoque" placeholder="Qtde Estoque"value='${vinho.qtdeEstoque}'>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3 d-flex">
                        <label for="status">STATUS:</label>
                        <select name="status" id="status" class="form-control">
                            <option value="">selecione</option>
                            <option value="true">Ativo</option>
                            <option value="false">Inativo</option>
                        </select>
                    </div>
                    <div class="form-group col-md-3 d-flex">MOTIVO:
                        <div id="motivosContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-6 d-flex">JUSTIFICATIVA:
                        <input type="text" class="form-control" id="justificativa" name="justificativa" placeholder="Justificativa"value='${vinho.motivo.justificativa}'>
                    </div>
                </div>
                <input type="hidden" name="tiposUvaSelecionadas" id="tiposUvaSelecionadas">
                <input type="hidden" name="id" id="id" value='${vinho.id}'>
                <button type="submit" class="btn" id="cadastrar">Cadastrar</button>
            </form>
        </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        gerarNavbar();
        criarCheckboxesUvas(tiposUva);
        criarSelect(tiposVinho, "tiposVinhoContainer", "tipoVinho");
        criarSelect(paises, "paisesContainer", "pais");
        criarSelect(gruposPrecif, "gruposPrecificacaoContainer", "grupoPrecif");
        criarSelect(motivos, "motivosContainer", "motivoCategoria");
        document.getElementById('tipoVinho').value = '${vinho.tipoVinho}';
        document.getElementById('pais').value = '${vinho.pais}';
        document.getElementById('grupoPrecif').value = '${vinho.grupoPrecificacao}';
        document.getElementById('status').value = '${vinho.status}';
        document.getElementById('motivoCategoria').value = '${vinho.motivo.categoria}';
        vinhoTiposUva.forEach(function (tipoUva) {
            document.getElementById(tipoUva.toLowerCase()).checked = true;
        });
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
