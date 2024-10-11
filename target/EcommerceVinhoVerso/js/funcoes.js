window.addEventListener('beforeunload', function () {
    navigator.sendBeacon('/invalidaSessao');
});

function criarSelect(ValoresEnum, containerId, selectId) {
    const container = document.getElementById(containerId);
    const select = document.createElement("select");
    select.className = selectId;
    select.setAttribute("name", selectId);
    select.setAttribute("class", "form-control");
    if (selectId !== "autor") {
        select.setAttribute("id", selectId);
    }
    const option = document.createElement("option");
    ValoresEnum.forEach(function (value) {
        const option = document.createElement("option");
        option.value = value;
        option.text = value.replace(/_/g, " ");
        select.appendChild(option);
    });
    container.appendChild(select);
}

function criarCheckboxesUvas(valoresEnum) {
    var contadorOptions = 0;
    const container = document.getElementById("tiposUvaContainer");

    // Limpa o container para evitar duplicação quando a função for chamada novamente
    container.innerHTML = '';

    // Cria uma div row para agrupar as colunas
    let currentRow = document.createElement("div");
    currentRow.classList.add("row");
    container.appendChild(currentRow);

    valoresEnum.forEach(function (tipoUva) {
        // Cria um novo checkbox
        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.id = tipoUva.toLowerCase();
        checkbox.name = "tipoUva";
        checkbox.value = tipoUva.toLowerCase();

        // Cria um label associado ao checkbox
        const label = document.createElement("label");
        label.htmlFor = checkbox.id;
        label.appendChild(document.createTextNode(tipoUva.toUpperCase()));

        // Cria uma div de coluna para o checkbox e label
        const coluna = document.createElement("div");
        coluna.classList.add("col-md-3", "d-flex"); // Define o tamanho da coluna

        // Adiciona o checkbox e label na coluna
        coluna.appendChild(checkbox);
        coluna.appendChild(label);

        // Adiciona a coluna à linha atual
        currentRow.appendChild(coluna);

        contadorOptions++;

        // Se já inseriu 4 checkboxes, cria uma nova linha
        if (contadorOptions % 4 === 0) {
            currentRow = document.createElement("div");
            currentRow.classList.add("row");
            container.appendChild(currentRow);
        }
    });
}


function obterTiposUvaSelecionadas() {
    const tiposUvasSelecionadas = [];
    const checkboxesUvas = document.querySelectorAll('input[name="tipoUva"]:checked');
    checkboxesUvas.forEach(function (checkbox) {
        tiposUvasSelecionadas.push(checkbox.value);
    });
    document.getElementById('tiposUvaSelecionadas').value = tiposUvasSelecionadas.join(", ").toUpperCase();
}


let contador;

function copiarEndereco(campo) {
    if (campo == 'Ent') {
        contador = '1';
    } else {
        contador = '';
    }
    document.getElementById('tiporesid' + campo + contador).value = document.getElementById('tiporesidRes').value;
    document.getElementById('tipolograd' + campo + contador).value = document.getElementById('tipologradRes').value;
    document.getElementById('end' + campo + 'Lograd' + contador).value = document.getElementById('endResLograd').value;
    document.getElementById('end' + campo + 'Num' + contador).value = document.getElementById('endResNum').value;
    document.getElementById('end' + campo + 'Bairro' + contador).value = document.getElementById('endResBairro').value;
    document.getElementById('end' + campo + 'Cidade' + contador).value = document.getElementById('endResCidade').value;
    document.getElementById('end' + campo + 'Est' + contador).value = document.getElementById('endResEst').value;
    document.getElementById('end' + campo + 'Cep' + contador).value = document.getElementById('endResCep').value;
    document.getElementById('end' + campo + 'Pais' + contador).value = document.getElementById('endResPais').value;
    document.getElementById('end' + campo + 'Obs' + contador).value = document.getElementById('endResObs').value;
}

function validarSenhas() {
    if (document.getElementById('senha').value !== document.getElementById('confirmaSenha').value) {
        document.getElementById('senhaFeedback').textContent = 'As senhas nao coincidem!';
        return false;
    } else {
        document.getElementById('senhaFeedback').textContent = 'As senhas coincidem!';
        return true;
    }
}

let enderecoCount = 1;

