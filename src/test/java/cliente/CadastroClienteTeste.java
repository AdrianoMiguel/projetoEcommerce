package cliente;

import dominio.cliente.Cliente;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import persistencia.ClienteDAO;
import utils.Factory;
import utils.SlowdownListener;

import java.text.SimpleDateFormat;
import java.time.Duration;


public class CadastroClienteTeste {
//ANOTACOES REFERENTE AO TESTE
    //ATENDE AS SEGUINTES REGRAS E REQUISITOS:
    //RF0021 - CADASTRAR CLIENTE
    //RF0022 - ALTERAR CLIENTE
    //RF0023 - INATIVAR CADASTRO DE CLIENTE
    //RF0024 - CONSULTA DE CLIENTES
    //RF0026 - CADASTRO DE ENDERECOS DE ENTREGA
    //RF0027 - CADASTRO DE CARTOES DE CREDITO
    //RF0028 - ALTERACAO APENAS DE SENHA

    //RNF0012 - LOG DE TRANSACAO
    //RNF0031 - SENHA FORTE
    //RNF0032 - CONFIRMACAO DE SENHA
    //RNF0033 - SENHA CRIPTOGRAFADA
    //RNF0034 - ALTERACAO APENAS DE ENDERECOS
    //RNF0035 - CODIGO DE CLIENTE

    //RN0021 - CADASTRO DE ENDERECO DE COBRANCA
    //RN0022 - CADASTRO DE ENDERECO DE ENTREGA
    //RN0023 - COMPOSICAO DO REGISTRO DE ENDERECOS
    //RN0024 - COMPOSICAO DO REGISTRO DE CARTOES DE CREDITO
    //RN0025 - BANDEIRAS PERMITIDAS PARA REGISTRO DE CARTOES DE CREDITO
    //RN0026 - DADOS OBRIGATORIOS PARA O CADASTRO DE UM CLIENTE


