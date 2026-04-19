package selenium_assess;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Practice01 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		ChromeOptions options=new ChromeOptions();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		driver.get("https://automationexercise.com/");
		
		String actualUrl=driver.getCurrentUrl();
		System.out.println(actualUrl);
		SoftAssert soft=new SoftAssert();
		soft.assertTrue(actualUrl.contains("automationexercise"),"url invalid");
//	      Assert.assertTrue(driver.getCurrentUrl().contains("automationexercise"),
//	                "Home page not loaded");
//		if(actualUrl.equals("https://automationexercise.com/")) {
			WebElement cart=driver.findElement(By.xpath("//a[text()=\" Cart\"]"));
			cart.click();
			wait.until(ExpectedConditions.urlContains("https://automationexercise.com/view_cart"));
			WebElement footer=driver.findElement(By.id("footer"));
			Thread.sleep(2000); // before scroll
			js.executeScript("arguments[0].scrollIntoView({block:'center'});", footer);
			Thread.sleep(2000); // after scroll
			String expectedtext="SUBSCRIPTION";
			WebElement actualtext=driver.findElement(By.xpath("//footer[@id=\"footer\"]//div[@class=\"single-widget\"]/h2"));
			if(expectedtext.equals(actualtext.getText())){
				driver.findElement(By.id("susbscribe_email")).sendKeys("skbalamurugan1205@gmail.com");
				String expectedmsg="You have been successfully subscribed!";
				driver.findElement(By.id("subscribe")).click();
				WebElement subselement=driver.findElement(By.className("alert-success"));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className("alert-success")));
				String actualmsg=subselement.getText();
				if(actualmsg.equals(expectedmsg)) {
					System.out.println("test case runned successfully");
				}
				
			}
			soft.assertAll();
		
		Thread.sleep(5000);
		driver.close();

	}

}
