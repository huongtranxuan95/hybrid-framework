package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class AbstractTest {
	WebDriver driver;
	String userDir = System.getProperty("user.dir");
	protected WebDriver getDriverBroswer(String browserName) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());
		switch ( browser) {
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", userDir+"\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver", userDir+"\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case EDGE:
			System.setProperty("webdriver.edge.driver", userDir+"\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		case IE:
			
			break;

		default:
			throw new RuntimeException("Please input your browser");
		}
		
		return driver;
	}
}
