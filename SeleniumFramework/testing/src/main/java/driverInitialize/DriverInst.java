package driverInitialize;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverInst {
	 
	public WebDriver driver;
	static DriverInst driverinst =null;
	public String baseUrl = "http://jt-dev.azurewebsites.net/#/SignUp";
	String driverPath = "./chromedriver91.exe";
	
	public static DriverInst getInstance() {
		if(driverinst==null) {
			driverinst = new DriverInst();
		}
		return driverinst;
	}
	
	private DriverInst() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		this.driver = new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.manage().window().maximize();
		this.driver.get(baseUrl);
	}
	
	public WebDriver getDriver() {
		return this.driver;		
	}

}
