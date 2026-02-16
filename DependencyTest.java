package demo;

import org.testng.Assert; // 2) assertions 

import org.testng.annotations.AfterClass; // 3) runs once after all tests in class 

import org.testng.annotations.AfterMethod; // 4) runs after each test method 

import org.testng.annotations.BeforeClass; // 5) runs once before all tests 

import org.testng.annotations.BeforeMethod; // 6) runs before each test method 

import org.testng.annotations.Test; // 7) @Test annotation 

 

public class DependencyTest { // 8) class 

 

  @BeforeClass // 9) ✅ Procedure: runs once before ANY @Test in this class 

  public void beforeClass() { 

    System.out.println("BeforeClass: starting DependencyTest"); 

  } 

 

  @BeforeMethod // 10) ✅ Procedure: runs before EACH @Test method 

  public void beforeMethod() { 

    System.out.println("BeforeMethod: setup"); 

  } 

 

  @Test(groups = {"smoke"}) // 11) group smoke 

  public void createUser() { 

    System.out.println("Step 1: createUser executed"); 

    Assert.assertTrue(true); // 12) passes 

  } 

 

  @Test(groups = {"smoke"}, dependsOnMethods = {"createUser"}) 

  // 13) ✅ Dependency: loginUser runs ONLY if createUser passed 

  public void loginUser() { 

    System.out.println("Step 2: loginUser executed (depends on createUser)"); 

    Assert.assertTrue(true); 

  } 

 

  @Test(groups = {"smoke"}, dependsOnMethods = {"loginUser"}) 

  // 14) ✅ Dependency: logoutUser runs ONLY if loginUser passed 

  public void logoutUser() { 

    System.out.println("Step 3: logoutUser executed (depends on loginUser)"); 

  } 

 

  @AfterMethod // 15) ✅ Procedure: runs after EACH @Test method 

  public void afterMethod() { 

    System.out.println("AfterMethod: cleanup"); 

  } 

 

  @AfterClass // 16) ✅ Procedure: runs once after ALL @Test methods finish 

  public void afterClass() { 

    System.out.println("AfterClass: finished DependencyTest"); 

  } 

} 