function adicionarEndereco(quantidade) {
    if (quantidade > 0) {
        for (let i = 1; i <= quantidade; i++) {
            enderecoCount++;
            const container = document.getElementById('enderecosContainer');

            const novoEndereco = document.createElement('div');
            novoEndereco.classList.add('form-row');
            novoEndereco.innerHTML = `
            <div class="form-group col-md-12">
                <p>ENDERECO DE ENTREGA ${enderecoCount}</p>
            
                <div class="form-row">
                    <div class="form-group col-md-7 d-flex">
                        <button type="button" class="btn btn-menor btn-alternar" id="expandir${enderecoCount}" onclick="alternarExibicao(${enderecoCount})"><i class="fa-solid fa-caret-down"></i></button>
                        <input type="text" class="form-control ml-1" id="endEntNome${enderecoCount}" name="endEntNome${enderecoCount}" placeholder="Atribuir Nome" >
                    </div>
                    <div class="form-group col-md-5"><label><input type="radio" name="endEntPrincipal" value="${enderecoCount}"> Definir como endereco principal</label></div>
                </div>
                <div id="detalhes${enderecoCount}" class="collapse">
                    <div class="form-row">
                    <div class="form-group col-md-3">
                        <div id="tipoResidenciaEntContainer${enderecoCount}" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-2">
                        <div id="tipoLogradouroEntContainer${enderecoCount}" class="form-control"></div>
                    </div>
                    <div class="form-group col-md-7">
                        <input type="text" class="form-control" id="endEntLograd${enderecoCount}" name="endEntLograd${enderecoCount}" placeholder="Logradouro" >
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <input type="text" class="form-control" id="endEntNum${enderecoCount}" name="endEntNum${enderecoCount}" placeholder="Numero" oninput="this.value = this.value.replace(/[^0-9]/g, '');" >
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text" class="form-control" id="endEntBairro${enderecoCount}" name="endEntBairro${enderecoCount}" placeholder="Bairro" >
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endEntCidade${enderecoCount}" name="endEntCidade${enderecoCount}" placeholder="Cidade" >
                    </div>
                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endEntEst${enderecoCount}" name="endEntEst${enderecoCount}" placeholder="Estado" >
                    </div>
                </div>
                <div class="form-row">

                    <div class="form-group col-md-3">
                        <input type="text" class="form-control" id="endEntCep${enderecoCount}" name="endEntCep${enderecoCount}" placeholder="CEP" >
                    </div>
                    <div class="form-group col-md-4">
                        <input type="text" class="form-control" id="endEntPais${enderecoCount}" name="endEntPais${enderecoCount}" placeholder="Pais" >
                    </div>
                    <div class="form-group col-md-5">
                        <input type="text" class="form-control" id="endEntObs${enderecoCount}" name="endEntObs${enderecoCount}" placeholder="Observacoes" >
                    </div>
                </div>
                </div>
            </div>
    `;

            container.appendChild(novoEndereco);

            criarSelect(tiposlograd, `tipoLogradouroEntContainer${enderecoCount}`, `tipologradEnt${enderecoCount}`);
            criarSelect(tiposresid, `tipoResidenciaEntContainer${enderecoCount}`, `tiporesidEnt${enderecoCount}`);
        }
    }
}

let cartaoCount = 1;

function adicionarCartao(quantidade) {
    if (quantidade > 0) {
        for (let i = 1; i <= quantidade; i++) {
            cartaoCount++;
            const container = document.getElementById('cartoesContainer');

            const novoCartao = document.createElement('div');
            novoCartao.classList.add('form-row');
            novoCartao.innerHTML = `
        <div class="form-group col-md-12">
            <p>DADOS DO CARTAO ${cartaoCount}</p>
            <div class="form-row">
                <div class="form-group col-md-7">
                    <input type="text" class="form-control" id="cartaoNome${cartaoCount}" name="cartaoNome${cartaoCount}" placeholder="Nome Impresso no Cartao">
                </div>
                <div class="form-group col-md-5"><label><input type="radio" name="cartaoPrincipal" value="${cartaoCount}"> Definir como preferencial</label></div>
                </div>
                <div class="form-row">
                <div class="form-group col-md-3">
                <div id="bandeirasContainer${cartaoCount}" class="form-control"></div>
                </div>
                <div class="form-group col-md-6">
                    <input type="text" class="form-control" id="cartaoNum${cartaoCount}" name="cartaoNum${cartaoCount}" placeholder="Numero do Cartao" oninput="this.value = this.value.replace(/[^0-9]/g, '');" maxlength="16">
                </div>
            
                <div class="form-group col-md-3">
                    <input type="text" class="form-control" id="cartaoCodSeg${cartaoCount}" name="cartaoCodSeg${cartaoCount}" placeholder="Cod de Seguranca" oninput="this.value = this.value.replace(/[^0-9]/g, '');" maxlength="3">
                </div>
            </div>
        </div>
    `;

            container.appendChild(novoCartao);
            criarSelect(bandeiras, `bandeirasContainer${cartaoCount}`, `bandeira${cartaoCount}`);
        }
    }
}

