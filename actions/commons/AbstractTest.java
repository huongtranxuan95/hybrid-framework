package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
	protected WebDriver getDriverBroswer(String browserName, String url) {
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
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
	protected WebDriver getDriverBroswer(String browserName, String url, String urlCoccocBrowser) {
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
		case COCCOC:
			System.setProperty("webdriver.chrome.driver", userDir+"\\browserDrivers\\chromedriver_coccoc.exe");
			ChromeOptions options = new ChromeOptions();
			//options.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
			options.setBinary(urlCoccocBrowser);
			driver = new ChromeDriver(options);
			break;

		default:
			throw new RuntimeException("Please input your browser");
		}
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
}
