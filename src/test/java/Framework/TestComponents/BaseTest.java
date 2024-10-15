package Framework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Framework.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    
	public WebDriver driver;//initialize local driver
	public LandingPage landingPage;//initialize landingPage object inorder to utilize without declaration in submitOrderTest
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Framework//resources//GlobalDate.properties");
		//user.dir gives the local path of in the system
		prop.load(fis);
		String browserName =System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		// prop.getProperty("browser");
		//if(browserName.equalsIgnoreCase("chrome"))//driver initialization with head mode
//		{
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//		}
		if(browserName.contains("chrome"))//driver initialization head/headless mode based on run time
		{
			ChromeOptions options= new ChromeOptions();//utilize ChromOptions() to declare headless mode to the driver
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);//options is added in the argument of ChromeDriver class
			driver.manage().window().setSize(new Dimension(1440,900));//full screen diemnsion unlike maximize
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.iedriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			//initialize edgedriver
		}
			
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getDataToMap(String filePath) throws IOException
	{ 
		

	//json file to string	
	String jsonContent=FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
	//string to Hash map the below java code is used -JsonDataBind
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});//Converts String to json and stores in a list
    return data;
	
	}
	public String getScreenshot(String testCaseNAme, WebDriver driver) throws IOException
	   {
		   TakesScreenshot ts = (TakesScreenshot)driver;
		   File source = ts.getScreenshotAs(OutputType.FILE);
		   File file = new File(System.getProperty("user.dir")+ "//reports" + testCaseNAme + ".png");
		   FileUtils.copyFile(source, file);
		   return System.getProperty("user.dir")+ "//reports" + testCaseNAme + ".png";
		   }
	   
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();//launch the URL
	    return landingPage;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
		
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
