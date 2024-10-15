package Framework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.PageObjects.CartPage;
import Framework.PageObjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartHeader;
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderHeader;				
	
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); explicit wait method
	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	//driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
	
        public void waitForWebElementToAppear(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
		
	public OrderPage goToOrdersPage() //to navigate to ordersPage
	{
		orderHeader.click();
		
		OrderPage orderPage= new OrderPage(driver);//create an object for CartPage
		return orderPage;
	}
	public CartPage goToCart()
	{
		cartHeader.click();
		
		CartPage cartPage= new CartPage(driver);//create an object for CartPage
		return cartPage;
	}
	public void waitForElementToDisappear(WebElement Ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(Ele));
	}

}