    private WebDriver driver;
    private Cliente cliente;
    private Cliente cliente2;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver baseDriver = new ChromeDriver();
        SlowdownListener listener = new SlowdownListener(1000);
        driver = new EventFiringDecorator(listener).decorate(baseDriver);
        //driver = new ChromeDriver();
        driver.get("http://localhost:8080/EcommerceVinhoVerso_war/CtrlClienteNovo");
    }

    @Test
    public void testePreenchimentoFormulario() throws Exception {
        cliente = Factory.ClienteTeste();
        cliente2 = Factory.ClienteTeste2();

        Actions actions = new Actions(driver);

        driver.findElement(By.id("nome")).sendKeys(cliente.getNome());
        Thread.sleep(300);
        driver.findElement(By.id("cpf")).sendKeys(cliente.getCpf());
        Thread.sleep(300);
        WebElement generoSelect = driver.findElement(By.id("genero"));
        Select genero = new Select(generoSelect);
        genero.selectByVisibleText(cliente.getGenero().toString());
        Thread.sleep(300);
        driver.findElement(By.id("data-nascimento")).sendKeys(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento()));
        Thread.sleep(300);
        Select tiporesidRes = new Select(driver.findElement(By.id("tiporesidRes")));
        Thread.sleep(300);
        tiporesidRes.selectByVisibleText(cliente.getEndResid().getTipoResid().toString());
        Thread.sleep(300);
        Select tipologradRes = new Select(driver.findElement(By.id("tipologradRes")));
        Thread.sleep(300);
        tipologradRes.selectByVisibleText(cliente.getEndResid().getTipoLograd().toString());
        Thread.sleep(300);
        driver.findElement(By.id("endResLograd")).sendKeys(cliente.getEndResid().getLogradouro());
        Thread.sleep(300);
        driver.findElement(By.id("endResNum")).sendKeys(cliente.getEndResid().getNumero().toString());
        Thread.sleep(300);
        driver.findElement(By.id("endResBairro")).sendKeys(cliente.getEndResid().getBairro().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endResCidade")).sendKeys(cliente.getEndResid().getBairro().getCidade().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endResEst")).sendKeys(cliente.getEndResid().getBairro().getCidade().getEstado().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endResCep")).sendKeys(cliente.getEndResid().getCep());
        Thread.sleep(300);
        driver.findElement(By.id("endResPais")).sendKeys(cliente.getEndResid().getBairro().getCidade().getEstado().getPais().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endResObs")).sendKeys(cliente.getEndResid().getObs());
        Thread.sleep(2000);


        // Preencher Endereço de Cobrança
        Select tiporesidCob = new Select(driver.findElement(By.id("tiporesidCob")));
        Thread.sleep(300);
        tiporesidCob.selectByVisibleText(cliente.getEndCob().getTipoResid().toString());
        Thread.sleep(300);
        Select tipologradCob = new Select(driver.findElement(By.id("tipologradCob")));
        Thread.sleep(300);
        tipologradCob.selectByVisibleText(cliente.getEndCob().getTipoLograd().toString());
        Thread.sleep(300);
        driver.findElement(By.id("endCobLograd")).sendKeys(cliente.getEndCob().getLogradouro());
        Thread.sleep(300);
        driver.findElement(By.id("endCobNum")).sendKeys(cliente.getEndCob().getNumero().toString());
        Thread.sleep(300);
        driver.findElement(By.id("endCobBairro")).sendKeys(cliente.getEndCob().getBairro().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endCobCidade")).sendKeys(cliente.getEndCob().getBairro().getCidade().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endCobEst")).sendKeys(cliente.getEndCob().getBairro().getCidade().getEstado().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endCobCep")).sendKeys(cliente.getEndCob().getCep());
        Thread.sleep(300);
            driver.findElement(By.id("endCobPais")).sendKeys(cliente.getEndCob().getBairro().getCidade().getEstado().getPais().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endCobObs")).sendKeys(cliente.getEndCob().getObs());
        Thread.sleep(2000);



        // Preencher Endereço de Entrega
        actions.moveToElement(driver.findElement(By.id("expandir1"))).perform();
        Thread.sleep(2000);
        driver.findElement(By.id("expandir1")).click();
        driver.findElement(By.id("endEntNome1")).sendKeys(cliente.getEndEnt().get(0).getNome());
        Thread.sleep(300);
        Select tiporesidEnt1 = new Select(driver.findElement(By.id("tiporesidEnt1")));
        tiporesidEnt1.selectByVisibleText(cliente.getEndEnt().get(0).getTipoResid().toString());
        Thread.sleep(300);
        Select tipologradEnt1 = new Select(driver.findElement(By.id("tipologradEnt1")));
        tipologradEnt1.selectByVisibleText(cliente.getEndEnt().get(0).getTipoLograd().toString());
        Thread.sleep(300);
        driver.findElement(By.id("endEntLograd1")).sendKeys(cliente.getEndEnt().get(0).getLogradouro());
        Thread.sleep(300);
        driver.findElement(By.id("endEntNum1")).sendKeys(cliente.getEndEnt().get(0).getNumero().toString());
        Thread.sleep(300);
        driver.findElement(By.id("endEntBairro1")).sendKeys(cliente.getEndEnt().get(0).getBairro().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endEntCidade1")).sendKeys(cliente.getEndEnt().get(0).getBairro().getCidade().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endEntEst1")).sendKeys(cliente.getEndEnt().get(0).getBairro().getCidade().getEstado().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endEntCep1")).sendKeys(cliente.getEndEnt().get(0).getCep());
        Thread.sleep(300);
        driver.findElement(By.id("endEntPais1")).sendKeys(cliente.getEndEnt().get(0).getBairro().getCidade().getEstado().getPais().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endEntObs1")).sendKeys(cliente.getEndEnt().get(0).getObs());
        Thread.sleep(2000);


        // Preencher Endereço de Entrega 2
        actions.moveToElement(driver.findElement(By.id("addEnd"))).perform();
        driver.findElement(By.id("addEnd")).click();
        actions.moveToElement(driver.findElement(By.id("expandir2"))).perform();
        Thread.sleep(2000);
        driver.findElement(By.id("expandir2")).click();
        driver.findElement(By.id("endEntNome2")).sendKeys(cliente.getEndEnt().get(1).getNome());
        Thread.sleep(300);
        Select tiporesidEnt2 = new Select(driver.findElement(By.id("tiporesidEnt2")));
        tiporesidEnt2.selectByVisibleText(cliente.getEndEnt().get(1).getTipoResid().toString());
        Thread.sleep(300);
        Select tipologradEnt2 = new Select(driver.findElement(By.id("tipologradEnt2")));
        tipologradEnt2.selectByVisibleText(cliente.getEndEnt().get(1).getTipoLograd().toString());
        Thread.sleep(300);
        driver.findElement(By.id("endEntLograd2")).sendKeys(cliente.getEndEnt().get(1).getLogradouro());
        Thread.sleep(300);
        driver.findElement(By.id("endEntNum2")).sendKeys(cliente.getEndEnt().get(1).getNumero().toString());
        Thread.sleep(300);
        driver.findElement(By.id("endEntBairro2")).sendKeys(cliente.getEndEnt().get(1).getBairro().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endEntCidade2")).sendKeys(cliente.getEndEnt().get(1).getBairro().getCidade().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endEntEst2")).sendKeys(cliente.getEndEnt().get(1).getBairro().getCidade().getEstado().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endEntCep2")).sendKeys(cliente.getEndEnt().get(1).getCep());
        Thread.sleep(300);
        driver.findElement(By.id("endEntPais2")).sendKeys(cliente.getEndEnt().get(1).getBairro().getCidade().getEstado().getPais().getNome());
        Thread.sleep(300);
        driver.findElement(By.id("endEntObs2")).sendKeys(cliente.getEndEnt().get(1).getObs());
        Thread.sleep(2000);


        // Preencher contato
        driver.findElement(By.id("email")).sendKeys(cliente.getContato().getEmail());
        Thread.sleep(300);
        Select tipotel = new Select(driver.findElement(By.id("tipotel")));
        Thread.sleep(300);
        tipotel.selectByVisibleText(cliente.getContato().getTelefone().getTipo().toString());
        Thread.sleep(300);
        driver.findElement(By.id("ddd")).sendKeys(cliente.getContato().getTelefone().getDdd().toString());
        Thread.sleep(300);
        driver.findElement(By.id("numerotel")).sendKeys(cliente.getContato().getTelefone().getNumero().toString());
        Thread.sleep(2000);


        // Preencher dados do cartão
        driver.findElement(By.id("cartaoNome1")).sendKeys(cliente.getCartoes().get(0).getNome());
        Thread.sleep(300);
        Select bandeira1 = new Select(driver.findElement(By.id("bandeira1")));
        bandeira1.selectByVisibleText(cliente.getCartoes().get(0).getBandeira().toString().replace("_", " "));
        Thread.sleep(300);
        driver.findElement(By.id("cartaoNum1")).sendKeys(cliente.getCartoes().get(0).getNumero());
        Thread.sleep(300);
        driver.findElement(By.id("cartaoCodSeg1")).sendKeys(cliente.getCartoes().get(0).getCod().toString());
        Thread.sleep(2000);


        // Preencher dados do cartão 2
        actions.moveToElement(driver.findElement(By.id("addCartao"))).perform();
        Thread.sleep(2000);
        driver.findElement(By.id("addCartao")).click();
        driver.findElement(By.id("cartaoNome2")).sendKeys(cliente.getCartoes().get(1).getNome());
        Thread.sleep(300);
        Select bandeira2 = new Select(driver.findElement(By.id("bandeira2")));
        bandeira2.selectByVisibleText(cliente.getCartoes().get(1).getBandeira().toString().replace("_", " "));
        Thread.sleep(300);
        driver.findElement(By.id("cartaoNum2")).sendKeys(cliente.getCartoes().get(1).getNumero());
        Thread.sleep(300);
        driver.findElement(By.id("cartaoCodSeg2")).sendKeys(cliente.getCartoes().get(1).getCod().toString());
        Thread.sleep(2000);


        // Preencher senha
        driver.findElement(By.id("senha")).sendKeys(cliente.getSenha());
        Thread.sleep(300);
        driver.findElement(By.id("confirmasenha")).sendKeys(cliente.getSenha());
        //driver.findElement(By.id("senha")).sendKeys("teste");
        //driver.findElement(By.id("confirmasenha")).sendKeys("teste");

        // Submeter o formulário
        WebElement submitButton = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton).perform();
        Thread.sleep(2000);
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clienteIdElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("clienteId")));
        cliente.setId(Integer.valueOf(clienteIdElement.getAttribute("value")));

        WebElement adminDropdown = driver.findElement(By.id("adminDropdown"));
        actions.moveToElement(adminDropdown).perform();
        adminDropdown.click();
        WebElement consultarCliente = driver.findElement(By.id("consultarCliente"));
        actions.moveToElement(consultarCliente).perform();
        consultarCliente.click();
        driver.findElement(By.id("filtro")).sendKeys(cliente.getNome());
        WebElement buscarClientes = driver.findElement(By.id("btnConsultar"));
        actions.moveToElement(buscarClientes).perform();
        buscarClientes.click();
        WebElement mudarStatus = driver.findElement(By.id("mudarStatus_" + cliente.getId()));
        actions.moveToElement(mudarStatus).perform();
        Thread.sleep(2000);
        mudarStatus.click();

        WebElement adminDropdown2 = driver.findElement(By.id("adminDropdown"));
        actions.moveToElement(adminDropdown2).perform();
        adminDropdown2.click();
        WebElement consultarCliente2 = driver.findElement(By.id("consultarCliente"));
        actions.moveToElement(consultarCliente2).perform();
        consultarCliente2.click();
        driver.findElement(By.id("filtro")).sendKeys(cliente.getEndResid().getLogradouro() + " " + cliente.getEndResid().getBairro().getCidade().getNome());
        WebElement buscarClientes2 = driver.findElement(By.id("btnConsultar"));
        actions.moveToElement(buscarClientes2).perform();
        buscarClientes2.click();
        WebElement mudarStatus2 = driver.findElement(By.id("mudarStatus_" + cliente.getId()));
        actions.moveToElement(mudarStatus2).perform();
        Thread.sleep(2000);
        mudarStatus2.click();

        WebElement adminDropdown3 = driver.findElement(By.id("adminDropdown"));
        actions.moveToElement(adminDropdown3).perform();
        adminDropdown3.click();
        WebElement consultarCliente3 = driver.findElement(By.id("consultarCliente"));
        actions.moveToElement(consultarCliente3).perform();
        consultarCliente3.click();
        driver.findElement(By.id("filtro")).sendKeys(cliente.getCpf());
        WebElement buscarClientes3 = driver.findElement(By.id("btnConsultar"));
        actions.moveToElement(buscarClientes3).perform();
        buscarClientes3.click();
        WebElement alterar = driver.findElement(By.id("alterar_" + cliente.getId()));
        actions.moveToElement(alterar).perform();
        Thread.sleep(2000);
        alterar.click();



        WebElement alterarDadosButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Alterar Dados Cadastrais')]")));
        Thread.sleep(2000);
        actions.moveToElement(alterarDadosButton).perform();
        Thread.sleep(2000);
        alterarDadosButton.click();

        driver.findElement(By.id("nome")).clear();
        driver.findElement(By.id("nome")).sendKeys(cliente2.getNome());
        Thread.sleep(250);
        driver.findElement(By.id("cpf")).clear();
        driver.findElement(By.id("cpf")).sendKeys(cliente2.getCpf());
        Thread.sleep(250);
        WebElement generoSelect2 = driver.findElement(By.id("genero"));
        Select genero2 = new Select(generoSelect2);
        genero2.selectByVisibleText(cliente2.getGenero().toString());
        Thread.sleep(250);
        driver.findElement(By.id("data-nascimento")).clear();
        driver.findElement(By.id("data-nascimento")).sendKeys(new SimpleDateFormat("dd/MM/yyyy").format(cliente2.getDataNascimento()));
        Thread.sleep(250);
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(cliente2.getContato().getEmail());
        Thread.sleep(250);
        Select tipotel2 = new Select(driver.findElement(By.id("tipotel")));
        tipotel2.selectByVisibleText(cliente2.getContato().getTelefone().getTipo().toString());
        Thread.sleep(250);
        driver.findElement(By.id("ddd")).clear();
        driver.findElement(By.id("ddd")).sendKeys(cliente2.getContato().getTelefone().getDdd().toString());
        Thread.sleep(250);
        driver.findElement(By.id("numerotel")).clear();
        driver.findElement(By.id("numerotel")).sendKeys(cliente2.getContato().getTelefone().getNumero().toString());
        Thread.sleep(250);
        WebElement salvarAlteracoesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='form-group col-md-8']//button[text()='Salvar Alterações']")));
        actions.moveToElement(salvarAlteracoesButton).perform();
        Thread.sleep(2000);
        salvarAlteracoesButton.click();
        WebElement botaoVoltar = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar).perform();
        Thread.sleep(1000);
        botaoVoltar.click();

        WebElement alterarDadosCadastraisBtn = driver.findElement(By.id("alterarDadosCadastraisBtn"));
        actions.moveToElement(alterarDadosCadastraisBtn).perform();
        Thread.sleep(1000);
        alterarDadosCadastraisBtn.click();

        Thread.sleep(4000);
        WebElement botaoVoltar2 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar2).perform();
        Thread.sleep(1000);
        botaoVoltar2.click();

        WebElement alterarEnderecosBtn = driver.findElement(By.id("alterarEnderecosBtn"));
        actions.moveToElement(alterarEnderecosBtn).perform();
        Thread.sleep(1000);
        alterarEnderecosBtn.click();

        Select tiporesidRes2 = new Select(driver.findElement(By.id("tiporesidRes")));
        tiporesidRes2.selectByVisibleText(cliente2.getEndResid().getTipoResid().toString());
        Thread.sleep(250);
        Select tipologradRes2 = new Select(driver.findElement(By.id("tipologradRes")));
        tipologradRes2.selectByVisibleText(cliente2.getEndResid().getTipoLograd().toString());
        Thread.sleep(250);
        driver.findElement(By.id("endResLograd")).clear();
        driver.findElement(By.id("endResLograd")).sendKeys(cliente2.getEndResid().getLogradouro());
        Thread.sleep(250);
        driver.findElement(By.id("endResNum")).clear();
        driver.findElement(By.id("endResNum")).sendKeys(cliente2.getEndResid().getNumero().toString());
        Thread.sleep(250);
        driver.findElement(By.id("endResBairro")).clear();
        driver.findElement(By.id("endResBairro")).sendKeys(cliente2.getEndResid().getBairro().getNome());
        Thread.sleep(250);
        driver.findElement(By.id("endResCidade")).clear();
        driver.findElement(By.id("endResCidade")).sendKeys(cliente2.getEndResid().getBairro().getCidade().getNome());
        Thread.sleep(250);
        driver.findElement(By.id("endResEst")).clear();
        driver.findElement(By.id("endResEst")).sendKeys(cliente2.getEndResid().getBairro().getCidade().getEstado().getNome());
        Thread.sleep(250);
        driver.findElement(By.id("endResCep")).clear();
        driver.findElement(By.id("endResCep")).sendKeys(cliente2.getEndResid().getCep());
        Thread.sleep(250);
        driver.findElement(By.id("endResPais")).clear();
        driver.findElement(By.id("endResPais")).sendKeys(cliente2.getEndResid().getBairro().getCidade().getEstado().getPais().getNome());
        Thread.sleep(250);
        driver.findElement(By.id("endResObs")).clear();
        driver.findElement(By.id("endResObs")).sendKeys(cliente2.getEndResid().getObs());
        Thread.sleep(3000);

        // Preencher Endereço de Cobrança
        Select tiporesidCob2 = new Select(driver.findElement(By.id("tiporesidCob")));
        tiporesidCob2.selectByVisibleText(cliente2.getEndCob().getTipoResid().toString());
        Thread.sleep(250);
        Select tipologradCob2 = new Select(driver.findElement(By.id("tipologradCob")));
        tipologradCob2.selectByVisibleText(cliente2.getEndCob().getTipoLograd().toString());
        Thread.sleep(250);
        driver.findElement(By.id("endCobLograd")).clear();
        driver.findElement(By.id("endCobLograd")).sendKeys(cliente2.getEndCob().getLogradouro());
        Thread.sleep(250);
        driver.findElement(By.id("endCobNum")).clear();
        driver.findElement(By.id("endCobNum")).sendKeys(cliente2.getEndCob().getNumero().toString());
        Thread.sleep(250);
        driver.findElement(By.id("endCobBairro")).clear();
        driver.findElement(By.id("endCobBairro")).sendKeys(cliente2.getEndCob().getBairro().getNome());
        Thread.sleep(250);
        driver.findElement(By.id("endCobCidade")).clear();
        driver.findElement(By.id("endCobCidade")).sendKeys(cliente2.getEndCob().getBairro().getCidade().getNome());
        Thread.sleep(250);
        driver.findElement(By.id("endCobEst")).clear();
        driver.findElement(By.id("endCobEst")).sendKeys(cliente2.getEndCob().getBairro().getCidade().getEstado().getNome());
        Thread.sleep(250);
        driver.findElement(By.id("endCobCep")).clear();
        driver.findElement(By.id("endCobCep")).sendKeys(cliente2.getEndCob().getCep());
        Thread.sleep(250);
        driver.findElement(By.id("endCobPais")).clear();
        driver.findElement(By.id("endCobPais")).sendKeys(cliente2.getEndCob().getBairro().getCidade().getEstado().getPais().getNome());
        Thread.sleep(250);
        driver.findElement(By.id("endCobObs")).clear();
        driver.findElement(By.id("endCobObs")).sendKeys(cliente2.getEndCob().getObs());
        Thread.sleep(3000);


        // Preencher Endereço de Entrega
        driver.findElement(By.id("expandir1")).click();
        driver.findElement(By.id("endEntNome1")).clear();
        driver.findElement(By.id("endEntNome1")).sendKeys(cliente2.getEndEnt().get(0).getNome());
        Thread.sleep(250);
        Select tiporesidEnt3 = new Select(driver.findElement(By.id("tiporesidEnt1")));
        tiporesidEnt3.selectByVisibleText(cliente2.getEndEnt().get(0).getTipoResid().toString());
        Thread.sleep(250);
        Select tipologradEnt3 = new Select(driver.findElement(By.id("tipologradEnt1")));
        tipologradEnt3.selectByVisibleText(cliente2.getEndEnt().get(0).getTipoLograd().toString());
        Thread.sleep(250);
        driver.findElement(By.id("endEntLograd1")).clear();
        driver.findElement(By.id("endEntLograd1")).sendKeys(cliente2.getEndEnt().get(0).getLogradouro());
        Thread.sleep(250);
        driver.findElement(By.id("endEntNum1")).clear();
        driver.findElement(By.id("endEntNum1")).sendKeys(cliente2.getEndEnt().get(0).getNumero().toString());
        Thread.sleep(250);
        driver.findElement(By.id("endEntBairro1")).clear();
        driver.findElement(By.id("endEntBairro1")).sendKeys(cliente2.getEndEnt().get(0).getBairro().getNome());
        Thread.sleep(250);
        driver.findElement(By.id("endEntCidade1")).clear();
        driver.findElement(By.id("endEntCidade1")).sendKeys(cliente2.getEndEnt().get(0).getBairro().getCidade().getNome());
        Thread.sleep(250);
        driver.findElement(By.id("endEntEst1")).clear();
        driver.findElement(By.id("endEntEst1")).sendKeys(cliente2.getEndEnt().get(0).getBairro().getCidade().getEstado().getNome());
        Thread.sleep(250);
        driver.findElement(By.id("endEntCep1")).clear();
        driver.findElement(By.id("endEntCep1")).sendKeys(cliente2.getEndEnt().get(0).getCep());
        Thread.sleep(250);
        driver.findElement(By.id("endEntPais1")).clear();
        driver.findElement(By.id("endEntPais1")).sendKeys(cliente2.getEndEnt().get(0).getBairro().getCidade().getEstado().getPais().getNome());
        Thread.sleep(250);
        driver.findElement(By.id("endEntObs1")).clear();
        driver.findElement(By.id("endEntObs1")).sendKeys(cliente2.getEndEnt().get(0).getObs());
        Thread.sleep(250);
        WebElement salvarAlteracoesButton2 = driver.findElement(By.id("salvarAlteracoes"));
        actions.moveToElement(salvarAlteracoesButton2).perform();
        Thread.sleep(2000);
        salvarAlteracoesButton2.click();
        WebElement botaoVoltar3 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar3).perform();
        Thread.sleep(1000);
        botaoVoltar3.click();

        WebElement alterarEnderecosBtn2 = driver.findElement(By.id("alterarEnderecosBtn"));
        actions.moveToElement(alterarEnderecosBtn2).perform();
        Thread.sleep(1000);
        alterarEnderecosBtn2.click();

        Thread.sleep(4000);
        WebElement botaoVoltar4 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar4).perform();
        Thread.sleep(1000);
        botaoVoltar4.click();

        WebElement alterarCartoesBtn = driver.findElement(By.id("alterarCartoesBtn"));
        actions.moveToElement(alterarCartoesBtn).perform();
        Thread.sleep(1000);
        alterarCartoesBtn.click();

        driver.findElement(By.id("cartaoNome1")).clear();
        driver.findElement(By.id("cartaoNome1")).sendKeys(cliente2.getCartoes().get(0).getNome());
        Thread.sleep(200);
        Select bandeira3 = new Select(driver.findElement(By.id("bandeira1")));
        bandeira3.selectByVisibleText(cliente2.getCartoes().get(0).getBandeira().toString().replace("_", " "));
        driver.findElement(By.id("cartaoNum1")).clear();
        driver.findElement(By.id("cartaoNum1")).sendKeys(cliente2.getCartoes().get(0).getNumero());
        Thread.sleep(250);
        driver.findElement(By.id("cartaoCodSeg1")).clear();
        driver.findElement(By.id("cartaoCodSeg1")).sendKeys(cliente2.getCartoes().get(0).getCod().toString());
        Thread.sleep(1000);
        WebElement salvarAlteracoesButton3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='form-group col-md-8']//button[text()='Salvar Alteracoes']")));
        actions.moveToElement(salvarAlteracoesButton3).perform();
        Thread.sleep(2000);
        salvarAlteracoesButton3.click();
        WebElement botaoVoltar5 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar5).perform();
        Thread.sleep(1000);
        botaoVoltar5.click();

        WebElement alterarCartoesBtn2 = driver.findElement(By.id("alterarCartoesBtn"));
        actions.moveToElement(alterarCartoesBtn2).perform();
        Thread.sleep(1000);
        alterarCartoesBtn2.click();

        Thread.sleep(4000);
        WebElement botaoVoltar6 =driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar6).perform();
        Thread.sleep(1000);
        botaoVoltar6.click();

        WebElement alterarSenhaBtn = driver.findElement(By.id("alterarSenhaBtn"));
        actions.moveToElement(alterarSenhaBtn).perform();
        Thread.sleep(1000);
        alterarSenhaBtn.click();

        driver.findElement(By.id("senha")).sendKeys(cliente2.getSenha());
        driver.findElement(By.id("confirmaSenha")).sendKeys(cliente2.getSenha());
        WebElement salvarAlteracoesButton4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='form-group col-md-8']//button[text()='Salvar Alterações']")));
        actions.moveToElement(salvarAlteracoesButton4).perform();
        Thread.sleep(2000);
        salvarAlteracoesButton4.click();
        WebElement botaoVoltar7 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar7).perform();
        Thread.sleep(1000);
        botaoVoltar7.click();

        WebElement consultarLogsBtn = driver.findElement(By.id("consultarLogsBtn"));
        actions.moveToElement(consultarLogsBtn).perform();
        Thread.sleep(1000);
        consultarLogsBtn.click();
        Thread.sleep(7000);

        Cliente clienteTesteCadastrado = ClienteDAO.buscarClientePorId(cliente.getId());

        System.out.println("ID do cliente: " + cliente.getId());
        System.out.println("Nome do cliente: " + cliente2.getNome());
        System.out.println("Senha digitada: " + cliente2.getSenha());
        System.out.println("Senha criptografada: " + clienteTesteCadastrado.getSenha());

        WebElement adminDropdown4 = driver.findElement(By.id("adminDropdown"));
        actions.moveToElement(adminDropdown4).perform();
        adminDropdown4.click();
        WebElement consultarCliente4 = driver.findElement(By.id("consultarCliente"));
        actions.moveToElement(consultarCliente4).perform();
        consultarCliente4.click();
        driver.findElement(By.id("filtro")).sendKeys(cliente2.getNome());
        WebElement buscarClientes4 = driver.findElement(By.id("btnConsultar"));
        actions.moveToElement(buscarClientes4).perform();
        buscarClientes4.click();
        WebElement excluir = driver.findElement(By.id("excluir_" + cliente.getId()));
        actions.moveToElement(excluir).perform();
        Thread.sleep(2000);
        excluir.click();

        WebElement adminDropdown5 = driver.findElement(By.id("adminDropdown"));
        actions.moveToElement(adminDropdown5).perform();
        adminDropdown5.click();
        WebElement consultarCliente5 = driver.findElement(By.id("consultarCliente"));
        actions.moveToElement(consultarCliente5).perform();
        consultarCliente5.click();
        driver.findElement(By.id("filtro")).sendKeys(cliente2.getNome());
        WebElement buscarClientes5 = driver.findElement(By.id("btnConsultar"));
        actions.moveToElement(buscarClientes5).perform();
        buscarClientes5.click();

        Thread.sleep(1000);




    }

    @After
    public void tearDown() throws Exception {

        driver.quit();
    }
}
