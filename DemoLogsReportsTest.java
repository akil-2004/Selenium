package demo.tests; 

 

import org.testng.Assert; 

import org.testng.Reporter; 

import org.testng.annotations.Listeners; 

import org.testng.annotations.Test; 

import demo.listeners.MyTestListener; 

 

@Listeners(MyTestListener.class) 

public class DemoLogsReportsTest { 

 

    @Test(groups = "smoke") 

    public void loginTest() { 

        System.out.println("Console Log: loginTest running..."); 

        Reporter.log("Report Log: loginTest step executed", true); 

        Assert.assertTrue(true); 

    } 

 

    @Test(groups = "regression") 

    public void failureTest() { 

        Reporter.log("This test will fail to show failure in report", true); 

        Assert.fail("Intentional failure for demo"); 

    } 

 

    @Test(dependsOnMethods = "failureTest") 

    public void dependentTest() { 

        Reporter.log("This will be SKIPPED because dependency failed", true); 

        Assert.assertTrue(true); 

    } 

} 