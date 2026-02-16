package demo;
import org.testng.Assert; // 2) TestNG assertion class (used for validation) 
import org.testng.annotations.Factory; // 3) Factory annotation 
import org.testng.annotations.Test; // 4) Test annotation 
public class FactoryTest { // 5) Test class 

 

  private final String name; // 6) A value stored in each object instance 

 

  // 7) Constructor: Factory will create objects and pass value here 

  public FactoryTest(String name) { 

    this.name = name; // 8) Store that value inside this object 
    
  } 

  @Test(groups = {"smoke"}) // 9) ✅ Grouping: this test belongs to "smoke" 

  public void printNameSmoke() { // 10) test method 

 

    System.out.println("Smoke test running for: " + name); 

    // 11) prints which factory object is running 

 

    Assert.assertNotNull(name, "Name should not be null"); 

    // 12) assertion: if name is null => test FAIL 

  } 

 

  @Test(groups = {"regression"}) // 13) ✅ Grouping: belongs to "regression" 

  public void printNameRegression() { 

 

    System.out.println("Regression test running for: " + name); 

    Assert.assertTrue(name.length() > 0, "Name should not be empty"); 

  } 

 

  @Factory // 14) ✅ Factory in Use: TestNG calls this to create test objects 

  public static Object[] createObjects() {  

    // 15) must return Object[] containing test class objects 

    return new Object[] { 

      new FactoryTest("Amlan"), // 16) object 1 

      new FactoryTest("Sai"),   // 17) object 2 

      new FactoryTest("Mohan")  // 18) object 3 

    }; 

  } 

} 
