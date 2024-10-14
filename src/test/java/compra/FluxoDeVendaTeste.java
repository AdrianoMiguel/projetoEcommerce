package compra;

import dominio.cliente.Cliente;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.events.EventFiringDecorator;
import utils.Factory;
import utils.FakerModificado;
import utils.SlowdownListener;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FluxoDeVendaTeste {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver baseDriver = new ChromeDriver();
        SlowdownListener listener = new SlowdownListener(1000);
        driver = new EventFiringDecorator(listener).decorate(baseDriver);
        //driver = new ChromeDriver();
        driver.get("http://localhost:8080/EcommerceVinhoVerso_war/CtrlProdutoListar");
    }

    @Test
    public void teste() throws InterruptedException {
        String cupomCredito;
        String cupomTroca;
        Actions actions = new Actions(driver);
        FakerModificado faker = new FakerModificado();
        Cliente cliente = Factory.ClienteTeste();
        Thread.sleep(5000);
        WebElement adminDropdown = driver.findElement(By.id("adminDropdown"));
        actions.moveToElement(adminDropdown).perform();
        adminDropdown.click();
        WebElement consultarCliente = driver.findElement(By.id("consultarCliente"));
        actions.moveToElement(consultarCliente).perform();
        consultarCliente.click();
        driver.findElement(By.id("filtro")).sendKeys("Brasil");
        WebElement buscarClientes = driver.findElement(By.id("btnConsultar"));
        actions.moveToElement(buscarClientes).perform();
        buscarClientes.click();
        WebElement alterar = driver.findElement(By.id("alterar_" + cliente.getId()));
        actions.moveToElement(alterar).perform();
        Thread.sleep(2000);
        alterar.click();
        WebElement consultarCuponsBtn = driver.findElement(By.id("consultarCuponsBtn"));
        actions.moveToElement(consultarCuponsBtn).perform();
        Thread.sleep(3000);
        consultarCuponsBtn.click();
        WebElement novoCupomBtn = driver.findElement(By.id("novoCupomBtn"));
        actions.moveToElement(novoCupomBtn).perform();
        novoCupomBtn.click();
        driver.findElement(By.id("codigo")).sendKeys("TESTEAUTOMATIZADO");
        driver.findElement(By.id("valor")).sendKeys("250");
        Thread.sleep(2000);
        WebElement cadastrarCupomBtn = driver.findElement(By.id("cadastrarCupomBtn"));
        actions.moveToElement(cadastrarCupomBtn).perform();
        cadastrarCupomBtn.click();
        WebElement botaoVoltar = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar).perform();
        botaoVoltar.click();
        WebElement novoCupomBtn2 = driver.findElement(By.id("novoCupomBtn"));
        actions.moveToElement(novoCupomBtn2).perform();
        novoCupomBtn2.click();
        driver.findElement(By.id("codigo")).sendKeys("TESTEAUTOMATIZADO2");
        driver.findElement(By.id("valor")).sendKeys("115");
        Thread.sleep(2000);
        WebElement cadastrarCupomBtn2 = driver.findElement(By.id("cadastrarCupomBtn"));
        actions.moveToElement(cadastrarCupomBtn2).perform();
        cadastrarCupomBtn2.click();
        WebElement botaoVoltar5 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar5).perform();
        botaoVoltar5.click();
        WebElement novoCupomBtn3 = driver.findElement(By.id("novoCupomBtn"));
        actions.moveToElement(novoCupomBtn3).perform();
        novoCupomBtn3.click();
        driver.findElement(By.id("codigo")).sendKeys("TESTEAUTOMATIZADO3");
        driver.findElement(By.id("valor")).sendKeys("10");
        Thread.sleep(2000);
        WebElement cadastrarCupomBtn3 = driver.findElement(By.id("cadastrarCupomBtn"));
        actions.moveToElement(cadastrarCupomBtn3).perform();
        cadastrarCupomBtn3.click();
        WebElement botaoVoltar14 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar14).perform();
        botaoVoltar14.click();
        WebElement novoCupomBtn4 = driver.findElement(By.id("novoCupomBtn"));
        actions.moveToElement(novoCupomBtn4).perform();
        novoCupomBtn4.click();
        driver.findElement(By.id("codigo")).sendKeys("TESTEAUTOMATIZADO4");
        driver.findElement(By.id("valor")).sendKeys("1000");
        Thread.sleep(2000);
        WebElement cadastrarCupomBtn4 = driver.findElement(By.id("cadastrarCupomBtn"));
        actions.moveToElement(cadastrarCupomBtn4).perform();
        cadastrarCupomBtn4.click();
        WebElement login = driver.findElement(By.id("login"));
        actions.moveToElement(login).perform();
        login.click();
        WebElement clienteSelect = driver.findElement(By.id("idClienteLogin"));
        clienteSelect.click();
        Select clienteId = new Select(clienteSelect);
        clienteId.selectByValue(String.valueOf(cliente.getId()));
        WebElement submitButton = driver.findElement(By.id("btnConsultar"));
        actions.moveToElement(submitButton).perform();
        submitButton.click();
        Thread.sleep(2000);
        WebElement detalhesButton = driver.findElement(By.id(faker.verDetalhes()));
        actions.moveToElement(detalhesButton).perform();
        Thread.sleep(2000);
        detalhesButton.click();


        //RF0031 - Gerenciar carrinho de compras Parte 1/2:
        // O sistema deve permitir que produtos sejam colocados em um repositorio temporario para futura comrpra.
        // Deve ser possível adicionar itens ao carrinho de compras.
        //RN0031 - Validar estoque para adição de itens no carrinho
        WebElement qtdeSelect = driver.findElement(By.id("quantidadeVinho"));
        qtdeSelect.click();
        Select quantidade = new Select(qtdeSelect);
        List<WebElement> opcoes = quantidade.getOptions();
        int indexAleatorio = new Random().nextInt(opcoes.size());
        quantidade.selectByIndex(indexAleatorio);
        WebElement adicionarCarrinho = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(adicionarCarrinho).perform();
        adicionarCarrinho.click();
        WebElement botaoVoltar2 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar2).perform();
        botaoVoltar2.click();
        WebElement botaoVoltar3 = driver.findElement(By.id("voltar"));

        //RN0044 - Bloqueio de produtos
        WebElement qtdeSelect4 = driver.findElement(By.id("quantidadeVinho"));
        qtdeSelect4.click();
        Thread.sleep(4000);
        actions.moveToElement(botaoVoltar3).perform();
        botaoVoltar3.click();
        WebElement detalhesButton2 = driver.findElement(By.id(faker.verDetalhes()));
        actions.moveToElement(detalhesButton2).perform();
        Thread.sleep(2000);
        detalhesButton2.click();
        WebElement qtdeSelect2 = driver.findElement(By.id("quantidadeVinho"));
        qtdeSelect2.click();
        Select quantidade2 = new Select(qtdeSelect2);
        List<WebElement> opcoes2 = quantidade2.getOptions();
        int indexAleatorio2 = new Random().nextInt(opcoes2.size());
        quantidade2.selectByIndex(indexAleatorio2);
        WebElement adicionarCarrinho2 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(adicionarCarrinho2).perform();
        adicionarCarrinho2.click();
        WebElement botaoVoltar4 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar4).perform();
        botaoVoltar4.click();
        WebElement botaoVoltar6 = driver.findElement(By.id("voltar"));
        WebElement qtdeSelect5 = driver.findElement(By.id("quantidadeVinho"));
        qtdeSelect5.click();
        actions.moveToElement(botaoVoltar6).perform();
        botaoVoltar6.click();
        WebElement detalhesButton3 = driver.findElement(By.id(faker.verDetalhes()));
        actions.moveToElement(detalhesButton3).perform();
        Thread.sleep(2000);
        detalhesButton3.click();
        WebElement qtdeSelect3 = driver.findElement(By.id("quantidadeVinho"));
        qtdeSelect3.click();
        Select quantidade3 = new Select(qtdeSelect3);
        List<WebElement> opcoes3 = quantidade3.getOptions();
        int indexAleatorio3 = new Random().nextInt(opcoes3.size());
        quantidade3.selectByIndex(indexAleatorio3);
        WebElement adicionarCarrinho3 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(adicionarCarrinho3).perform();
        adicionarCarrinho3.click();
        WebElement botaoVoltar12 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar12).perform();
        botaoVoltar12.click();
        WebElement botaoVoltar13 = driver.findElement(By.id("voltar"));
        WebElement qtdeSelect13 = driver.findElement(By.id("quantidadeVinho"));
        qtdeSelect13.click();
        actions.moveToElement(botaoVoltar13).perform();
        botaoVoltar13.click();
        WebElement detalhesButton8 = driver.findElement(By.id(faker.verDetalhes()));
        actions.moveToElement(detalhesButton8).perform();
        Thread.sleep(2000);
        detalhesButton8.click();
        WebElement qtdeSelect10 = driver.findElement(By.id("quantidadeVinho"));
        qtdeSelect10.click();
        Select quantidade10 = new Select(qtdeSelect10);
        List<WebElement> opcoes13 = quantidade10.getOptions();
        int indexAleatorio13 = new Random().nextInt(opcoes13.size());
        quantidade10.selectByIndex(indexAleatorio13);
        WebElement adicionarCarrinho10 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(adicionarCarrinho10).perform();
        adicionarCarrinho10.click();


        WebElement meuCarrinho = driver.findElement(By.id("meuCarrinho"));
        actions.moveToElement(meuCarrinho).perform();
        meuCarrinho.click();
        Thread.sleep(5000);


        //RF0035 - Selecionar endereço de entrega (adicionando novo endereço de entrega e incorporando ao perfil do cliente)
        WebElement novoEndereco = driver.findElement(By.id("novoEndereco"));
        actions.moveToElement(novoEndereco).perform();
        Thread.sleep(2000);
        novoEndereco.click();
        driver.findElement(By.id("endEntNome1")).sendKeys("ENDERECO TESTE AUTOMATIZADO");
        Select tiporesidEnt1 = new Select(driver.findElement(By.id("tiporesidEnt1")));
        tiporesidEnt1.selectByVisibleText(cliente.getEndEnt().get(0).getTipoResid().toString());
        Select tipologradEnt1 = new Select(driver.findElement(By.id("tipologradEnt1")));
        tipologradEnt1.selectByVisibleText(cliente.getEndEnt().get(0).getTipoLograd().toString());
        driver.findElement(By.id("endEntLograd1")).sendKeys(cliente.getEndEnt().get(0).getLogradouro());
        driver.findElement(By.id("endEntNum1")).sendKeys(cliente.getEndEnt().get(0).getNumero().toString());
        driver.findElement(By.id("endEntBairro1")).sendKeys(cliente.getEndEnt().get(0).getBairro().getNome());
        driver.findElement(By.id("endEntCidade1")).sendKeys(cliente.getEndEnt().get(0).getBairro().getCidade().getNome());
        driver.findElement(By.id("endEntEst1")).sendKeys(cliente.getEndEnt().get(0).getBairro().getCidade().getEstado().getNome());
        driver.findElement(By.id("endEntCep1")).sendKeys(cliente.getEndEnt().get(0).getCep());
        driver.findElement(By.id("endEntPais1")).sendKeys(cliente.getEndEnt().get(0).getBairro().getCidade().getEstado().getPais().getNome());
        driver.findElement(By.id("endEntObs1")).sendKeys(cliente.getEndEnt().get(0).getObs());
        WebElement cadastrarEndBtn = driver.findElement(By.id("cadastrarEndBtn"));
        Thread.sleep(3000);
        actions.moveToElement(cadastrarEndBtn).perform();
        cadastrarEndBtn.click();
        WebElement botaoVoltar7 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar7).perform();
        botaoVoltar7.click();


        //RF0036 - Selecionar forma de pagamento (adicionando novo cartão de crédito e incorporando ao perfil do cliente)
        WebElement novoCartaoBtn = driver.findElement(By.id("novoCartaoBtn"));
        actions.moveToElement(novoCartaoBtn).perform();
        Thread.sleep(2000);
        novoCartaoBtn.click();
        driver.findElement(By.id("cartaoNome1")).sendKeys(cliente.getCartoes().get(0).getNome());
        Select bandeira1 = new Select(driver.findElement(By.id("bandeira1")));
        bandeira1.selectByVisibleText(cliente.getCartoes().get(0).getBandeira().toString().replace("_", " "));
        driver.findElement(By.id("cartaoNum1")).sendKeys(cliente.getCartoes().get(0).getNumero());
        driver.findElement(By.id("cartaoCodSeg1")).sendKeys(cliente.getCartoes().get(0).getCod().toString());
        WebElement cadastrarCartaoBtn = driver.findElement(By.id("cadastrarCartaoBtn"));
        Thread.sleep(3000);
        actions.moveToElement(cadastrarCartaoBtn).perform();
        cadastrarCartaoBtn.click();
        WebElement botaoVoltar8 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar8).perform();
        botaoVoltar8.click();


        //RF0031 - Gerenciar carrinho de compras Parte 2/2:
        // Deve ser possível também alterar e excluir itens de compra no carrinho, assim como visualizar
        // todos os itens no carrinho
        //RN0045 - Retirar item do carrinho
        WebElement primeiroRemover = driver.findElement(By.xpath("//*[contains(@id, 'remover_')]"));
        actions.moveToElement(primeiroRemover).perform();
        Thread.sleep(2000);
        primeiroRemover.click();


        // RF0032 - Definir quantidade de itens no carrinho
        WebElement ultimoItemSelect = driver.findElement(By.xpath("//*[contains(@id, 'quantidade_')]"));
        actions.moveToElement(ultimoItemSelect).perform();
        ultimoItemSelect.click();
        Thread.sleep(2000);
        Select quantidadeSelect4 = new Select(ultimoItemSelect);
        List<WebElement> opcoes4 = quantidadeSelect4.getOptions();
        int indexAleatorio4 = new Random().nextInt(opcoes4.size());
        quantidadeSelect4.selectByIndex(indexAleatorio4);


        //RF0035 - Selecionar endereço de entrega
        WebElement enderecoEntrega = driver.findElement(By.id("enderecoEntrega"));
        Thread.sleep(4000);
        actions.moveToElement(enderecoEntrega).perform();
        enderecoEntrega.click();
        Select enderecos = new Select(enderecoEntrega);
        List<WebElement> opcoes5 = enderecos.getOptions();
        int indexAleatorio5 = new Random().nextInt(opcoes5.size());
        enderecos.selectByIndex(indexAleatorio5);


        //RF0034 - Calcular Frete
        WebElement calcularFrete = driver.findElement(By.id("calcularFreteBtn"));
        actions.moveToElement(calcularFrete).perform();
        calcularFrete.click();


        //RF0036 - Selecionar forma de pagamento (adicionando 1 cupom promocional e 2 cartões de crédito aleatórios)
        //RN0034 - Uso de diversos cartoes de credito

        WebElement botaoCupom_0 = driver.findElement(By.id("botaoCupom_0"));
        actions.moveToElement(botaoCupom_0).perform();
        driver.findElement(By.id("cupom_0")).sendKeys("TESTEAUTOMATIZADO");
        botaoCupom_0.click();

        WebElement cartao_0 = driver.findElement(By.id("cartao_0"));
        actions.moveToElement(cartao_0).perform();
        cartao_0.click();
        Select cartoes_0 = new Select(cartao_0);
        List<WebElement> opcoes6 = cartoes_0.getOptions();
        int indexAleatorio6 = new Random().nextInt(opcoes6.size());
        cartoes_0.selectByIndex(indexAleatorio6);
        if (Double.valueOf(driver.findElement(By.id("totalCarrinhoHidden")).getAttribute("value")) > 0) {
            if (Double.valueOf(driver.findElement(By.id("totalCarrinhoHidden")).getAttribute("value")) >= 20) {
                driver.findElement(By.id("valor_cartao_0")).sendKeys("10");
            } else {
                driver.findElement(By.id("valor_cartao_0")).sendKeys(driver.findElement(By.id("totalCarrinhoHidden")).getAttribute("value"));
            }
        }
        WebElement adicionarCartaoBtn = driver.findElement(By.id("adicionarCartaoBtn"));
        actions.moveToElement(adicionarCartaoBtn).perform();
        adicionarCartaoBtn.click();
        WebElement cartao_1 = driver.findElement(By.id("cartao_1"));
        cartao_1.click();
        Select cartoes_1 = new Select(cartao_1);
        List<WebElement> opcoes7 = cartoes_1.getOptions();
        int indexAleatorio7 = new Random().nextInt(opcoes7.size());
        cartoes_1.selectByIndex(indexAleatorio7);
        if ((Double.valueOf(driver.findElement(By.id("totalCarrinhoHidden")).getAttribute("value")) - 10) >= 10) {
            driver.findElement(By.id("valor_cartao_1")).sendKeys(String.valueOf(Double.valueOf(driver.findElement(By.id("totalCarrinhoHidden")).getAttribute("value")) - 10));
        }


        //RF0033 - Realizar compra
        WebElement finalizarCompra = driver.findElement(By.id("finalizarCompra"));
        actions.moveToElement(finalizarCompra).perform();
        Thread.sleep(4000);
        finalizarCompra.click();


        //RF0037 - Finalizar compra
        WebElement clienteDropdown = driver.findElement(By.id("clienteDropdown"));
        actions.moveToElement(clienteDropdown).perform();
        clienteDropdown.click();

        WebElement meusPedidos = driver.findElement(By.id("meusPedidos"));
        actions.moveToElement(meusPedidos).perform();
        meusPedidos.click();

        WebElement primeiroExpandir = driver.findElement(By.xpath("//*[contains(@id, 'expandir')]"));
        actions.moveToElement(primeiroExpandir).perform();
        primeiroExpandir.click();

        WebElement primeiroTrDetalhes = driver.findElement(By.xpath("//*[contains(@id, 'detalhes')]"));
        actions.moveToElement(primeiroTrDetalhes).perform();
        Thread.sleep(4000);



        WebElement adminDropdown2 = driver.findElement(By.id("adminDropdown"));
        actions.moveToElement(adminDropdown2).perform();
        adminDropdown2.click();
        WebElement consultarCliente2 = driver.findElement(By.id("consultarCliente"));
        actions.moveToElement(consultarCliente2).perform();
        consultarCliente2.click();
        driver.findElement(By.id("filtro")).sendKeys("Brasil");
        WebElement buscarClientes2 = driver.findElement(By.id("btnConsultar"));
        actions.moveToElement(buscarClientes2).perform();
        buscarClientes2.click();
        WebElement alterar2 = driver.findElement(By.id("alterar_" + cliente.getId()));
        actions.moveToElement(alterar2).perform();
        Thread.sleep(2000);
        alterar2.click();
        WebElement consultarCuponsBtn2 = driver.findElement(By.id("consultarCuponsBtn"));
        Thread.sleep(3000);
        actions.moveToElement(consultarCuponsBtn2).perform();
        consultarCuponsBtn2.click();
        WebElement botaoVoltar9 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar9).perform();
        Thread.sleep(3000);
        botaoVoltar9.click();

        //RF0038 - Despachar produtos para entrega
        WebElement transacoesBtn = driver.findElement(By.id("transacoesBtn"));
        Thread.sleep(2000);
        actions.moveToElement(transacoesBtn).perform();
        transacoesBtn.click();

            //RN0038 - Alterar status da compra conforme processo de aprovacao de forma de pagamento
        WebElement primeiroExpandir2 = driver.findElement(By.xpath("//*[contains(@id, 'expandir')]"));
        actions.moveToElement(primeiroExpandir2).perform();
        primeiroExpandir2.click();
        WebElement primeiroTrDetalhes2 = driver.findElement(By.xpath("//*[contains(@id, 'detalhes')]"));
        actions.moveToElement(primeiroTrDetalhes2).perform();
        Thread.sleep(4000);

            //RN0039 - Alterar status da compra para transporte
        WebElement proximoStatus = driver.findElement(By.xpath("//*[contains(@id, 'proxStatusBtn_')]"));
        actions.moveToElement(proximoStatus).perform();
        proximoStatus.click();
        WebElement botaoVoltar10 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar10).perform();
        botaoVoltar10.click();


        //RF0039 - Produtos entregues
        //RN0040 - Alterar status da compra apos entrega
        WebElement proximoStatus2 = driver.findElement(By.xpath("//*[contains(@id, 'proxStatusBtn_')]"));
        Thread.sleep(1000);
        actions.moveToElement(proximoStatus2).perform();
        proximoStatus2.click();
        WebElement botaoVoltar11 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar11).perform();
        botaoVoltar11.click();


        //RF0040 - Solicitar troca
            //RN0041 - Gerar pedido de troca (troca parcial - gera novo pedido com outro ID)
            //RN0043 - Validação para solicitar troca
        WebElement clienteDropdown2 = driver.findElement(By.id("clienteDropdown"));
        clienteDropdown2.click();

        WebElement meusPedidos2 = driver.findElement(By.id("meusPedidos"));
        actions.moveToElement(meusPedidos2).perform();
        meusPedidos2.click();

        WebElement primeiroExpandir3 = driver.findElement(By.xpath("//*[contains(@id, 'expandir')]"));
        actions.moveToElement(primeiroExpandir3).perform();
        primeiroExpandir3.click();

        WebElement realizarTroca = driver.findElement(By.xpath("//*[contains(@id, 'realizarTroca_')]"));
        Thread.sleep(3000);
        actions.moveToElement(realizarTroca).perform();
        realizarTroca.click();

        WebElement selecionarItem = driver.findElement(By.xpath("//*[contains(@id, 'item_')]"));
        selecionarItem.click();

        WebElement trocaParcialBtn = driver.findElement(By.id("trocaParcialBtn"));
        Thread.sleep(1000);
        actions.moveToElement(trocaParcialBtn).perform();
        trocaParcialBtn.click();
        Thread.sleep(2000);


        //RF0042 - Visualização de trocas
        WebElement adminDropdown3 = driver.findElement(By.id("adminDropdown"));
        adminDropdown3.click();
        WebElement pedidosdeTroca = driver.findElement(By.id("pedidosdeTroca"));
        actions.moveToElement(pedidosdeTroca).perform();
        pedidosdeTroca.click();


        //RF0041 - Autorizar trocas
            //RF0043 - Confirmar recebimento de itens para troca
            //RF0044 - Gerar cupom de troca após recebimento de itens
            //RN0042 - Alterar status do pedido apos recebimento de troca
        WebElement primeiroExpandir4 = driver.findElement(By.xpath("//*[contains(@id, 'expandir')]"));
        actions.moveToElement(primeiroExpandir4).perform();
        primeiroExpandir4.click();
        WebElement reporSim = driver.findElement(By.xpath("//*[contains(@id, 'reporSimBtn_')]"));
        Thread.sleep(3000);
        actions.moveToElement(reporSim).perform();
        reporSim.click();
        cupomTroca = capturarCupom("TROCA");


        WebElement clienteDropdown3 = driver.findElement(By.id("clienteDropdown"));
        actions.moveToElement(clienteDropdown3).perform();
        clienteDropdown3.click();

        WebElement meusPedidos3 = driver.findElement(By.id("meusPedidos"));
        actions.moveToElement(meusPedidos3).perform();
        meusPedidos3.click();
        Thread.sleep(3000);
        WebElement clienteDropdown4 = driver.findElement(By.id("clienteDropdown"));
        actions.moveToElement(clienteDropdown4).perform();
        clienteDropdown4.click();

        WebElement logout = driver.findElement(By.id("logout"));
        Thread.sleep(1000);
        actions.moveToElement(logout).perform();
        logout.click();

        //RN0046 - Gerar notificacao de autorizacao de troca
        WebElement login2 = driver.findElement(By.id("login"));
        actions.moveToElement(login2).perform();
        login2.click();

        WebElement clienteSelect2 = driver.findElement(By.id("idClienteLogin"));
        Select clienteId2 = new Select(clienteSelect2);
        clienteId2.selectByValue(String.valueOf(cliente.getId()));

        WebElement submitButton2 = driver.findElement(By.id("btnConsultar"));
        actions.moveToElement(submitButton2).perform();
        submitButton2.click();

        Thread.sleep(4000);
        actions.moveByOffset(10, 10).click().perform();
        Thread.sleep(2000);



        // PARTE 2 : EFETUANDO COMPRA QUE GERA CUPOM DE CREDITO



        WebElement listarVinhos = driver.findElement(By.id("listarVinhos"));
        actions.moveToElement(listarVinhos).perform();
        listarVinhos.click();

        WebElement detalhesButton4 = driver.findElement(By.id("detalhes_3"));
        actions.moveToElement(detalhesButton4).perform();
        Thread.sleep(2000);
        detalhesButton4.click();

        WebElement qtdeSelect6 = driver.findElement(By.id("quantidadeVinho"));
        qtdeSelect6.click();

        WebElement adicionarCarrinho4 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(adicionarCarrinho4).perform();
        adicionarCarrinho4.click();

        WebElement listarVinhos2 = driver.findElement(By.id("listarVinhos"));
        actions.moveToElement(listarVinhos2).perform();
        listarVinhos2.click();

        WebElement detalhesButton5 = driver.findElement(By.id("detalhes_39"));
        actions.moveToElement(detalhesButton5).perform();
        Thread.sleep(2000);
        detalhesButton5.click();

        WebElement qtdeSelect7 = driver.findElement(By.id("quantidadeVinho"));
        qtdeSelect7.click();

        WebElement adicionarCarrinho5 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(adicionarCarrinho5).perform();
        adicionarCarrinho5.click();

        //RN0036 - Gerar cupom de troca (cupom de crédito)
        WebElement meuCarrinho2 = driver.findElement(By.id("meuCarrinho"));
        actions.moveToElement(meuCarrinho2).perform();
        meuCarrinho2.click();
        Thread.sleep(5000);

        WebElement calcularFrete2 = driver.findElement(By.id("calcularFreteBtn"));
        actions.moveToElement(calcularFrete2).perform();
        calcularFrete2.click();

        //RF0036 - Selecionar forma de pagamento (utilizando 1 cupom de troca)
        WebElement botaoCupom_0_3 = driver.findElement(By.id("botaoCupom_0"));
        actions.moveToElement(botaoCupom_0_3).perform();
        driver.findElement(By.id("cupom_0")).sendKeys(cupomTroca);
        botaoCupom_0_3.click();

        driver.findElement(By.id("valor_cartao_0")).sendKeys("100");

        WebElement finalizarCompra2 = driver.findElement(By.id("finalizarCompra"));
        actions.moveToElement(finalizarCompra2).perform();
        Thread.sleep(2000);
        finalizarCompra2.click();
        Thread.sleep(2000);
        WebElement botaoVoltar20 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar20).perform();
        botaoVoltar20.click();

        WebElement calcularFrete6 = driver.findElement(By.id("calcularFreteBtn"));
        actions.moveToElement(calcularFrete6).perform();
        calcularFrete6.click();




        WebElement finalizarCompra8 = driver.findElement(By.id("finalizarCompra"));
        actions.moveToElement(finalizarCompra8).perform();

        driver.findElement(By.id("valor_cartao_0")).clear();
        Thread.sleep(2000);
        finalizarCompra8.click();
        Thread.sleep(2000);


        cupomCredito = capturarCupom("CREDITO");

        WebElement adminDropdown4 = driver.findElement(By.id("adminDropdown"));
        actions.moveToElement(adminDropdown4).perform();
        adminDropdown4.click();
        WebElement consultarCliente3 = driver.findElement(By.id("consultarCliente"));
        actions.moveToElement(consultarCliente3).perform();
        consultarCliente3.click();
        driver.findElement(By.id("filtro")).sendKeys("Brasil");
        WebElement buscarClientes3 = driver.findElement(By.id("btnConsultar"));
        actions.moveToElement(buscarClientes3).perform();
        buscarClientes3.click();
        WebElement alterar3 = driver.findElement(By.id("alterar_" + cliente.getId()));
        actions.moveToElement(alterar3).perform();
        Thread.sleep(2000);
        alterar3.click();
        WebElement transacoesBtn2 = driver.findElement(By.id("transacoesBtn"));
        actions.moveToElement(transacoesBtn2).perform();
        transacoesBtn2.click();

        WebElement primeiroExpandir6 = driver.findElement(By.xpath("//*[contains(@id, 'expandir')]"));
        actions.moveToElement(primeiroExpandir6).perform();
        primeiroExpandir6.click();

        WebElement proximoStatus3 = driver.findElement(By.xpath("//*[contains(@id, 'proxStatusBtn_')]"));
        actions.moveToElement(proximoStatus3).perform();
        proximoStatus3.click();
        WebElement botaoVoltar15 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar15).perform();
        botaoVoltar15.click();

        WebElement proximoStatus4 = driver.findElement(By.xpath("//*[contains(@id, 'proxStatusBtn_')]"));
        Thread.sleep(1000);
        actions.moveToElement(proximoStatus4).perform();
        proximoStatus4.click();
        WebElement botaoVoltar16 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar16).perform();
        botaoVoltar16.click();

        WebElement clienteDropdown5 = driver.findElement(By.id("clienteDropdown"));
        actions.moveToElement(clienteDropdown5).perform();
        clienteDropdown5.click();

        //RN0041 - Gerar pedido de troca (troca total da compra - mesmo ID da compra original)
        WebElement meusPedidos4 = driver.findElement(By.id("meusPedidos"));
        actions.moveToElement(meusPedidos4).perform();
        meusPedidos4.click();

        WebElement primeiroExpandir5 = driver.findElement(By.xpath("//*[contains(@id, 'expandir')]"));
        actions.moveToElement(primeiroExpandir5).perform();
        primeiroExpandir5.click();

        WebElement realizarTroca2 = driver.findElement(By.xpath("//*[contains(@id, 'realizarTroca_')]"));
        Thread.sleep(3000);
        actions.moveToElement(realizarTroca2).perform();
        realizarTroca2.click();

        WebElement trocaTotalBtn = driver.findElement(By.id("trocaTotalBtn"));
        Thread.sleep(1000);
        actions.moveToElement(trocaTotalBtn).perform();
        trocaTotalBtn.click();

        WebElement adminDropdown5 = driver.findElement(By.id("adminDropdown"));
        adminDropdown5.click();
        WebElement pedidosdeTroca2 = driver.findElement(By.id("pedidosdeTroca"));
        actions.moveToElement(pedidosdeTroca2).perform();
        pedidosdeTroca2.click();

        WebElement reporNao = driver.findElement(By.xpath("//*[contains(@id, 'reporNaoBtn_')]"));
        Thread.sleep(3000);
        actions.moveToElement(reporNao).perform();
        reporNao.click();

        cupomTroca = capturarCupom("TROCA");


        // PARTE 3: EFETUANDO COMPRA COM CUPOM DE TROCA E PAGAMENTO MINIMO ABAIXO DE 10,00


        WebElement listarVinhos4 = driver.findElement(By.id("listarVinhos"));
        actions.moveToElement(listarVinhos4).perform();
        listarVinhos4.click();

        WebElement detalhesButton7 = driver.findElement(By.id("detalhes_39"));
        actions.moveToElement(detalhesButton7).perform();
        Thread.sleep(2000);
        detalhesButton7.click();

        WebElement qtdeSelect9 = driver.findElement(By.id("quantidadeVinho"));
        qtdeSelect9.click();

        WebElement adicionarCarrinho7 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(adicionarCarrinho7).perform();
        adicionarCarrinho7.click();

        WebElement meuCarrinho3 = driver.findElement(By.id("meuCarrinho"));
        actions.moveToElement(meuCarrinho3).perform();
        meuCarrinho3.click();
        Thread.sleep(4000);

        WebElement finalizarCompra3 = driver.findElement(By.id("finalizarCompra"));
        actions.moveToElement(finalizarCompra3).perform();
        Thread.sleep(2000);
        finalizarCompra3.click();
        Thread.sleep(2000);
        WebElement botaoVoltar17 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar17).perform();
        botaoVoltar17.click();

        WebElement calcularFrete3 = driver.findElement(By.id("calcularFreteBtn"));
        actions.moveToElement(calcularFrete3).perform();
        calcularFrete3.click();

        WebElement finalizarCompra4 = driver.findElement(By.id("finalizarCompra"));
        actions.moveToElement(finalizarCompra4).perform();
        Thread.sleep(2000);
        finalizarCompra4.click();
        Thread.sleep(2000);
        WebElement botaoVoltar18 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar18).perform();
        botaoVoltar18.click();

        WebElement calcularFrete4 = driver.findElement(By.id("calcularFreteBtn"));
        actions.moveToElement(calcularFrete4).perform();
        calcularFrete4.click();

        WebElement cartao_0_3 = driver.findElement(By.id("cartao_0"));
        actions.moveToElement(cartao_0_3).perform();
        cartao_0_3.click();
        Select cartoes_0_3 = new Select(cartao_0_3);
        List<WebElement> opcoes10 = cartoes_0_3.getOptions();
        int indexAleatorio10 = new Random().nextInt(opcoes10.size());
        cartoes_0_3.selectByIndex(indexAleatorio10);
        driver.findElement(By.id("valor_cartao_0")).sendKeys("5");
        WebElement adicionarCartaoBtn2 = driver.findElement(By.id("adicionarCartaoBtn"));
        actions.moveToElement(adicionarCartaoBtn2).perform();
        adicionarCartaoBtn2.click();
        WebElement cartao_1_3 = driver.findElement(By.id("cartao_1"));
        cartao_1_3.click();
        Select cartoes_1_3 = new Select(cartao_1_3);
        List<WebElement> opcoes11 = cartoes_1_3.getOptions();
        int indexAleatorio11 = new Random().nextInt(opcoes11.size());
        cartoes_1_3.selectByIndex(indexAleatorio11);
        driver.findElement(By.id("valor_cartao_1")).sendKeys(String.valueOf(Double.valueOf(driver.findElement(By.id("totalCarrinhoHidden")).getAttribute("value")) - 5));

        WebElement finalizarCompra5 = driver.findElement(By.id("finalizarCompra"));
        actions.moveToElement(finalizarCompra5).perform();
        Thread.sleep(2000);
        finalizarCompra5.click();
        Thread.sleep(2000);
        WebElement botaoVoltar19 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar19).perform();
        botaoVoltar19.click();

        WebElement calcularFrete5 = driver.findElement(By.id("calcularFreteBtn"));
        actions.moveToElement(calcularFrete5).perform();
        calcularFrete5.click();

        WebElement botaoCupom_0_4 = driver.findElement(By.id("botaoCupom_0"));
        actions.moveToElement(botaoCupom_0_4).perform();
        driver.findElement(By.id("cupom_0")).sendKeys("TESTEAUTOMATIZADO2");
        botaoCupom_0_4.click();
        actions.moveToElement(calcularFrete5).perform();
        WebElement cartao_0_4 = driver.findElement(By.id("cartao_0"));
        actions.moveToElement(cartao_0_4).perform();
        cartao_0_4.click();
        Select cartoes_0_4 = new Select(cartao_0_4);
        List<WebElement> opcoes12 = cartoes_0_4.getOptions();
        int indexAleatorio12 = new Random().nextInt(opcoes12.size());
        cartoes_0_4.selectByIndex(indexAleatorio12);
        driver.findElement(By.id("valor_cartao_0")).clear();
        driver.findElement(By.id("valor_cartao_0")).sendKeys(String.valueOf(Double.valueOf(driver.findElement(By.id("totalCarrinhoHidden")).getAttribute("value"))));
        driver.findElement(By.id("valor_cartao_1")).clear();

        WebElement finalizarCompra6 = driver.findElement(By.id("finalizarCompra"));
        actions.moveToElement(finalizarCompra6).perform();
        Thread.sleep(2000);
        finalizarCompra6.click();


        Thread.sleep(4000);



        // PARTE 4: UTILIZANDO DIVERSOS CUPONS DE DESCONTO

        WebElement listarVinhos5 = driver.findElement(By.id("listarVinhos"));
        actions.moveToElement(listarVinhos5).perform();
        listarVinhos5.click();

        WebElement detalhesButton9 = driver.findElement(By.id(faker.verDetalhes()));
        actions.moveToElement(detalhesButton9).perform();
        Thread.sleep(2000);
        detalhesButton9.click();

        WebElement qtdeSelect11 = driver.findElement(By.id("quantidadeVinho"));
        qtdeSelect11.click();
        Select quantidade11 = new Select(qtdeSelect11);
        List<WebElement> opcoes14 = quantidade11.getOptions();
        int indexAleatorio14 = new Random().nextInt(opcoes14.size());
        quantidade11.selectByIndex(indexAleatorio14);

        WebElement adicionarCarrinho8 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(adicionarCarrinho8).perform();
        adicionarCarrinho8.click();

        WebElement listarVinhos8 = driver.findElement(By.id("listarVinhos"));
        actions.moveToElement(listarVinhos8).perform();
        listarVinhos8.click();

        WebElement detalhesButton11 = driver.findElement(By.id(faker.verDetalhes()));
        actions.moveToElement(detalhesButton11).perform();
        Thread.sleep(2000);
        detalhesButton11.click();

        WebElement qtdeSelect14 = driver.findElement(By.id("quantidadeVinho"));
        Select quantidade14 = new Select(qtdeSelect14);
        List<WebElement> opcoes16 = quantidade14.getOptions();
        int indexAleatorio16 = new Random().nextInt(opcoes16.size());
        quantidade14.selectByIndex(indexAleatorio16);

        WebElement adicionarCarrinho11 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(adicionarCarrinho11).perform();
        adicionarCarrinho11.click();

        WebElement listarVinhos6 = driver.findElement(By.id("listarVinhos"));
        actions.moveToElement(listarVinhos6).perform();
        listarVinhos6.click();

        WebElement detalhesButton10 = driver.findElement(By.id(faker.verDetalhes()));
        actions.moveToElement(detalhesButton10).perform();
        Thread.sleep(2000);
        detalhesButton10.click();

        WebElement qtdeSelect12 = driver.findElement(By.id("quantidadeVinho"));
        Select quantidade12 = new Select(qtdeSelect12);
        List<WebElement> opcoes15 = quantidade12.getOptions();
        int indexAleatorio15 = new Random().nextInt(opcoes15.size());
        quantidade12.selectByIndex(indexAleatorio15);

        WebElement adicionarCarrinho9 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(adicionarCarrinho9).perform();
        adicionarCarrinho9.click();


        WebElement meuCarrinho4 = driver.findElement(By.id("meuCarrinho"));
        actions.moveToElement(meuCarrinho4).perform();
        meuCarrinho4.click();

        WebElement calcularFrete7 = driver.findElement(By.id("calcularFreteBtn"));
        actions.moveToElement(calcularFrete7).perform();
        calcularFrete7.click();


        //RN0035 - Uso de cupons junto a cartao de credito
        WebElement botaoCupom_0_5 = driver.findElement(By.id("botaoCupom_0"));
        actions.moveToElement(botaoCupom_0_5).perform();
        driver.findElement(By.id("cupom_0")).sendKeys(cupomTroca);
        botaoCupom_0_5.click();

        WebElement adicionarCupomBtn = driver.findElement(By.id("adicionarCupomBtn"));
        actions.moveToElement(adicionarCupomBtn).perform();
        adicionarCupomBtn.click();

        WebElement botaoCupom_1 = driver.findElement(By.id("botaoCupom_1"));
        actions.moveToElement(botaoCupom_1).perform();
        driver.findElement(By.id("cupom_1")).sendKeys(cupomCredito);
        botaoCupom_1.click();

        adicionarCupomBtn.click();

        WebElement botaoCupom_2 = driver.findElement(By.id("botaoCupom_2"));
        actions.moveToElement(botaoCupom_2).perform();
        driver.findElement(By.id("cupom_2")).sendKeys("TESTEAUTOMATIZADO4");
        botaoCupom_2.click();

        adicionarCupomBtn.click();

        WebElement botaoCupom_3 = driver.findElement(By.id("botaoCupom_3"));
        actions.moveToElement(botaoCupom_3).perform();
        driver.findElement(By.id("cupom_3")).sendKeys("TESTEAUTOMATIZADO4");
        botaoCupom_3.click();

        Alert alert = driver.switchTo().alert();
        Thread.sleep(2000);
        alert.accept();

        driver.findElement(By.id("cupom_3")).clear();
        driver.findElement(By.id("cupom_3")).sendKeys("TESTEAUTOMATIZADO3");
        botaoCupom_3.click();

        Alert alert2 = driver.switchTo().alert();
        Thread.sleep(2000);
        alert2.accept();

        driver.findElement(By.id("cupom_3")).clear();

        if ((Double.valueOf(driver.findElement(By.id("totalCarrinhoHidden")).getAttribute("value")) > 0)) {
            driver.findElement(By.id("valor_cartao_0")).sendKeys(String.valueOf(Double.valueOf(driver.findElement(By.id("totalCarrinhoHidden")).getAttribute("value"))));
            WebElement finalizarCompra7 = driver.findElement(By.id("finalizarCompra"));
            actions.moveToElement(finalizarCompra7).perform();
            Thread.sleep(2000);
            finalizarCompra7.click();
            Thread.sleep(4000);
            return;
        }

        WebElement finalizarCompra7 = driver.findElement(By.id("finalizarCompra"));
        actions.moveToElement(finalizarCompra7).perform();
        Thread.sleep(2000);
        finalizarCompra7.click();
        Thread.sleep(2000);

        //RN0036 - Gerar cupom de troca (cupom de crédito)
        WebElement meuCarrinho5 = driver.findElement(By.id("meuCarrinho"));
        actions.moveToElement(meuCarrinho5).perform();
        meuCarrinho5.click();

        WebElement calcularFrete8 = driver.findElement(By.id("calcularFreteBtn"));
        actions.moveToElement(calcularFrete8).perform();
        calcularFrete8.click();

        WebElement botaoCupom_0_6 = driver.findElement(By.id("botaoCupom_0"));
        actions.moveToElement(botaoCupom_0_6).perform();
        driver.findElement(By.id("cupom_0")).sendKeys(cupomTroca);
        botaoCupom_0_6.click();

        WebElement adicionarCupomBtn2 = driver.findElement(By.id("adicionarCupomBtn"));
        actions.moveToElement(adicionarCupomBtn2).perform();
        adicionarCupomBtn2.click();

        WebElement botaoCupom_1_2 = driver.findElement(By.id("botaoCupom_1"));
        actions.moveToElement(botaoCupom_1_2).perform();
        driver.findElement(By.id("cupom_1")).sendKeys(cupomCredito);
        botaoCupom_1_2.click();

        if ((Double.valueOf(driver.findElement(By.id("totalCarrinhoHidden")).getAttribute("value")) > 0)) {
            driver.findElement(By.id("valor_cartao_0")).sendKeys(String.valueOf(Double.valueOf(driver.findElement(By.id("totalCarrinhoHidden")).getAttribute("value"))));
        }

        WebElement finalizarCompra9 = driver.findElement(By.id("finalizarCompra"));
        actions.moveToElement(finalizarCompra9).perform();
        Thread.sleep(2000);
        finalizarCompra9.click();
        Thread.sleep(3000);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public String capturarCupom(String tipoCupom) {
        WebElement mensagemElemento = driver.findElement(By.id("mensagem"));
        String mensagemTexto = mensagemElemento.getAttribute("value");

        Pattern pattern = Pattern.compile(tipoCupom.trim().toUpperCase() +"(\\d+)");
        Matcher matcher = pattern.matcher(mensagemTexto);

        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return null;
        }
    }
}