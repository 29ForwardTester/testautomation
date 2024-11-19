package managers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.config.external.XmlConfigLoader;

public class ReportManager {
	
	public static ExtentReports report;
	public static ExtentTest exTest;
	public static ExtentSparkReporter sparker;
	
	public static ExtentReports reportGenerator() {
		if(report == null) {
			ExtentSparkReporter sparker = new ExtentSparkReporter("C://Users//ET/selenium//reports//sauce.html");
			sparker.config().setDocumentTitle("sauceLabsTest");
			sparker.config().setReportName("Test Report: sauceLabs.com");
			sparker.config().setTheme(Theme.STANDARD);
			
			report = new ExtentReports();
			report.attachReporter(sparker);
		}
		return report;
	}
	
	public static ExtentTest testGenerator(String testname, String testDescription) {
		
		exTest = reportGenerator().createTest(testname, testDescription);
		return exTest;
	}
	
	public static void reportFlush() {
		if(report != null)
			report.flush();
	}

	
	
	

}
