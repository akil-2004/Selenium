package demo;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.*;
public class TestHerokuApp {
	private WebDriver driver;
	
	@BeforeMethod
	public void loginaction() {
	    driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	
	
	@Test 
	public void appTest(){
		
		driver.navigate().to("https://the-internet.herokuapp.com/login");
		
		String head = driver.findElement(By.tagName("h2")).getText();
		Assert.assertEquals(head,"Login Page", "Invalid Text");
		
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		driver.findElement(By.cssSelector("button[type='Submit']")).click();

		SoftAssert soft = new SoftAssert();
		soft.assertTrue(driver.getCurrentUrl().contains("/secure"),"Verify Failed-> Url doesn't match");
		String msg = driver.findElement(By.id("flash")).getText();
		soft.assertTrue(msg.contains("into a secure area"), "Verify failed for msg");
		WebElement wb = driver.findElement(By.cssSelector("a.button.secondary.radius"));
		soft.assertTrue(wb.isDisplayed(), "Verify Failed for displaying the logout button");
		soft.assertAll();
		
	}
	@AfterMethod
	public void tearDown() {
		if(driver != null) {
			driver.close();
		}
	}

}
