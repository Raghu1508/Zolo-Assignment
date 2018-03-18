package TestCases;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Controls.FirstTestCaseControls;
import Controls.LoginControls;
import Controls.SecondTestCaseControls;
import Utils.Constant;
import Utils.ExceptionHandlingFunctions;


public class TestCase2 {

public ChromeDriver driver;
	
	TestCase2()
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
	public void Test() throws Exception
	{	
		try
		{
		//ShortListed Property
			driver.findElementById(FirstTestCaseControls.search).sendKeys("Zolo GoodFellas");
			Thread.sleep(5000);
			driver.findElementById(FirstTestCaseControls.search).sendKeys(Keys.DOWN,Keys.RETURN);
			driver.findElementByXPath(SecondTestCaseControls.clickOnGoodFellas).click();
			Thread.sleep(5000);
			driver.findElementByXPath(SecondTestCaseControls.requestABed).click();
			driver.findElementByXPath(SecondTestCaseControls.cal).sendKeys(Keys.UP);
			Thread.sleep(5000);
			driver.findElementByXPath(SecondTestCaseControls.clickOn2Sharing).click();
			driver.findElementByXPath(SecondTestCaseControls.proccedToPay).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//Verify Phone
			Assert.assertEquals(driver.findElementByXPath(SecondTestCaseControls.verifyPhone).getAttribute("value"), Constant.Username);
			//Verify Name
			Assert.assertEquals(driver.findElementByXPath(SecondTestCaseControls.verifyName).getAttribute("value"),"Test User ");
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//Clicking on check box
			driver.findElementByXPath(SecondTestCaseControls.clickONCheckBox).click();
			Thread.sleep(5000);
			driver.findElementByXPath(SecondTestCaseControls.promoCodeEditBox).sendKeys("Promo Code");
			driver.findElementByXPath(SecondTestCaseControls.applyCodeButton).click();
			Thread.sleep(5000);
			driver.findElementByXPath(SecondTestCaseControls.makePaymentButton).click();
			Thread.sleep(5000);
			driver.findElementByXPath(SecondTestCaseControls.paymentGateWay).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath(SecondTestCaseControls.contactNumber)).clear();
			driver.findElement(By.xpath(SecondTestCaseControls.contactNumber)).sendKeys(Constant.Username);;
			String parent = driver.getWindowHandle();
			driver.findElementByXPath(SecondTestCaseControls.paymentOptions).click();
			driver.findElement(By.xpath(SecondTestCaseControls.enterCVV)).sendKeys("733");
			driver.findElement(By.xpath(SecondTestCaseControls.payButton)).click();
			
			Thread.sleep(10000);
			
			Set<String>s1=driver.getWindowHandles();
			Iterator<String> I1= s1.iterator();
			while(I1.hasNext())
			{
			 
			   String child_window=I1.next();
			 
			 
			 
			if(!parent.equals(child_window))
			{
			driver.switchTo().window(child_window);
			 
			driver.findElement(By.xpath("//button[contains(text(),'Success')]")).click();
			Thread.sleep(5000);
			
			}
			 
			}
			
			driver.switchTo().window(parent);
			Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(),'RETURN HOME')]")),"RETURN HOME");
			Thread.sleep(5000);
			driver.findElementByXPath("//nav[@class='leftNav scrollable']//a[@href='/manage-booking']").click();
			Assert.assertEquals(driver.findElement(By.xpath("//h4[contains(text(),'Goodfellas')]/../..//h4[contains(text(),'PRE_BOOKING')]")).getAttribute("value"), "PRE_BOOKING");
			
			
	}
			catch (Exception e) 
			{
				  
				  ExceptionHandlingFunctions.writeTOLog(e.toString(),Thread.currentThread().getStackTrace()[1].getMethodName());
				  ExceptionHandlingFunctions.captureScreenShot(driver,Thread.currentThread().getStackTrace()[1].getMethodName());
				  System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
				  Assert.fail("Booking did not get created successfully");
			}
			 
			
	
}
	
	@AfterTest
	public void Close()
	{
		driver.quit();
	}

}


