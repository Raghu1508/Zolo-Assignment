package Utils;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ExceptionHandlingFunctions {
	public static String CurrentDate()
	 {
		    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
			Date date =new Date();
			return dateFormat.format(date).toString();
	 }
	public static void captureScreenShot(WebDriver driver,String screenshotName)
	{
		try {
			TakesScreenshot ts =(TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("./Screenshot/"+ screenshotName + ExceptionHandlingFunctions.CurrentDate() + ".png"));
			
		
		} catch (Exception e) {
			
			System.out.println("Exception while taking screen shot "+e.getMessage());
		}
	}
	
	
	public static void writeTOLog(String Error,String TestcaseName) throws Exception
	{ 	
				String FilePathAndName= String.format("./Log/"+TestcaseName + ExceptionHandlingFunctions.CurrentDate() + ".txt");
				FileWriter fw = new FileWriter(FilePathAndName);
				fw.write(Error.toString());
				fw.close();
	}
}
