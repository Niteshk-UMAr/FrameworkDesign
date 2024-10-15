package Framework.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.PageObjects.CartPage;
import Framework.PageObjects.CheckoutPage;
import Framework.PageObjects.ConfirmationPage;
import Framework.PageObjects.LandingPage;
import Framework.PageObjects.OrderPage;
import Framework.PageObjects.ProductCatalog;
import Framework.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	 String productName = "ZARA COAT 3";
	         @Test(dataProvider= "getData",groups= {"Purchase"})
	         public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	         {
	        			
		   ProductCatalog productsCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));//passing email and password to be entered into the arguement, which is then provided to loinApplication()		
		List<WebElement> products=productsCatalog.getProductsList();//using getProductList() to extract all the products and assign it to a variable
		productsCatalog.addProductToCart(input.get("product"));//both finding name and add to cart are executed in this method
		System.out.println("Prod display success");
		
		CartPage cartPage = productsCatalog.goToCart();//navigate to cart page		
		Boolean match=cartPage.verifyProductsDisplay(input.get("product"));//Pass product into argument so to be utilized in class
		Assert.assertTrue(match);// checking whether the match boolean variable is returning true(Means it is acquiring Zaracoat3)
		System.out.println("added to cart");
		
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("india");//select country
		
		
		ConfirmationPage confrimationPage=checkoutPage.submit();//click on place order button
		String confirmmessage=confrimationPage.getConfirmationMessage();		
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		// Applying assertion to compare the text with the web element
	  }
	   
	   @Test(dependsOnMethods= {"submitOrder"})      
	   public void OrderHistoryTest()//to check the products in the orders page
	   {
		   ProductCatalog productsCatalog = landingPage.loginApplication("abdeveliers@gmail.com", "Test@123");
		   OrderPage orderPage=productsCatalog.goToOrdersPage();
		   Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	   }
		
	   
	
	   @DataProvider
	   public Object[][] getData() throws IOException
	   {
		   //1.manual scenario
		   //return new Object[][]  {{"abdeveliers@gmail.com","Test@123","ZARA COAT 3"}, {"msdhoni@yopmail.com","Test@123","ADIDAS ORIGINAL"}};//for direct method entry
//		   HashMap<String,String> map = new HashMap<String,String>();
//		   map.put("email", "abdeveliers@gmail.com");
//		   map.put("password", "Test@123");
//	   map.put("product", "ZARA COAT 3");
//	   
//	   HashMap<String,String> map1 = new HashMap<String,String>();
//	   map1.put("email","msdhoni@yopmail.com");
//	   map1.put("password", "Test@123");
//		   map1.put("product", "ADIDAS ORIGINAL");		  
//		  		   
		   //2.Using Json file to fetch the data
		  List<HashMap<String,String>> data=getDataToMap(System.getProperty("user.dir")+"//src//test//java//Framework//data//PurchaseOrder.json");
		   return new Object[][]  {{data.get(0)},{data.get(1)}};  
		  // return new Object[][]  {{map},{map1}};  
	   }
	}

