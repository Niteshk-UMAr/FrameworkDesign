package Framework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//String confirmmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	@FindBy(css=".hero-primary")
	WebElement confirmmessage;
	
	public String getConfirmationMessage()
	{
		return confirmmessage.getText();
	}

}



