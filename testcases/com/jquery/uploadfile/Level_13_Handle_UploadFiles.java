package com.jquery.uploadfile;

import org.testng.annotations.Test;

import commons.AbstractTest;
import driverFactory.DriverFactory;
import driverFactory.DriverManager;

import pageObjects.jquery.JqueryUploadFilePageObject;
import pageObjects.jquery.PageGenerationManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;

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
		
		url = appUrl;
	}
	@BeforeMethod
	public void getLoadPage() {
		driver.get(url);
	}
	@Test
	public void TC_01_UploadOneFileOneTime() {
		uploadFilePage = PageGenerationManager.getJqueryUploadFilePage(driver);
		//uploadMutipleFiles(driver, "01.png","02.png","03.png");
		uploadFilePage.uploadOnePicture( manualImg);
		uploadFilePage.sleepSeconds(4);
		Assert.assertTrue(uploadFilePage.isImageLoaded(manualImg));
		uploadFilePage.clickToStartButton(manualImg);
		Assert.assertTrue(uploadFilePage.isImageUpLoaded(manualImg));
		
		uploadFilePage.uploadOnePicture(appiumImg);
		sleepSeconds(4);
		Assert.assertTrue(uploadFilePage.isImageLoaded(appiumImg));
		uploadFilePage.clickToStartButton(appiumImg);
		Assert.assertTrue(uploadFilePage.isImageUpLoaded(appiumImg));
		
		uploadFilePage.uploadOnePicture( seleniumImg);
		sleepSeconds(4);
		Assert.assertTrue(uploadFilePage.isImageLoaded(seleniumImg));
		uploadFilePage.clickToStartButton(seleniumImg);
		Assert.assertTrue(uploadFilePage.isImageUpLoaded(seleniumImg));
		
		
		uploadFilePage.clickToImageLink(manualImg);
		uploadFilePage.verifyImage(manualImg);

	}
	@Test
	public void TC_02_UploadMutipleFile() {
		uploadFilePage = PageGenerationManager.getJqueryUploadFilePage(driver);
		//uploadFilePage.refreshPage(driver);
		uploadFilePage.uploadThreePicture(manualImg, appiumImg , seleniumImg);
		uploadFilePage.sleepSeconds(4);
		Assert.assertTrue(uploadFilePage.isImageLoaded(manualImg));
		Assert.assertTrue(uploadFilePage.isImageLoaded(appiumImg));
		Assert.assertTrue(uploadFilePage.isImageLoaded(appiumImg));
		uploadFilePage.clickToStartButton(manualImg);
		uploadFilePage.clickToStartButton(appiumImg);
		uploadFilePage.clickToStartButton(seleniumImg);
		Assert.assertTrue(uploadFilePage.isImageUpLoaded(manualImg));
		Assert.assertTrue(uploadFilePage.isImageUpLoaded(appiumImg));
		Assert.assertTrue(uploadFilePage.isImageUpLoaded(seleniumImg));
	}
	
	@Test
	public void TC_02_Address() {

	}
		

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
