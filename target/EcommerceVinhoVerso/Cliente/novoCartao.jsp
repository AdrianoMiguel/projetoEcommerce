<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script>
    const nomeCliente = '${sessionScope.nomeCliente != null ? sessionScope.nomeCliente : "null"}';
    const idCliente = '${sessionScope.idCliente != null ? sessionScope.idCliente : "null"}';
    const bandeiras = [
        <c:forEach items="${bandeiras}" var="bandeira" varStatus="status">
        '${bandeira.toString()}'<c:if test="${!status.last}">, </c:if>
        </c:forEach>
    ];
</script>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Novo Cart�o</title>
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
            <h3 class="text-center mb-4">NOVO CART�O</h3>
            <form action=CtrlClienteNovoCartao method="post" >
                <div class="form-row">
                    <div class="form-group col-md-7">
                        <input type="text" class="form-control" id="cartaoNome1" name="cartaoNome1" placeholder="Nome Impresso no Cartao" >
                    </div>
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
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <button class="btn" type="button" onclick="javascript:window.history.go(-1);">Voltar</button>
                    </div>
                    <div class="form-group col-md-1"></div>
                    <div class="form-group col-md-8">
                        <button id="cadastrarCartaoBtn" type="submit" class="btn">CADASTRAR</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        gerarNavbar();
        criarSelect(bandeiras, "bandeirasContainer1", "bandeira1");
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
