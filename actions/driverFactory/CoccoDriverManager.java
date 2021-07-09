package driverFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CoccoDriverManager extends DriverManager{

	@Override
	protected void createDriver() {
		WebDriverManager.chromedriver().driverVersion("90.0.4430.24").setup();
		ChromeOptions options = new ChromeOptions();
		options.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
		//options.setBinary(urlCoccocBrowser);
		driver = new ChromeDriver(options);
	}

}
