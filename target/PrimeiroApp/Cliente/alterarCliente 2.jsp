<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script>
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
    const enderecosEntrega = [
        <c:forEach var="endereco" items="${cliente.endEnt}">
        {
            nome: '${endereco.nome}',
            tipoResid: '${endereco.tipoResid}',
            tipoLograd: '${endereco.tipoLograd}',
            logradouro: '${endereco.logradouro}',
            numero: '${endereco.numero}',
            bairro: '${endereco.bairro.nome}',
            cidade: '${endereco.bairro.cidade.nome}',
            estado: '${endereco.bairro.cidade.estado.nome}',
            cep: '${endereco.cep}',
            pais: '${endereco.bairro.cidade.estado.pais.nome}',
            observacoes: '${endereco.obs}'
        }<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];
    const cartoes = [
        <c:forEach var="cartao" items="${cliente.cartoes}">
        {
            nome: '${cartao.nome}',
            bandeira: '${cartao.bandeira}',
            numero: '${cartao.numero}',
            codseg: '${cartao.cod}'
        }<c:if test="${!status.last}">,</c:if>
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
    <link rel="stylesheet" href="css/styles2.css">
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400..700&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a4e795a207.js" crossorigin="anonymous"></script>
    <script src="js/funcoes.js"></script>
</head>
<body>
<div id="navbarContainer"></div>
<div class="background-image">
    <div class="container d-flex justify-content-center align-items-start min-vh-100 py-5">
        <div class="form-container p-4 glass">
            <h3 class="text-center mb-4">ALTERAR DADOS DO CLIENTE</h3>
            <form action=CtrlClienteAtualizar method="post" >
                <div class="form-row">
                    <div class="form-group col-md-1">ID: ${cliente.id}
                        <input type="text" id="id" name="id" value="${cliente.id}" hidden>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-7">
                        <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome Completo" value="${cliente.nome}" >
                    </div>
                    <div class="form-group col-md-5">
                        <input type="text" class="form-control" id="cpf" name="cpf" placeholder="CPF" value="${cliente.cpf}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <div id="generoContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-4"><label>DATA DE NASCIMENTO</label></div>
                    <div class="form-group col-md-4">
                        <input type="date" class="form-control" id="data-nascimento" name="data-nascimento" value="${cliente.dataNascimento}">
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
                        <input type="text" class="form-control" id="endResLograd" name="endResLograd" placeholder="Logradouro" value="${cliente.endResid.logradouro}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <input type="text" class="form-control" id="endResNum" name="endResNum" placeholder="Numero" value="${cliente.endResid.numero}">
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text" class="form-control" id="endResBairro" name="endResBairro" placeholder="Bairro" value="${cliente.endResid.bairro.nome}">
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endResCidade" name="endResCidade" placeholder="Cidade" value="${cliente.endResid.bairro.cidade.nome}">
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endResEst" name="endResEst" placeholder="Estado" value="${cliente.endResid.bairro.cidade.estado.nome}">
                    </div>
                </div>
                <div class="form-row">

                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endResCep" name="endResCep" placeholder="CEP" value="${cliente.endResid.cep}">
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text" class="form-control" id="endResPais" name="endResPais" placeholder="Pais" value="${cliente.endResid.bairro.cidade.estado.pais.nome}">
                    </div>
                    <div class="form-group col-md-5">
                        <input type="text" class="form-control" id="endResObs" name="endResObs" placeholder="Observacoes" value="${cliente.endResid.obs}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6"><p>ENDERECO DE COBRANÇA</p></div>
                    <div class="form-group col-md-5"><button type="button" id="copiarEnderecoParaCobranca" class="btn">Repetir Endereco Residencial</button></div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <div id="tipoResidenciaCobContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-2">
                        <div id="tipoLogradouroCobContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-7">
                        <input type="text" class="form-control" id="endCobLograd" name="endCobLograd" placeholder="Logradouro" value="${cliente.endCob.logradouro}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <input type="text" class="form-control" id="endCobNum" name="endCobNum" placeholder="Numero" value="${cliente.endCob.numero}">
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text" class="form-control" id="endCobBairro" name="endCobBairro" placeholder="Bairro" value="${cliente.endCob.bairro.nome}">
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endCobCidade" name="endCobCidade" placeholder="Cidade" value="${cliente.endCob.bairro.cidade.nome}">
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endCobEst" name="endCobEst" placeholder="Estado" value="${cliente.endCob.bairro.cidade.estado.nome}">
                    </div>
                </div>
                <div class="form-row">

                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endCobCep" name="endCobCep" placeholder="CEP" value="${cliente.endCob.cep}">
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text" class="form-control" id="endCobPais" name="endCobPais" placeholder="Pais" value="${cliente.endCob.bairro.cidade.estado.pais.nome}">
                    </div>
                    <div class="form-group col-md-5">
                        <input type="text" class="form-control" id="endCobObs" name="endCobObs" placeholder="Observacoes" value="${cliente.endCob.obs}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6"><p>ENDERECO DE ENTREGA</p></div>
                    <div class="form-group col-md-5"><button type="button" id="copiarEnderecoParaEntrega" class="btn btn-menor">Repetir Endereco Residencial</button></div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <input type="text" class="form-control" id="endEntNome1" name="endEntNome1" placeholder="Atribuir Nome" >
                    </div>
                </div>
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
                        <input type="text" class="form-control" id="endEntNum1" name="endEntNum1" placeholder="Numero" >
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

                <div id="enderecosContainer"></div>
                <button type="button" id="addEndereco" class="btn btn-menor">Adicionar Endereco de Entrega</button><br>
                <p>CONTATO</p>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <input type="email" class="form-control" id="email" name="email" placeholder="E-mail" value="${cliente.contato.email}">
                    </div>
                    <div class="form-group col-md-2">
                        <div id="tiposTelContainer" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-1">
                        <input type="text" class="form-control" id="ddd" name="ddd" placeholder="ddd" value="${cliente.contato.telefone.ddd}">
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="numerotel" name="numerotel" placeholder="Numero" value="${cliente.contato.telefone.numero}">
                    </div>

                </div>
                <p>DADOS DO CARTAO</p>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <input type="text" class="form-control" id="cartaoNome1" name="cartaoNome1" placeholder="Nome Impresso no Cartao" >
                    </div>

                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <div id="bandeirasContainer1" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-6">
                        <input type="text" class="form-control" id="cartaoNum1" name="cartaoNum1" placeholder="Numero do Cartao" >
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="cartaoCodSeg1" name="cartaoCodSeg1" placeholder="Cod de Seguranca" >
                    </div>
                </div>
                <div id="cartoesContainer"></div>
                <button type="button" id="addCartao" class="btn">Adicionar Cartao</button><br>
                <p>SENHA</p>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <input type="password" class="form-control" id="senha" name="senha" placeholder="Senha" value="${cliente.senha}">
                    </div>
                    <div class="form-group col-md-6">
                        <input type="password" class="form-control" id="confirmaSenha" name="confirmaSenha" placeholder="Confirme a Senha" value="${cliente.senha}">
                        <small id="senhaFeedback" class="form-text text-muted"></small>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <button class="btn" type="button" onclick="javascript:window.history.go(-1);">Voltar</button>
                    </div>
                    <div class="form-group col-md-1"></div>
                    <div class="form-group col-md-8">
                        <button type="submit" class="btn">Salvar Alterações</button>
                    </div>
                </div>
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
        adicionarEndereco(${fn:length(cliente.endEnt)}-1);
        adicionarCartao(${fn:length(cliente.endEnt)}-1);
        preencherEnderecosEntrega(enderecosEntrega);
        preencherCartoes(cartoes);
        document.getElementById('copiarEnderecoParaCobranca').addEventListener('click', copiarEnderecoParaCobranca);
        document.getElementById('copiarEnderecoParaEntrega').addEventListener('click', copiarEnderecoParaEntrega);
        document.getElementById('senha').addEventListener('input', validarSenhas);
        document.getElementById('confirmaSenha').addEventListener('input', validarSenhas);
        document.getElementById('addEndereco').addEventListener('click', function () {
            adicionarEndereco(1);
        });
        document.getElementById('addCartao').addEventListener('click', function () {
            adicionarCartao(1);
        });
        document.getElementById('genero').value = '${cliente.genero}';
        document.getElementById('tiporesidRes').value = '${cliente.endResid.tipoResid}';
        document.getElementById('tipologradRes').value = '${cliente.endResid.tipoLograd}';
        document.getElementById('tiporesidCob').value = '${cliente.endCob.tipoResid}';
        document.getElementById('tipologradCob').value = '${cliente.endCob.tipoLograd}';
        document.getElementById('tipotel').value = '${cliente.contato.telefone.tipo}';
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
