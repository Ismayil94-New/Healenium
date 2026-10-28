package org.example.tests;

import com.epam.healenium.SelfHealingDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SeleniumWithHealingTest {
    @Test
    void recordBaseline() {
        WebDriver driver = SelfHealingDriver.create(WorkshopSupport.chrome());
        try {
            WorkshopSupport.openLogin(driver);
            driver.findElement(WorkshopSupport.EMAIL).sendKeys("baseline@example.com");
            assertEquals("baseline@example.com", driver.findElement(WorkshopSupport.EMAIL).getAttribute("value"));
        } finally { driver.quit(); }
    }

    @Test
    void attemptHealing() {
        WebDriver driver = SelfHealingDriver.create(WorkshopSupport.chrome());
        try {
            WorkshopSupport.openLogin(driver);
            WorkshopSupport.breakEmailLocator(driver);
            driver.findElement(WorkshopSupport.EMAIL).sendKeys("healed@example.com");
            assertEquals("healed@example.com", driver.findElement(By.cssSelector("input[data-qa='login-email-new']")).getAttribute("value"));
        } finally { driver.quit(); }
    }
}
