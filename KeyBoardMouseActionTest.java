package demo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class KeyBoardMouseActionTest {
	private WebDriver driver;
	private WebDriverWait wait;
	
//	@BeforeMethod
//	public void setup() {
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();	
//		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//	}
//	
//	@Test
//	public void demoTest() {
//		Actions act = new Actions(driver);
//		driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
//		
//		WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Element']")));
//		act.doubleClick(addBtn).perform();
//		
//		List<WebElement> deleteBtn = driver.findElements(By.cssSelector("button.added-manually"));
//		Assert.assertEquals(deleteBtn.size()>=2 , "Double click failed");
//		
//	}
//	@Test 
//	public void demohovers() throws InterruptedException {
//		Actions act = new Actions(driver);
//		driver.get("https://the-internet.herokuapp.com/hovers");
//		
//		WebElement firstimg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".figure:nth-of-type(1) img ")));
//		act.moveToElement(firstimg).perform();
//
//		WebElement user1Link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".figure:nth-of-type(1) .figcaption a")));
//		Assert.assertTrue(user1Link.isDisplayed(), "Hover failed: User1 link not visible");
//		
//	}
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
//	input.sendKeys("Akila");
//	driver.findElement(By.name("username")).sendKeys("varun");
//	driver.findElement(By.name("password")).sendKeys("varun@0561");
//	driver.findElement(By.cssSelector
//	
	@Test
	public void sendKeysTest() {

	}
	


}
