package Framework.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.PageObjects.CartPage;
import Framework.PageObjects.CheckoutPage;
import Framework.PageObjects.ConfirmationPage;
import Framework.PageObjects.ProductCatalog;
import Framework.TestComponents.BaseTest;
import Framework.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	         @Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	         public void LoginErrorValidation() throws IOException, InterruptedException
	         {
	        	 String productName = "ZARA COAT 3";		
		   landingPage.loginApplication("abdevelierss@gmail.com", "Test@1234");//passing email and password to be entered into the arguement, which is then provided to loinApplication()		
	       Assert.assertEquals("Incorrect email  password", landingPage.getErrorMessage());
	         }
	       
	       @Test(retryAnalyzer=Retry.class)
	       public void ProductErrorValidation() throws InterruptedException
	         {
	        	 String productName = "ZARA COAT 3";		
		   ProductCatalog productsCatalog = landingPage.loginApplication("abdeveliers@gmail.com", "Test@123");//passing email and password to be entered into the arguement, which is then provided to loinApplication()		
		List<WebElement> products=productsCatalog.getProductsList();//using getProductList() to extract all the products and assign it to a variable
		productsCatalog.addProductToCart(productName);//both finding name and add to cart are executed in this method
		System.out.println("Prod display success");
		
		CartPage cartPage = productsCatalog.goToCart();//navigate to cart page		
		Boolean match=cartPage.verifyProductsDisplay(productName);//Pass product into argument so to be utilized in class
		Assert.assertTrue(match);// checking whether the match boolean variable is returning true(Means it is acquiring Zaracoat3)
		System.out.println("added to cart");
		
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("india");//select country
		
		
		ConfirmationPage confrimationPage=checkoutPage.submit();//click on place order button
		String confirmmessage=confrimationPage.getConfirmationMessage();		
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		// Applying assertion to compare the text with the web element
		
		
	}
	
		
	}

