package questionTwo;

import org.testng.annotations.Test;

import driverInitialize.DriverInst;
import readExcelFile.ReadXlsFile;
import reportingFIle.ExtentReportsDemo;
import utility.Util;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class FightUI {
	
	public WebDriver driver = DriverInst.getInstance().getDriver() ;
	ExtentReportsDemo report = new ExtentReportsDemo();
	Util util = new Util();
	ReadXlsFile readexcel = new ReadXlsFile();
	boolean status = false;
	
	@BeforeTest  
	public void beforeTest() {    
	System.out.println("before test");  
	}     
	@AfterTest  
	public void afterTest() {
	driver.quit(); 
	report.teardown();
	System.out.println("after test");  
	}
	
	@Test
	public void test() throws Exception {
		status = false;
		try {
			status = util.clickToButtonWithXpath("Dropdown");    
			report.statusLogger(status, "clicked on language dropdown");
			Assert.assertTrue(util.displayButtonWithXpath("EnglishDD")); 
			Assert.assertTrue(util.displayButtonWithXpath("DutchDD"));
			report.statusLogger(status, "checked dropdown values");
			
			status &= util.enterValueWithXpath("Name", "testing name");
			report.statusLogger(status, "entered name");
			
			status &= util.enterValueWithXpath("Organistion" , "test organisation"); 
			report.statusLogger(status, "entered organization");
			
			status &= util.enterValueWithXpath("Email", "testemail@gmail.com"); 
			report.statusLogger(status, "Enter email");
			
			status &= util.clickToButtonWithXpath("Agree"); 
			report.statusLogger(status, "Clicked on agree button");
			
			status &= util.clickToButtonWithXpath("Start"); 
			report.statusLogger(status, "clicked on get started");
			
			Assert.assertTrue(util.displayButtonWithXpath("SuccessMsg"));
			report.statusLogger(status, "validated success msg");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}finally {
			String screensht = util.caputreScreenshot(driver);
			report.addSccreenshot(screensht);
		}
		
		
		
	}
	
	

}
