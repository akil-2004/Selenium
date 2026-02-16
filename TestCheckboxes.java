package demo;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCheckboxes {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to("https://www.qa-practice.com/elements/checkbox/mult_checkbox");
		Thread.sleep(2000);
		List<WebElement> checkboxes = driver.findElement(By.cssSelector("input[type='checkbox']"));
		System.out.println("Total number of check boxes " + checkboxes.size());
	}

}
