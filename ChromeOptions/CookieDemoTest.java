package com.training.pomdemo.base;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class CookieDemoTest {
	
	private WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));	
	}
	@AfterMethod
	public void teardown() {
//		if(driver!=null) {
//			driver.close();
//		}
	}
	
	@Test
	public void addAndDeleteCookie() {
		driver.get("https://the-internet.herokuapp.com/");
		
		Cookie mycookie = new Cookie("trainerCookie", "akila123");
		driver.manage().addCookie(mycookie);
		
		Cookie fetched = driver.manage().getCookieNamed("trainerCookie");
		Assert.assertNotNull(fetched,  "Cookie was not added");
		Assert.assertEquals(fetched.getValue(), "akila123", "cookie value mismatch");
		
		System.out.println("Added cookie: " + fetched);
		Set<Cookie> all = driver.manage().getCookies();
		System.out.println("Total cookies now: " + all.size());
		for(Cookie c: all) {
			System.out.println("Cookie -> " + c.getName() + " = " + c.getValue());
		}
		
		driver.manage().deleteCookieNamed("trainerCookie");
		
		Cookie afterDelete = driver.manage().getCookieNamed("trainerCookie");
		Assert.assertNull(afterDelete, "Cookie was NOT deleted");
		
		System.out.println("Deleted cookie trainerCookie");
		
		driver.manage().deleteAllCookies();
		
		
	}

}
