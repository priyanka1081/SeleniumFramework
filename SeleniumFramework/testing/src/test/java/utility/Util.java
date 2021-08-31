package utility;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import driverInitialize.DriverInst;
import readExcelFile.ReadXlsFile;

public class Util {

	boolean status = false ;
	public WebDriver driver = DriverInst.getInstance().getDriver() ;
	ReadXlsFile excel = new ReadXlsFile();

	public boolean clickToButtonWithXpath(String rowData) {
		status = false;
		String value = excel.getCellData(rowData);
		if(value !=null && !value.trim().isEmpty()) {
			By element = By.xpath(value);
			WebElement button = driver.findElement(element);
			if(button.isEnabled()) {
				button.click();
				status = true;
			}
		}
		return status;
	}

	public boolean displayButtonWithXpath(String rowData) {
		status = false;
		String value = excel.getCellData(rowData);
		if(value !=null && !value.trim().isEmpty()) {
			By element = By.xpath(excel.getCellData(rowData));
			WebElement button = driver.findElement(element);
			if(button.isDisplayed()) {
				status = true;
			}
		}
		return status;
	}

	public boolean enterValueWithXpath(String rowData, String value) {
		status = false;
		String cellValue = excel.getCellData(rowData);
		if(cellValue !=null && !cellValue.trim().isEmpty()) {
			By element = By.xpath(excel.getCellData(rowData));
			WebElement button = driver.findElement(element);
			if(button.isEnabled()) {
				button.sendKeys(value);
				status = true;
			}
		}
		return status;
	}

	public String caputreScreenshot(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" +System.currentTimeMillis() + ".png";
		File desti = new File(path);
		try {
			FileHandler.copy(src, desti);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}





}
