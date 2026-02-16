package demo;
 
import org.testng.Assert;
import org.testng.annotations.DataProvider; // DataProvider annotation
import org.testng.annotations.Test;
 
public class Demo3_DataProvider {
 
  @DataProvider(name = "numbers") // ✅ DataProvider Method
//  public int[][] numbers() {
//	  int[][] data = {
//			  {1,2,3},
//			  {5,5,10},
//			  {10,20,30}};
//    return data;
//	  
//  }
  public Object[][] numbers() {
	  
	    return new Object[][]{
	        {1, 2, 3},     // a, b, expectedSum
	        {5, 5, 10},
	        {10, 20, 30}
	    };
	  }
 
  @Test(dataProvider = "numbers") // ✅ DataProvider Feature
  public void addTest(int a, int b, int expected) {
 
    int actual = a + b;
    Assert.assertEquals(actual, expected, "Sum mismatch!");
 
    System.out.println(a + " + " + b + " = " + actual);
  }
}
 