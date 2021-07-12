package pageUIs.liveguru;

public class MyDashBroadPageUI {
	public static final String WELCOME_MSG_SUCCESSFUL_XPATH = "//span[text()='Thank you for registering with Main Website Store.']";
	public static final String HELLO_MSG_USERNAME_XPATH = "//div[@class='welcome-msg']//strong";
	public static final String CONTACT_INFORMATION_XPATH = "//a[text()='Change Password']//parent::p";
	public static final String ADDRESS_BOOK_LINK_XPATH = "//a[contains(.,'Address Book')]";
	public static final String LOGOUT_LINK_HEADER_XPATH ="//div[@id='header-account']//a[text()='Log Out']";
	public static final String MY_DASHBROAD_HEADER_XPATH = "//div[@class='page-title']//h1[text()='My Dashboard']";
	public static final String ACCOUNT_LINK_HEADER_XPATH = "//header[@id='header']//a[@class='skip-link skip-account']";
}
