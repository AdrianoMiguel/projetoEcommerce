package produto;

import dominio.produto.TpUva;
import dominio.produto.Vinho;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringDecorator;

import org.openqa.selenium.support.ui.Select;

import utils.Factory;
import utils.FakerModificado;
import utils.SlowdownListener;


public class CadastroProdutoTeste {
    //ANOTACOES REFERENTE AO TESTE
    //ATENDE AS SEGUINTES REGRAS E REQUISITOS:
    //RF0011 - CADASTRAR VINHO
    //RF0012 - INATIVAR CADASTRO DE VINHO
    //RF0014 - ALTERAR CADASTRO DE VINHO
    //RF0015 - CONSULTA DE VINHOS
    //RF0016 - ATIVA CADASTRO DE VINHOS
    //RN0012 - ASSOCIACAO COM CATEGORIAS
    //RN0015 - ASSOCIAR MOTIVO DE INATIVACAO
    //RNF0021 - CODIGO DE VINHO
    //RNF0013 - CADASTRO DE DOMINIOS



    private WebDriver driver;
    private Vinho vinho;
    private FakerModificado faker = new FakerModificado();

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver baseDriver = new ChromeDriver();
        SlowdownListener listener = new SlowdownListener(500);
        driver = new EventFiringDecorator(listener).decorate(baseDriver);
        //driver = new ChromeDriver();
        driver.get("http://localhost:8080/EcommerceVinhoVerso_war/CtrlProdutoNovo");
    }

    @Test
    public void testePreenchimentoFormulario() throws InterruptedException {
        vinho = Factory.vinhoTeste();
        Actions actions = new Actions(driver);

        Thread.sleep(2000);
        WebElement submitButton4 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton4).perform();
        Thread.sleep(2000);
        submitButton4.click();
        Thread.sleep(2000);
        WebElement botaoVoltar8 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar8).perform();
        Thread.sleep(1000);
        botaoVoltar8.click();

        actions.moveToElement(driver.findElement(By.id("nome"))).perform();
        driver.findElement(By.id("nome")).sendKeys(vinho.getNome());
        Thread.sleep(300);

        WebElement submitButton5 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton5).perform();
        Thread.sleep(2000);
        submitButton5.click();
        Thread.sleep(2000);
        WebElement botaoVoltar9 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar9).perform();
        Thread.sleep(1000);
        botaoVoltar9.click();

        actions.moveToElement(driver.findElement(By.id("safra"))).perform();
        driver.findElement(By.id("safra")).sendKeys(vinho.getSafra().toString());
        Thread.sleep(300);

        WebElement submitButton6 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton6).perform();
        Thread.sleep(2000);
        submitButton6.click();
        Thread.sleep(2000);
        WebElement botaoVoltar10 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar10).perform();
        Thread.sleep(1000);
        botaoVoltar10.click();

        actions.moveToElement(driver.findElement(By.id("tipoVinho"))).perform();
        WebElement tipoVinhoSelect = driver.findElement(By.id("tipoVinho"));
        Select tipoVinho = new Select(tipoVinhoSelect);
        tipoVinho.selectByVisibleText(vinho.getTipoVinho().toString());
        Thread.sleep(300);

        WebElement submitButton7 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton7).perform();
        Thread.sleep(2000);
        submitButton7.click();
        Thread.sleep(2000);
        WebElement botaoVoltar11 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar11).perform();
        Thread.sleep(1000);
        botaoVoltar11.click();

        actions.moveToElement(driver.findElement(By.id("pais"))).perform();
        WebElement paisSelect = driver.findElement(By.id("pais"));
        Select pais = new Select(paisSelect);
        pais.selectByVisibleText(vinho.getPais().toString().replace("_", " "));
        Thread.sleep(300);

        WebElement submitButton8 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton8).perform();
        Thread.sleep(2000);
        submitButton8.click();
        Thread.sleep(2000);
        WebElement botaoVoltar12 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar12).perform();
        Thread.sleep(1000);
        botaoVoltar12.click();

        actions.moveToElement(driver.findElement(By.id("volume"))).perform();
        driver.findElement(By.id("volume")).sendKeys(vinho.getVolume().toString());
        Thread.sleep(300);
        driver.findElement(By.id("teorAlc")).sendKeys(vinho.getTeorAlc().toString());
        Thread.sleep(300);

        WebElement submitButton9 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton9).perform();
        Thread.sleep(2000);
        submitButton9.click();
        Thread.sleep(2000);
        WebElement botaoVoltar13 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar13).perform();
        Thread.sleep(1000);
        botaoVoltar13.click();

        actions.moveToElement(driver.findElement(By.id("grupoPrecif"))).perform();
        WebElement grupoPrecificacaoSelect = driver.findElement(By.id("grupoPrecif"));
        Select grupoPrecificacao = new Select(grupoPrecificacaoSelect);
        grupoPrecificacao.selectByVisibleText(vinho.getGrupoPrecificacao().toString());
        Thread.sleep(300);

        WebElement submitButton10 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton10).perform();
        Thread.sleep(2000);
        submitButton10.click();
        Thread.sleep(2000);
        WebElement botaoVoltar14 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar14).perform();
        Thread.sleep(1000);
        botaoVoltar14.click();

        actions.moveToElement(driver.findElement(By.id("codBarras"))).perform();
        driver.findElement(By.id("codBarras")).sendKeys(vinho.getCodBarras());
        Thread.sleep(300);

        WebElement submitButton11 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton11).perform();
        Thread.sleep(2000);
        submitButton11.click();
        Thread.sleep(2000);
        WebElement botaoVoltar15 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar15).perform();
        Thread.sleep(1000);
        botaoVoltar15.click();

        for (TpUva tipoUva : vinho.getTipoUva()) {
            WebElement tipoUvaCheckbox = driver.findElement(By.id(tipoUva.toString()));
            tipoUvaCheckbox.click();
            Thread.sleep(200);
        }

        WebElement submitButton12 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton12).perform();
        Thread.sleep(2000);
        submitButton12.click();
        Thread.sleep(2000);
        WebElement botaoVoltar16 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar16).perform();
        Thread.sleep(1000);
        botaoVoltar16.click();

        actions.moveToElement(driver.findElement(By.id("descricao"))).perform();
        driver.findElement(By.id("descricao")).sendKeys(vinho.getDescricao());
        Thread.sleep(300);

        WebElement submitButton13 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton13).perform();
        Thread.sleep(2000);
        submitButton13.click();
        Thread.sleep(2000);
        WebElement botaoVoltar17 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar17).perform();
        Thread.sleep(1000);
        botaoVoltar17.click();

        actions.moveToElement(driver.findElement(By.id("fornecedor"))).perform();
        WebElement fornecedorSelect = driver.findElement(By.id("fornecedor"));
        Select fornecedor = new Select(fornecedorSelect);
        fornecedor.selectByVisibleText(faker.fornecedor().toString().replace("_", " "));
        Thread.sleep(300);
        driver.findElement(By.id("custo")).sendKeys(vinho.getMaiorCusto().toString());
        Thread.sleep(300);
        driver.findElement(By.id("preco")).sendKeys(vinho.getMaiorCusto().toString());
        Thread.sleep(300);

        WebElement submitButton16 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton16).perform();
        Thread.sleep(2000);
        submitButton16.click();
        Thread.sleep(2000);
        WebElement botaoVoltar20 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar20).perform();
        Thread.sleep(1000);
        botaoVoltar20.click();

        actions.moveToElement(driver.findElement(By.id("preco"))).perform();
        driver.findElement(By.id("preco")).clear();
        driver.findElement(By.id("preco")).sendKeys(vinho.getPreco().toString());
        Thread.sleep(300);

        driver.findElement(By.id("qtdeEstoque")).sendKeys(faker.qtdeEstoque().toString());
        Thread.sleep(300);

        WebElement statusSelect = driver.findElement(By.id("status"));
        Select status = new Select(statusSelect);
        status.selectByVisibleText("Ativo");
        Thread.sleep(300);

        WebElement submitButton14 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton14).perform();
        Thread.sleep(2000);
        submitButton14.click();
        Thread.sleep(2000);
        WebElement botaoVoltar18 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar18).perform();
        Thread.sleep(1000);
        botaoVoltar18.click();

        WebElement motivoCategoriaSelect = driver.findElement(By.id("motivoCategoria"));
        Select motivoCategoria = new Select(motivoCategoriaSelect);
        motivoCategoria.selectByVisibleText(vinho.getMotivo().getCategoria()
                .toString().replace("_", " "));
        Thread.sleep(300);

        WebElement submitButton15 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton15).perform();
        Thread.sleep(2000);
        submitButton15.click();
        Thread.sleep(2000);
        WebElement botaoVoltar19 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar19).perform();
        Thread.sleep(1000);
        botaoVoltar19.click();

        driver.findElement(By.id("justificativa")).sendKeys(vinho.getMotivo()
                .getJustificativa());
        Thread.sleep(300);

        // Submeter o formulário
        WebElement submitButton = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton).perform();
        Thread.sleep(2000);
        submitButton.click();


        WebElement adminDropdown2 = driver.findElement(By.id("adminDropdown"));
        actions.moveToElement(adminDropdown2).perform();
        adminDropdown2.click();
        WebElement consultarCliente2 = driver.findElement(By.id("consultarVinho"));
        actions.moveToElement(consultarCliente2).perform();
        consultarCliente2.click();
        driver.findElement(By.id("filtro")).sendKeys(vinho.getSafra().toString() + " "
                + vinho.getPais().toString().replace("_", " ") + " " +
                vinho.getTipoVinho().toString() + " " + vinho.getTipoUva().toString()
                .replace("[", "").replace("]", "")
                .replace(",", ""));
        WebElement buscarClientes2 = driver.findElement(By.id("btnConsultar"));
        actions.moveToElement(buscarClientes2).perform();
        Thread.sleep(4000);
        buscarClientes2.click();
        Thread.sleep(1000);
        WebElement botaoAlterar = driver.findElement(By.xpath("//*[contains(@id, 'alterar')]"));
        vinho.setId(Integer.valueOf(botaoAlterar.getAttribute("id").replace("alterar_", "")));
        botaoAlterar.click();


        WebElement statusSelect2 = driver.findElement(By.id("status"));
        Select status2 = new Select(statusSelect2);
        status2.selectByVisibleText("Inativo");
        Thread.sleep(300);
        driver.findElement(By.id("justificativa")).clear();

        WebElement submitButton17 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton17).perform();
        Thread.sleep(2000);
        submitButton17.click();
        Thread.sleep(2000);
        WebElement botaoVoltar21 = driver.findElement(By.id("botaoVoltar"));
        actions.moveToElement(botaoVoltar21).perform();
        Thread.sleep(1000);
        botaoVoltar21.click();

        WebElement motivoCategoriaSelect2 = driver.findElement(By.id("motivoCategoria"));
        Select motivoCategoria2 = new Select(motivoCategoriaSelect2);
        motivoCategoria2.selectByVisibleText("Vinhos Extraviados");
        Thread.sleep(300);
        driver.findElement(By.id("justificativa")).clear();
        driver.findElement(By.id("justificativa")).sendKeys("SIMULACAO DE TESTE");
        Thread.sleep(300);
        // Submeter o formulário
        WebElement submitButton2 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton2).perform();
        Thread.sleep(2000);
        submitButton2.click();

        WebElement adminDropdown3 = driver.findElement(By.id("adminDropdown"));
        actions.moveToElement(adminDropdown3).perform();
        adminDropdown3.click();
        WebElement consultarCliente3 = driver.findElement(By.id("consultarVinho"));
        actions.moveToElement(consultarCliente3).perform();
        consultarCliente3.click();
        driver.findElement(By.id("filtro")).sendKeys(vinho.getNome());
        WebElement buscarClientes3 = driver.findElement(By.id("btnConsultar"));
        actions.moveToElement(buscarClientes3).perform();
        buscarClientes3.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@id, 'alterar')]")).click();

        WebElement tipoVinhoSelect3 = driver.findElement(By.id("tipoVinho"));
        Select tipoVinho3 = new Select(tipoVinhoSelect3);
        tipoVinho3.selectByVisibleText(faker.tipoDeVinho().toString());
        Thread.sleep(300);
        WebElement paisSelect3 = driver.findElement(By.id("pais"));
        Select pais3 = new Select(paisSelect3);
        pais3.selectByVisibleText(faker.pais().toString().replace("_", " "));
        Thread.sleep(300);

        for (TpUva tipoUva2 : faker.tipoDeUva()) {
            WebElement tipoUva2Checkbox = driver.findElement(By.id(tipoUva2.toString()));
            tipoUva2Checkbox.click();
            Thread.sleep(200);
        }
        driver.findElement(By.id("descricao")).clear();
        driver.findElement(By.id("descricao")).sendKeys("Alteracao de dados para teste automatizado");
        Thread.sleep(300);

        WebElement statusSelect3 = driver.findElement(By.id("status"));
        Select status3 = new Select(statusSelect3);
        status3.selectByVisibleText("Ativo");
        Thread.sleep(300);
        WebElement motivoCategoriaSelect3 = driver.findElement(By.id("motivoCategoria"));
        Select motivoCategoria3 = new Select(motivoCategoriaSelect3);
        motivoCategoria3.selectByVisibleText(vinho.getMotivo().getCategoria()
                .toString().replace("_", " "));
        Thread.sleep(300);
        driver.findElement(By.id("justificativa")).clear();
        driver.findElement(By.id("justificativa")).sendKeys(vinho.getMotivo().getJustificativa());
        Thread.sleep(300);
        // Submeter o formulário
        WebElement submitButton3 = driver.findElement(By.id("cadastrar"));
        actions.moveToElement(submitButton3).perform();
        Thread.sleep(2000);
        submitButton3.click();

        WebElement listarVinhos = driver.findElement(By.id("listarVinhos"));
        actions.moveToElement(listarVinhos).perform();
        Thread.sleep(2000);
        listarVinhos.click();

        WebElement detalhesButton = driver.findElement(By.id("detalhes_" + vinho.getId()));
        actions.moveToElement(detalhesButton).perform();
        Thread.sleep(2000);
        detalhesButton.click();
        Thread.sleep(3000);

        WebElement adminDropdown4 = driver.findElement(By.id("adminDropdown"));
        actions.moveToElement(adminDropdown4).perform();
        adminDropdown4.click();
        WebElement consultarCliente4 = driver.findElement(By.id("consultarVinho"));
        actions.moveToElement(consultarCliente4).perform();
        consultarCliente4.click();
        driver.findElement(By.id("filtro")).sendKeys(vinho.getNome());
        WebElement buscarClientes4 = driver.findElement(By.id("btnConsultar"));
        actions.moveToElement(buscarClientes4).perform();
        buscarClientes4.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@id, 'excluir')]")).click();

        WebElement adminDropdown5 = driver.findElement(By.id("adminDropdown"));
        actions.moveToElement(adminDropdown5).perform();
        adminDropdown5.click();
        WebElement consultarCliente5 = driver.findElement(By.id("consultarVinho"));
        actions.moveToElement(consultarCliente5).perform();
        consultarCliente5.click();
        driver.findElement(By.id("filtro")).sendKeys(vinho.getNome());
        WebElement buscarClientes5 = driver.findElement(By.id("btnConsultar"));
        actions.moveToElement(buscarClientes5).perform();
        buscarClientes5.click();
        Thread.sleep(4000);

    }

    @After
    public void tearDown() {

        driver.quit();
    }
}

