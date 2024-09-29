<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    const nomeCliente = '${sessionScope.nomeCliente != null ? sessionScope.nomeCliente : "null"}';
    const idCliente = '${sessionScope.idCliente != null ? sessionScope.idCliente : "null"}';
    const bandeiras = [
        <c:forEach items="${bandeiras}" var="bandeira" varStatus="status">
        '${bandeira.toString()}'<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];
    const generos = [
        <c:forEach items="${generos}" var="genero" varStatus="status">
        '${genero.toString()}'<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];
    const tiposresid = [
        <c:forEach items="${tiposresid}" var="tiporesid" varStatus="status">
        '${tiporesid.toString()}'<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];
    const tiposlograd = [
        <c:forEach items="${tiposlograd}" var="tipolograd" varStatus="status">
        '${tipolograd.toString()}'<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];
    const tipostel = [
        <c:forEach items="${tipostel}" var="tipotel" varStatus="status">
        '${tipotel.toString()}'<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];

</script>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Cliente</title>
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
            <h3 class="text-center mb-4">Cadastro de Cliente</h3>
            <form action=CtrlClienteSalvar method="post" id="formularioSalvar" name="formularioSalvar">
                <div class="form-row">
                    <div class="form-group col-md-7">
                        <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome Completo" >
                    </div>
                    <div class="form-group col-md-5">
                        <input type="text" class="form-control" id="cpf" name="cpf" placeholder="CPF" oninput="this.value = this.value.replace(/[^0-9]/g, '');" maxlength="11">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <div id="generoContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-4"><label>DATA DE NASCIMENTO</label></div>
                    <div class="form-group col-md-4">
                        <input type="date" class="form-control" id="data-nascimento" name="data-nascimento">
                    </div>
                </div>
                <p>ENDERECO RESIDENCIAL</p>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <div id="tipoResidenciaResContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-2">
                        <div id="tipoLogradouroResContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-7">
                        <input type="text" class="form-control" id="endResLograd" name="endResLograd" placeholder="Logradouro" >
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <input type="text" class="form-control" id="endResNum" name="endResNum" placeholder="Numero" oninput="this.value = this.value.replace(/[^0-9]/g, '');">
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text" class="form-control" id="endResBairro" name="endResBairro" placeholder="Bairro" >
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endResCidade" name="endResCidade" placeholder="Cidade" >
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endResEst" name="endResEst" placeholder="Estado" >
                    </div>
                </div>
                <div class="form-row">

                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endResCep" name="endResCep" placeholder="CEP" >
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text" class="form-control" id="endResPais" name="endResPais" placeholder="Pais" >
                    </div>
                    <div class="form-group col-md-5">
                        <input type="text" class="form-control" id="endResObs" name="endResObs" placeholder="Observacoes" >
                    </div>
                </div>
                <div class="form-row">
                <div class="form-group col-md-6"><p>ENDERECO DE COBRANÇA</p></div>
                    <div class="form-group col-md-2"></div>
                    <div class="form-group col-md-4"><button type="button" class="btn btn-menor" onclick="copiarEndereco('Cob')">Repetir Endereco Residencial</button></div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <div id="tipoResidenciaCobContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-2">
                        <div id="tipoLogradouroCobContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-7">
                        <input type="text" class="form-control" id="endCobLograd" name="endCobLograd" placeholder="Logradouro" >
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <input type="text" class="form-control" id="endCobNum" name="endCobNum" placeholder="Numero" oninput="this.value = this.value.replace(/[^0-9]/g, '');">
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text" class="form-control" id="endCobBairro" name="endCobBairro" placeholder="Bairro" >
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endCobCidade" name="endCobCidade" placeholder="Cidade" >
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endCobEst" name="endCobEst" placeholder="Estado" >
                    </div>
                </div>
                <div class="form-row">

                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endCobCep" name="endCobCep" placeholder="CEP" >
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text" class="form-control" id="endCobPais" name="endCobPais" placeholder="Pais" >
                    </div>
                    <div class="form-group col-md-5">
                        <input type="text" class="form-control" id="endCobObs" name="endCobObs" placeholder="Observacoes" >
                    </div>
                </div>
                <div class="form-row">
                <div class="form-group col-md-6"><p>ENDERECO DE ENTREGA</p></div>
                    <div class="form-group col-md-2"></div>
                <div class="form-group col-md-4"><button type="button" class="btn btn-menor" onclick="copiarEndereco('Ent')">Repetir Endereco Residencial</button><br></div>
        </div>
                <div class="form-row">
                    <div class="form-group col-md-7 d-flex">
                        <button type="button" class="btn btn-menor btn-alternar" id="expandir1"  onclick="alternarExibicao(1)"><i class="fa-solid fa-caret-down"></i></button>
                        <input type="text" class="form-control ml-1" id="endEntNome1" name="endEntNome1" placeholder="Atribuir Nome" >
                    </div>
                    <div class="form-group col-md-5"><label><input type="radio" name="endEntPrincipal" value="1" checked> Definir como endereco principal</label></div>
                </div>
                <div id="detalhes1" class="collapse">
                    <div class="form-row">
                    <div class="form-group col-md-3">
                        <div id="tipoResidenciaEntContainer1" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-2">
                        <div id="tipoLogradouroEntContainer1" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-7">
                        <input type="text" class="form-control" id="endEntLograd1" name="endEntLograd1" placeholder="Logradouro" >
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <input type="text" class="form-control" id="endEntNum1" name="endEntNum1" placeholder="Numero" oninput="this.value = this.value.replace(/[^0-9]/g, '');">
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text" class="form-control" id="endEntBairro1" name="endEntBairro1" placeholder="Bairro" >
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endEntCidade1" name="endEntCidade1" placeholder="Cidade" >
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endEntEst1" name="endEntEst1" placeholder="Estado" >
                    </div>
                </div>
                <div class="form-row">

                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endEntCep1" name="endEntCep1" placeholder="CEP" >
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text" class="form-control" id="endEntPais1" name="endEntPais1" placeholder="Pais" >
                    </div>
                    <div class="form-group col-md-5">
                        <input type="text" class="form-control" id="endEntObs1" name="endEntObs1" placeholder="Observacoes" >
                    </div>
                </div>
                </div>

                    <div id="enderecosContainer"></div>
                <button type="button" class="btn" id="addEnd" onclick="adicionarEndereco(1)">Adicionar Endereco de Entrega</button>
                <br>
                <p>CONTATO</p>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <input type="email" class="form-control" id="email" name="email" placeholder="E-mail" >
                    </div>
                    <div class="form-group col-md-2">
                        <div id="tiposTelContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-1">
                        <input type="text" class="form-control" id="ddd" name="ddd" placeholder="ddd" oninput="this.value = this.value.replace(/[^0-9]/g, '');" maxlength="2">
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="numerotel" name="numerotel" placeholder="Numero" oninput="this.value = this.value.replace(/[^0-9]/g, '');" maxlength="9">
                </div>

                </div>
                <p>DADOS DO CARTÃO</p>
                <div class="form-row">
                    <div class="form-group col-md-7">
                        <input type="text" class="form-control" id="cartaoNome1" name="cartaoNome1" placeholder="Nome Impresso no Cartao" >
                    </div>
                    <div class="form-group col-md-5"><label><input type="radio" name="cartaoPrincipal" value="1" checked> Definir como preferencial</label></div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <div id="bandeirasContainer1" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-6">
                        <input type="text" class="form-control" id="cartaoNum1" name="cartaoNum1" placeholder="Numero do Cartao" oninput="this.value = this.value.replace(/[^0-9]/g, '');" maxlength="16">
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="cartaoCodSeg1" name="cartaoCodSeg1" placeholder="Cod de Seguranca" oninput="this.value = this.value.replace(/[^0-9]/g, '');" maxlength="3">
                    </div>
                </div>
                <div id="cartoesContainer"></div>
                <button type="button" class="btn" id="addCartao" onclick="adicionarCartao(1)">Adicionar Cartao<br></button>
                <br>
                <p>SENHA</p>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <input type="password" class="form-control" id="senha" name="senha" placeholder="Senha" oninput="validarSenhas()">
                    </div>
                    <div class="form-group col-md-6">
                        <input type="password" class="form-control" id="confirmaSenha" name="confirmaSenha" placeholder="Confirme a Senha" oninput="validarSenhas()">
                        <small id="senhaFeedback" class="form-text text-muted"></small>
                    </div>
                </div>
                <button type="button" class="btn" id="cadastrar" onclick="submeterFormulario()">Cadastrar</button>
            </form>
        </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        gerarNavbar();
        criarSelect(generos, "generoContainer", "genero");
        criarSelect(tiposresid, "tipoResidenciaResContainer", "tiporesidRes");
        criarSelect(tiposresid, "tipoResidenciaEntContainer1", "tiporesidEnt1");
        criarSelect(tiposresid, "tipoResidenciaCobContainer", "tiporesidCob");
        criarSelect(tiposlograd, "tipoLogradouroResContainer", "tipologradRes");
        criarSelect(tiposlograd, "tipoLogradouroEntContainer1", "tipologradEnt1");
        criarSelect(tiposlograd, "tipoLogradouroCobContainer", "tipologradCob");
        criarSelect(tipostel, "tiposTelContainer", "tipotel");
        criarSelect(bandeiras, "bandeirasContainer1", "bandeira1");
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
