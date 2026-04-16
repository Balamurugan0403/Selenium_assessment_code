package seleniumtestng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoTest {
  @Test
  public void f() {
  }
  @Test(priority=1)
  public void A() {
	  System.out.println("hi");
  }
  @Test(priority=1)
  public void B() {
	  System.out.println("hello");
  }
  @Test(dependsOnMethods="A")
  public void C() {
	  System.out.println("bye");
  }
  @BeforeMethod
  public void beforemethod() {
	  System.out.println("this is bfore method");
  }
  @AfterMethod
  public void aftermethod() {
	  System.out.println("this is after method");
  }
  @AfterTest
  public void aftertest() {
	  System.out.println("this is after test");
  }
  @BeforeTest
  public void beforetest() {
	  System.out.println("this is bfore test");
  }
  @AfterSuite
  public void aftersuite() {
	  System.out.println("this is after suite");
  }
  @BeforeSuite
  public void beforesuite() {
	  System.out.println("this is before suite");
  }
}
