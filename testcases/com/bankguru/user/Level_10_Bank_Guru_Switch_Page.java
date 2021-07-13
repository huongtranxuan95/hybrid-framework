package com.bankguru.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import driverFactory.DriverFactory;
import driverFactory.DriverManager;
import pageObjects.bankguru.BankHomePageObject;
import pageObjects.bankguru.BankManagerPageObject;
import pageObjects.bankguru.DepositPageObject;
import pageObjects.bankguru.NewAccountPageObject;
import pageObjects.bankguru.NewCustomerPageObject;
import pageObjects.bankguru.PageGenerationManager;
import pageObjects.bankguru.WithdrawalPageObject;

public class Level_10_Bank_Guru_Switch_Page extends AbstractTest {
	
	private WebDriver driver;
	private DriverManager driverManager;
	private BankHomePageObject bankHomePage;
	private BankManagerPageObject bankManagerHomePage;
	private NewCustomerPageObject newCustomerPage;
	private NewAccountPageObject newAccountPage;
	private WithdrawalPageObject withdrawalPage;
	private DepositPageObject depositPage;
	
	//private String email = "";
	private  String username=""; 
	private String pass="";
	
	//url = http://demo.guru99.com/v3/
	
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driverManager = DriverFactory.getBrowserDriver(browserName);
		//driver = getDriverBroswer(browserName, appUrl);
		driver = driverManager.getDriver(appUrl);
		
		username="mngr341100";
		pass="hAzAnas";
		
		bankHomePage = PageGenerationManager.getBankHomePage(driver);
		
	}
	
	@Test
	public void TC_01_LoginWithUIdAndPassValid() {
		bankHomePage.inputUserIDTextbox(username);
		bankHomePage.inputPasswordTextbox(pass);
		bankManagerHomePage=bankHomePage.clickToButtonLogin();
	}
	
	@Test
	public void TC_02_SwitchPage() {
		newCustomerPage = bankManagerHomePage.openNewCustomerPage(driver);
		newAccountPage = newCustomerPage.openNewAccountPage(driver);
		withdrawalPage = newAccountPage.openWithdrawalPage(driver);
		bankManagerHomePage = withdrawalPage.openManagerPage(driver);
		depositPage = bankManagerHomePage.openDepositPage(driver);
		newCustomerPage = depositPage.openNewCustomerPage(driver);
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
