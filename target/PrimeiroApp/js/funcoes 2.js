
function obterAutoresSelecionados() {
    const autoresSelecionados = [];
    const selectsAutores = document.querySelectorAll("#autoresContainer select");
    selectsAutores.forEach(select => {
        if (select.value) { // Verifica se o valor é diferente de vazio
            autoresSelecionados.push(select.value);
        }
    });
    document.getElementById('autoresSelecionados').value = autoresSelecionados.join(", ");
}

function obterCategoriasSelecionadas() {
    const categoriasSelecionadas = [];
    const checkboxesCategorias = document.querySelectorAll('input[name="categoria"]:checked');
    checkboxesCategorias.forEach(function (checkbox) {
        categoriasSelecionadas.push(checkbox.value);
    });
    document.getElementById('categoriasSelecionadas').value = categoriasSelecionadas.join(", ");
}

function atualizarCamposSelecionados() {
    obterCategoriasSelecionadas()
    obterAutoresSelecionados()
}

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
function criarCheckboxesCategorias(valoresEnum) {
    var contadorOptions = 0;
    const container = document.getElementById("categoriasContainer");
    valoresEnum.forEach(function (categoria) {
        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.id = categoria.toLowerCase();
        checkbox.name = "categoria";
        checkbox.value = categoria.toLowerCase();
        if (contadorOptions % 5 === 0) {
            // Cria uma nova linha a cada 5 checkboxes
            const novaColuna = document.createElement("div");
            novaColuna.classList.add("col-3");
            container.appendChild(novaColuna);
        }
        contadorOptions++;
        const label = document.createElement("label");
        label.htmlFor = categoria;
        label.appendChild(document.createTextNode(categoria)); 
        const currentColumn = container.lastElementChild;
     currentColumn.appendChild(checkbox);
    currentColumn.appendChild(label);
    currentColumn.appendChild(document.createElement("br"));     
    });
}

