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
import utils.Factory;
import utils.SlowdownListener;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;

public class ConsultarClienteTeste {
    //ANOTACOES REFERENTE AO TESTE
    //ATENDE AO SEGUINTE REQUISITO:
    //RF0024 - CONSULTA DE CLIENTES

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
    }

    @After
    public void tearDown() {
// Esperar que os resultados sejam carregados
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".row.d-flex.align-items-center")));

        // Coletar e imprimir os nomes e status
        List<WebElement> rows = driver.findElements(By.cssSelector(".row.d-flex.align-items-center"));

        for (WebElement row : rows) {
            String nome = row.findElement(By.cssSelector(".col-md-7")).getText().trim();
            WebElement statusBadge = row.findElement(By.cssSelector(".col-md-1 .badge"));
            String status = statusBadge != null ? statusBadge.getText().trim() : "";

            System.out.println("Nome: " + nome + ", Status: " + status);
        }

        driver.quit();
    }
}
