package cliente;
import dominio.cliente.Cliente;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import persistencia.ClienteDAO;
import utils.Factory;
import utils.SlowdownListener;

import java.time.Duration;

public class AlterarSenhaClienteTeste {
    //ANOTACOES REFERENTE AO TESTE
    //ATENDE AS SEGUINTES REGRAS E REQUISITOS:
    //RF0028 - ALTERACAO APENAS DE SENHA
    private Cliente cliente;
    private Cliente clienteAntesDeAlterar;
    private Cliente clienteAlterado;
    private WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver baseDriver = new ChromeDriver();
        SlowdownListener listener = new SlowdownListener(1000);
        driver = new EventFiringDecorator(listener).decorate(baseDriver);
        //driver = new ChromeDriver();
        driver.get("http://localhost:8080/EcommerceVinhoVerso_war/CtrlClienteEncaminharID?id=33");
    }

    @Test
    public void testePreenchimentoFormulario() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alterarDadosButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Alterar Senha')]")));
        alterarDadosButton.click();
        WebElement clienteIdElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id")));
        clienteAntesDeAlterar = ClienteDAO.buscarClientePorId(Integer.valueOf(clienteIdElement.getAttribute("value")));

        cliente = Factory.ClienteTeste();
        driver.findElement(By.id("senha")).sendKeys(cliente.getSenha());
        driver.findElement(By.id("confirmaSenha")).sendKeys(cliente.getSenha());
        WebElement salvarAlteracoesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='form-group col-md-8']//button[text()='Salvar Alterações']")));
        salvarAlteracoesButton.click();
    }
    @After
    public void tearDown() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement mensagemElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mensagem")));
        WebElement clienteId = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("clienteId")));

        clienteAlterado = ClienteDAO.buscarClientePorId(Integer.valueOf(clienteId.getAttribute("value")));

        System.out.println(mensagemElement.getAttribute("value"));
        System.out.println("Nova senha:" + cliente.getSenha());

        driver.quit();
    }
}

