package pageObjects.livegurru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import commons.ByLocator;

public class RegisterPageObject {
	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		// TODO Auto-generated method stub

	}

	public String getRequireErrorMessageAtFirstnameTextbox() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRequireErrorMessageAtLastnameTextbox() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRequireErrorMessageAtEmailTextbox() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRequireErrorMessageAtPasswordTextbox() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRequireErrorMessageAtConfirmTextbox() {
		// TODO Auto-generated method stub
		return null;
	}

	public void inputToEmailTextbox(String string) {
		// TODO Auto-generated method stub

	}

	public String getInvalidErrorMessageAtEmailTextbox() {
		// TODO Auto-generated method stub
		return null;
	}

	public void inputToFirstnameTextbox(String firstName) {
		// TODO Auto-generated method stub

	}

	public void inputToLastnameTextbox(String lastname) {
		// TODO Auto-generated method stub

	}

	public void inputToPasswordTextbox(String string) {
		// TODO Auto-generated method stub

	}

	public void inputToConfirmPassTextbox(String string) {
		// TODO Auto-generated method stub

	}


	public String getInvalidErrorMessageAtPasswordTextbox() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getInvalidErrorMessageAtConfirmTextbox() {
		// TODO Auto-generated method stub
		return null;
	}

}
