package Framework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import Framework.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	public WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	@FindBy(className="btnn action__submit ng-star-inserted")//PageFactory for place order
	WebElement submit;
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	By results =By.cssSelector(".ta-results");

	public void selectCountry(String countryName) throws InterruptedException	{
		System.err.println(driver.getTitle());
		Thread.sleep(4000);
		Actions a = new Actions(driver);//Actions class is used to select autosuggest dropdown
//		country.click();
		WebElement count = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		System.out.println(count);
		count.click();
		a.sendKeys(count, countryName).build().perform();//Through send keys country is entered into the element
		Thread.sleep(7000);
		waitForElementToAppear(results);//wait for the element to appear
		WebElement selectcount = driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
		selectcount.click();
		
		//selectCountry.click();//clickon india element
	}

	public ConfirmationPage submit()
	{
		WebElement placeEle = driver.findElement(By.xpath("//a[contains(text(),'Place Order')]"));// click on place order

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", placeEle);
		return new ConfirmationPage(driver);
	}

}
