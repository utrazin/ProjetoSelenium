import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HomeTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testHomePageTitle() {
        driver.get("https://www.scriptbrasil.com.br/");
        String expectedTitle = "Script Brasil - O maior portal de scripts em língua portuguesa";
        String actualTitle = driver.getTitle();

        if (actualTitle.equals(expectedTitle)) {
            System.out.println("✅ Funcionou! O título da página está correto.");
        } else {
            System.out.println("❌ Falhou! Título esperado: '" + expectedTitle + "', mas foi: '" + actualTitle + "'.");
        }
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testLogoPresence() {
        driver.get("https://www.scriptbrasil.com.br/");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'logo')]")));

            if (logo.isDisplayed()) {
                System.out.println("✅ Logo está presente na página.");
            } else {
                System.out.println("❌ Logo não foi encontrado.");
            }

            Assertions.assertTrue(logo.isDisplayed(), "Logo não está presente na página.");
        } catch (NoSuchElementException e) {
            System.out.println("❌ Logo não encontrado: " + e.getMessage());
            Assertions.fail("Logo não encontrado.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao verificar a presença do logo: " + e.getMessage());
            Assertions.fail("Erro na verificação do logo.");
        }
    }


    @Test
    public void testNavigationLinks() {
        driver.get("https://www.scriptbrasil.com.br/");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement forumLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Fórum")));

            String initialUrl = driver.getCurrentUrl();

            forumLink.click();

            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(initialUrl)));

            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.equals(initialUrl)) {
                System.out.println("✅ A navegação para o Fórum foi bem-sucedida!");
                Thread.sleep(5000);
            } else {
                System.out.println("❌ A navegação para o Fórum falhou.");
            }
        } catch (TimeoutException e) {
            System.out.println("❌ Não foi possível encontrar o link 'Fórum' após aguardar 20 segundos.");
        } catch (InterruptedException e) {
            System.out.println("❌ O teste foi interrompido.");
        }
    }

    @Test
    public void testSearchFunctionality() {
        driver.get("https://www.scriptbrasil.com.br/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
            searchInput.sendKeys("Java");

            System.out.println("✅ Campo de busca foi encontrado e preenchido com sucesso.");

            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
            searchButton.click();

            Thread.sleep(5000);

            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("Java")) {
                System.out.println("✅ A busca foi realizada com sucesso e a página foi redirecionada.");
            } else {
                System.out.println("❌ A busca falhou. A URL não contém o termo 'Java'.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao interagir com o campo de busca: " + e.getMessage());
            Assertions.fail("Erro na busca.");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}