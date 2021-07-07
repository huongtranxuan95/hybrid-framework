package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {
	WebDriver driver;
	String userDir = System.getProperty("user.dir");
	protected WebDriver getDriverBroswer(String browserName) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());
		switch ( browser) {
		case FIREFOX:
			//System.setProperty("webdriver.gecko.driver", userDir+"\\browserDrivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case CHROME:
			//System.setProperty("webdriver.chrome.driver", userDir+"\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case EDGE:
			//System.setProperty("webdriver.edge.driver", userDir+"\\browserDrivers\\msedgedriver.exe");
			WebDriverManager.chromedriver().setup();
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
			//System.setProperty("webdriver.gecko.driver", userDir+"\\browserDrivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case CHROME:
			//System.setProperty("webdriver.chrome.driver", userDir+"\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case EDGE:
			//System.setProperty("webdriver.edge.driver", userDir+"\\browserDrivers\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case IE:
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
			break;
		case COCCOC:
			//System.setProperty("webdriver.chrome.driver", userDir+"\\browserDrivers\\chromedriver_coccoc.exe");
			WebDriverManager.chromedriver().driverVersion("90.0.4430.24").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
			//options.setBinary(urlCoccocBrowser);
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
