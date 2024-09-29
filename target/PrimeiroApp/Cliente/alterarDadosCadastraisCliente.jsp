<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script>
    const generos = [
        <c:forEach items="${generos}" var="genero" varStatus="status">
        '${genero.toString()}'<c:if test="${!status.last}">, </c:if>
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
        criarSelect(tipostel, "tiposTelContainer", "tipotel");
        document.getElementById('genero').value = '${cliente.genero}';
        document.getElementById('tipotel').value = '${cliente.contato.telefone.tipo}';
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
