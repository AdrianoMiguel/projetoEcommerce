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

import java.text.SimpleDateFormat;
import java.time.Duration;

public class AlterarDadosCadastraisClienteTeste {
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
        WebElement alterarDadosButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Alterar Dados Cadastrais')]")));
        alterarDadosButton.click();
        WebElement clienteIdElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id")));
        clienteAntesDeAlterar = ClienteDAO.buscarClientePorId(Integer.valueOf(clienteIdElement.getAttribute("value")));

        cliente = Factory.ClienteTeste();
        driver.findElement(By.id("nome")).clear();
        driver.findElement(By.id("nome")).sendKeys(cliente.getNome());
        driver.findElement(By.id("cpf")).clear();
        driver.findElement(By.id("cpf")).sendKeys(cliente.getCpf());
        WebElement generoSelect = driver.findElement(By.id("genero"));
        Select genero = new Select(generoSelect);
        genero.selectByVisibleText(cliente.getGenero().toString());
        driver.findElement(By.id("data-nascimento")).clear();
        driver.findElement(By.id("data-nascimento")).sendKeys(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento()));
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(cliente.getContato().getEmail());
        Select tipotel = new Select(driver.findElement(By.id("tipotel")));
        tipotel.selectByVisibleText(cliente.getContato().getTelefone().getTipo().toString());
        driver.findElement(By.id("ddd")).clear();
        driver.findElement(By.id("ddd")).sendKeys(cliente.getContato().getTelefone().getDdd().toString());
        driver.findElement(By.id("numerotel")).clear();
        driver.findElement(By.id("numerotel")).sendKeys(cliente.getContato().getTelefone().getNumero().toString());
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

            System.out.println("ANTES DA ALTERACAO:");
        System.out.println("NOME: " + clienteAntesDeAlterar.getNome());
        System.out.println("CPF: " + clienteAntesDeAlterar.getCpf());
        System.out.println("GENERO: " + clienteAntesDeAlterar.getGenero());
        System.out.println("DATA DE NASCIMENTO: " + clienteAntesDeAlterar.getDataNascimento());
        System.out.println("EMAIL: " + clienteAntesDeAlterar.getContato().getEmail());

        System.out.println("TELEFONE: " + clienteAntesDeAlterar.getContato().getTelefone().getDdd() + clienteAntesDeAlterar.getContato().getTelefone().getNumero());
        System.out.println("DEPOIS DA ALTERACAO:");
        System.out.println("NOME: " + clienteAlterado.getNome());
        System.out.println("CPF: " + clienteAlterado.getCpf());
        System.out.println("GENERO: " + clienteAlterado.getGenero());
        System.out.println("DATA DE NASCIMENTO: " + clienteAlterado.getDataNascimento());
        System.out.println("EMAIL: " + clienteAlterado.getContato().getEmail());
        System.out.println("TELEFONE: " + clienteAlterado.getContato().getTelefone().getDdd() + clienteAlterado.getContato().getTelefone().getNumero());


        driver.quit();
    }
}
