package homePage;

import static org.testng.Assert.assertEquals;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import FunctionalLibrary.HomepageFunctions;
import GenericComponents.GenericComponents;

public class Homepagetest2 extends GenericComponents
{
	
	Homepagetest2 obj = null;
	HomepageFunctions hpf = null;
	ExtentReports report;
	ExtentTest test;
	
	@BeforeClass
	public void beginExecution()
	{
		obj = new Homepagetest2();
		obj.createResultsFolder();
		report = new ExtentReports(resultsFolderName+"\\Homepagetest.html");
		report.addSystemInfo("User Name", "Testing QA User");
	}
	
	@BeforeMethod
	public void launchApplication(Method method) 
	{
		testFolderLocation = obj.createTestsFolder(method.getName());
		obj.LaunchApplication("firefox","http://the-internet.herokuapp.com/");
		hpf =  new HomepageFunctions();
		test = report.startTest(method.getName());
	}
	
	@Test
	public void validateCheckbox1()
	{
		hpf.clickOnCheckboxesLink();
		assertEquals(hpf.verifyCheckbox1IsSelected(), false);
		test.log(LogStatus.PASS, test.addScreenCapture(takeScreenshot()) + "Check box is not selected");
		hpf.clickOnCheckbox1();
		assertEquals(hpf.verifyCheckbox1IsSelected(), true);
		test.log(LogStatus.PASS, "Validated Checkbox 1");
	}
	
	@Test
	public void validateCheckbox2()
	{
		hpf.clickOnCheckboxesLink();
		assertEquals(hpf.verifyCheckbox2IsSelected(), true);
		test.log(LogStatus.PASS, test.addScreenCapture(takeScreenshot())+ "Validated Checkbox 2");
		test.log(LogStatus.PASS, test.addScreenCapture(takeScreenshot()) + "Check box is selected");
		hpf.clickOnCheckbox2();
		assertEquals(hpf.verifyCheckbox2IsSelected(), true);
		test.log(LogStatus.PASS, test.addScreenCapture(takeScreenshot()) + "Validated Checkbox 2");
	}
	
	@AfterMethod
	public void quitApplication(ITestResult result) 
	{
		if(result.getStatus() == ITestResult.SUCCESS)
	    {
			test.log(LogStatus.PASS, "Test Passed");
	    }
		else if(result.getStatus() == ITestResult.FAILURE)
	    {
			test.log(LogStatus.FAIL, test.addScreenCapture(takeScreenshot()) + "Test Failed with error");
			test.log(LogStatus.INFO, result.getThrowable());
			
	    }
	     else if(result.getStatus() == ITestResult.SKIP ){
	    	 test.log(LogStatus.SKIP, "Test execution is skipped");
	    }
		report.endTest(test);
		
		obj.quitBrowser();
	}
	
	@AfterClass
	public void pushResults()
	{
		report.flush();
	}
	
}
