package pageObjects.jquery;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.ByLocator;
import pageUIs.liveguru.AbstractPageUI;

public class JqueryUploadFilePageObject extends AbstractPage{
	WebDriver driver;

	public JqueryUploadFilePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void uploadOnePicture(String manualImg) {
		waitElementPresence(driver, ByLocator.XPATH, AbstractPageUI.UPLOAD_FILE_TYPE);
		uploadMutipleFiles(driver, manualImg);
		
		
	}

	public void uploadThreePicture(String manualImg, String appiumImg, String seleniumImg) {
		waitElementPresence(driver, ByLocator.XPATH, AbstractPageUI.UPLOAD_FILE_TYPE);
		uploadMutipleFiles(driver, manualImg, appiumImg, seleniumImg);
		
	}

	
	
}
