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
import java.time.Duration;

public class AlterarCartoesClienteTeste {
    //ANOTACOES REFERENTE AO TESTE
    //ATENDE AS SEGUINTES REGRAS E REQUISITOS:
    //RF0022 - ALTERAR CLIENTE
    private Cliente cliente;
    private Cliente clienteAntesDeAlterar;
    private Cliente clienteAlterado;
    private WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/EcommerceVinhoVerso_war/CtrlClienteEncaminharID?id=33");
    }

    @Test
    public void testePreenchimentoFormulario() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alterarDadosButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Alterar cartoes')]")));
        alterarDadosButton.click();
        WebElement clienteIdElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id")));
        clienteAntesDeAlterar = ClienteDAO.buscarClientePorId(Integer.valueOf(clienteIdElement.getAttribute("value")));

        cliente = Factory.ClienteTeste();
        driver.findElement(By.id("cartaoNome1")).clear();
        driver.findElement(By.id("cartaoNome1")).sendKeys(cliente.getCartoes().get(0).getNome());
        Select bandeira1 = new Select(driver.findElement(By.id("bandeira1")));
        bandeira1.selectByVisibleText(cliente.getCartoes().get(0).getBandeira().toString().replace("_", " "));
        driver.findElement(By.id("cartaoNum1")).clear();
        driver.findElement(By.id("cartaoNum1")).sendKeys(cliente.getCartoes().get(0).getNumero());
        driver.findElement(By.id("cartaoCodSeg1")).clear();
        driver.findElement(By.id("cartaoCodSeg1")).sendKeys(cliente.getCartoes().get(0).getCod().toString());
        WebElement salvarAlteracoesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='form-group col-md-8']//button[text()='Salvar Alteracoes']")));
        salvarAlteracoesButton.click();
    }
    @After
    public void tearDown() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement mensagemElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mensagem")));
        WebElement clienteId = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("clienteId")));

        clienteAlterado = ClienteDAO.buscarClientePorId(Integer.valueOf(clienteId.getAttribute("value")));

        System.out.println(mensagemElement.getAttribute("value"));

        System.out.println("ANTES DA ALTERACAO:");
        System.out.println("NOME IMPRESSO NO CARTAO: " + clienteAntesDeAlterar.getCartoes().get(0).getNome());
        System.out.println("BANDEIRA: " + clienteAntesDeAlterar.getCartoes().get(0).getBandeira());
        System.out.println("NUMERO DO CARTAO: " + clienteAntesDeAlterar.getCartoes().get(0).getNumero());
        System.out.println("CODIGO DE SEGURANCA: " + clienteAntesDeAlterar.getCartoes().get(0).getCod());

        System.out.println("DEPOIS DA ALTERACAO:");
        System.out.println("NOME IMPRESSO NO CARTAO: " + clienteAlterado.getCartoes().get(0).getNome());
        System.out.println("BANDEIRA: " + clienteAlterado.getCartoes().get(0).getBandeira());
        System.out.println("NUMERO DO CARTAO: " + clienteAlterado.getCartoes().get(0).getNumero());
        System.out.println("CODIGO DE SEGURANCA: " + clienteAlterado.getCartoes().get(0).getCod());

        driver.quit();
    }
}

