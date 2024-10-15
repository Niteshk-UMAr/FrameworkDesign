 package Framework.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Framework.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));// implicitly wait for 10sec
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.manage().window().maximize();
		String item = "ZARA COAT 3";
		driver.findElement(By.id("userEmail")).sendKeys("abdeveliers@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));// Explicit wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));// wait for products to load
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));// capturing all the products into
																					// products list
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(item)).findFirst()
				.orElse(null);
		// Capturing the ZARA element into a variable
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		// in second element finding(inCSS 'last-of-type' is used so to select last tag
		// name)and click on add to cart button

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));// toaster message
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));// load image
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		List<WebElement> cartprods = driver.findElements(By.cssSelector("div[Class='cartSection'] h3"));// items in cart
																										// to be added
																										// into list
		Boolean match = cartprods.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(item));
		// iterating through all items and by anyMatch() we are comparing that added
		// item is "Zara coat 3". We are grabbing it into a boolean variable
		Assert.assertTrue(match);// checking whether the match boolean variable is returning true(Means it is
									// acquiring Zaracoat3)
		driver.findElement(By.cssSelector(".totalRow button")).click();// click on checkout

		Actions a = new Actions(driver);// actions class is used to handle the autosuggest country dropdown
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		// sendkeys(WebElement,value)
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		// wait until the auto suggest dropdown is visible
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();// click on india
		WebElement placeEle = driver.findElement(By.xpath("//a[text()='Place Order ']"));// click on place order

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", placeEle);
        //driver.findElement(By.xpath("//div/a[@class='btnn action__submit ng-star-inserted']")).click();
		String confirmmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		// grabbing the thank you message into a variable
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		// Applying assertion to compare the text with the web element
	}
}
