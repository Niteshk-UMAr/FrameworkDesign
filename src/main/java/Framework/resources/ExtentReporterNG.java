package Framework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	public static ExtentReports getReportObject()
	{
	String path =System.getProperty("user.dir")+"\\reports\\index.html";//extent file should be stored in a file under project>reports>index.html
	ExtentSparkReporter reporter = new ExtentSparkReporter(path);//ExtentSparkReporter(helper class) class is created and path is inscribed into the arguement
	reporter.config().setReportName("Web automation Results");//to set the name of the reporter in extent file
	reporter.config().setDocumentTitle("Test Results");//to set the name of the title of the document
	ExtentReports extent = new ExtentReports();//main class 
	extent.attachReporter(reporter);//we have to attach the object of helper class to main class object, so helper configs are included in main class
	extent.setSystemInfo("Tester", "Selenium");//to set the tester name	
	return extent;
	
	}
}
