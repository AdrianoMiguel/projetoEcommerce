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
public class AlterarEnderecosClienteTeste {
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
    WebElement alterarDadosButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Alterar Endereços')]")));
        alterarDadosButton.click();
    WebElement clienteIdElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id")));
    clienteAntesDeAlterar = ClienteDAO.buscarClientePorId(Integer.valueOf(clienteIdElement.getAttribute("value")));

    cliente = Factory.ClienteTeste();
        Select tiporesidRes = new Select(driver.findElement(By.id("tiporesidRes")));
        tiporesidRes.selectByVisibleText(cliente.getEndResid().getTipoResid().toString());
        Select tipologradRes = new Select(driver.findElement(By.id("tipologradRes")));
        tipologradRes.selectByVisibleText(cliente.getEndResid().getTipoLograd().toString());
        driver.findElement(By.id("endResLograd")).clear();
        driver.findElement(By.id("endResLograd")).sendKeys(cliente.getEndResid().getLogradouro());
        driver.findElement(By.id("endResNum")).clear();
        driver.findElement(By.id("endResNum")).sendKeys(cliente.getEndResid().getNumero().toString());
        driver.findElement(By.id("endResBairro")).clear();
        driver.findElement(By.id("endResBairro")).sendKeys(cliente.getEndResid().getBairro().getNome());
        driver.findElement(By.id("endResCidade")).clear();
        driver.findElement(By.id("endResCidade")).sendKeys(cliente.getEndResid().getBairro().getCidade().getNome());
        driver.findElement(By.id("endResEst")).clear();
        driver.findElement(By.id("endResEst")).sendKeys(cliente.getEndResid().getBairro().getCidade().getEstado().getNome());
        driver.findElement(By.id("endResCep")).clear();
        driver.findElement(By.id("endResCep")).sendKeys(cliente.getEndResid().getCep());
        driver.findElement(By.id("endResPais")).clear();
        driver.findElement(By.id("endResPais")).sendKeys(cliente.getEndResid().getBairro().getCidade().getEstado().getPais().getNome());
        driver.findElement(By.id("endResObs")).clear();
        driver.findElement(By.id("endResObs")).sendKeys(cliente.getEndResid().getObs());

        // Preencher Endereço de Cobrança
        Select tiporesidCob = new Select(driver.findElement(By.id("tiporesidCob")));
        tiporesidCob.selectByVisibleText(cliente.getEndCob().getTipoResid().toString());
        Select tipologradCob = new Select(driver.findElement(By.id("tipologradCob")));
        tipologradCob.selectByVisibleText(cliente.getEndCob().getTipoLograd().toString());
        driver.findElement(By.id("endCobLograd")).clear();
        driver.findElement(By.id("endCobLograd")).sendKeys(cliente.getEndCob().getLogradouro());
        driver.findElement(By.id("endCobNum")).clear();
        driver.findElement(By.id("endCobNum")).sendKeys(cliente.getEndCob().getNumero().toString());
        driver.findElement(By.id("endCobBairro")).clear();
        driver.findElement(By.id("endCobBairro")).sendKeys(cliente.getEndCob().getBairro().getNome());
        driver.findElement(By.id("endCobCidade")).clear();
        driver.findElement(By.id("endCobCidade")).sendKeys(cliente.getEndCob().getBairro().getCidade().getNome());
        driver.findElement(By.id("endCobEst")).clear();
        driver.findElement(By.id("endCobEst")).sendKeys(cliente.getEndCob().getBairro().getCidade().getEstado().getNome());
        driver.findElement(By.id("endCobCep")).clear();
        driver.findElement(By.id("endCobCep")).sendKeys(cliente.getEndCob().getCep());
        driver.findElement(By.id("endCobPais")).clear();
        driver.findElement(By.id("endCobPais")).sendKeys(cliente.getEndCob().getBairro().getCidade().getEstado().getPais().getNome());
        driver.findElement(By.id("endCobObs")).clear();
        driver.findElement(By.id("endCobObs")).sendKeys(cliente.getEndCob().getObs());


