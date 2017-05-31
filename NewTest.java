package testAutomation.testing;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class NewTest {
	public static WebDriver wd;
	@Test  
  public void titleVerification() {
		String actual = wd.getTitle();
		String Expected = "Google";
		Assert.assertEquals(actual, Expected);
		System.out.println("Execution Completed");
		System.out.println("Completed");
		
  }

@BeforeMethod
public void methodBefore(){
	System.out.println("Before Method");
}

@AfterMethod
public void methodAfter(){
	System.out.println("After Method");
}

  @BeforeTest
  public void beforeTest() {
	  wd = testAutomation.testing.SetupWebDriverClass.getDriver("Firefox");
	  wd.get("https://www.google.com/");  
      
  }

  @AfterTest
  public void afterTest() {
	  wd.quit();
  }
}