function preencherEnderecosEntrega(enderecosEntrega) {
    for (let i = 0; i < enderecosEntrega.length; i++) {
        // Acessa os campos do formulário pelo ID e preenche com os valores
        document.getElementById(`endEntNome${i + 1}`).value = enderecosEntrega[i].nome;
        if (enderecosEntrega[i].principal) {
            document.querySelector(`input[name="endEntPrincipal"][value="${i + 1}"]`).checked = true;
        }
        document.getElementById(`tiporesidEnt${i + 1}`).value = enderecosEntrega[i].tipoResid;
        document.getElementById(`tipologradEnt${i + 1}`).value = enderecosEntrega[i].tipoLograd;
        document.getElementById(`endEntLograd${i + 1}`).value = enderecosEntrega[i].logradouro;
        document.getElementById(`endEntNum${i + 1}`).value = enderecosEntrega[i].numero;
        document.getElementById(`endEntBairro${i + 1}`).value = enderecosEntrega[i].bairro;
        document.getElementById(`endEntCidade${i + 1}`).value = enderecosEntrega[i].cidade;
        document.getElementById(`endEntEst${i + 1}`).value = enderecosEntrega[i].estado;
        document.getElementById(`endEntCep${i + 1}`).value = enderecosEntrega[i].cep;
        document.getElementById(`endEntPais${i + 1}`).value = enderecosEntrega[i].pais;
        document.getElementById(`endEntObs${i + 1}`).value = enderecosEntrega[i].observacoes;
    }
}

function preencherCartoes(cartoes) {
    for (let i = 0; i < cartoes.length; i++) {
        // Acessa os campos do formulário pelo ID e preenche com os valores
        document.getElementById(`cartaoNome${i + 1}`).value = cartoes[i].nome;
        if (cartoes[i].principal) {
            document.querySelector(`input[name="cartaoPrincipal"][value="${i + 1}"]`).checked = true;
        }
        document.getElementById(`bandeira${i + 1}`).value = cartoes[i].bandeira;
        document.getElementById(`cartaoNum${i + 1}`).value = cartoes[i].numero;
        document.getElementById(`cartaoCodSeg${i + 1}`).value = cartoes[i].codseg;
    }
}

function atualizarPreco(produtoId, precoUnitario, novaQuantidade) {
    const precoProduto = precoUnitario * novaQuantidade;
    document.getElementById(`preco_` + produtoId).innerHTML = `R$` + precoProduto.toFixed(2);
    document.getElementById(`precoHidden_` + produtoId).value = precoProduto;

    // Atualiza o total do carrinho
    atualizarTotalCarrinho();
}

function removerItem(produtoId) {
    // Remove a linha do produto
    const linha = document.getElementById(`linha_` + produtoId);
    linha.parentNode.removeChild(linha);

    // Atualiza o total do carrinho
    atualizarTotalCarrinho();
}

function atualizarTotalCarrinho() {
    let totalCarrinho = 0;

    // Soma os valores dos itens no carrinho
    document.querySelectorAll('[id^="precoHidden_"]').forEach(function (element) {
        let precoAtual = parseFloat(element.value);

        totalCarrinho += precoAtual;
    });

    // Obtém o valor dos cupons a partir dos inputs hidden
    document.querySelectorAll('[id^="valorCupomHidden_"]').forEach(function (element2) {
        const valorCupons = element2.value || 0
        totalCarrinho -= valorCupons;
    });

    // Obtém o valor do cupom a partir do input hidden
    const valorFrete = parseFloat(document.getElementById('valorFreteHidden').value) || 0;

    // Subtrai o valor do cupom do total
    totalCarrinho += valorFrete;

    // Atualiza o valor total do carrinho na página
    document.getElementById('totalCarrinho').innerHTML = `R$` + totalCarrinho.toFixed(2);
    document.getElementById('totalCarrinhoHidden').value = totalCarrinho.toFixed(2);
}

