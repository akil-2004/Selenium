package com.training.pomdemo.base;
import java.util.*;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.openqa.selenium.remote.CapabilityType;
/**
 * SINGLE FILE DEMO:
 * - Why capabilities? In Selenium 4 (W3C WebDriver), a browser session is created from "capabilities":
 *   a set of requested features and settings (browser name, version, platform, pageLoadStrategy,
 *   acceptInsecureCerts, proxy, logging, and vendor-specific options). ChromeOptions is a typed
 *   builder for Chrome’s vendor capabilities ("goog:chromeOptions") and some W3C-standard ones.
 *   You pass ChromeOptions into ChromeDriver to create a session; afterward you can read back the
 *   "negotiated" capabilities to confirm what actually took effect.
 *
 * - This class demonstrates most commonly used ChromeOptions methods and capabilities in ONE place.
 */
public class ChromeOptionsAndCapabilitiesSingleDemo {

    @Test
    public void fullChromeOptionsAndCapabilitiesDemo() {
        WebDriver driver = null;

        // --------------------------
        // 1) Construct ChromeOptions
        // --------------------------
        ChromeOptions options = new ChromeOptions();

        // A) Command-line arguments (window, headless, language, incognito, disabling notifications, etc.)
        options.addArguments(
                "--start-maximized",
                "--window-size=1920,1080",
                "--disable-notifications",
                "--disable-gpu",
                "--incognito",
                "--lang=en-US"
                // Modern headless; uncomment if you want headless runs:
                // ,"--headless=new"
        );

        // B) Accept insecure certificates (W3C standard capability)
        options.setAcceptInsecureCerts(true);

        // C) Optional: Custom Chrome/Chromium binary (uncomment and set a valid path if needed)
        // options.setBinary("/path/to/custom/chrome");

        // D) Extensions (uncomment and set valid .crx paths if you want to load extensions)
        // File adblock = new File("/absolute/path/adblock.crx");
        // options.addExtensions(adblock);

        // E) Experimental options (prefs, mobile emulation, exclude switches, debugger attach)
        // E1) Preferences (download dir, block notifications, etc.)
        Map<String, Object> prefs = new HashMap<>();
        // Change to a valid path on your machine:
        prefs.put("download.default_directory", System.getProperty("java.io.tmpdir"));
        prefs.put("download.prompt_for_download", false);
        prefs.put("safebrowsing.enabled", true);
        prefs.put("profile.default_content_setting_values.notifications", 2); // 1=allow, 2=block
        options.setExperimentalOption("prefs", prefs);

        // E2) Mobile emulation example (choose either deviceName OR deviceMetrics+userAgent)
        Map<String, Object> mobileEmulation = new HashMap<>();
        // Predefined device:
        // mobileEmulation.put("deviceName", "Pixel 7");
        // OR custom metrics:
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 412);
        deviceMetrics.put("height", 915);
        deviceMetrics.put("pixelRatio", 2.625);
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 13) AppleWebKit/537.36 "
                + "(KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        // E3) Exclude switches (to suppress "Chrome is being controlled by automated software" infobar)
        options.setExperimentalOption("excludeSwitches", List.of("enable-automation"));

        // E4) Attach to an already-running Chrome instance via DevTools (optional)
        // Start Chrome manually: chrome --remote-debugging-port=9222
        // options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // F) Logging (Performance logs, etc.). This is done via capabilities.
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL); // collect performance logs
        // "goog:loggingPrefs" is the Chrome-specific capability name for logs
        options.setCapability("goog:loggingPrefs", logPrefs);

        // G) Proxy (W3C standard). Uncomment and set your corporate proxy if needed.
        // Proxy proxy = new Proxy();
        // proxy.setHttpProxy("proxy.mycorp.local:8080");
        // proxy.setSslProxy("proxy.mycorp.local:8080");
        // options.setCapability("proxy", proxy);

        // H) Page load strategy & unhandled prompt behavior (W3C standard)
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); // or EAGER, NONE
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);

        // --------------------------
        // 2) (Optional) Build a service to capture driver logs into a file
        // --------------------------
        ChromeDriverService service = new ChromeDriverService.Builder()
                .withSilent(true)
                // .withLogFile(new File("chromedriver.log")) // uncomment to persist chromedriver logs
                .build();

        // --------------------------
        // 3) Create the driver with options (capabilities get negotiated here)
        // --------------------------
        driver = new ChromeDriver(service, options);

        try {
            // --------------------------
            // 4) Use the driver and print capabilities that were actually applied
            // --------------------------
            driver.get("https://the-internet.herokuapp.com/");

            // The driver implements HasCapabilities → lets you inspect negotiated caps
            Capabilities caps = ((HasCapabilities) driver).getCapabilities();

            System.out.println("========= NEGOTIATED CAPABILITIES ========");
            System.out.println("browserName            : " + caps.getBrowserName());
            System.out.println("browserVersion         : " + caps.getBrowserVersion());
            System.out.println("platformName           : " + caps.getPlatformName());
            
           // System.out.println("acceptInsecureCerts    : " + caps.isAcceptInsecureCerts());
//            Object chromeOptionsCap = caps.getCapability("goog:chromeOptions");
//            if (chromeOptionsCap instanceof Map) {
//                @SuppressWarnings("unchecked")
//                Map<String, Object> map = (Map<String, Object>) chromeOptionsCap;
//                System.out.println("chromeOptions.args             : " + map.get("args"));
//                System.out.println("chromeOptions.prefs            : " + map.get("prefs"));
//                System.out.println("chromeOptions.mobileEmulation  : " + map.get("mobileEmulation"));
//                System.out.println("chromeOptions.excludeSwitches  : " + map.get("excludeSwitches"));
//                System.out.println("chromeOptions.binary           : " + map.get("binary"));
//            }
            
            System.out.println("pageLoadStrategy       : " + caps.getCapability("pageLoadStrategy"));
            System.out.println("unhandledPromptBehavior: " + caps.getCapability("unhandledPromptBehavior"));
            System.out.println("proxy                  : " + caps.getCapability("proxy"));
            System.out.println("goog:chromeOptions     : " + caps.getCapability("goog:chromeOptions"));
            System.out.println("goog:loggingPrefs      : " + caps.getCapability("goog:loggingPrefs"));

            // You can also cast goog:chromeOptions map if needed:
            Object chromeOptionsCap = caps.getCapability("goog:chromeOptions");
//            if (chromeOptionsCap instanceof Map<?, ?> map) {  //ERROR HERE IN ININSTANCE OF 
//                System.out.println("chromeOptions.args     : " + map.get("args"));
//                System.out.println("chromeOptions.prefs    : " + map.get("prefs"));
//                System.out.println("chromeOptions.mobileEmulation: " + map.get("mobileEmulation"));
//                System.out.println("chromeOptions.excludeSwitches: " + map.get("excludeSwitches"));
//                System.out.println("chromeOptions.binary   : " + map.get("binary"));
//            }
            
           

         // Option A: via CapabilityType constant
         Object aic = caps.getCapability(CapabilityType.ACCEPT_INSECURE_CERTS);
         System.out.println("acceptInsecureCerts    : " + (aic instanceof Boolean ? (Boolean) aic : null));

         // Option B: via literal key (equivalent)
         Boolean aic2 = Boolean.TRUE.equals(caps.getCapability("acceptInsecureCerts")) ? Boolean.TRUE
                         : (Boolean) caps.getCapability("acceptInsecureCerts");
         System.out.println("acceptInsecureCerts    : " + aic2);
        

            // --------------------------
            // 5) Simple assert/log to indicate the page title was loaded
            // --------------------------
            System.out.println("Page title: " + driver.getTitle());

        } finally {
            // Always end the session cleanly
            if (driver != null) {
                driver.quit();
            }
        }
    }

    /**
     * Utility: shows how you could merge arbitrary W3C capabilities with ChromeOptions
     * (usually not needed, but useful if you must set a raw capability).
     */
    private static MutableCapabilities mergeWithExtraCaps(ChromeOptions options) {
        MutableCapabilities extra = new MutableCapabilities();
        extra.setCapability("someCustomKey", "someValue");
        // options.merge(extra); // you can merge if needed
        return options;
    }
}