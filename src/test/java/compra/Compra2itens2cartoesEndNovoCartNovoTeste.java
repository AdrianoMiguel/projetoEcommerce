package compra;

import dominio.cliente.Cliente;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.events.EventFiringDecorator;
import utils.Factory;
import utils.FakerModificado;
import utils.SlowdownListener;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Compra2itens2cartoesEndNovoCartNovoTeste {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver baseDriver = new ChromeDriver();
        SlowdownListener listener = new SlowdownListener(1000);
        driver = new EventFiringDecorator(listener).decorate(baseDriver);
        driver.get("http://localhost:8080/EcommerceVinhoVerso_war/CtrlProdutoListar");
    }

    @Test
    public void testePreenchimentoFormulario() {
        FakerModificado faker = new FakerModificado();
        Cliente cliente = Factory.ClienteTeste();
        WebElement adminDropdown = driver.findElement(By.id("adminDropdown"));
        adminDropdown.click();
        WebElement consultarCliente = driver.findElement(By.id("consultarCliente"));
        consultarCliente.click();
        driver.findElement(By.id("filtro")).sendKeys("Brasil");
        WebElement buscarClientes = driver.findElement(By.id("btnConsultar"));
        buscarClientes.click();
        WebElement alterar = driver.findElement(By.id("alterar_" + cliente.getId()));
        alterar.click();
        WebElement consultarCuponsBtn = driver.findElement(By.id("consultarCuponsBtn"));
        consultarCuponsBtn.click();
        WebElement novoCupomBtn = driver.findElement(By.id("novoCupomBtn"));
        novoCupomBtn.click();
        driver.findElement(By.id("codigo")).sendKeys("TESTEAUTOMATIZADO");
        driver.findElement(By.id("valor")).sendKeys("200");
        WebElement cadastrarCupomBtn = driver.findElement(By.id("cadastrarCupomBtn"));
        cadastrarCupomBtn.click();
        WebElement botaoVoltar = driver.findElement(By.id("botaoVoltar"));
        botaoVoltar.click();
        WebElement novoCupomBtn2 = driver.findElement(By.id("novoCupomBtn"));
        novoCupomBtn2.click();
        driver.findElement(By.id("codigo")).sendKeys("TESTEAUTOMATIZADO2");
        driver.findElement(By.id("valor")).sendKeys("100");
        WebElement cadastrarCupomBtn2 = driver.findElement(By.id("cadastrarCupomBtn"));
        cadastrarCupomBtn2.click();
        WebElement login = driver.findElement(By.id("login"));
        login.click();
        WebElement clienteSelect = driver.findElement(By.id("idClienteLogin"));
        Select clienteId = new Select(clienteSelect);
        clienteId.selectByValue(String.valueOf(cliente.getId()));
        WebElement submitButton = driver.findElement(By.id("btnConsultar"));
        submitButton.click();
        WebElement detalhesButton = driver.findElement(By.id(faker.verDetalhes()));
        detalhesButton.click();
        WebElement qtdeSelect = driver.findElement(By.id("quantidadeVinho"));
        Select quantidade = new Select(qtdeSelect);
        List<WebElement> opcoes = quantidade.getOptions();
        int indexAleatorio = new Random().nextInt(opcoes.size());
        quantidade.selectByIndex(indexAleatorio);
        WebElement adicionarCarrinho = driver.findElement(By.id("cadastrar"));
        adicionarCarrinho.click();
        WebElement botaoVoltar2 = driver.findElement(By.id("botaoVoltar"));
        botaoVoltar2.click();
        WebElement botaoVoltar3 = driver.findElement(By.id("voltar"));
        botaoVoltar3.click();
        WebElement detalhesButton2 = driver.findElement(By.id(faker.verDetalhes()));
        detalhesButton2.click();
        WebElement qtdeSelect2 = driver.findElement(By.id("quantidadeVinho"));
        Select quantidade2 = new Select(qtdeSelect2);
        List<WebElement> opcoes2 = quantidade2.getOptions();
        int indexAleatorio2 = new Random().nextInt(opcoes2.size());
        quantidade2.selectByIndex(indexAleatorio2);
        WebElement adicionarCarrinho2 = driver.findElement(By.id("cadastrar"));
        adicionarCarrinho2.click();
        WebElement botaoVoltar4 = driver.findElement(By.id("botaoVoltar"));
        botaoVoltar4.click();
        WebElement botaoVoltar6 = driver.findElement(By.id("voltar"));
        botaoVoltar6.click();
        WebElement detalhesButton3 = driver.findElement(By.id(faker.verDetalhes()));
        detalhesButton3.click();
        WebElement qtdeSelect3 = driver.findElement(By.id("quantidadeVinho"));
        Select quantidade3 = new Select(qtdeSelect3);
        List<WebElement> opcoes3 = quantidade3.getOptions();
        int indexAleatorio3 = new Random().nextInt(opcoes3.size());
        quantidade3.selectByIndex(indexAleatorio3);
        WebElement adicionarCarrinho3 = driver.findElement(By.id("cadastrar"));
        adicionarCarrinho3.click();

        WebElement meuCarrinho = driver.findElement(By.id("meuCarrinho"));
        meuCarrinho.click();

        WebElement novoEndereco = driver.findElement(By.id("novoEndereco"));
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
        cadastrarEndBtn.click();
        WebElement botaoVoltar7 = driver.findElement(By.id("botaoVoltar"));
        botaoVoltar7.click();
        WebElement novoCartaoBtn = driver.findElement(By.id("novoCartaoBtn"));
        novoCartaoBtn.click();
        driver.findElement(By.id("cartaoNome1")).sendKeys(cliente.getCartoes().get(0).getNome());
        Select bandeira1 = new Select(driver.findElement(By.id("bandeira1")));
        bandeira1.selectByVisibleText(cliente.getCartoes().get(0).getBandeira().toString().replace("_", " "));
        driver.findElement(By.id("cartaoNum1")).sendKeys(cliente.getCartoes().get(0).getNumero());
        driver.findElement(By.id("cartaoCodSeg1")).sendKeys(cliente.getCartoes().get(0).getCod().toString());
        WebElement cadastrarCartaoBtn = driver.findElement(By.id("cadastrarCartaoBtn"));
        cadastrarCartaoBtn.click();
        WebElement botaoVoltar8 = driver.findElement(By.id("botaoVoltar"));
        botaoVoltar8.click();
        WebElement primeiroRemover = driver.findElement(By.xpath("//*[contains(@id, 'remover_')]"));
        primeiroRemover.click();
        //WebElement ultimoItemSelect = driver.findElement(By.xpath("//*[contains(@id, 'quantidade_')]"));
        //ultimoItemSelect.click();
        //Select quantidadeSelect = new Select(ultimoItemSelect);
        //List<WebElement> opcoes4 = quantidadeSelect.getOptions();
        //int indexAleatorio4 = new Random().nextInt(opcoes4.size());
        //quantidadeSelect.selectByIndex(indexAleatorio4);

        WebElement enderecoEntrega = driver.findElement(By.id("enderecoEntrega"));
        enderecoEntrega.click();
        Select enderecos = new Select(enderecoEntrega);
        List<WebElement> opcoes5 = enderecos.getOptions();
        int indexAleatorio5 = new Random().nextInt(opcoes5.size());
        enderecos.selectByIndex(indexAleatorio5);

        WebElement calcularFrete = driver.findElement(By.id("calcularFreteBtn"));
        calcularFrete.click();

        driver.findElement(By.id("cupom_0")).sendKeys("TESTEAUTOMATIZADO");
        WebElement botaoCupom_0 = driver.findElement(By.id("botaoCupom_0"));
        botaoCupom_0.click();

        WebElement adicionarCupomBtn = driver.findElement(By.id("adicionarCupomBtn"));
        adicionarCupomBtn.click();

        driver.findElement(By.id("cupom_1")).sendKeys("TESTEAUTOMATIZADO2");
        WebElement botaoCupom_1 = driver.findElement(By.id("botaoCupom_1"));
        botaoCupom_1.click();

        WebElement cartao_0 = driver.findElement(By.id("cartao_0"));
        cartao_0.click();
        Select cartoes_0 = new Select(cartao_0);
        List<WebElement> opcoes6 = cartoes_0.getOptions();
        int indexAleatorio6 = new Random().nextInt(opcoes6.size());
        cartoes_0.selectByIndex(indexAleatorio6);
        driver.findElement(By.id("valor_cartao_0")).sendKeys("10");

        WebElement adicionarCartaoBtn = driver.findElement(By.id("adicionarCartaoBtn"));
        adicionarCartaoBtn.click();
        WebElement cartao_1 = driver.findElement(By.id("cartao_1"));
        cartao_1.click();
        Select cartoes_1 = new Select(cartao_1);
        List<WebElement> opcoes7 = cartoes_1.getOptions();
        int indexAleatorio7 = new Random().nextInt(opcoes7.size());
        cartoes_1.selectByIndex(indexAleatorio7);
        driver.findElement(By.id("valor_cartao_1")).sendKeys(String.valueOf (Double.valueOf(driver.findElement(By.id("totalCarrinhoHidden")).getAttribute("value"))-10));


        WebElement finalizarCompra = driver.findElement(By.id("finalizarCompra"));
        finalizarCompra.click();

        WebElement clienteDropdown = driver.findElement(By.id("clienteDropdown"));
        clienteDropdown.click();

        WebElement meusPedidos = driver.findElement(By.id("meusPedidos"));
        meusPedidos.click();

        WebElement primeiroExpandir = driver.findElement(By.xpath("//*[contains(@id, 'expandir')]"));
        primeiroExpandir.click();

        WebElement adminDropdown2 = driver.findElement(By.id("adminDropdown"));
        adminDropdown2.click();
        WebElement consultarCliente2 = driver.findElement(By.id("consultarCliente"));
        consultarCliente2.click();
        driver.findElement(By.id("filtro")).sendKeys("Brasil");
        WebElement buscarClientes2 = driver.findElement(By.id("btnConsultar"));
        buscarClientes2.click();
        WebElement alterar2 = driver.findElement(By.id("alterar_" + cliente.getId()));
        alterar2.click();
        WebElement consultarCuponsBtn2 = driver.findElement(By.id("consultarCuponsBtn"));
        consultarCuponsBtn2.click();
        WebElement botaoVoltar9 = driver.findElement(By.id("botaoVoltar"));
        botaoVoltar9.click();
        WebElement transacoesBtn = driver.findElement(By.id("transacoesBtn"));
        transacoesBtn.click();
        WebElement primeiroExpandir2 = driver.findElement(By.xpath("//*[contains(@id, 'expandir')]"));
        primeiroExpandir2.click();
        WebElement proximoStatus = driver.findElement(By.xpath("//*[contains(@id, 'proxStatusBtn_')]"));
        proximoStatus.click();
        WebElement botaoVoltar10 = driver.findElement(By.id("botaoVoltar"));
        botaoVoltar10.click();
        WebElement proximoStatus2 = driver.findElement(By.xpath("//*[contains(@id, 'proxStatusBtn_')]"));
        proximoStatus2.click();
        WebElement botaoVoltar11 = driver.findElement(By.id("botaoVoltar"));
        botaoVoltar11.click();
        WebElement clienteDropdown2 = driver.findElement(By.id("clienteDropdown"));
        clienteDropdown2.click();

        WebElement meusPedidos2 = driver.findElement(By.id("meusPedidos"));
        meusPedidos2.click();

        WebElement realizarTroca = driver.findElement(By.xpath("//*[contains(@id, 'realizarTroca_')]"));
        realizarTroca.click();

        WebElement selecionarItem = driver.findElement(By.xpath("//*[contains(@id, 'item_')]"));
        selecionarItem.click();

        WebElement trocaParcialBtn = driver.findElement(By.id("trocaParcialBtn"));
        trocaParcialBtn.click();

        WebElement adminDropdown3 = driver.findElement(By.id("adminDropdown"));
        adminDropdown3.click();

        WebElement pedidosdeTroca = driver.findElement(By.id("pedidosdeTroca"));
        pedidosdeTroca.click();

//        WebElement reporSim = driver.findElement(By.xpath("//*[contains(@id, 'reporSimBtn_')]"));
//        reporSim.click();

//        WebElement clienteDropdown3 = driver.findElement(By.id("clienteDropdown"));
 //       clienteDropdown3.click();

 //       WebElement meusPedidos3 = driver.findElement(By.id("meusPedidos"));
 //       meusPedidos3.click();












    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
