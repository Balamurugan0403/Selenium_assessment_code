package seleniumtestng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewTest {
	public WebDriver driver;
  @Test
  public void loginTest() {
	  driver.findElement(By.xpath("//div[@id=\"navbarExample\"]/ul/li[5]/a")).click();
	  driver.findElement(By.id("loginusername")).sendKeys("karthiksiva");
	  driver.findElement(By.id("loginpassword")).sendKeys("karthik@123");
	  driver.findElement(By.xpath("//button[text()=\"Log in\"]")).click();
	  
	  
	  
  }
  @BeforeTest
  public void beforeTest() {
	  System.out.println("start the test");
	  ChromeOptions options=new ChromeOptions();
	  options.addArguments("--start-maximized");
//	  options.addArguments("--headless");
	  driver=new ChromeDriver(options);
	  driver.get("https://demoblaze.com/");
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	  
	  
  }

  @AfterTest
  public void afterTest() {
  }

}
