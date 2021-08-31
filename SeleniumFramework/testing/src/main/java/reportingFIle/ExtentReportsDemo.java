package reportingFIle;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import driverInitialize.DriverInst;

public class ExtentReportsDemo {

	ExtentReports extent =null;
	ExtentTest tester = null;
	WebDriver driver = DriverInst.getInstance().getDriver();

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	Date date = new Date();
	String reportName = formatter.format(date).toString();
	
	public ExtentReportsDemo() {
		System.out.println(reportName);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportName+"Report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		tester = extent.createTest("Test" );
		tester.log(Status.INFO,"*** Test is started ***");
	}

	public void statusLogger(boolean status, String desc) throws Exception {
		if(status) {
			tester.log(Status.PASS, desc);
		}
		else {
			tester.log(Status.FAIL, desc);
			TakesScreenshot ts = (TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshots/" +System.currentTimeMillis() + ".png";
			File desti = new File(path);
			try {
				FileHandler.copy(src, desti);
				tester.fail(desc , MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	public void addSccreenshot(String path) {
		try {
			tester.log(Status.INFO, "Test Completed",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void teardown() {
		extent.flush();
	}
	
	

}