function gerarNavbar() {
    const navbarContainer = document.getElementById('navbarContainer');

    if (navbarContainer) {
        navbarContainer.innerHTML = `
            <nav class="navbar navbar-expand-lg fixed-top">
    <div class="container menu">
        <a class="navbar-brand dancing-script" href="CtrlProdutoListar">VinhoVerso</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a id="login" class="nav-link" href="Index">
                        ${nomeCliente === 'null' ? 'Fazer Login' : 'Ola, ' + nomeCliente}
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" onclick="document.getElementById('listarVinhosForm').submit()">Vinhos</a>
                    <form id="listarVinhosForm" action="CtrlProdutoListar" method="GET" style="display: none;"></form>
                </li>

                <!-- Carrinho -->
                <li class="nav-item">
                    <a id="meuCarrinho" class="nav-link" href="CtrlCompraVisualizar">Meu Carrinho</a>
                </li>
                <div id="acessoClienteContainer" ${nomeCliente === null ? '' : ''}>

                </div>
        
        <!-- Dropdown Administrador -->
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="adminDropdown" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                Administrador
            </a>
            <div class="dropdown-menu" aria-labelledby="adminDropdown">
                <h6 class="dropdown-header">Cliente</h6>
                <a id="cadastarCliente" class="dropdown-item" href="CtrlClienteNovo">Cadastrar Cliente</a>
                <a id="consultarCliente" class="dropdown-item" href="ConsultaCliente">Consultar Cliente</a>
                <div class="dropdown-divider"></div>
                <h6 class="dropdown-header">Vinho</h6>
                <a id="cadastrarVinho" class="dropdown-item" href="CtrlProdutoNovo">Cadastrar Vinho</a>
                <a id="consultarVinho" class="dropdown-item" href="ConsultaProduto">Consultar Vinho</a>
                <div class="dropdown-divider"></div>
                <a id="pedidosDeTroca" class="dropdown-item" href="CtrlCompraPedidosDeTroca">Pedidos De Troca</a>
            </div>
        </li>
        </ul>
    </div>
    </div>
</nav>

        `;
    } else {
        console.error("O container 'navbarContainer' não foi encontrado.");
    }
    const container = document.getElementById('acessoClienteContainer');
    const abaCliente = document.createElement('li');
    abaCliente.classList.add('nav-item');
    abaCliente.classList.add('dropdown');
    abaCliente.innerHTML = `
                <a class="nav-link dropdown-toggle" href="#" id="clienteDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Cliente
                    </a>
                    <div class="dropdown-menu" aria-labelledby="vinhoDropdown">
                    <a id="meusPedidos" class="dropdown-item" href="#" onclick="document.getElementById('meusPedidosForm').submit();">Meus Pedidos</a>
                        <form id="meusPedidosForm" action="CtrlCompraTransacoes" method="GET" style="display: none;">
                            <input type="hidden" name="encaminhamento" value="meusPedidos">
                        </form>
                        <a id="logout" class="dropdown-item" href="#" onclick="document.getElementById('logoutForm').submit();">Logout</a>
                        <form id="logoutForm" action="Logout" method="GET" style="display: none;"></form>
                       `;
    if (nomeCliente !== 'null') {
        container.append(abaCliente);
    }
}

function alternarExibicao(index) {
    const detalhes = document.getElementById(`detalhes${index}`);
    const botao = detalhes.previousElementSibling.querySelector(".btn");

    if (detalhes.classList.contains("show")) {
        detalhes.classList.remove("show");
        detalhes.classList.add("collapse");
        botao.innerHTML = '<i class="fa-solid fa-caret-down"></i>';
    } else {
        detalhes.classList.remove("collapse");
        detalhes.classList.add("show");
        botao.innerHTML = '<i class="fa-solid fa-caret-up"></i>';
    }
}

function submeterFormulario() {
    if (validarSenhas()) {
        document.getElementById('formularioSalvar').submit();
    } else {
        alert('As senhas nao coincidem. Por favor, verifique.');
    }
}

let contadorCupons = 1;

