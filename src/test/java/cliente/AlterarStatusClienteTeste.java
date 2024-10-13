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

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;

public class AlterarStatusClienteTeste {
    //ANOTACOES REFERENTE AO TESTE
    //ATENDE AO SEGUINTE REQUISITO:
    //RF0023 - INATIVAR CADASTRO DE CLIENTE
    private Cliente clienteAlterado;
    private WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver baseDriver = new ChromeDriver();
        SlowdownListener listener = new SlowdownListener(1000);
        driver = new EventFiringDecorator(listener).decorate(baseDriver);
        //driver = new ChromeDriver();
        driver.get("http://localhost:8080/EcommerceVinhoVerso_war/ConsultaCliente");
    }

    @Test
    public void testePreenchimentoFormulario() {

        driver.findElement(By.id("filtro")).sendKeys("brasil");
        driver.findElement(By.id("btnConsultar")).click();
        WebElement form = driver.findElement(By.xpath("//input[@name='id' and @value='33']/ancestor::form"));
        // Localizar e clicar no botão submit dentro do formulário encontrado
        WebElement submitButton = form.findElement(By.xpath(".//button[@type='submit']"));
        submitButton.click();
    }

    @After
    public void tearDown() throws Exception {
// Esperar que os resultados sejam carregados

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement mensagemElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mensagem")));
        WebElement clienteId = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("clienteId")));
        clienteAlterado = ClienteDAO.buscarClientePorId(Integer.valueOf(clienteId.getAttribute("value")));

        System.out.println(mensagemElement.getAttribute("value"));

        System.out.println("ID: " + clienteAlterado.getId());
        System.out.println("Nome: " + clienteAlterado.getNome());
        System.out.println("Status alterado para: " + clienteAlterado.getStatus());

        driver.quit();
    }
}
