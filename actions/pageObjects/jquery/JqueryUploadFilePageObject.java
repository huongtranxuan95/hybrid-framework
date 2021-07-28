package pageObjects.jquery;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.ByLocator;
import pageUIs.jquery.JqueryUploadFilePageUI;
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

	public boolean isImageLoaded(String manualImg) {
		waitElementVisible(driver,JqueryUploadFilePageUI.IMAGE_NAME_LOADED_XPATH, manualImg);
		return isElementDisplayed(driver, JqueryUploadFilePageUI.IMAGE_NAME_LOADED_XPATH, manualImg);
	}

	public void clickToStartButton(String manualImg) {
		waitElementVisible(driver,JqueryUploadFilePageUI.BUTTON_UPLOAD_START_XPATH, manualImg);
		clickToElement(driver, JqueryUploadFilePageUI.BUTTON_UPLOAD_START_XPATH, manualImg);
	}

	public boolean isImageUpLoaded(String manualImg) {
		waitElementVisible(driver,JqueryUploadFilePageUI.IMAGE_LINK_XPATH, manualImg);
		return isElementDisplayed(driver,JqueryUploadFilePageUI.IMAGE_LINK_XPATH, manualImg);
	}

	public void clickToImageLink(String manualImg) {
		waitElementClickable(driver,JqueryUploadFilePageUI.IMAGE_LINK_XPATH, manualImg);
		clickToElement(driver, JqueryUploadFilePageUI.IMAGE_LINK_XPATH, manualImg);
	}

	public boolean verifyImage(String name) {
		//String targetImgPath = "E:\\AutomationTest\\03-Hybrid-framework\\uploadFiles\\Manual.png";
		String targetImgPath = getFilePathUploadFolder() + name;
		System.out.println("path: "+targetImgPath);
		waitElementVisible(driver, JqueryUploadFilePageUI.IMAGE_LINK_LOAD, name);
		return compareImage(driver, JqueryUploadFilePageUI.IMAGE_LINK_LOAD,targetImgPath, name);
	}

	
	
}
