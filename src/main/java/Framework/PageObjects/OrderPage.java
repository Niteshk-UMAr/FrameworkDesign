package Framework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Framework.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	public WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="tr td:nth-child(3)")//PageFactory for adding cart product to list
	List<WebElement> productNames;
	
	

	public boolean verifyOrderDisplay(String productName) {
		// TODO Auto-generated method stub
		Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName)); 
		return match;
	}
	
	
}


