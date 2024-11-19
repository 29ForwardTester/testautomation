package managers;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotsManager {
	
	public static String takeScreenshot(WebDriver driver, String filename) throws IOException{
		
		String finalname = filename + ".png";
		String dir = System.getProperty("user.dir") + "\\REPORT_SCREENSHOTS\\";
		File sourcefile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourcefile, new File(dir + finalname));
		
		return dir + finalname;
		
	}

}
