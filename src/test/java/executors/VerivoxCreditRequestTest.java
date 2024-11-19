package executors;

import constants.CurrentConstants;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class VerivoxCreditRequestTest {
	
	
	
	public static WebDriver chromeDr = new ChromeDriver();
	public static String currentURL = "https://www.verivox.de";
	
	
	public static void setUp() {
		chromeDr.manage().window().maximize();
		chromeDr.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		chromeDr.get(currentURL);
		chromeDr.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		chromeDr.findElement(By.xpath("//div[@class='cmp-container first-load']//button[contains(@class, 'gdpr-deny-all')]")).click();
		chromeDr.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
//		chromeDr.findElement(By.linkText("Kredit")).click();
		}
	
	

	public static void main(String[] args) {
		
		setUp();
		
		
		
		
		
		
	}

}
