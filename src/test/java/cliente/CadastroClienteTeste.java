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
    //RF0026 - CADASTRO DE ENDERECOS DE ENTREGA
    //RF0027 - CADASTRO DE CARTOES DE CREDITO
    //RNF0031 - SENHA FORTE
    //RNF0032 - CONFIRMACAO DE SENHA
    //RNF0033 - SENHA CRIPTOGRAFADA
    //RNF0035 - CODIGO DE CLIENTE
    //RN0021 - CADASTRO DE ENDERECO DE COBRANCA
    //RN0022 - CADASTRO DE ENDERECO DE ENTREGA
    //RN0023 - COMPOSICAO DO REGISTRO DE ENDERECOS
    //RN0025 - BANDEIRAS PERMITIDAS PARA REGISTRO DE CARTOES DE CREDITO
    //RN0026 - DADOS OBRIGATORIOS PARA O CADASTRO DE UM CLIENTE


    private WebDriver driver;
    private Cliente cliente;

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
    public void testePreenchimentoFormulario() throws InterruptedException {
        cliente = Factory.ClienteTeste();
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
        submitButton.click();}

    @After
    public void tearDown() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement mensagemElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mensagem")));
        WebElement clienteIdElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("clienteId")));

        System.out.println(mensagemElement.getAttribute("value"));

        if (!clienteIdElement.getAttribute("value").isBlank()) {
            System.out.println("ID do cliente: " + clienteIdElement.getAttribute("value"));
        }
        Cliente clienteTesteCadastrado = ClienteDAO.buscarClientePorId(Integer.valueOf(clienteIdElement.getAttribute("value")));

        System.out.println("Senha digitada: " + cliente.getSenha());
        System.out.println("Senha criptografada: " + clienteTesteCadastrado.getSenha());
        driver.quit();
    }
}
