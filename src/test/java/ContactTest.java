import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ContactTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.scriptbrasil.com.br/contact/");
    }

    @Test
    public void testContactFormFill() throws InterruptedException {
        try {
            WebElement nameInput = driver.findElement(By.xpath("//input[@name='name']"));
            nameInput.sendKeys("Enzo Dutra");

            WebElement emailInput = driver.findElement(By.xpath("//input[@name='email']"));
            emailInput.sendKeys("enzodutra@example.com");

            WebElement messageInput = driver.findElement(By.xpath("//textarea[@name='message']"));
            messageInput.sendKeys("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...");

            System.out.println("✅ Formulário preenchido com sucesso!");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("❌ Erro ao preencher o formulário: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}