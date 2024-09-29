<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    const nomeCliente = '${sessionScope.nomeCliente != null ? sessionScope.nomeCliente : "null"}';
    const idCliente = '${sessionScope.idCliente != null ? sessionScope.idCliente : "null"}';
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
            <div class="row">
                <div class="col-md-12">
                    <p>ID: ${cliente.id}</p>
                    <p>NOME: ${cliente.nome}</p>
                    <input type="text" id="id" name="id" value="${cliente.id}" hidden>
                </div>
            </div>
            <div class="modal-body">
                <form id="clienteForm" action="CtrlClienteAlterar" method="post">
                    <input type="hidden" name="id" value="${cliente.id}">
                    <input type="hidden" name="encaminhamento" id="encaminhamento" value="">

                    <button type="button" class="btn" onclick="submitForm('alterarDadosCadastraisCliente')">Alterar Dados Cadastrais</button><br>
                    <button type="button" class="btn" onclick="submitForm('alterarEnderecosCliente')">Alterar Endereços</button><br>
                    <button type="button" class="btn" onclick="submitForm('alterarCartoesCliente')">Alterar cartoes</button><br>
                    <button type="button" class="btn" onclick="submitForm('alterarSenhaCliente')">Alterar Senha</button><br>
                    <button type="button" class="btn" onclick="submitForm('consultarLogs')">Consultar Logs</button><br>
                    <button type="button" class="btn" onclick="submitForm('consultarCupons')">Consultar Cupons</button><br>
                    <button type="button" class="btn" onclick="submitForm('transacoes')">Consultar Transações</button><br>
                    <button class="btn" type="button" onclick="javascript:window.history.go(-1);">Voltar</button>
                </form>
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
