package com.training.pomdemo.base;

import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class localCapabilities_modernOptions {
	
	private WebDriver driver;
	
//	@BeforeMethod
//	public void setUp() {
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));	
//	}
//	
	@Test
	public void desiredCapabilitiesChromeOptions() throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.setAcceptInsecureCerts(true);
		driver = new ChromeDriver(options);
		try {
			driver.get("https://the-internet.herokuapp.com/");
			Capabilities caps = ((HasCapabilities) driver).getCapabilities();
			System.out.println("Browser: " + caps.getBrowserName());
			System.out.println("Browser Version: " + caps.getBrowserVersion());
			System.out.println("Platform: " + caps.getPlatformName());	
		}finally {
			Thread.sleep(2000);
			driver.close();
			
		}
	}

}
