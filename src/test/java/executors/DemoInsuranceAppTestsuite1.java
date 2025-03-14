package executors;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import constants.CurrentConstants;
import managers.DataManager;
import managers.FunctionsManager;
import managers.ReportManager;
import managers.ScreenShotsManager;

public class DemoInsuranceAppTestsuite1 {
	
	//http://127.0.0.1:8000/
	
	public static CurrentConstants constantsObj = new CurrentConstants("chrome");
	
	public static ReportManager reporterM;
	public ReportManager testM;
	public static ScreenShotsManager screenshot;
	public static ExtentTest test;
	
	public DataManager dataM = new DataManager();
	public FunctionsManager functionM = new FunctionsManager();
	
	@BeforeMethod
	public void setUp() {
		ReportManager.reportGenerator();
		constantsObj.cd.manage().window().maximize();
		constantsObj.cd.get(constantsObj.cURL);
	}
	@Test
	public void loginTestN() {
		//simple single login, positive
		functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[2]", "frauheidelbergg");
		functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[3]", "ilogin123");
		functionM.clickByXPATH(constantsObj.cd,"/html/body/div[2]/form/button");
		
	}
	
	@Test
	public void loginTestP() {
		//simple single login, negative
		functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[2]", "frauheidelberg");
		functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[3]", "ilogin123");
		functionM.clickByXPATH(constantsObj.cd,"/html/body/div[2]/form/button");
		
	}
	

}
