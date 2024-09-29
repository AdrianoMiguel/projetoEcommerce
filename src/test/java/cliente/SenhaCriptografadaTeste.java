package cliente;
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
import persistencia.ClienteDAO;
import utils.Factory;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class SenhaCriptografadaTeste {
    //ANOTACOES REFERENTE AO TESTE
    //ATENDE A SEGUINTE REGRA DE NEGOCIO:
    //RNF0033 - SENHA CRIPTOGRAFADA

    private WebDriver driver;
    private Cliente cliente;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/EcommerceVinhoVerso_war/CtrlClienteNovo");
    }

    @Test
    public void testePreenchimentoFormulario() {
        cliente = Factory.ClienteTeste();
        driver.findElement(By.id("nome")).sendKeys(cliente.getNome());
        driver.findElement(By.id("cpf")).sendKeys(cliente.getCpf());
        WebElement generoSelect = driver.findElement(By.id("genero"));
        Select genero = new Select(generoSelect);
        genero.selectByVisibleText(cliente.getGenero().toString());
        driver.findElement(By.id("data-nascimento")).sendKeys(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento()));
        Select tiporesidRes = new Select(driver.findElement(By.id("tiporesidRes")));
        tiporesidRes.selectByVisibleText(cliente.getEndResid().getTipoResid().toString());
        Select tipologradRes = new Select(driver.findElement(By.id("tipologradRes")));
        tipologradRes.selectByVisibleText(cliente.getEndResid().getTipoLograd().toString());
        driver.findElement(By.id("endResLograd")).sendKeys(cliente.getEndResid().getLogradouro());
        driver.findElement(By.id("endResNum")).sendKeys(cliente.getEndResid().getNumero().toString());
        driver.findElement(By.id("endResBairro")).sendKeys(cliente.getEndResid().getBairro().getNome());
        driver.findElement(By.id("endResCidade")).sendKeys(cliente.getEndResid().getBairro().getCidade().getNome());
        driver.findElement(By.id("endResEst")).sendKeys(cliente.getEndResid().getBairro().getCidade().getEstado().getNome());
        driver.findElement(By.id("endResCep")).sendKeys(cliente.getEndResid().getCep());
        driver.findElement(By.id("endResPais")).sendKeys(cliente.getEndResid().getBairro().getCidade().getEstado().getPais().getNome());
        driver.findElement(By.id("endResObs")).sendKeys(cliente.getEndResid().getObs());

        // Preencher Endereço de Cobrança
        Select tiporesidCob = new Select(driver.findElement(By.id("tiporesidCob")));
        tiporesidCob.selectByVisibleText(cliente.getEndCob().getTipoResid().toString());
        Select tipologradCob = new Select(driver.findElement(By.id("tipologradCob")));
        tipologradCob.selectByVisibleText(cliente.getEndCob().getTipoLograd().toString());
        driver.findElement(By.id("endCobLograd")).sendKeys(cliente.getEndCob().getLogradouro());
        driver.findElement(By.id("endCobNum")).sendKeys(cliente.getEndCob().getNumero().toString());
        driver.findElement(By.id("endCobBairro")).sendKeys(cliente.getEndCob().getBairro().getNome());
        driver.findElement(By.id("endCobCidade")).sendKeys(cliente.getEndCob().getBairro().getCidade().getNome());
        driver.findElement(By.id("endCobEst")).sendKeys(cliente.getEndCob().getBairro().getCidade().getEstado().getNome());
        driver.findElement(By.id("endCobCep")).sendKeys(cliente.getEndCob().getCep());
        driver.findElement(By.id("endCobPais")).sendKeys(cliente.getEndCob().getBairro().getCidade().getEstado().getPais().getNome());
        driver.findElement(By.id("endCobObs")).sendKeys(cliente.getEndCob().getObs());


        // Preencher Endereço de Entrega
        driver.findElement(By.id("expandir1")).click();
        driver.findElement(By.id("endEntNome1")).sendKeys(cliente.getEndEnt().get(0).getNome());
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

        // Preencher Endereço de Entrega 2
        driver.findElement(By.id("addEnd")).click();
        driver.findElement(By.id("expandir2")).click();
        driver.findElement(By.id("endEntNome2")).sendKeys(cliente.getEndEnt().get(1).getNome());
        Select tiporesidEnt2 = new Select(driver.findElement(By.id("tiporesidEnt2")));
        tiporesidEnt2.selectByVisibleText(cliente.getEndEnt().get(1).getTipoResid().toString());
        Select tipologradEnt2 = new Select(driver.findElement(By.id("tipologradEnt2")));
        tipologradEnt2.selectByVisibleText(cliente.getEndEnt().get(1).getTipoLograd().toString());
        driver.findElement(By.id("endEntLograd2")).sendKeys(cliente.getEndEnt().get(1).getLogradouro());
        driver.findElement(By.id("endEntNum2")).sendKeys(cliente.getEndEnt().get(1).getNumero().toString());
        driver.findElement(By.id("endEntBairro2")).sendKeys(cliente.getEndEnt().get(1).getBairro().getNome());
        driver.findElement(By.id("endEntCidade2")).sendKeys(cliente.getEndEnt().get(1).getBairro().getCidade().getNome());
        driver.findElement(By.id("endEntEst2")).sendKeys(cliente.getEndEnt().get(1).getBairro().getCidade().getEstado().getNome());
        driver.findElement(By.id("endEntCep2")).sendKeys(cliente.getEndEnt().get(1).getCep());
        driver.findElement(By.id("endEntPais2")).sendKeys(cliente.getEndEnt().get(1).getBairro().getCidade().getEstado().getPais().getNome());
        driver.findElement(By.id("endEntObs2")).sendKeys(cliente.getEndEnt().get(1).getObs());

        // Preencher contato
        driver.findElement(By.id("email")).sendKeys(cliente.getContato().getEmail());
        Select tipotel = new Select(driver.findElement(By.id("tipotel")));
        tipotel.selectByVisibleText(cliente.getContato().getTelefone().getTipo().toString());
        driver.findElement(By.id("ddd")).sendKeys(cliente.getContato().getTelefone().getDdd().toString());
        driver.findElement(By.id("numerotel")).sendKeys(cliente.getContato().getTelefone().getNumero().toString());

        // Preencher dados do cartão
        driver.findElement(By.id("cartaoNome1")).sendKeys(cliente.getCartoes().get(0).getNome());
        Select bandeira1 = new Select(driver.findElement(By.id("bandeira1")));
        bandeira1.selectByVisibleText(cliente.getCartoes().get(0).getBandeira().toString());
        driver.findElement(By.id("cartaoNum1")).sendKeys(cliente.getCartoes().get(0).getNumero());
        driver.findElement(By.id("cartaoCodSeg1")).sendKeys(cliente.getCartoes().get(0).getCod().toString());

        // Preencher dados do cartão 2
        driver.findElement(By.id("addCartao")).click();
        driver.findElement(By.id("cartaoNome2")).sendKeys(cliente.getCartoes().get(1).getNome());
        Select bandeira2 = new Select(driver.findElement(By.id("bandeira2")));
        bandeira2.selectByVisibleText(cliente.getCartoes().get(1).getBandeira().toString());
        driver.findElement(By.id("cartaoNum2")).sendKeys(cliente.getCartoes().get(1).getNumero());
        driver.findElement(By.id("cartaoCodSeg2")).sendKeys(cliente.getCartoes().get(1).getCod().toString());

        // Preencher senha
        driver.findElement(By.id("senha")).sendKeys(cliente.getSenha());
        driver.findElement(By.id("confirmasenha")).sendKeys(cliente.getSenha());

        // Submeter o formulário
        WebElement submitButton = driver.findElement(By.id("cadastrar"));
        submitButton.click();}

    @After
    public void tearDown() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clienteIdElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("clienteId")));

        Cliente clienteTesteCadastrado = ClienteDAO.buscarClientePorId(Integer.valueOf(clienteIdElement.getAttribute("value")));
        System.out.println("Senha digitada: " + cliente.getSenha());
        System.out.println("Senha criptografada: " + clienteTesteCadastrado.getSenha());
        driver.quit();
    }
}


