package demo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RadioButtonTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.chabadpotomac.com/templates/articlecco_cdo/aid/1083290/jewish/Membership-5786.htm\r\n");
		Thread.sleep(8000);
		List<WebElement> radios = driver.findElements(By.name("x_amount"));
		System.out.println("Total radio buttons " + radios.size());
		System.out.println(radios.get(0).getAttribute("checked"));
		System.out.println(radios.get(1).getAttribute("checked"));
		radios.get(1).click();
		System.out.println("After click on second Attribute");
		System.out.println(radios.get(0).getAttribute("checked"));
		System.out.println(radios.get(1).getAttribute("checked"));	
	}
}
