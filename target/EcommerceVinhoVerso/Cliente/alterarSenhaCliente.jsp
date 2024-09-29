<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
            <h3 class="text-center mb-4">ALTERAR SENHA DO CLIENTE</h3>
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
                <p>NOVA SENHA</p>
                <div class="form-row">
                    <div class="form-group col-md-12">Digite a senha
                        <input type="password" class="form-control" id="senha" name="senha" value="">
                </div>
                </div>
                    <div class="form-row">
                    <div class="form-group col-md-12">Confirme a senha
                        <input type="password" class="form-control" id="confirmaSenha" name="confirmaSenha" value="">
                        <small id="senhaFeedback" class="form-text text-muted"></small>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <button class="btn" type="button" onclick="javascript:window.history.go(-1);">Voltar</button>
                    </div>
                    <div class="form-group col-md-1"></div>
                    <div class="form-group col-md-8">
                        <button type="submit" class="btn" onclick="submeterFormulario()">Salvar Alterações</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        gerarNavbar();
        document.getElementById('senha').addEventListener('input', validarSenhas);
        document.getElementById('confirmaSenha').addEventListener('input', validarSenhas);
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
