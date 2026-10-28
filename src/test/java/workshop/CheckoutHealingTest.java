package workshop;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutHealingTest {
    private static final String BASE_URL = System.getProperty("app.url", "http://demo-app");
    private static final String DRIVER_URL = System.getProperty("driver.url", "http://localhost:8085");

    private WebDriver driver() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        return new RemoteWebDriver(URI.create(DRIVER_URL).toURL(), options);
    }

    private void checkout(String version) throws Exception {
        WebDriver driver = driver();
        try {
            driver.get(BASE_URL + "/?version=" + version);
            // Keep original IDs deliberately: v2 changes all three IDs.
            driver.findElement(By.id("customer-name")).sendKeys("Ada Lovelace");
            driver.findElement(By.id("email-address")).sendKeys("ada@example.com");
            driver.findElement(By.id("submit-order")).click();
            assertEquals("Order placed for Ada Lovelace", driver.findElement(By.id("order-status")).getText());
        } finally { driver.quit(); }
    }

    @Test void baselineOriginalUi() throws Exception { checkout("1"); }
    @Test void changedUiNeedsHealing() throws Exception { checkout("2"); }
}
