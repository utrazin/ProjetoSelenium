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

public class TopicsTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testVerifyTopics() {
        driver.get("https://www.scriptbrasil.com.br/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement moreReadElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.glyphicon.glyphicon-fire")));
            System.out.println("✅ 'Mais Lidos da Semana' está presente.");
        } catch (Exception e) {
            System.out.println("❌ 'Mais Lidos da Semana' não encontrado: " + e.getMessage());
        }

        try {
            WebElement mobileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.glyphicon.glyphicon-phone")));
            System.out.println("✅ 'Mobile' está presente.");
        } catch (Exception e) {
            System.out.println("❌ 'Mobile' não encontrado: " + e.getMessage());
        }

        try {
            WebElement electronicsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.glyphicon.glyphicon-camera")));
            System.out.println("✅ 'Eletrônicos' está presente.");
        } catch (Exception e) {
            System.out.println("❌ 'Eletrônicos' não encontrado: " + e.getMessage());
        }

        try {
            WebElement informaticsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.glyphicon.glyphicon-hdd")));
            System.out.println("✅ 'Informática' está presente.");
        } catch (Exception e) {
            System.out.println("❌ 'Informática' não encontrado: " + e.getMessage());
        }

        try {
            WebElement gamesElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.glyphicon.glyphicon-eject")));
            System.out.println("✅ 'Jogos' está presente.");
        } catch (Exception e) {
            System.out.println("❌ 'Jogos' não encontrado: " + e.getMessage());
        }

        try {
            WebElement digitalLifeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.glyphicon.glyphicon-globe")));
            System.out.println("✅ 'Vida Digital' está presente.");
        } catch (Exception e) {
            System.out.println("❌ 'Vida Digital' não encontrado: " + e.getMessage());
        }
    }

    @Test
    public void testBannerImageRedirection() {
        driver.get("https://www.scriptbrasil.com.br/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement bannerImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.carousel-inner div.item.active img")));
            WebElement link = bannerImage.findElement(By.xpath(".."));
            String expectedUrl = link.getAttribute("href");

            bannerImage.click();

            wait.until(ExpectedConditions.urlToBe(expectedUrl));

            if (driver.getCurrentUrl().equals(expectedUrl)) {
                System.out.println("✅ A imagem do banner redireciona corretamente.");
            } else {
                System.out.println("❌ A imagem do banner não redireciona corretamente.");
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao testar o redirecionamento da imagem do banner: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}