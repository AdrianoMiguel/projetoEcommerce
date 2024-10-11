<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
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
    <title>Novo Cupom</title>
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

            <h3 class="text-center mb-4">NOVO CUPOM</h3>


            <form action=CtrlClienteCupom method="post" id="cupomForm">
                <input type="hidden" name="id" value="${clienteId}">
                <div class="form-row justify-content-between">
                    <div class="form-group col-md-5">
                        <h5>Código:</h5>
                    </div>
                    <div class="form-group col-md-3">
                        <h5>Valor:</h5>
                    </div>
                </div>
                <div class="form-row justify-content-between">
                    <div class="form-group col-md-5">
                        <input type="text" class="form-control" id="codigo" name="codigo" placeholder="Código" required>
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="valor" name="valor" placeholder="Valor" required>
                    </div>
                </div>
                <div class="row justify-content-between">
                    <div class="col-md-4">
                        <form id="clienteForm" action="CtrlClienteAlterar" method="post">
                            <input type="hidden" name="id" value="${clienteId}">
                            <input type="hidden" name="encaminhamento" id="encaminhamento" value="">
                            <button type="button" class="btn" onclick="submitForm('consultarCupons')">Voltar</button>
                            <br>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <button id="cadastrarCupomBtn" class="btn" type="submit">Cadastrar Cupom</button>
                    </div>
                </div>
            </form>
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
