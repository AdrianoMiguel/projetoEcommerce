<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    const nomeCliente = '${sessionScope.nomeCliente != null ? sessionScope.nomeCliente : "null"}';
    const idCliente = '${sessionScope.idCliente != null ? sessionScope.idCliente : "null"}';
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
    const fornecedores = [
        <c:forEach items="${fornecedores}" var="fornecedor" varStatus="status">
        '${fornecedor.toString()}'<c:if test="${!status.last}">, </c:if>
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
    <title>Cadastro de Vinho</title>
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
            <h3 class="text-center mb-4">Cadastro de Vinho</h3>
            <form action=CtrlProdutoSalvar method="post" id="formularioSalvar" name="formularioSalvar" onsubmit="obterTiposUvaSelecionadas()">
                <div class="form-row">
                    <div class="form-group col-md-9">
                        <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome" >
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="safra" name="safra" placeholder="Safra (Ano)">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <div id="tiposVinhoContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-3">
                        <div id="paisesContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="volume" name="volume" placeholder="volume da garrafa em ml" >
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="teorAlc" name="teorAlc" placeholder="Teor Alcoolico" >
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-5">
                        <div id="gruposPrecificacaoContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-7">
                        <input type="text" class="form-control" id="codBarras" name="codBarras" placeholder="Codigo de Barras">
                    </div>
                </div>
                <p>TIPOS DE UVA:</p>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <div id="tiposUvaContainer" class="form-group"></div>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-12">
                    <textarea id="descricao" name="descricao" placeholder="descricao" class="form-control"></textarea>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <div id="fornecedoresContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-2">
                        <input type="text" class="form-control" id="custo" name="custo" placeholder="Custo Unit.">
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="preco" name="preco" placeholder="Preço de Venda">
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="qtdeEstoque" name="qtdeEstoque" placeholder="Qtde Estoque">
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
                    <div class="form-group col-md-3">
                        <div id="motivosContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-6">
                        <input type="text" class="form-control" id="justificativa" name="justificativa" placeholder="Justificativa">
                    </div>
                </div>
                    <input type="hidden" name="tiposUvaSelecionadas" id="tiposUvaSelecionadas">
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
        criarSelect(fornecedores, "fornecedoresContainer", "fornecedor");
        criarSelect(paises, "paisesContainer", "pais");
        criarSelect(gruposPrecif, "gruposPrecificacaoContainer", "grupoPrecif");
        criarSelect(motivos, "motivosContainer", "motivoCategoria");
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
