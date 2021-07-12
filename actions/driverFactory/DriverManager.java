package driverFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {//
	protected WebDriver driver;
	protected abstract void createDriver();
	
	public WebDriver getDriver() {
		if(driver==null) {
			createDriver();
		}
		return driver;
	}
	
	public WebDriver getDriver(String appUrl) {
		if(driver==null) {
			createDriver();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(appUrl);
		return driver;
	}
	
	public void quitDriver() {
		if (driver!= null) {
			driver.quit();
		}
	}
}
