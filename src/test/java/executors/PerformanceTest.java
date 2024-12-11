package executors;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import managers.ReportManagerPerf;
import managers.ScreenShotsManager;
import managers.DataManager;
import managers.FunctionsManager;
import constants.CurrentConstants;
//not a load test: no putting of a heavy load on the system!
	//just checking the answer-back time after sending a single request
public class PerformanceTest {
	
	
	public WebDriver driver;
	public String url;
	public static CurrentConstants current = new CurrentConstants("chrome");
	public static ReportManagerPerf reporterM;
	public ReportManagerPerf testM;
	public static ScreenShotsManager screenshot;
	public static ExtentTest test;
	
	public DataManager dataM = new DataManager();
	public FunctionsManager functionM = new FunctionsManager();
	
	
//log the single steps?
	
	@BeforeMethod
	public void setUp() throws Exception {
		
		ReportManagerPerf.reportGenerator(); //a report has been generated / initialized
		DataManager.setDataFile(CurrentConstants.path+CurrentConstants.fileName,CurrentConstants.sheet);//test-data file being recognized
		current.cd.manage().window().maximize();
		current.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		current.cd.get(current.cURL);
		current.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	
	}
	@Test(groups="GSgroup")
	public void test_performance1() {
		//with single user: time acquired for navigating between functions. not a load test!
		long startTime = System.currentTimeMillis();
		long endTime;
		long duration;
		
		String login = "standard_user";
		String password = "secret_sauce";
		ExtentTest test11 = ReportManagerPerf.testGenerator("Test_Performance1", "LOGIN INFORMATION[ username:  <b>" + login + "</b> , password:  <b>"+ password+ "</b>]");
		test11.assignAuthor("Achouak Hassini");
		try {
			functionM.inputByID(current.cd, "user-name", login);
			functionM.inputByID(current.cd, "password", password);
			functionM.clickByID(current.cd, "login-button");
			functionM.clickByID(current.cd,"add-to-cart-sauce-labs-bike-light");
			functionM.clickByID(current.cd,"add-to-cart-sauce-labs-onesie");
			functionM.clickByXPATH(current.cd,"//a[@data-test=\"shopping-cart-link\"]");
			
			endTime = System.currentTimeMillis();
			duration = endTime - startTime;
			test11.info("++++++++++time duration in nanoseconds:  "+duration);
			test11.info("++++++++++time duration in seconds:  "+(duration / 1000000000.0));
			//remove items from last test
			functionM.clickByID(current.cd,"react-burger-menu-btn");
			functionM.clickByID(current.cd,"logout_sidebar_link");
			functionM.inputByID(current.cd, "user-name", "standard_user");
			functionM.inputByID(current.cd, "password", "secret_sauce");
			functionM.clickByID(current.cd, "login-button");
			functionM.clickByXPATH(current.cd, "//button[@id=\"remove-sauce-labs-backpack\"]");
			functionM.clickByXPATH(current.cd, "//button[@id=\"remove-sauce-labs-bike-light\"]");
			functionM.clickByXPATH(current.cd, "//button[@id=\"remove-sauce-labs-onesie\"]");
			functionM.clickByID(current.cd,"react-burger-menu-btn");
			functionM.clickByID(current.cd,"logout_sidebar_link");
					//page now reset
			}
		catch (Exception e) {
			test11.fail("login information incorrect"); //catch the test code execution failure
			
		}
	}
	@Test(groups="GSgroup")
	public void test_performance2() {
		//with single user: time acquired for navigating between functions. not a load test!
		long startTime = System.currentTimeMillis();
		long endTime;
		long duration;
		
		String login = "performance_glitch_user";
		String password = "secret_sauce";
		ExtentTest test12 = ReportManagerPerf.testGenerator("Test_Performance2 ", "LOGIN INFORMATION[ username:  <b>" + login + "</b> , password:  <b>"+ password+ "</b>]");
		test12.assignAuthor("Achouak Hassini");
		try {
			functionM.inputByID(current.cd, "user-name", login);
			functionM.inputByID(current.cd, "password", password);
			functionM.clickByID(current.cd, "login-button");
			functionM.clickByID(current.cd,"add-to-cart-sauce-labs-bike-light");
			functionM.clickByID(current.cd,"add-to-cart-sauce-labs-onesie");
			functionM.clickByXPATH(current.cd,"//a[@data-test=\"shopping-cart-link\"]");
			
			endTime = System.currentTimeMillis();
			duration = endTime - startTime;
			test12.info("++++++++++time duration in nanoseconds:  "+duration);
			test12.info("++++++++++time duration in seconds:  "+(duration / 1000000000.0));
			//remove items from last test
			functionM.clickByID(current.cd,"react-burger-menu-btn");
			functionM.clickByID(current.cd,"logout_sidebar_link");
			functionM.inputByID(current.cd, "user-name", "standard_user");
			functionM.inputByID(current.cd, "password", "secret_sauce");
			functionM.clickByID(current.cd, "login-button");
			functionM.clickByXPATH(current.cd, "//button[@id=\"remove-sauce-labs-backpack\"]");
			functionM.clickByXPATH(current.cd, "//button[@id=\"remove-sauce-labs-bike-light\"]");
			functionM.clickByXPATH(current.cd, "//button[@id=\"remove-sauce-labs-onesie\"]");
			functionM.clickByID(current.cd,"react-burger-menu-btn");
			functionM.clickByID(current.cd,"logout_sidebar_link");
					//page now reset
			}
		catch (Exception e) {
			test12.fail("login information incorrect"); //catch the test code execution failure
			
		}
	}
	@AfterMethod(onlyForGroups="GSgroup")
	public void tearDown(ITestResult testResult) throws IOException {
		

		test = ReportManagerPerf.testGenerator( testResult.getMethod().getMethodName() + ": screenshot", "screenshot taken at the end of execution of the testmethod");
		String screenpath = ScreenShotsManager.takeScreenshot(current.cd, testResult.getMethod().getMethodName());//generate a screenpath
		test.addScreenCaptureFromPath(screenpath);
		
		ReportManagerPerf.reportFlush();
		
		//important not to close the driver, otherwise invalid id session
		
	 }
	


}
