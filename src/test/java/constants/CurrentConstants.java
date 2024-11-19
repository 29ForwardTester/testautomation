package constants;

//class to manage the constant values of the current test, to be modified directly here

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CurrentConstants {
	
	public String cURL ;
	
	public WebDriver cd ;
	
	
	public CurrentConstants(String wd) {
		this.cURL = "https://www.saucedemo.com";
		if(wd.contains("chrome")) {
			this.cd = new ChromeDriver();
		}
		if(wd.contains("firefox")) {
			this.cd = new FirefoxDriver();
	
	
		}
	}
}
