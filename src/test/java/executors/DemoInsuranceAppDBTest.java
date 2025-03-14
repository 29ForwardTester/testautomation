package executors;


	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.ITestResult;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import com.aventstack.extentreports.ExtentTest;

	import managers.ReportManagerDB;
	import managers.ScreenShotsManager;
	import constants.CurrentConstants;

	public class DemoInsuranceAppDBTest{
		
		public static Connection coDApp = null;
		private static Statement sta;
		private static ResultSet results = null;
		
		public WebDriver driver;
		public String url;
		public ExtentTest test;
		
		public static String dbDRIVER = "com.mysql.jdbc.Driver";
		
		
		@BeforeClass
		public void setUp() {
			
			driver = new ChromeDriver(); //this serves here only to show the extentereport after, if it works
			test = ReportManagerDB.testGenerator("database test of the Insurance Demo App", "database name: demoinsuranceapp");
			ReportManagerDB.reportGenerator();
		
			try {
				Class.forName(dbDRIVER);
				coDApp = DriverManager.getConnection(CurrentConstants.dbDAppURL, CurrentConstants.dbDAppUSERNAME, CurrentConstants.dbDAppPASSWORD);
				System.out.println("got connected successefully");
				sta = coDApp.createStatement();		//this will create a statement that will send SQL statements to the db 
				System.out.println("a statement has been created");
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		@Test
		public void theTest() {
			test.info("test just started");
			String quer = "select * from firstdjangoapp_kunde";
			try {
				results = sta.executeQuery(quer); // the statement will execute the query
				while(results.next()) {
					String nachname = results.getString("partner_nachname");
					String name = results.getString("partner_vorname");
					int id = results.getInt("p_id");
					//now display the values
					test.assignAuthor("A.Hassini");
					test.assignCategory("Data Base Test");
					test.assignDevice("Azure Virtual machine");
					
					test.info("Vorname: " + name + ", Nachname: " + nachname + ", PartnerID: " + id);
					
				}
				results.close();
			} catch(SQLException se) {
				se.getStackTrace();
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
		
		
		//the results should be set in report file .html
		@AfterMethod
		public void afterDone(ITestResult res) throws IOException {
			if (res.isSuccess()) {
				
				String spath = ScreenShotsManager.takeScreenshot(driver, res.getName());
				test.addScreenCaptureFromPath(spath);
			    ReportManagerDB.reportFlush();;
			}
			
		}
		
		@AfterClass
		public void tearDown() {
			
			
			try {
				if(!results.isClosed())
					results.close();
				if(!coDApp.isClosed())
					coDApp.close();
				if(!sta.isClosed())
					sta.close();
			} catch(Exception e) {
				e.getStackTrace();
			}
		}

	}


