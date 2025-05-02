package executors;

import java.io.IOException;
import java.time.Duration;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
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
		ReportManager.reportGenerator();//a report is created for the whole test suite
		//DataManager.setDataFile(null, null);// test data file here(see arguments requirements in constants/CurrentConstants.java)
		//data file (in .xls format) must match the given structure already established in the current file in order to be correctly read!!!)
		constantsObj.cd.manage().window().maximize();
		constantsObj.cd.get(constantsObj.cURL);
	}
	@Test
	public void _Test1() throws IOException {
		//simple single login, negative
		String login = "frauheidelbergg";
		String password = "ilogin123";
		ExtentTest test1 = ReportManager.testGenerator("Test1: functional negative login test with a single data set", "LOGIN INFORMATION[ username:  <b>" + login + "</b> , password:  <b>"+ password+ "</b>]");
		test1.assignAuthor("Achouak Hassini");
		try {
			functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[2]",login);
			functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[3]",password);
			functionM.clickByXPATH(constantsObj.cd,"/html/body/div[2]/form/button");
			if(functionM.selectorXPATHExists(constantsObj.cd, "//p[text()='Wählen Sie ein Menu']")) {
				test1.pass("login successeful");
			} else {
				test1.fail("login information incorrect");
			}
		} catch(Exception e){
			test1.fail("login information incorrect"); //catch code execution failure
		}
		String screenpath = ScreenShotsManager.takeScreenshot(constantsObj.cd,"Test 1");//generate a screenpath
		test1.addScreenCaptureFromPath(screenpath);
	}
	
	@Test
	public void _Test2() throws IOException {
		//simple single login, positive
		String login = "frauheidelberg";
		String password = "ilogin123";
		ExtentTest test2 = ReportManager.testGenerator("Test2: functional positive login test with a single data set", "LOGIN INFORMATION[ username:  <b>" + login + "</b> , password:  <b>"+ password+ "</b>]");
		test2.assignAuthor("Achouak Hassini");
		try {
			functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[2]",login);
			functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[3]",password);
			functionM.clickByXPATH(constantsObj.cd,"/html/body/div[2]/form/button");
			if(functionM.selectorXPATHExists(constantsObj.cd, "//p[text()='Wählen Sie ein Menu']")) {
				test2.pass("login successeful");
			} else {
				test2.fail("login information incorrect");
			}
		} catch(Exception e){
			test2.fail("login information incorrect"); //catch code execution failure
		}
		String screenpath = ScreenShotsManager.takeScreenshot(constantsObj.cd, "Test 2");//generate a screenpath
		test.addScreenCaptureFromPath(screenpath);
	}
	
	@Test
	public void _Test3() throws IOException {
		//positive login
		//add new Kunde, correct entries
		String login = "frauheidelberg";
		String password = "ilogin123";
		String vornameK = "Sabrina";
		String nachnameK = "Daudet";
		ExtentTest test3 = ReportManager.testGenerator("Test3: positive login test with a adding a new Kunde", "LOGIN INFORMATION[ username:  <b>" + login + "</b> , password:  <b>"+ password+ "</b>]");
		test3.assignAuthor("Achouak Hassini");
		try {
			functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[2]",login);
			functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[3]",password);
			functionM.clickByXPATH(constantsObj.cd,"/html/body/div[2]/form/button");
		//add new Kunde
			constantsObj.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
			functionM.clickByID(constantsObj.cd, "kunde-btn");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"kunde-menu\"]/li[3]/a");
			functionM.inputByID(constantsObj.cd, "id_Vorname", vornameK);
			functionM.inputByID(constantsObj.cd, "id_Nachname", nachnameK);
			functionM.inputByID(constantsObj.cd, "id_Adresse", "Rathausmarkt 5, 20095 Hanmburg");
			functionM.inputByID(constantsObj.cd, "id_Telefonnummer", "017665438");
			functionM.inputByID(constantsObj.cd, "id_Email", "d.sabrina@gmail.com");
			functionM.inputByID(constantsObj.cd, "id_Geburtsdatum", "23-10-2000");
			functionM.inputByID(constantsObj.cd, "id_Bankverbindung", "DE00920000761141");
			functionM.inputByID(constantsObj.cd, "id_SteuerID", "334510000971123444454");
			functionM.inputByID(constantsObj.cd, "id_Nationalität", "Deutsch");
			functionM.inputByID(constantsObj.cd, "id_Beruf", "017665438");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Rolle\"]/option[1]"); //
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Kundentyp\"]/option[2]");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Beziehungsstatus\"]/option[1]");
			functionM.clickByXPATH(constantsObj.cd,"//*[@id=\"id_Raucher\"]/option[1]");
			functionM.clickByXPATH(constantsObj.cd,"//*[@id=\"id_Zahlungsmethode\"]/option[3]");
			functionM.clickByXPATH(constantsObj.cd,"//*[@id=\"id_Raucher\"]/option[1]");
			functionM.clickByXPATH(constantsObj.cd,"/html/body/form/button");
			
			if(functionM.selectorXPATHExists(constantsObj.cd, "//*[text()='Kunde Liste']")) {
				test3.pass("Kunde "+ vornameK +" "+ nachnameK+"  erolgreich hinzugefügt");
			} else {
				test3.fail("Kunde "+ vornameK +" "+ nachnameK+"  hinzugefügen fehlgeschlagen");
			}
		} catch(Exception e){
			test3.fail("hinzugügen vom Kunde: "+ vornameK +" "+ nachnameK +"fehlgeschlagen"); //catch code execution failure
		}
		String screenpath = ScreenShotsManager.takeScreenshot(constantsObj.cd, "Test 3");//generate a screenpath
		test.addScreenCaptureFromPath(screenpath);
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		
//		 
//		String filename = testResult.getMethod().getMethodName() + ".png";
//		String dir = System.getProperty("user.dir")	+ "//SCREENSHOTS//";
//		System.out.println(dir);
//		File sourcefile = ((TakesScreenshot)current.cd).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(sourcefile, new File(dir + filename));
//		test = ReportManager.testGenerator( testResult.getMethod().getMethodName() + ": screenshot", "screenshot taken at the end of execution of the testmethod");
//		String screenpath = ScreenShotsManager.takeScreenshot(constantsObj.cd, testResult.getMethod().getMethodName());//generate a screenpath
//		test.addScreenCaptureFromPath(screenpath);
		
		ReportManager.reportFlush();
		
		//important not to close the driver, otherwise invalid id session
		
	 }
		

}
