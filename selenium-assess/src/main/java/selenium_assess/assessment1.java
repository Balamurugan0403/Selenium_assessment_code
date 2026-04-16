package selenium_assess;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class assessment1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//1. Login to the application and verify whether successful login.
		//adding driver
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demoblaze.com/");
		WebElement login=driver.findElement(By.xpath("//div[@id=\"navbarExample\"]/ul/li[5]/a"));
		login.click();
		WebElement username=driver.findElement(By.id("loginusername"));
		username.sendKeys("karthiksiva");
		WebElement password=driver.findElement(By.id("loginpassword"));
		password.sendKeys("karthik@123");
		WebElement loginbtn=driver.findElement(By.xpath("//button[text()=\"Log in\"]"));
		loginbtn.click();
		String actualtext="Welcome karthiksiva";
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"navbarExample\"]/ul/li/a[@id=\"nameofuser\"]")));
		String logintext=element.getText();
		if(actualtext.equals(logintext)) {
			System.out.println("Login Successfull");
		}
		else {
			System.out.println("Not login");
		}
		
		//2. Category Navigation & Product Handling}
		
		WebElement laptops = driver.findElement(By.xpath("//a[text()='Laptops']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(laptops).click().perform();
		laptops = wait.until(ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//div[@id='tbodyid']/div/div/div/h4/a[text()='MacBook Pro']")));
		List<WebElement> laptopList = new ArrayList<>();
		laptopList = driver.findElements(By.xpath("//div[@id='tbodyid']/div/div/div/h4/a"));
		laptopList.sort(Comparator.comparing(WebElement::getText));
		Set<String> laptopSet = new HashSet<>();
		for (WebElement i : laptopList) {
		    laptopSet.add(i.getText());
		    System.out.println(i.getText());
		    if ("MacBook Pro".equals(i.getText())) {
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].scrollIntoView(true);", i);
		        System.out.println("Found Laptop: " + i.getText());
		    }
		}
		if (laptopSet.size() > 0) {
		    System.out.println("Values are stored");
		} else {
		    System.out.println("Values are not stored");
		}
		
		//3. 
		driver.quit();

	}

}
