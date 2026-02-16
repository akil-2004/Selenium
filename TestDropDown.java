package demo;

import java.time.Duration;
import java.util.List;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebElement.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
public class TestDropDown {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://practice.expandtesting.com/dropdown");
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement simpleDropdown= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdown")));
		
		Select select = new Select(simpleDropdown);
		List<WebElement> options = select.getOptions();
		System.out.println("Total items in Simple dropdown: "+ options.size());
		
		for(int i=0;i<options.size();i++) {
			System.out.println((i+1) + " -> " + options.get(i).getText().trim());
		}
		
	}

}
