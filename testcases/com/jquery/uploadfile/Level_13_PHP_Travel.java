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
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_13_PHP_Travel extends AbstractTest {
	private WebDriver driver;
	private DriverManager driverManager;
	JqueryUploadFilePageObject uploadFilePage;
	
	String manualImg = "Manual.png";
	String appiumImg = "Appium.png";
	String seleniumImg = "Selenium.png";
	
	String url="";
	JavascriptExecutor js;
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driverManager = DriverFactory.getBrowserDriver(browserName);
		//driver = getDriverBroswer(browserName, appUrl);
		driver = driverManager.getDriver(appUrl);
		js = (JavascriptExecutor)driver;
		url = appUrl;
	}
	@BeforeMethod
	public void getLoadPage() {
		driver.get(url);
	}
	@Test
	public void TC_01_UploadOneFileOneTime() {
		sleepSeconds(1);
		driver.findElement(By.xpath("//button[@id='cookie_stop']")).click();
		sleepSeconds(10);
		System.out.println("1");
		

	}
	
	
	@Test
	public void TC_02_Address() {
		System.out.println("1.1");
		//driver.findElement(By.xpath("//button[@type='submit']")).click();
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[@type='submit']")));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@type='submit']")));
		System.out.println("2");
		sleepSeconds(2);
	}
		

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
