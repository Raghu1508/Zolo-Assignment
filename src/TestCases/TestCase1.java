package TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Controls.FirstTestCaseControls;
import Controls.LoginControls;
import Utils.Constant;
import Utils.ExceptionHandlingFunctions;



public class TestCase1 {
	
	public ChromeDriver driver;
	
	
	TestCase1()
  {
	  System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "\\ExternalLibraries\\chromedriver.exe");
	  driver = new ChromeDriver (); 
	  
  }
	@BeforeClass
	public void Login()throws InterruptedException
	{
		  driver.get(Constant.URL);
		  driver.manage().window().maximize();
		  driver.findElementByXPath(LoginControls.LoginButton).click();
		  driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		  driver.findElementByXPath(LoginControls.username).sendKeys(Constant.Username);
		  driver.findElementByXPath(LoginControls.password).sendKeys(Constant.Password);
		  Thread.sleep(5000);
		  driver.findElementByXPath(LoginControls.signIn).click();
	}

	@Test
	  public void Test() throws Exception {
			
		try
		{
			  driver.findElementById(FirstTestCaseControls.search).sendKeys(Constant.FirstSearch);
			  Thread.sleep(5000);
			  driver.findElementById(FirstTestCaseControls.search).sendKeys(Keys.DOWN,Keys.DOWN,Keys.DOWN,Keys.ENTER);
			  driver.findElement(By.xpath("//p[contains(text(),'Electronic')]")).getText().contains(Constant.FirstSearch);
			  
			  // 2. Search Result			  
			  Select budget = new Select(driver.findElement(By.xpath(FirstTestCaseControls.budgetFilter)));
			  budget.selectByVisibleText("5000 - 8000");			  
			  Select sharingPrefernces = new Select(driver.findElement(By.xpath(FirstTestCaseControls.sharingPrefFilter)));
			  sharingPrefernces.selectByVisibleText("2 Sharing");			
			  Select pgType = new Select(driver.findElement(By.xpath(FirstTestCaseControls.pgTypeFilter)));
			  pgType.selectByVisibleText("Men");		
			  driver.findElement(By.xpath(FirstTestCaseControls.cLickOnFilter)).click();			     
			  driver.findElement(By.xpath("//h2[contains(text(),' Goodfellas ')]")).click();			  	
			  Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(text(),'Zolo Goodfellas for Men')]")).getText(), "Zolo Goodfellas for Men");
			  
			  //3. Schedule a Visit
			  driver.findElement(By.xpath(FirstTestCaseControls.clickOnScheduleAVisit)).click();			  			  
			  driver.findElement(By.xpath(FirstTestCaseControls.visitDate)).sendKeys(Keys.UP);
			  Select timeOfVisit = new Select(driver.findElement(By.xpath(FirstTestCaseControls.timeOfVisitDropODwn)));
			  timeOfVisit.selectByIndex(5);			  
			  driver.findElement(By.xpath("//div[contains(text(),'Schedule ')]")).click();
			  Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='NOT_VISITED']")).getText(), "NOT_VISITED");	  
	}
			  catch (Exception e) {
				  
				  ExceptionHandlingFunctions.writeTOLog(e.toString(),Thread.currentThread().getStackTrace()[1].getMethodName());
				  ExceptionHandlingFunctions.captureScreenShot(driver,Thread.currentThread().getStackTrace()[1].getMethodName());
				  System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
				  Assert.fail("Schedule visit booking did not get created");
			}
			  
	}
	  
	
	@AfterTest
	public void Close()
	{
		driver.quit();
	}
}