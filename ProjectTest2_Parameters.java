package demo;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ProjectTest2_Parameters {
	@Test
	@Parameters({"browser","env"})
	public void showParans(String browser, String env) {
		System.out.println("Browser = ");
	}

}
