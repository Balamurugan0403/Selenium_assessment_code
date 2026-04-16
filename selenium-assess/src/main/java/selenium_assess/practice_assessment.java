package selenium_assess;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class practice_assessment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoblaze.com/");
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement login=driver.findElement(By.xpath("//div[@id=\"navbarExample\"]/ul/li[5]/a"));
		login.click();
		WebElement username1=driver.findElement(By.id("loginusername"));
		wait.until(ExpectedConditions.visibilityOf(username1)).sendKeys("karthiksiva");
		WebElement password=driver.findElement(By.id("loginpassword"));
		password.sendKeys("karthik@123");
		WebElement loginbtn=driver.findElement(By.xpath("//button[text()=\"Log in\"]"));
		loginbtn.click();
		String actualtext="Welcome karthiksiva";
		
//		WebElement logintextelement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id=\"nameofuser\"]")));
//		String logintext=logintextelement.getText();
		//here use of the textToBePresentInElementLocated it will first locate and check whether the text present in that element.
		
		Boolean check=wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//a[@id=\"nameofuser\"]"), actualtext));
		if(check) {
			System.out.println("login successful");
		}
		else {
			System.out.println("login not successful");
		}
		
		
		// 2.Locates to laptop category
		WebElement laptop=driver.findElement(By.xpath("//a[@id=\"itemc\" and text()=\"Laptops\"]"));
		laptop.click();
		//finding no of laptops 
		
		
//        List<WebElement>li=driver.findElements(By.xpath("//div[@id='tbodyid']//a[@class='hrefch']"));
////		System.out.println(li.size());
//        wait.until(ExpectedConditions.visibilityOfAllElements(li));
		
		//that above is wrong because listed all elements before showing laptops then we used wait
		
//		List<WebElement>li=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='tbodyid']//a[@class='hrefch']")));
        //above ,see that after laptop clicks it still have elements like phones ,that also condition satisfied like visisbilty of all elements
		
//		List<WebElement>li=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='tbodyid']//a[@class='hrefch' and text()=\"Sony vaio i5\"]")));

		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(
//			    By.xpath("//a[text()='Sony vaio i5']")
//			));
//		List<WebElement> li = driver.findElements(
//			    By.xpath("//div[@id='tbodyid']//a[@class='hrefch']")
//			);
//		System.out.println("Laptops list:");
//		for(WebElement list:li) {
//			System.out.println(list.getText());
//		}

		// wait until a phone category disappears
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
		    By.xpath("//a[text()='Samsung galaxy s6']")
		));
		// then listing the laptops by locating
		List<WebElement> li = driver.findElements(
		    By.xpath("//div[@id='tbodyid']//a[@class='hrefch']")
		);

		Set<String> laptops=new <String>TreeSet();
		
		for (WebElement list : li) {
//		    System.out.println(list.getText());
			laptops.add(list.getText());
		}
		JavascriptExecutor js=(JavascriptExecutor) driver;
		WebElement element=driver.findElement(By.xpath("//a[text()=\"MacBook Pro\"]"));
		js.executeScript("arguments[0].scrollIntoView(true);",element);
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();
		
		String producttitle=element.getText();

		if (producttitle.equals("MacBook Pro")) {
		    System.out.println("Found Laptop: " +producttitle);
		}
		//display laptops list after sorting
		System.out.println("Laptops list:");
		for(String Laptop:laptops) {
		System.out.println(Laptop);
		}
		
		// 3. Add a product to cart
		//click the macbook product
		WebElement macbookproduct=driver.findElement(By.xpath("//a[text()=\"MacBook Pro\"]"));
		macbookproduct.click();
		//adding to cart
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=\"Add to cart\"]"))).click();
		//the alert message appears getting the alert message and accepting
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		String addtocart=alert.getText();
		alert.accept();
		//printing the alert message
		System.out.println(addtocart);
		driver.findElement(By.xpath("//a[@id=\"cartur\"]")).click();
		//wait until the table row visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class='success']")));
		String actualtitle = driver.findElement(By.xpath("//tbody[@id='tbodyid']/tr/td[2]")).getText();
		String actualprice = driver.findElement(By.xpath("//tbody[@id='tbodyid']/tr/td[3]")).getText();
		// validating whether matches with product added to cart
		if (actualtitle.equals("MacBook Pro")) {
		    System.out.println("Product added to cart");
		    System.out.println("MacBook Pro added to cart.");
		} else {
		    System.out.println("Product not added correctly");
		}
		//printing the product and its price
		System.out.println("Title: "+actualtitle);
		System.out.println("Price: "+actualprice);
		
		//4.place order
		// wait until the place order button clickable and click
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Order']"))).click();
		// filling the details
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("siva");
		driver.findElement(By.id("country")).sendKeys("India");
		driver.findElement(By.id("city")).sendKeys("Salem");
		driver.findElement(By.id("card")).sendKeys("123456789");
		driver.findElement(By.id("month")).sendKeys("5");
		driver.findElement(By.id("year")).sendKeys("2026");
		// clicking the purchase button
		driver.findElement(By.xpath("//button[text()='Purchase']")).click();
		// get the confirmation message
		WebElement confirmBox=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sweet-alert")));
		String purchase=confirmBox.getText();
		// confirming the order
		if (purchase.contains("Id")) {
		    System.out.println("Order is placed successfully");
		    String[] details = purchase.split("\n");
		    // printing the details
		    for (String detail : details) {
		        if (detail.contains("Id") || detail.contains("Amount") || detail.contains("Date")) {
		            System.out.println(detail);
		        }
		    }

		}  else {
		    System.out.println("Order is Unsuccessful");
		}
		
		//5. handle the alert
		try {
		    WebDriverWait wait1= new WebDriverWait(driver, Duration.ofSeconds(10));
		    Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
		    alert1.accept();
		    System.out.println("Alert handled successfully.");
		} catch (TimeoutException e) {
		    System.out.println("No alert present.");
		}
	}

}