        // Preencher Endereço de Entrega
        driver.findElement(By.id("expandir1")).click();
        driver.findElement(By.id("endEntNome1")).clear();
        driver.findElement(By.id("endEntNome1")).sendKeys(cliente.getEndEnt().get(0).getNome());
        Select tiporesidEnt1 = new Select(driver.findElement(By.id("tiporesidEnt1")));
        tiporesidEnt1.selectByVisibleText(cliente.getEndEnt().get(0).getTipoResid().toString());
        Select tipologradEnt1 = new Select(driver.findElement(By.id("tipologradEnt1")));
        tipologradEnt1.selectByVisibleText(cliente.getEndEnt().get(0).getTipoLograd().toString());
        driver.findElement(By.id("endEntLograd1")).clear();
        driver.findElement(By.id("endEntLograd1")).sendKeys(cliente.getEndEnt().get(0).getLogradouro());
        driver.findElement(By.id("endEntNum1")).clear();
        driver.findElement(By.id("endEntNum1")).sendKeys(cliente.getEndEnt().get(0).getNumero().toString());
        driver.findElement(By.id("endEntBairro1")).clear();
        driver.findElement(By.id("endEntBairro1")).sendKeys(cliente.getEndEnt().get(0).getBairro().getNome());
        driver.findElement(By.id("endEntCidade1")).clear();
        driver.findElement(By.id("endEntCidade1")).sendKeys(cliente.getEndEnt().get(0).getBairro().getCidade().getNome());
        driver.findElement(By.id("endEntEst1")).clear();
        driver.findElement(By.id("endEntEst1")).sendKeys(cliente.getEndEnt().get(0).getBairro().getCidade().getEstado().getNome());
        driver.findElement(By.id("endEntCep1")).clear();
        driver.findElement(By.id("endEntCep1")).sendKeys(cliente.getEndEnt().get(0).getCep());
        driver.findElement(By.id("endEntPais1")).clear();
        driver.findElement(By.id("endEntPais1")).sendKeys(cliente.getEndEnt().get(0).getBairro().getCidade().getEstado().getPais().getNome());
        driver.findElement(By.id("endEntObs1")).clear();
        driver.findElement(By.id("endEntObs1")).sendKeys(cliente.getEndEnt().get(0).getObs());
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
    System.out.println("ENDERECO RESIDENCIAL:");
    System.out.println("Tipo Residencia: " + clienteAntesDeAlterar.getEndResid().getTipoResid());
    System.out.println("Tipo Logradouro: " + clienteAntesDeAlterar.getEndResid().getTipoLograd());
    System.out.println("Logradouro: " + clienteAntesDeAlterar.getEndResid().getLogradouro());
    System.out.println("Numero: " + clienteAntesDeAlterar.getEndResid().getNumero());
    System.out.println("Bairro: " + clienteAntesDeAlterar.getEndResid().getBairro().getNome());
    System.out.println("Cidade: " + clienteAntesDeAlterar.getEndResid().getBairro().getCidade().getNome());
    System.out.println("Estado: " + clienteAntesDeAlterar.getEndResid().getBairro().getCidade().getEstado().getNome());
    System.out.println("CEP: " + clienteAntesDeAlterar.getEndResid().getCep());
    System.out.println("Pais: " + clienteAntesDeAlterar.getEndResid().getBairro().getCidade().getEstado().getPais().getNome());
    System.out.println("Observacao: " + clienteAntesDeAlterar.getEndResid().getObs());

    System.out.println("ENDERECO DE COBRANCA:");
    System.out.println("Tipo Residencia: " + clienteAntesDeAlterar.getEndCob().getTipoResid());
    System.out.println("Tipo Logradouro: " + clienteAntesDeAlterar.getEndCob().getTipoLograd());
    System.out.println("Logradouro: " + clienteAntesDeAlterar.getEndCob().getLogradouro());
    System.out.println("Numero: " + clienteAntesDeAlterar.getEndCob().getNumero());
    System.out.println("Bairro: " + clienteAntesDeAlterar.getEndCob().getBairro().getNome());
    System.out.println("Cidade: " + clienteAntesDeAlterar.getEndCob().getBairro().getCidade().getNome());
    System.out.println("Estado: " + clienteAntesDeAlterar.getEndCob().getBairro().getCidade().getEstado().getNome());
    System.out.println("CEP: " + clienteAntesDeAlterar.getEndCob().getCep());
    System.out.println("Pais: " + clienteAntesDeAlterar.getEndCob().getBairro().getCidade().getEstado().getPais().getNome());
    System.out.println("Observacao: " + clienteAntesDeAlterar.getEndCob().getObs());

