package Framework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);//pageFactory class declared in constructor. Constructor is the first method executed in this class
	}
	//WebElement useremails = driver.findElement(By.id("userEmail"));// this line is no longer needed as we are using page factory below
	@FindBy(id="userEmail")//Page Factory
	WebElement userEmail;//Variable declaration
	//driver.findElement(By.id("userPassword"))
	@FindBy(id="userPassword")
	WebElement passwordEle;//element for password 
	//driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement submitbutton; //login button
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage ;//WebElement(toaster) displayed for incorrect email and password entry    
	
	public ProductCatalog loginApplication(String email,String password)//Action method for login 
	{
		userEmail.sendKeys(email);//email mentioned here is feteched from submitorder() by declaring
		passwordEle.sendKeys(password);//password mentioned here is feteched from submitorder() by declaring
		submitbutton.click();//click on login button
		
		ProductCatalog productsCatalog = new ProductCatalog(driver);//creating object of products catalog class
		return productsCatalog;    
	}
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()//navigate to the URL
	{
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
	}
}