function adicionarCupom() {

    const novaDiv = document.createElement('div');
    const cuponsContainer = document.getElementById('cuponsContainer');
    novaDiv.classList.add('form-row');

    novaDiv.innerHTML = `
            <div class="form-group col-md-3">
                            <label>Cupom:</label>
                        </div>
                        <div class="form-group col-md-6">
                            <input type="text" id="cupom_${contadorCupons}" name="cupom_${contadorCupons}" class="form-control"
                                   placeholder="Digite seu cupom">
                        </div>
                        <div class="form-group col-md-3">
                            <button type="button" class="btn btn-primary btn-menor" id="botaoCupom_${contadorCupons}" onclick="aplicarCupom(${contadorCupons})">Aplicar
                                Cupom
                            </button>
                        </div>
        `;

    cuponsContainer.appendChild(novaDiv);
    document.getElementById('adicionarCupomBtn').disabled = true;
    contadorCupons++;
}

function adicionarCartaoCompra(cartoes, maxCartoes) {
    if (numCartao < maxCartoes) {
        const novaDiv = document.createElement('div');
        novaDiv.classList.add('form-row');
        novaDiv.classList.add('justify-content-end');

        // Criando o HTML com base nos cartões recebidos como parâmetro
        let options = '';
        cartoes.forEach(cartao => {
            options += `<option value="${cartao.id}">${cartao.bandeira} Final ${cartao.numero.slice(-4)}</option>`;
        });

        // Adicionando o HTML ao novo elemento
        novaDiv.innerHTML = `
            <div class="form-group col-md-6">
                <select id="cartao_${numCartao}" name="cartao_${numCartao}" class="form-control">
                    ${options}
                </select>
            </div>
            <div class="form-group col-md-3">
                <input type="number" id="valor_cartao_${numCartao}" name="valor_cartao_${numCartao}" class="form-control" placeholder="Valor">
            </div>
        `;

        cartoesContainer.appendChild(novaDiv);
        numCartao++;
        document.getElementById('numCartoesHidden').value++;

        if (numCartao >= maxCartoes) {
            botaoAdicionarCartao.disabled = true;
        }
    }
}

function submitFormGeral(action, method, formId) {
    var form = document.getElementById(formId);
    form.action = action;
    form.method = method;
    form.submit();
}

function converterParaSegundos(hhmmss) {
    const partes = hhmmss.split(':');
    const horas = parseInt(partes[0], 10);
    const minutos = parseInt(partes[1], 10);
    const segundos = parseInt(partes[2], 10);
    return horas * 3600 + minutos * 60 + segundos;
}

function aplicarCupom(numeroCupom) {
    const inputCupom = document.getElementById(`cupom_${numeroCupom}`).value.toLowerCase(); // Cupom digitado pelo usuário
    let cupomEncontrado = false;
    const cuponsTabela = document.getElementById('cuponsTabela');
    const novaTr = document.createElement('tr');

    // Procura o cupom correspondente (ignorando case sensitive)
    for (let i = 0; i < cupons.length; i++) {
        if (cupons[i].codigo.toLowerCase() === inputCupom && !cupons[i].utilizado) {
            cupomEncontrado = true;
            cupons[i].utilizado = true;
            novaTr.innerHTML = `
                        <td colspan="2"><strong>Cupom Aplicado</strong></td>
                        <td id="valorCupom_${numeroCupom}" name="valorCupom_${numeroCupom}"></td>
                        <input type="hidden" name="idCupomHidden_${numeroCupom}" id="idCupomHidden_${numeroCupom}" value="0">
                        <input type="hidden" name="valorCupomHidden_${numeroCupom}" id="valorCupomHidden_${numeroCupom}" value="0">
                        <td></td>
        `;
            cuponsTabela.appendChild(novaTr);
            document.getElementById('idCupomHidden_' + numeroCupom).value = cupons[i].id; // Limpa o campo de texto

            // Aplica o valor do cupom no input hidden
            document.getElementById('valorCupom_' + numeroCupom).innerHTML = `-R$${cupons[i].valor.toFixed(2)}`;
            document.getElementById('valorCupomHidden_' + numeroCupom).value = cupons[i].valor.toFixed(2);
            document.getElementById('numCuponsHidden').value++;


            document.getElementById(`botaoCupom_${numeroCupom}`).disabled = true;
            document.getElementById(`botaoCupom_${numeroCupom}`).innerText = "APLICADO";
            document.getElementById(`cupom_${numeroCupom}`).disabled = true;
            document.getElementById('adicionarCupomBtn').disabled = false;
            // Atualiza o valor total do carrinho
            atualizarTotalCarrinho();
            break;
        }
    }
    if (!cupomEncontrado) {
        alert('Cupom invalido');
    }
}

