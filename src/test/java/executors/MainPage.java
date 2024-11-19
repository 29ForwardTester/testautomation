package executors;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class MainPage {
	
	static WebDriver wd;
	
	
	@BeforeClass
	//@Parameters("browser")
	public static void firstAccess(String mainUrl, String browser) {
		
		
		  if(browser.equalsIgnoreCase("chrome")) {
			  wd = new ChromeDriver();
		  }
		  else if(browser.equalsIgnoreCase("firefox")){
			  wd = new FirefoxDriver();
		  }
		  wd.manage().window().maximize();
		  wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		  wd.get(mainUrl);
	}

}
