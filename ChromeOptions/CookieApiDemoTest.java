package com.training.pomdemo.base;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class CookieApiDemoTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get("https://the-internet.herokuapp.com/"); // domain for which cookies will be added
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void demonstrateCookieApi() {
        // 1) Build cookies with various attributes
        Date expiry = Date.from(ZonedDateTime.now(ZoneId.of("UTC")).plusDays(7).toInstant());

        Cookie simple = new Cookie("trainerCookie", "akila123"); // minimal

        Cookie withAttrs = new Cookie.Builder("prefs", "dark")
                .domain("the-internet.herokuapp.com")
                .path("/")
                .expiresOn(expiry)
                .isSecure(true)
                .isHttpOnly(true)
                .sameSite("Lax") // Values typically "Lax", "Strict", or "None"
                .build();

        // 2) Add cookies
        driver.manage().addCookie(simple);
        driver.manage().addCookie(withAttrs);

        // 3) Read/inspect cookies (getByName + getters)
        Cookie fetchedSimple = driver.manage().getCookieNamed("trainerCookie");
        Assert.assertNotNull(fetchedSimple, "trainerCookie should be present");
        System.out.println("=== fetchedSimple ===");
        System.out.println("getName(): " + fetchedSimple.getName());
        System.out.println("getValue(): " + fetchedSimple.getValue());
        System.out.println("getDomain(): " + fetchedSimple.getDomain()); // may be null for host-only
        System.out.println("getPath(): " + fetchedSimple.getPath());     // may be null
        System.out.println("getExpiry(): " + fetchedSimple.getExpiry()); // may be null
        System.out.println("getSameSite(): " + fetchedSimple.getSameSite()); // may be null
        System.out.println("isSecure(): " + fetchedSimple.isSecure());
        System.out.println("isHttpOnly(): " + fetchedSimple.isHttpOnly());
        System.out.println("hashCode(): " + fetchedSimple.hashCode());
        System.out.println("toString(): " + fetchedSimple.toString());
        System.out.println("toJson(): " + fetchedSimple.toJson()); // Map<String,Object>

        // Another cookie with attributes
        Cookie fetchedWithAttrs = driver.manage().getCookieNamed("prefs");
        Assert.assertNotNull(fetchedWithAttrs, "prefs should be present");
        System.out.println("\n=== fetchedWithAttrs ===");
        System.out.println("getName(): " + fetchedWithAttrs.getName());
        System.out.println("getValue(): " + fetchedWithAttrs.getValue());
        System.out.println("getDomain(): " + fetchedWithAttrs.getDomain());
        System.out.println("getPath(): " + fetchedWithAttrs.getPath());
        System.out.println("getExpiry(): " + fetchedWithAttrs.getExpiry());
        System.out.println("getSameSite(): " + fetchedWithAttrs.getSameSite());
        System.out.println("isSecure(): " + fetchedWithAttrs.isSecure());
        System.out.println("isHttpOnly(): " + fetchedWithAttrs.isHttpOnly());
        System.out.println("hashCode(): " + fetchedWithAttrs.hashCode());
        System.out.println("toString(): " + fetchedWithAttrs.toString());
        System.out.println("toJson(): " + fetchedWithAttrs.toJson());

        // 4) equals(Object o): compare cookies
        Cookie anotherSimpleSame = new Cookie("trainerCookie", "akila123");
        Cookie anotherSimpleDifferentValue = new Cookie("trainerCookie", "different");
        Cookie differentName = new Cookie("trainerCookie2", "akila123");

        System.out.println("\n=== equals() checks ===");
        System.out.println("simple.equals(anotherSimpleSame): " + simple.equals(anotherSimpleSame)); // likely true if equal criteria match
        System.out.println("simple.equals(anotherSimpleDifferentValue): " + simple.equals(anotherSimpleDifferentValue)); // likely false
        System.out.println("simple.equals(differentName): " + simple.equals(differentName)); // false

        // You can also assert as needed:
        // Assert.assertTrue(simple.equals(anotherSimpleSame), "Cookies should be equal when name and value match (per your equality rule).");

        // 5) Iterate all cookies
        System.out.println("\n=== All cookies currently set ===");
        Set<Cookie> allCookies = driver.manage().getCookies();
        for (Cookie c : allCookies) {
            System.out.printf("Cookie -> %s | value=%s | domain=%s | path=%s | expiry=%s | sameSite=%s | secure=%s | httpOnly=%s%n",
                    c.getName(), c.getValue(), c.getDomain(), c.getPath(), c.getExpiry(), c.getSameSite(),
                    c.isSecure(), c.isHttpOnly());
        }

        // 6) validate(): validate cookie fields (throws if invalid)
        System.out.println("\n=== validate() ===");
        fetchedSimple.validate();     // should pass
        fetchedWithAttrs.validate();  // should pass

        // Example of invalid cookie to trigger validation error:
        // (E.g., empty name or invalid characters)
        try {
            Cookie invalid = new Cookie("", "value"); // invalid name
            invalid.validate(); // should throw
            Assert.fail("Expected validation to fail for invalid cookie");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation failed as expected for invalid cookie: " + e.getMessage());
        }

        // 7) Cleanup
        driver.manage().deleteCookieNamed("trainerCookie");
        driver.manage().deleteCookieNamed("prefs");
        Assert.assertNull(driver.manage().getCookieNamed("trainerCookie"));
        Assert.assertNull(driver.manage().getCookieNamed("prefs"));
    }
}