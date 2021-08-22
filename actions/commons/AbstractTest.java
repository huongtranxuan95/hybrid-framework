package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;

import driverFactory.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {
	WebDriver driver;
	String userDir = System.getProperty("user.dir");
	
	protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	
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
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
			break;
			
		default:
			throw new RuntimeException("Please input your browser");
		}
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	
	
	//getDriverBrowser use threadlocal
	protected WebDriver getDriverBroswerUseThreadLocal(String browserName, String url) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());
		switch ( browser) {
		case FIREFOX:
			//System.setProperty("webdriver.gecko.driver", userDir+"\\browserDrivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			setDriver(new FirefoxDriver());
			break;
		case CHROME:
			//System.setProperty("webdriver.chrome.driver", userDir+"\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			setDriver(new ChromeDriver());
			break;
		case EDGE:
			//System.setProperty("webdriver.edge.driver", userDir+"\\browserDrivers\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			setDriver(new EdgeDriver());
			break;
		case IE:
			WebDriverManager.iedriver().arch32().setup();
			setDriver(new InternetExplorerDriver());
			break;
		case COCCOC:
			//System.setProperty("webdriver.chrome.driver", userDir+"\\browserDrivers\\chromedriver_coccoc.exe");
			WebDriverManager.chromedriver().driverVersion("90.0.4430.24").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
			//options.setBinary(urlCoccocBrowser);
			setDriver(new ChromeDriver(options));
			break;
			
		default:
			throw new RuntimeException("Please input your browser");
		}
		threadLocalDriver.get().get(url);
		threadLocalDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return threadLocalDriver.get();
	}
	
	//getDriverBrowser don't use threadlocal
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
	
	// remove driver in threadlocalDriver
	protected void removeDriver() {
		threadLocalDriver.get().quit();
		threadLocalDriver.remove();
	}
	
	private WebDriver getDriver() {
		return threadLocalDriver.get();
	}
	
	private void setDriver(WebDriver driver) {
		threadLocalDriver.set(driver);
	}
	
	protected boolean checkTrue(boolean condition) {
		boolean pass= true;
		try {
			if (condition == true) {
				//log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				//log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
//			if (condition == false) {
//				log.info(" -------------------------- PASSED -------------------------- ");
//			} else {
//				log.info(" -------------------------- FAILED -------------------------- ");
//			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			 //log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			// log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	protected int randomInt() {
		Random ran = new Random();
		return ran.nextInt(100000) + 1000;
	}
	
	public void sleepSeconds(long timeout) {
		try {
			Thread.sleep(1000 * timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
