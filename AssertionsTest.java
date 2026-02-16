package demo; 

import org.testng.Assert; 

import org.testng.annotations.Test; 

import org.testng.asserts.SoftAssert; 


public class AssertionsTest { 

 

    // ---------------- HARD ASSERTION DEMO ---------------- 

    @Test 

    public void hardAssertionDemo() { 

 

        System.out.println("Step 1: Start hard assertion demo"); 

 

        // rd assertion: if this fails, test STOPS here immediately 

        Assert.assertEquals(2 + 2, 4, "Math failed!"); 

 

        System.out.println("Step 2: This prints because previous assert passed"); 

 

        //Intentionally failing hard assert (for training demo) 

        Assert.assertTrue("Selenium".contains("Java"), "Hard Assert Failed: Selenium does not contain Java"); 

 

        // This line will NEVER run because above hard assert fails 

        System.out.println("Step 3: You will NOT see this line"); 

    } 

 

    // ---------------- SOFT ASSERTION DEMO ---------------- 

    @Test 

    public void softAssertionDemo() { 

 

        System.out.println("Step 1: Start soft assertion demo"); 

 

        SoftAssert soft = new SoftAssert(); 

 

        // Soft asserts: even if they fail, test continues 

        soft.assertEquals(2 + 2, 4, "Math failed!"); 

        System.out.println("Step 2: Still running..."); 

 

        // Intentionally failing soft assert 

        soft.assertTrue("Selenium".contains("Java"), "Soft Assert Failed: Selenium does not contain Java"); 

        System.out.println("Step 3: Still running even after failure"); 

 

        //Another failing soft assert 

        soft.assertEquals("TestNG", "testng", "Soft Assert Failed: Case mismatch"); 

        System.out.println("Step 4: Still running... collected all failures"); 

 

        // MOST IMPORTANT LINE: 

        // This will finally FAIL the test if any soft asserts failed 

        soft.assertAll(); 

 

        // This line runs only if all soft asserts passed 

        System.out.println("Step 5: This prints only if ALL soft asserts passed"); 

    } 

} 