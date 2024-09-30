<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script>
    const nomeCliente = '${sessionScope.nomeCliente != null ? sessionScope.nomeCliente : "null"}';
    const idCliente = '${sessionScope.idCliente != null ? sessionScope.idCliente : "null"}';
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
    const enderecosEntrega = [
        <c:forEach var="endereco" items="${cliente.endEnt}" varStatus="status">
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
            observacoes: '${endereco.obs}',
            principal: ${endereco.principal}
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
            <h3 class="text-center mb-4">ALTERAR ENDERECOS DO CLIENTE</h3>
            <form action=CtrlClienteAtualizar method="post" >
                <div class="form-row">
                    <div class="form-group col-md-2">ID: ${cliente.id}
                        <input type="text" id="id" name="id" value="${cliente.id}" hidden>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-7">CLIENTE: ${cliente.nome}<br>
                        <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome Completo" value="${cliente.nome}" hidden>
                    </div>
                </div>
                <br>
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
                        <input type="text" class="form-control" id="endResNum" name="endResNum" placeholder="Numero" oninput="this.value = this.value.replace(/[^0-9]/g, '');" value="${cliente.endResid.numero}">
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
                        <input type="text" class="form-control" id="endCobLograd" name="endCobLograd" placeholder="Logradouro" value="${cliente.endCob.logradouro}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <input type="text" class="form-control" id="endCobNum" name="endCobNum" placeholder="Numero" oninput="this.value = this.value.replace(/[^0-9]/g, '');" value="${cliente.endCob.numero}">
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
                    <div class="form-group col-md-2"></div>
                    <div class="form-group col-md-4"><button type="button" class="btn btn-menor" onclick="copiarEndereco('Ent')">Repetir Endereco Residencial</button></div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-7 d-flex">
                        <button type="button" class="btn btn-menor btn-alternar" id="expandir1" onclick="alternarExibicao(1)"><i class="fa-solid fa-caret-down"></i></button>
                        <input type="text" class="form-control ml-1" id="endEntNome1" name="endEntNome1" placeholder="Atribuir Nome" >
                    </div>
                    <div class="form-group col-md-5"><label><input type="radio" name="endEntPrincipal" value="1"> Definir como endereco principal</label></div>
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
                        <input type="text" class="form-control" id="endEntNum1" name="endEntNum1" oninput="this.value = this.value.replace(/[^0-9]/g, '');" placeholder="Numero" >
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
                <button type="button" class="btn" onclick="adicionarEndereco(1)">Adicionar Endereco de Entrega</button><br>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <button class="btn" type="button" onclick="window.location.href='CtrlClienteEncaminharID?id=${cliente.id}'">Voltar</button>
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
        criarSelect(tiposresid, "tipoResidenciaResContainer", "tiporesidRes");
        criarSelect(tiposresid, "tipoResidenciaEntContainer1", "tiporesidEnt1");
        criarSelect(tiposresid, "tipoResidenciaCobContainer", "tiporesidCob");
        criarSelect(tiposlograd, "tipoLogradouroResContainer", "tipologradRes");
        criarSelect(tiposlograd, "tipoLogradouroEntContainer1", "tipologradEnt1");
        criarSelect(tiposlograd, "tipoLogradouroCobContainer", "tipologradCob");
        adicionarEndereco(${fn:length(cliente.endEnt)}-1);
        preencherEnderecosEntrega(enderecosEntrega);
        document.getElementById('tiporesidRes').value = '${cliente.endResid.tipoResid}';
        document.getElementById('tipologradRes').value = '${cliente.endResid.tipoLograd}';
        document.getElementById('tiporesidCob').value = '${cliente.endCob.tipoResid}';
        document.getElementById('tipologradCob').value = '${cliente.endCob.tipoLograd}';
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