function copiarEnderecoParaCobranca() {

    document.getElementById('tiporesidCob').value = document.getElementById('tiporesidRes').value;
    document.getElementById('tipologradCob').value = document.getElementById('tipologradRes').value;
    document.getElementById('endCobLograd').value = document.getElementById('endResLograd').value;
   document.getElementById('endCobNum').value = document.getElementById('endResNum').value;
   document.getElementById('endCobBairro').value = document.getElementById('endResBairro').value;
   document.getElementById('endCobCidade').value = document.getElementById('endResCidade').value;
   document.getElementById('endCobEst').value = document.getElementById('endResEst').value;
   document.getElementById('endCobCep').value = document.getElementById('endResCep').value;
   document.getElementById('endCobPais').value = document.getElementById('endResPais').value;
   document.getElementById('endCobObs').value = document.getElementById('endResObs').value;
}
function copiarEnderecoParaEntrega() {

    document.getElementById('tiporesidEnt1').value = document.getElementById('tiporesidRes').value;
    document.getElementById('tipologradEnt1').value = document.getElementById('tipologradRes').value;
    document.getElementById('endEntLograd1').value = document.getElementById('endResLograd').value;
    document.getElementById('endEntNum1').value = document.getElementById('endResNum').value;
    document.getElementById('endEntBairro1').value = document.getElementById('endResBairro').value;
    document.getElementById('endEntCidade1').value = document.getElementById('endResCidade').value;
    document.getElementById('endEntEst1').value = document.getElementById('endResEst').value;
    document.getElementById('endEntCep1').value = document.getElementById('endResCep').value;
    document.getElementById('endEntPais1').value = document.getElementById('endResPais').value;
    document.getElementById('endEntObs1').value = document.getElementById('endResObs').value;
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
let cartaoCount = 1;

function adicionarEndereco(quantidade) {
    for (let i = 1; i <= quantidade; i++) {
        enderecoCount++;
        const container = document.getElementById('enderecosContainer');

        const novoEndereco = document.createElement('div');
        novoEndereco.classList.add('form-row');
        novoEndereco.innerHTML = `
<div class="form-group col-md-12">
                <p>ENDERECO DE ENTREGA ${enderecoCount}</p>
            
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <input type="text" class="form-control" id="endEntNome${enderecoCount}" name="endEntNome${enderecoCount}" placeholder="Atribuir Nome" >
                    </div>
                </div>
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
                        <input type="text" class="form-control" id="endEntNum${enderecoCount}" name="endEntNum${enderecoCount}" placeholder="Numero" >
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
    `;

        container.appendChild(novoEndereco);

        criarSelect(tiposlograd, `tipoLogradouroEntContainer${enderecoCount}`, `tipologradEnt${enderecoCount}`);
        criarSelect(tiposresid, `tipoResidenciaEntContainer${enderecoCount}`, `tiporesidEnt${enderecoCount}`);
    }
}
function adicionarCartao(quantidade) {
    for (let i = 1; i <= quantidade; i++) {
    cartaoCount++;
    const container = document.getElementById('cartoesContainer');

        const novoCartao = document.createElement('div');
        novoCartao.classList.add('form-row');
        novoCartao.innerHTML = `
        <div class="form-group col-md-12">
            <p>DADOS DO CARTAO ${cartaoCount}</p>
            <div class="form-row">
                <div class="form-group col-md-12">
                    <input type="text" class="form-control" id="cartaoNome${cartaoCount}" name="cartaoNome${cartaoCount}" placeholder="Nome Impresso no Cartao">
                </div>
                </div>
                <div class="form-row">
                <div class="form-group col-md-3">
                <div id="bandeirasContainer${cartaoCount}" class="form-control"></div>
</div>
                <div class="form-group col-md-6">
                    <input type="text" class="form-control" id="cartaoNum${cartaoCount}" name="cartaoNum${cartaoCount}" placeholder="Numero do Cartao">
                </div>
            
                <div class="form-group col-md-3">
                    <input type="text" class="form-control" id="cartaoCodSeg${cartaoCount}" name="cartaoCodSeg${cartaoCount}" placeholder="Cod de Seguranca">
                </div>
            </div>
        </div>
    `;

        container.appendChild(novoCartao);
        criarSelect(bandeiras, `bandeirasContainer${cartaoCount}`, `bandeira${cartaoCount}`);
    }
}

function preencherEnderecosEntrega(enderecosEntrega) {
    for (let i = 0; i < enderecosEntrega.length; i++) {
        // Acessa os campos do formulário pelo ID e preenche com os valores
        document.getElementById(`endEntNome${i+1}`).value = enderecosEntrega[i].nome;
        document.getElementById(`tiporesidEnt${i+1}`).value = enderecosEntrega[i].tipoResid;
        document.getElementById(`tipologradEnt${i+1}`).value = enderecosEntrega[i].tipoLograd;
        document.getElementById(`endEntLograd${i+1}`).value = enderecosEntrega[i].logradouro;
        document.getElementById(`endEntNum${i+1}`).value = enderecosEntrega[i].numero;
        document.getElementById(`endEntBairro${i+1}`).value = enderecosEntrega[i].bairro;
        document.getElementById(`endEntCidade${i+1}`).value = enderecosEntrega[i].cidade;
        document.getElementById(`endEntEst${i+1}`).value = enderecosEntrega[i].estado;
        document.getElementById(`endEntCep${i+1}`).value = enderecosEntrega[i].cep;
        document.getElementById(`endEntPais${i+1}`).value = enderecosEntrega[i].pais;
        document.getElementById(`endEntObs${i+1}`).value = enderecosEntrega[i].observacoes;
    }
}
function preencherCartoes(cartoes) {
    for (let i = 0; i < cartoes.length; i++) {
        // Acessa os campos do formulário pelo ID e preenche com os valores
        document.getElementById(`cartaoNome${i+1}`).value = cartoes[i].nome;
        document.getElementById(`bandeira${i+1}`).value = cartoes[i].bandeira;
        document.getElementById(`cartaoNum${i+1}`).value = cartoes[i].numero;
        document.getElementById(`cartaoCodSeg${i+1}`).value = cartoes[i].codseg;
    }
}


function gerarNavbar() {
    const navbarContainer = document.getElementById('navbarContainer');
    if (navbarContainer) {
        navbarContainer.innerHTML = `
            <nav class="navbar navbar-expand-lg fixed-top">
                <div class="container menu">
                    <a class="navbar-brand dancing-script" href="#"><i class="fa-solid fa-wine-bottle"></i> VinhoVerso</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <a class="nav-link" href='CtrlClienteNovo'>Cadastrar Cliente</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href='Consulta'>Consultar Clientes</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Produtos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Meus Pedidos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Meu Carrinho</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        `;
    } else {
        console.error("O container 'navbarContainer' não foi encontrado.");
    }
}