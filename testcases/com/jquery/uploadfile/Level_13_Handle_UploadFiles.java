package com.jquery.uploadfile;

import org.testng.annotations.Test;

//import commons.AbstractPage;
import commons.AbstractTest;
import driverFactory.DriverFactory;
import driverFactory.DriverManager;
import pageObjects.bankguru.PageGenerationManager;
import pageObjects.jquery.JqueryUploadFilePageObject;
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

public class Level_13_Handle_UploadFiles extends AbstractTest {
	private WebDriver driver;
	private DriverManager driverManager;
	JqueryUploadFilePageObject uploadFilePage;
	
	String manualImg = "Manual.png";
	String appiumImg = "Appium.png";
	String seleniumImg = "Selenium.png";
	
	String url="";
	
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driverManager = DriverFactory.getBrowserDriver(browserName);
		//driver = getDriverBroswer(browserName, appUrl);
		driver = driverManager.getDriver(appUrl);
		uploadFilePage = pageObjects.jquery.PageGenerationManager.getJqueryUploadFilePage(driver);
		url = appUrl;
	}
	@Test
	public void TC_01_UploadOneFileOneTime() {
		//uploadMutipleFiles(driver, "01.png","02.png","03.png");
		uploadFilePage.uploadOnePicture( manualImg);
		uploadFilePage.sleepSeconds(4);
		uploadFilePage.uploadOnePicture(appiumImg);
		sleepSeconds(4);
		
		uploadFilePage.uploadOnePicture( seleniumImg);
		sleepSeconds(4);

	}
	@Test
	public void TC_01_UploadMutipleFile() {
		uploadFilePage.uploadThreePicture(manualImg, appiumImg , seleniumImg);
		uploadFilePage.sleepSeconds(4);
	}
	
	@Test
	public void TC_02_Address() {

	}
		

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