    System.out.println("ENDERECO DE ENTREGA:");
    System.out.println("Tipo Residencia: " + clienteAntesDeAlterar.getEndEnt().get(0).getTipoResid());
    System.out.println("Tipo Logradouro: " + clienteAntesDeAlterar.getEndEnt().get(0).getTipoLograd());
    System.out.println("Logradouro: " + clienteAntesDeAlterar.getEndEnt().get(0).getLogradouro());
    System.out.println("Numero: " + clienteAntesDeAlterar.getEndEnt().get(0).getNumero());
    System.out.println("Bairro: " + clienteAntesDeAlterar.getEndEnt().get(0).getBairro().getNome());
    System.out.println("Cidade: " + clienteAntesDeAlterar.getEndEnt().get(0).getBairro().getCidade().getNome());
    System.out.println("Estado: " + clienteAntesDeAlterar.getEndEnt().get(0).getBairro().getCidade().getEstado().getNome());
    System.out.println("CEP: " + clienteAntesDeAlterar.getEndEnt().get(0).getCep());
    System.out.println("Pais: " + clienteAntesDeAlterar.getEndEnt().get(0).getBairro().getCidade().getEstado().getPais().getNome());
    System.out.println("Observacao: " + clienteAntesDeAlterar.getEndEnt().get(0).getObs());

    System.out.println("DEPOIS DA ALTERACAO:");
    System.out.println("ENDERECO RESIDENCIAL:");
    System.out.println("Tipo Residencia: " + clienteAlterado.getEndResid().getTipoResid());
    System.out.println("Tipo Logradouro: " + clienteAlterado.getEndResid().getTipoLograd());
    System.out.println("Logradouro: " + clienteAlterado.getEndResid().getLogradouro());
    System.out.println("Numero: " + clienteAlterado.getEndResid().getNumero());
    System.out.println("Bairro: " + clienteAlterado.getEndResid().getBairro().getNome());
    System.out.println("Cidade: " + clienteAlterado.getEndResid().getBairro().getCidade().getNome());
    System.out.println("Estado: " + clienteAlterado.getEndResid().getBairro().getCidade().getEstado().getNome());
    System.out.println("CEP: " + clienteAlterado.getEndResid().getCep());
    System.out.println("Pais: " + clienteAlterado.getEndResid().getBairro().getCidade().getEstado().getPais().getNome());
    System.out.println("Observacao: " + clienteAlterado.getEndResid().getObs());

    System.out.println("ENDERECO DE COBRANCA:");
    System.out.println("Tipo Residencia: " + clienteAlterado.getEndCob().getTipoResid());
    System.out.println("Tipo Logradouro: " + clienteAlterado.getEndCob().getTipoLograd());
    System.out.println("Logradouro: " + clienteAlterado.getEndCob().getLogradouro());
    System.out.println("Numero: " + clienteAlterado.getEndCob().getNumero());
    System.out.println("Bairro: " + clienteAlterado.getEndCob().getBairro().getNome());
    System.out.println("Cidade: " + clienteAlterado.getEndCob().getBairro().getCidade().getNome());
    System.out.println("Estado: " + clienteAlterado.getEndCob().getBairro().getCidade().getEstado().getNome());
    System.out.println("CEP: " + clienteAlterado.getEndCob().getCep());
    System.out.println("Pais: " + clienteAlterado.getEndCob().getBairro().getCidade().getEstado().getPais().getNome());
    System.out.println("Observacao: " + clienteAlterado.getEndCob().getObs());

    System.out.println("ENDERECO DE ENTREGA:");
    System.out.println("Tipo Residencia: " + clienteAlterado.getEndEnt().get(0).getTipoResid());
    System.out.println("Tipo Logradouro: " + clienteAlterado.getEndEnt().get(0).getTipoLograd());
    System.out.println("Logradouro: " + clienteAlterado.getEndEnt().get(0).getLogradouro());
    System.out.println("Numero: " + clienteAlterado.getEndEnt().get(0).getNumero());
    System.out.println("Bairro: " + clienteAlterado.getEndEnt().get(0).getBairro().getNome());
    System.out.println("Cidade: " + clienteAlterado.getEndEnt().get(0).getBairro().getCidade().getNome());
    System.out.println("Estado: " + clienteAlterado.getEndEnt().get(0).getBairro().getCidade().getEstado().getNome());
    System.out.println("CEP: " + clienteAlterado.getEndEnt().get(0).getCep());
    System.out.println("Pais: " + clienteAlterado.getEndEnt().get(0).getBairro().getCidade().getEstado().getPais().getNome());
    System.out.println("Observacao: " + clienteAlterado.getEndEnt().get(0).getObs());


    driver.quit();
}
}
