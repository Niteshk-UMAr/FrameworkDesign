package Framework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Framework.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	public WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> cartprods = driver.findElements(By.cssSelector("div[Class='cartSection'] h3"));// items in cart
	
    //Boolean match = cartprods.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(productName));

   //Assert.assertTrue(match);

  //driver.findElement(By.cssSelector(".totalRow button")).click();// click on checkout
	@FindBy(xpath="//div[@class='cartSection']/h3")//PageFactory for adding cart product to list
	List<WebElement> cartProducts;
	@FindBy(xpath="//button[contains(text(),'Checkout')]")//Page Factory for Check out
	WebElement checkoutEle;
	
	public Boolean verifyProductsDisplay(String productName)//Action method to check the product matches
	{
		Boolean match = cartProducts.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	public CheckoutPage goToCheckout() 
	{
		System.out.println("checkout button");
		checkoutEle.click();//Clicks on the check out button
		//CheckoutPage checkoutPage=new CheckoutPage(driver);
		//return checkoutPage;
		return new CheckoutPage(driver);
	}

}
