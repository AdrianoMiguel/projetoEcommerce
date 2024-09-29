<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    const nomeCliente = '${sessionScope.nomeCliente != null ? sessionScope.nomeCliente : "null"}';
    const idCliente = '${sessionScope.idCliente != null ? sessionScope.idCliente : "null"}';
</script>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultar Logs</title>
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
            <h3 class="text-center mb-4">CONSULTAR LOGS CADASTRADOS</h3>

            <br>
            <div class="main">
                <div class="listagem" id="listagemLogs">
                    <ul>
                        <c:forEach var="log" items="${logs}">
                            <div>
                                <strong>Data e Hora:</strong> <fmt:formatDate value="${log.dataHoraAcao}" pattern="dd/MM/yyyy HH:mm:ss" />
                                <br>
                                <div class="row">
                                    <div class="col-md-6">
                                <strong>Usu�rio:</strong> ${log.usuarioAcao}<br>
                                    </div>
                                    <div class="col-md-6">
                                <strong>Tipo de A��o:</strong> ${log.tipoAcao}<br>
                                    </div>
                                </div>
                                <strong>Detalhes da A��o:</strong> ${log.detalhesAcao}<br>
                            </div>
                            <hr>
                        </c:forEach>
                    </ul>
                </div>
                <button class="btn" type="button" onclick="javascript:window.history.go(-1);">Voltar</button>
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