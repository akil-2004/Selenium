package demo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ScrollToElementActions {
    WebDriver driver;
    WebDriverWait wait;

    @Test
    public void testScrollWithActions() throws InterruptedException {
        // If you don’t have WebDriverManager, set system property manually
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.navigate().to("https://the-internet.herokuapp.com/large");

        // Pick a valid element ID from the page
        WebElement deepElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sibling-50.3")));
        Thread.sleep(2000);
        // Scroll to the element
        new Actions(driver).scrollToElement(deepElement).perform();
        Thread.sleep(2000);
        // Verify
        Assert.assertTrue(deepElement.getText().equals("50.3"),"Scrolling Failed: The Elements scrolled to is not equal to 50.3");
        System.out.println("Scrolled to an specified element is successful");

        driver.quit();
    }
}