function calcularFrete() {
    // Seleciona o dropdown de endereços
    const selectEndereco = document.getElementById('enderecoEntrega');

    // Obtém o texto do option selecionado (o endereço completo)
    const enderecoSelecionado = selectEndereco.options[selectEndereco.selectedIndex].text;

    // Normaliza o texto, removendo acentos e convertendo para minúsculas
    const enderecoNormalizado = enderecoSelecionado.normalize('NFD').replace(/[\u0300-\u036f]/g, '').toLowerCase();

    // Verifica se a cidade no endereço selecionado é "são paulo" (insensível a maiúsculas e acentos)
    if (enderecoNormalizado.includes('brasil')) {
        if (enderecoNormalizado.includes('sao paulo') || enderecoNormalizado.includes('-SP')) {
            // Define o valor do frete como R$ 20,00
            document.getElementById('valorFrete').innerHTML = 'R$20,00';
            document.getElementById('valorFreteHidden').value = 20;
            document.getElementById('prazoFrete').innerHTML = 'Frete (5 dias uteis)';
            document.getElementById('prazoFreteHidden').value = '5 dias uteis';
        } else {
            // Caso contrário, define o frete como 0 ou outro valor
            document.getElementById('valorFrete').innerHTML = 'R$50,00';
            document.getElementById('prazoFrete').innerHTML = 'Frete (10 dias uteis)';
            document.getElementById('valorFreteHidden').value = 50;
            document.getElementById('prazoFreteHidden').value = '10 dias uteis';
        }
    } else {
        document.getElementById('valorFrete').innerHTML = 'R$100,00';
        document.getElementById('prazoFrete').innerHTML = 'Frete (20 dias uteis)';
        document.getElementById('valorFreteHidden').value = 100;
        document.getElementById('prazoFreteHidden').value = '20 dias uteis';
    }
    atualizarTotalCarrinho()
}

function submitForm(encaminhamentoValue) {
    document.getElementById('encaminhamento').value = encaminhamentoValue;
    if (encaminhamentoValue === 'consultarLogs') {
        document.getElementById('clienteForm').action = "CtrlLogConsultar";
    }
    if (encaminhamentoValue === 'consultarCupons') {
        document.getElementById('clienteForm').method = "get";
        document.getElementById('clienteForm').action = "CtrlClienteAlterar";
    }
    if (encaminhamentoValue === 'transacoes') {
        document.getElementById('clienteForm').method = "get";
        document.getElementById('clienteForm').action = "CtrlCompraTransacoes";
    }
    document.getElementById('clienteForm').submit();
}

function naoRepor(compraIdContainer) {
    document.getElementById('reporEstoque_' + compraIdContainer).value = 'false';
}
function Repor(compraIdContainer) {
    document.getElementById('reporEstoque_' + compraIdContainer).value = 'true';
}

function retornar(resposta) {
    if (resposta === null || resposta === "") {
        window.history.back();
    }
    else if (resposta === 'voltar2') {
        window.history.go(-2);
    }
    else {
        document.getElementById('formResposta').action = resposta;
        document.getElementById('formResposta').method = 'get';
        document.getElementById('formResposta').submit();
    }
}

function verificarSelecaoParcial() {
    const checkboxes = document.querySelectorAll('input[name="itensSelecionados"]');
    const trocaParcialBtn = document.getElementById("trocaParcialBtn");

    let totalSelecionados = 0;

    checkboxes.forEach(function (checkbox) {
        if (checkbox.checked) {
            totalSelecionados++;
        }
    });

    // Verifica se a seleção é parcial
    if (totalSelecionados > 0 && totalSelecionados < checkboxes.length) {
        trocaParcialBtn.disabled = false;
    } else {
        trocaParcialBtn.disabled = true;
    }
}

function abrirModal() {
    const modal = new bootstrap.Modal(document.getElementById('notificacoesModal'), {});
    const notificacoesContainer = document.getElementById("notificacoesContainer");

    // Limpa o conteúdo atual e adiciona cada notificação
    notificacoesContainer.innerHTML = "";
    if (notificacoes.length > 0) {
        notificacoes.forEach(function(notificacao) {
            const p = document.createElement("p");
            p.textContent = notificacao.mensagem;
            notificacoesContainer.appendChild(p);
        });
        modal.show();
    }
}