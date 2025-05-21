package executors;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
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
	
	//database backup file
	@BeforeSuite
	public void db_bckup() {
		try {
			String dumpcmd = "mysqldump -u root -ptempsprintanier+!HOOK!022 demoinsuranceapp > backupBeforeTestsuite.sql 2>nul"; //2>nul to silence stderr warning
			ProcessBuilder dumpProcessB = new ProcessBuilder("cmd.exe", "/c", dumpcmd);
			dumpProcessB.redirectErrorStream(true);
			Process proc = dumpProcessB.start();
			proc.waitFor();
			System.out.println("Database backup created successefully");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
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
			constantsObj.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			if(functionM.selectorXPATHExists(constantsObj.cd, "//p[text()='Wählen Sie ein Menu']")) {
				test2.pass("login successeful");
			} else {
				test2.fail("login information incorrect");
			}
		} catch(Exception e){
			test2.fail("login unseccesseful"); //catch code execution failure
		}
		String screenpath = ScreenShotsManager.takeScreenshot(constantsObj.cd, "Test 2");//generate a screenpath
		test2.addScreenCaptureFromPath(screenpath);
	}
	
	@Test
	public void _Test3() throws IOException {
		//positive login
		//add new Kunde, correct entries
		String login = "frauheidelberg";
		String password = "ilogin123";
		String vornameK = "Sabrina";
		String nachnameK = "Daudet";
		ExtentTest test3 = ReportManager.testGenerator("Test3: positive login test, then adding a new Kunde", "LOGIN INFORMATION[ username:  <b>" + login + "</b> , password:  <b>"+ password+ "</b>]");
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
			
			constantsObj.cd.findElement(By.id("id_Strasse")).clear();
			functionM.inputByID(constantsObj.cd, "id_Strasse", "Rathausmarkt");
			
			constantsObj.cd.findElement(By.id("id_Hausnummer")).clear();
			functionM.inputByID(constantsObj.cd, "id_Hausnummer", "5");
			
			constantsObj.cd.findElement(By.id("id_PLZ")).clear();
			functionM.inputByID(constantsObj.cd, "id_PLZ", "20095");
			
			constantsObj.cd.findElement(By.id("id_Stadt")).clear();
			functionM.inputByID(constantsObj.cd, "id_Stadt", "Hamburg");
			
			functionM.inputByID(constantsObj.cd, "id_Telefonnummer", "017665438");
			
			functionM.inputByID(constantsObj.cd, "id_Email", "d.sabrina@gmail.com");
			
			constantsObj.cd.findElement(By.id("id_Geburtsdatum")).clear();
			functionM.inputByID(constantsObj.cd, "id_Geburtsdatum", "2000-10-23");
			
			constantsObj.cd.findElement(By.id("id_Bankverbindung")).clear();
			functionM.inputByID(constantsObj.cd, "id_Bankverbindung", "DE00920000");
			
			constantsObj.cd.findElement(By.id("id_SteuerID")).clear();
			functionM.inputByID(constantsObj.cd, "id_SteuerID", "3380997018");
			
			constantsObj.cd.findElement(By.id("id_Nationalität")).clear();
			functionM.inputByID(constantsObj.cd, "id_Nationalität", "Deutsch");
			
			constantsObj.cd.findElement(By.id("id_Beruf")).clear();
			functionM.inputByID(constantsObj.cd, "id_Beruf", "Lehrerin");
			
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
		test3.addScreenCaptureFromPath(screenpath);
		
	}
	@Test
	public void _Test4() throws IOException {
		//positive login
		//add new Kunde, incorrect entries
		String login = "frauheidelberg";
		String password = "ilogin123";
		String vornameK = "Andrea";
		String nachnameK = "Saito";
		ExtentTest test4 = ReportManager.testGenerator("Test4: positive login test, then adding a new Kunde", "LOGIN INFORMATION[ username:  <b>" + login + "</b> , password:  <b>"+ password+ "</b>]");
		test4.assignAuthor("Achouak Hassini");
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
			
			constantsObj.cd.findElement(By.id("id_Strasse")).clear();
			functionM.inputByID(constantsObj.cd, "id_Strasse", "Bismarkstr");
			
			constantsObj.cd.findElement(By.id("id_Hausnummer")).clear();
			functionM.inputByID(constantsObj.cd, "id_Hausnummer", "4");
			
			constantsObj.cd.findElement(By.id("id_PLZ")).clear();
			functionM.inputByID(constantsObj.cd, "id_PLZ", "85952");
			
			constantsObj.cd.findElement(By.id("id_Stadt")).clear();
			functionM.inputByID(constantsObj.cd, "id_Stadt", "Augsburg2");//expected error
			
			functionM.inputByID(constantsObj.cd, "id_Telefonnummer", "017665438");
			
			functionM.inputByID(constantsObj.cd, "id_Email", "router999@gmail.com");
			
			constantsObj.cd.findElement(By.id("id_Geburtsdatum")).clear();
			functionM.inputByID(constantsObj.cd, "id_Geburtsdatum", "2008-10-29");
			
			constantsObj.cd.findElement(By.id("id_Bankverbindung")).clear();
			functionM.inputByID(constantsObj.cd, "id_Bankverbindung", "DE98924400");
			
			constantsObj.cd.findElement(By.id("id_SteuerID")).clear();
			functionM.inputByID(constantsObj.cd, "id_SteuerID", "311065567574");//expected error: length=11
			
			constantsObj.cd.findElement(By.id("id_Nationalität")).clear();
			functionM.inputByID(constantsObj.cd, "id_Nationalität", "Deutsch");
			
			constantsObj.cd.findElement(By.id("id_Beruf")).clear();
			functionM.inputByID(constantsObj.cd, "id_Beruf", "Mechanikerin");
			
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Rolle\"]/option[1]"); //
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Kundentyp\"]/option[2]");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Beziehungsstatus\"]/option[1]");
			functionM.clickByXPATH(constantsObj.cd,"//*[@id=\"id_Raucher\"]/option[1]");
			functionM.clickByXPATH(constantsObj.cd,"//*[@id=\"id_Zahlungsmethode\"]/option[3]");
			functionM.clickByXPATH(constantsObj.cd,"//*[@id=\"id_Raucher\"]/option[1]");
			functionM.clickByXPATH(constantsObj.cd,"/html/body/form/button");
			
			if(functionM.selectorXPATHExists(constantsObj.cd, "//*[text()='Kunde Liste']")) {
				test4.pass("Kunde "+ vornameK +" "+ nachnameK+"  erolgreich hinzugefügt");
			} else {
				test4.fail("Kunde "+ vornameK +" "+ nachnameK+"  hinzugefügen fehlgeschlagen");
			}
		} catch(Exception e){
			test4.fail("hinzugügen vom Kunde: "+ vornameK +" "+ nachnameK +" fehlgeschlagen"); //catch code execution failure
		}
		String screenpath = ScreenShotsManager.takeScreenshot(constantsObj.cd, "Test 4");//generate a screenpath
		test4.addScreenCaptureFromPath(screenpath);
		
	}
	
	@Test
	public void _Test5() throws IOException {
		//positive login
		//modify Kunde, correct entries
		String login = "frauheidelberg";
		String password = "ilogin123";
		String Knummer = "2";
		String nachnameK = "Callas";
		ExtentTest test5 = ReportManager.testGenerator("Test5: positive login test, then modify existing Kunde (Mike Callas) with correct entries", "LOGIN INFORMATION[ username:  <b>" + login + "</b> , password:  <b>"+ password+ "</b>]");
		test5.assignAuthor("Achouak Hassini");
		try {
			functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[2]",login);
			functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[3]",password);
			functionM.clickByXPATH(constantsObj.cd,"/html/body/div[2]/form/button");
		//add new Kunde
			constantsObj.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
			functionM.clickByID(constantsObj.cd, "kunde-btn");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"kunde-menu\"]/li[2]/a");
			
			functionM.inputByXPATH(constantsObj.cd, "/html/body/form/input", nachnameK );
			functionM.clickByXPATH(constantsObj.cd, "/html/body/form/button");
			
			test5.info("Kundensuche erfolgreich");
			
			constantsObj.cd.findElement(By.id("id_Strasse")).clear();
			functionM.inputByID(constantsObj.cd, "id_Strasse", "Bostonstr");
			
			constantsObj.cd.findElement(By.id("id_Hausnummer")).clear();
			functionM.inputByID(constantsObj.cd, "id_Hausnummer", "9");
			
			constantsObj.cd.findElement(By.id("id_PLZ")).clear();
			functionM.inputByID(constantsObj.cd, "id_PLZ", "30357");
			
			constantsObj.cd.findElement(By.id("id_Stadt")).clear();
			functionM.inputByID(constantsObj.cd, "id_Stadt", "Leipzig");
			
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Status_K\"]/option[1]");
			
			functionM.clickByXPATH(constantsObj.cd, "/html/body/form[2]/button");
			
			if(functionM.selectorXPATHExists(constantsObj.cd, "//*[text()='Kunde Liste']")) {
				test5.pass("Kunde "+" "+ nachnameK+"  erolgreich bearbeitet");
			} else {
				test5.fail("Kunde " +" "+ nachnameK+"  Bearbeitung fehlgeschlagen");
			}
		} catch(Exception e){
			test5.fail("Bearbeiten vom Kunde: "+" "+ nachnameK +" fehlgeschlagen"); //catch code execution failure
		}
		String screenpath = ScreenShotsManager.takeScreenshot(constantsObj.cd, "Test 5");//generate a screenpath
		test5.addScreenCaptureFromPath(screenpath);
		
	}
	
	@Test
	public void _Test6() throws IOException {
		//positive login
		//modify Kunde, incorrect entries
		String login = "frauheidelberg";
		String password = "ilogin123";
		String Knummer = "2";
		String nachnameK = "Callas";
		ExtentTest test6 = ReportManager.testGenerator("Test6: positive login test, then modify existing Kunde (p_id = 2) with wrong entries", "LOGIN INFORMATION[ username:  <b>" + login + "</b> , password:  <b>"+ password+ "</b>]");
		test6.assignAuthor("Achouak Hassini");
		try {
			functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[2]",login);
			functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[3]",password);
			functionM.clickByXPATH(constantsObj.cd,"/html/body/div[2]/form/button");
		//modify Kunde
			constantsObj.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
			functionM.clickByID(constantsObj.cd, "kunde-btn");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"kunde-menu\"]/li[2]/a");
			
			functionM.inputByXPATH(constantsObj.cd, "/html/body/form/input", Knummer );
			functionM.clickByXPATH(constantsObj.cd, "/html/body/form/button");
			
			test6.info("Kundensuche erfolgreich");
			
			constantsObj.cd.findElement(By.id("id_Strasse")).clear();
			functionM.inputByID(constantsObj.cd, "id_Strasse", "Max-Planckstr");
			
			constantsObj.cd.findElement(By.id("id_Hausnummer")).clear();
			functionM.inputByID(constantsObj.cd, "id_Hausnummer", "88");
			
			constantsObj.cd.findElement(By.id("id_PLZ")).clear();
			functionM.inputByID(constantsObj.cd, "id_PLZ", "30357");
			
			constantsObj.cd.findElement(By.id("id_Stadt")).clear();
			functionM.inputByID(constantsObj.cd, "id_Stadt", "Leipzig");
			
			constantsObj.cd.findElement(By.id("id_Beruf")).clear();
			functionM.inputByID(constantsObj.cd, "id_Beruf", "Singer33");//error expected
			
			functionM.clickByXPATH(constantsObj.cd, "/html/body/form[2]/button");
			
			if(functionM.selectorXPATHExists(constantsObj.cd, "//*[text()='Kunde Liste']")) {
				test6.pass("Kunde "+" Nr "+ Knummer+"  erolgreich bearbeitet");
			} else {
				test6.fail("Kunde " +" Nr "+ Knummer+"  Bearbeitung fehlgeschlagen");
			}
		} catch(Exception e){
			test6.fail("Bearbeiten vom Kunde: "+" Nummer "+ Knummer +"fehlgeschlagen"); //catch code execution failure
		}
		String screenpath = ScreenShotsManager.takeScreenshot(constantsObj.cd, "Test 6");//generate a screenpath
		test6.addScreenCaptureFromPath(screenpath);
		
	}
	
	@Test
	public void _Test7() throws IOException {
		//positive login
		//add new Vertrag, correct entries
		String login = "frauheidelberg";
		String password = "ilogin123";
		String vornameK = "Mike";
		String nachnameK = "Callas";
		ExtentTest test7 = ReportManager.testGenerator("Test7: positive login test, then adding a new Vertrag witch correct entries to Kunde Nr:3 (previous status: inactive)", "LOGIN INFORMATION[ username:  <b>" + login + "</b> , password:  <b>"+ password+ "</b>]");
		test7.assignAuthor("Achouak Hassini");
		try {
			functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[2]",login);
			functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[3]",password);
			functionM.clickByXPATH(constantsObj.cd,"/html/body/div[2]/form/button");
		//add new Vertrag
			constantsObj.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
			functionM.clickByID(constantsObj.cd, "contracts-btn");
			functionM.clickByXPATH(constantsObj.cd, "/html/body/div/div[1]/ul/li[3]/a");
			functionM.inputByID(constantsObj.cd, "id_Vertragnummer", "D554");
			functionM.inputByID(constantsObj.cd, "id_Risikoadresse", "20065 Köln");
			
			constantsObj.cd.findElement(By.id("id_Endedatum")).clear();
			functionM.inputByID(constantsObj.cd, "id_Endedatum", "2036-01-04");
			
			functionM.inputByID(constantsObj.cd, "id_Versicherungsssumme", "100000");
			functionM.inputByID(constantsObj.cd, "id_Selbstbeteiligung", "20");
			functionM.inputByID(constantsObj.cd, "id_Beitragshoehe", "200");
			functionM.inputByID(constantsObj.cd, "id_VersichererID", "D77643909");
			
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Status_V\"]/option[1]");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Zahlungsintervall\"]/option[1]");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Tarifgruppe\"]/option[1]");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Versicherungsart\"]/option[1]");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_kundenname\"]/option[3]");
			
			functionM.clickByXPATH(constantsObj.cd, "/html/body/form/button");
			
			
			
			
			if(functionM.selectorXPATHExists(constantsObj.cd, "//*[text()='Vertrag Liste']")) {
				test7.pass("Vertrag für "+ vornameK +" "+ nachnameK+"  erolgreich hinzugefügt");
			} else {
				test7.fail("Vertrag für"+ vornameK +" "+ nachnameK+"  hinzugefügen fehlgeschlagen");
			}
		} catch(Exception e){
			test7.fail("hinzugügen vom Vertrag für : "+ vornameK +" "+ nachnameK +" fehlgeschlagen"); //catch code execution failure
		}
		String screenpath = ScreenShotsManager.takeScreenshot(constantsObj.cd, "Test 7");//generate a screenpath
		test7.addScreenCaptureFromPath(screenpath);
		
	}
	
	@Test
	public void _Test8() throws IOException {
		//positive login
		//add new Vertrag, incorrect entries
		String login = "frauheidelberg";
		String password = "ilogin123";
		String vornameK = "Mariah";
		String nachnameK = "Bison";
		ExtentTest test8 = ReportManager.testGenerator("Test8: positive login test, then adding a new Vertrag witch correct entries to Kunde Nr:2 (previous status: active)", "LOGIN INFORMATION[ username:  <b>" + login + "</b> , password:  <b>"+ password+ "</b>]");
		test8.assignAuthor("Achouak Hassini");
		try {
			functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[2]",login);
			functionM.inputByXPATH(constantsObj.cd, "/html/body/div[2]/form/input[3]",password);
			functionM.clickByXPATH(constantsObj.cd,"/html/body/div[2]/form/button");
		//add new Vertrag
			constantsObj.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
			functionM.clickByID(constantsObj.cd, "contracts-btn");
			functionM.clickByXPATH(constantsObj.cd, "/html/body/div/div[1]/ul/li[3]/a");
			functionM.inputByID(constantsObj.cd, "id_Vertragnummer", "RT886094");
			functionM.inputByID(constantsObj.cd, "id_Risikoadresse", "49011 Berlin");
			
			constantsObj.cd.findElement(By.id("id_Endedatum")).clear();
			functionM.inputByID(constantsObj.cd, "id_Endedatum", "2035-01-01");
			
			functionM.inputByID(constantsObj.cd, "id_Versicherungsssumme", "150000");
			functionM.inputByID(constantsObj.cd, "id_Selbstbeteiligung", "20");
			functionM.inputByID(constantsObj.cd, "id_Beitragshoehe", "250000");//error expected
			functionM.inputByID(constantsObj.cd, "id_VersichererID", "D97643988");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Status_V\"]/option[1]");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Zahlungsintervall\"]/option[1]");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Tarifgruppe\"]/option[1]");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_Versicherungsart\"]/option[1]");
			functionM.clickByXPATH(constantsObj.cd, "//*[@id=\"id_kundenname\"]/option[2]");
			functionM.clickByXPATH(constantsObj.cd, "/html/body/form/button");
			
			if(functionM.selectorXPATHExists(constantsObj.cd, "//*[text()='Vertrag Liste']")) {
				test8.pass("Vertrag für "+ vornameK +" "+ nachnameK+"  erolgreich hinzugefügt");
			} else {
				test8.fail("Vertrag für"+ vornameK +" "+ nachnameK+"  hinzugefügen fehlgeschlagen");
			}
		} catch(Exception e){
			test8.fail("hinzugügen vom Vertrag für : "+ vornameK +" "+ nachnameK +" fehlgeschlagen"); //catch code execution failure
		}
		String screenpath = ScreenShotsManager.takeScreenshot(constantsObj.cd, "Test 8");//generate a screenpath
		test8.addScreenCaptureFromPath(screenpath);
		
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
	//restore saved db state
	@AfterSuite
	public void db_restore() {
		try {
			String restorecmd = "mysql -u root -ptempsprintanier+!HOOK!022 demoinsuranceapp < backupBeforeTestsuite.sql 2>nul";
			ProcessBuilder restoreProcessB = new ProcessBuilder("cmd.exe", "/c", restorecmd);
			restoreProcessB.redirectErrorStream(true);
			Process procR = restoreProcessB.start();
			procR.waitFor();
			System.out.println("Database state restored successefully");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		

}
