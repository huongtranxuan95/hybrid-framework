package come.liveguru.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
//
public class User_01_Login_To_System {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		// run on browser firefox
		System.setProperty("webdriver.gecko.driver",".\\browserDrivers\\geckodriver.exe" );
		driver = new FirefoxDriver();
		
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS );
		
	}
	
	@Test
	public void Login_01_Empty_Data() {
	}

	@Test
	public void Login_02_Invalid_Email() {
	}

	
	@Test
	public void Login_03_Email_Not_Exit() {
	}

	@Test
	public void Login_04_Password_Less_Than_Six_Character() {
	}

	@Test
	public void Login_05_Password_Incorrect() {
	}
	
	@Test
	public void Login_06_Email_And_Password_Valid() {
	}

	

	@AfterClass
	public void afterClass() {
	}

}
