package Framework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Framework.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatalog(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);//pageFactory class declared in constructor. Constructor is the first method executed in this class
	}
    
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); 
	@FindBy(css=".mb-3")//Page Factory
    List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
    
    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toast=By.cssSelector("#toast-container");
    
    
    public List<WebElement> getProductsList()
    {
    	waitForElementToAppear(productsBy);
    	return products;
    	
    }
    
   // WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
  //prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
   // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));// toaster message
	//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));// load image
    public WebElement getProductByName(String productName)//Action method for finding the element 
    {
    	WebElement prod=getProductsList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
    	return prod;
    }
    
    public void addProductToCart(String productName)//Action class for add to cart
    {
    	WebElement prod= getProductByName(productName);//storing the product into a variable
    	prod.findElement(addToCart).click();//form product find add to cart and click on it
    	waitForElementToAppear(toast);//wait untill toast message displayed
    	waitForElementToDisappear(spinner);//Wait untill loading message to disappear
    	
    }
}