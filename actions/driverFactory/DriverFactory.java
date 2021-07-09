package driverFactory;

import org.openqa.selenium.WebDriver;

import commons.Browser;

public class DriverFactory {
	public static DriverManager getBrowserDriver(String browserName) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());
		DriverManager driverManager = null;
		
		switch (browser) {
		case FIREFOX:
			driverManager = new FirefoxDriverManager();
		
			break;
		case CHROME:
			driverManager = new ChromeDriverManager();
			
			break;
		case EDGE:
			driverManager = new EdgeDriverManager();
			
			break;
		case IE:
			driverManager = new IEDriverManager();
			
			break;
		case COCCOC:
			driverManager = new CoccoDriverManager();
			break;

		default:
			throw new RuntimeException("Please choose your browser");
		}
		return driverManager;
	}
}
