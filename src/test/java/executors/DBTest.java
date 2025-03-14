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

public class DBTest {
	
	public static Connection co = null;
	private static Statement sta;
	private static ResultSet results = null;
	
	public WebDriver driver;
	public String url;
	public ExtentTest test;
	
	public static String dbDRIVER = "com.mysql.jdbc.Driver";
	
	
	@BeforeClass
	public void setUp() {
		
		driver = new ChromeDriver(); //this serves here only to show the extentereport after, if it works
		test = ReportManagerDB.testGenerator("databasetest", "data from scores table");
		ReportManagerDB.reportGenerator();
	
		try {
			Class.forName(dbDRIVER);
			co = DriverManager.getConnection(CurrentConstants.dbURL, CurrentConstants.dbUSERNAME, CurrentConstants.dbPASSWORD);
			System.out.println("got connected successefully");
			sta = co.createStatement();		//this will create a statement that will send SQL statements to the db 
			System.out.println("a statement has been created");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void theTest() {
		test.info("test just started");
		String quer = "select * from scores";
		try {
			results = sta.executeQuery(quer); // the statement will execute the query
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("pname");
				int score = results.getInt("score");
				//now display the values
				test.info("id: " + id);
				test.info("pname: " + name);
				test.info("score: " + score);
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
			if(!co.isClosed())
				co.close();
			if(!sta.isClosed())
				sta.close();
		} catch(Exception e) {
			e.getStackTrace();
		}
	}

}
