package come.liveguru.user;

import org.testng.annotations.Test;

//import commons.AbstractPage;
import commons.AbstractTest;
import driverFactory.DriverFactory;
import driverFactory.DriverManager;
import pageObjects.livegurru.AboutUsPageObject;
//import commons.Browser;
//import commons.ByLocator;
import pageObjects.livegurru.AddressBookPageObject;
import pageObjects.livegurru.AdvanceSearcgPageObject;
import pageObjects.livegurru.CustomerServicePageObject;
import pageObjects.livegurru.HomePageObject;
import pageObjects.livegurru.LoginAdminPageObject;
import pageObjects.livegurru.LoginPageObject;
import pageObjects.livegurru.ManageCustomersAdminPageObject;
import pageObjects.livegurru.MyDashBroadPageObject;
import pageObjects.livegurru.PageGeneratorManager;
import pageObjects.livegurru.RegisterPageObject;
import pageObjects.livegurru.SearchTermPageObject;

import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

//import java.util.Random;
//import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_12_Handle_Data_Table_Grid extends AbstractTest {
	private WebDriver driver;
	private DriverManager driverManager;
	LoginAdminPageObject loginAdminPage;
	ManageCustomersAdminPageObject manageCustomPage;
	String userDir= System.getProperty("user.dir");
	
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driverManager = DriverFactory.getBrowserDriver(browserName);
		//driver = getDriverBroswer(browserName, appUrl);
		driver = driverManager.getDriver(appUrl);
		
		loginAdminPage = PageGeneratorManager.getLoginAdminPage(driver);
		loginAdminPage.inputUsernameTextbox("user01");
		loginAdminPage.inputPasswordTextbox("guru99com");
		loginAdminPage.clickToLoginButton();
		manageCustomPage = PageGeneratorManager.getManagerCustomersAdminPage(driver);
	}
	@Test
	public void TC_01_Register() {

	}
	
	@Test
	public void TC_02_Address() {

	}

	@Test
	public void TC_Search_Form_Data_Table_Grid() {
		manageCustomPage.closePopup();
		manageCustomPage.inputToCustomerTableTextboxByColumnName("Email","namnguyen1995@gmail.com");
		manageCustomPage.inputToCustomerTableTextboxByColumnName("Name","nam");
		manageCustomPage.clickSearchButton();
		Assert.assertTrue(manageCustomPage.isValueDisplayedAtColumnNameByRowIndex("Email","1", "namnguyen1995@gmail.com"));
		Assert.assertTrue(manageCustomPage.isValueDisplayedAtColumnNameByRowIndex("Name","1", "nam nguyen"));
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
