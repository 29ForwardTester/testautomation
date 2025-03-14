package constants;

//class to manage the constant values of the current test, to be modified directly here

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class CurrentConstants {
	
	public String cURL ;
	
	public WebDriver cd ;
//	public static final String path = "C:\\Users\\User29F\\Desktop\\testData\\";
//	public static final String fileName = "mydata.xls";
//	public static final String sheet ="Sheet1";
	
//	public static String dbURL = "jdbc:mysql://localhost:3306/sakila";
//	public static String dbUSERNAME = "root";
//	public static String dbPASSWORD = "tempsprintanier+!HOOK!022";
	
	public static String dbDAppURL = "jdbc:mysql://localhost:3306/demoinsuranceapp";
	public static String dbDAppUSERNAME = "root";
	public static String dbDAppPASSWORD = "tempsprintanier+!HOOK!022";
	
	
	public CurrentConstants(String wd) {
		this.cURL = "http://127.0.0.1:8000/";
		if(wd.contains("chrome")) {
			this.cd = new ChromeDriver();
		}
		if(wd.contains("firefox")) {
			this.cd = new FirefoxDriver();
	
	
		}
	}
}
