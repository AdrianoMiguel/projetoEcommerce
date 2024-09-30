package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class SlowdownListener implements WebDriverListener {
    private final int delay; // Atraso em milissegundos

    public SlowdownListener(int delay) {
        this.delay = delay;
    }

    // Método para adicionar a pausa
    private void addDelay() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeClick(WebElement element) {
        addDelay(); // Adiciona pausa antes de clicar
    }

    @Override
    public void afterClick(WebElement element) {
        addDelay(); // Adiciona pausa após clicar
    }


    public void beforeNavigateTo(String url, WebDriver driver) {
        addDelay(); // Adiciona pausa antes de navegar para um URL
    }

    public void afterNavigateTo(String url, WebDriver driver) {
        addDelay(); // Adiciona pausa após navegar para um URL
    }

    // Você pode implementar outros métodos para adicionar pausas entre ações conforme necessário.
}