package org.example.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

class SeleniumWithoutHealingTest {
    @Test
    void brokenLocatorFails() {
        WebDriver driver = WorkshopSupport.chrome();
        try {
            WorkshopSupport.openLogin(driver);
            WorkshopSupport.breakEmailLocator(driver);
            // Intentionally fails with NoSuchElementException.
            driver.findElement(WorkshopSupport.EMAIL).sendKeys("demo@example.com");
        } finally {
            driver.quit();
        }
    }
}
