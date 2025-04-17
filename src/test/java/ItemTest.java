import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class ItemTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testClickMobileLink() {
        driver.get("https://www.scriptbrasil.com.br/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement mobileLink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.linkText("Mobile"))
            );

            mobileLink.click();

            System.out.println("✅ Link 'Mobile' clicado com sucesso!");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("❌ Erro ao clicar no link 'Mobile': " + e.getMessage());
        }
    }

    @Test
    public void testClickAppleArticleLink() {
        testClickMobileLink();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement appleLink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.linkText("Apple vai lançar iOS 18 dia 10 de junho"))
            );
            appleLink.click();

            System.out.println("✅ Link do artigo da Apple clicado com sucesso!");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("❌ Erro ao clicar no link do artigo da Apple: " + e.getMessage());
        }
    }

    @Test
    public void testClickShareViaFacebook() {
        driver.get("https://www.scriptbrasil.com.br/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement mobileLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Mobile")));
            mobileLink.click();
            Thread.sleep(2000);

            WebElement appleLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Apple vai lançar iOS 18 dia 10 de junho")));
            appleLink.click();
            Thread.sleep(2000);

            WebElement shareFacebook = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title='Compartilhar via Facebook']")));

            String originalWindow = driver.getWindowHandle();

            shareFacebook.click();
            Thread.sleep(3000);

            wait.until(driver -> driver.getWindowHandles().size() > 1);

            Set<String> windowHandles = driver.getWindowHandles();
            for (String handle : windowHandles) {
                if (!handle.equals(originalWindow)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }

            System.out.println("✅ Nova guia de compartilhamento no Facebook aberta com sucesso!");
            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("❌ Erro ao tentar compartilhar via Facebook: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}