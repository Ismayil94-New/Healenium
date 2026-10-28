package org.example.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

final class WorkshopSupport {
    static final String URL = "https://automationexercise.com/login";
    static final By EMAIL = By.cssSelector("input[data-qa='login-email']");
    private WorkshopSupport() {}

    static WebDriver chrome() { return new ChromeDriver(); }

    static void openLogin(WebDriver driver) {
        driver.get(URL);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fc-cta-consent"))).click();
        } catch (TimeoutException ignored) { }
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".fc-dialog-overlay")));
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(EMAIL));
    }

    static void breakEmailLocator(WebDriver driver) {
        WebElement email = driver.findElement(EMAIL);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].setAttribute('data-qa', 'login-email-new')", email);
    }
}
