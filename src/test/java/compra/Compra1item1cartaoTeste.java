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

public class Compra1item1cartaoTeste {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver baseDriver = new ChromeDriver();
        SlowdownListener listener = new SlowdownListener(700);
        driver = new EventFiringDecorator(listener).decorate(baseDriver);
        driver.get("http://localhost:8080/EcommerceVinhoVerso_war/CtrlProdutoListar");
    }

    @Test
    public void testePreenchimentoFormulario() {
        FakerModificado faker = new FakerModificado();
        WebElement login = driver.findElement(By.id("login"));
        login.click();
        WebElement generoSelect = driver.findElement(By.id("idClienteLogin"));
        Select cliente = new Select(generoSelect);
        try {
            cliente.selectByVisibleText(faker.cliente());
        } catch (Exception e) {
            System.out.println("Não foi possível prosseguir com o teste: " + e.getMessage());
        }
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
        WebElement botaoVoltar = driver.findElement(By.id("botaoVoltar"));
        botaoVoltar.click();
        WebElement meuCarrinho = driver.findElement(By.id("meuCarrinho"));
        meuCarrinho.click();
        WebElement calcularFrete = driver.findElement(By.id("calcularFreteBtn"));
        calcularFrete.click();
        driver.findElement(By.id("valor_cartao_0")).sendKeys(driver.findElement(By.id("totalCarrinhoHidden")).getAttribute("value"));
        WebElement finalizarCompra = driver.findElement(By.id("finalizarCompra"));
        finalizarCompra.click();

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
