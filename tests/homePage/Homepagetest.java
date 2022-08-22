package homePage;

import static org.testng.Assert.assertEquals;
import java.lang.reflect.Method;

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

public class Homepagetest extends GenericComponents
{
	
	Homepagetest obj = null;
	HomepageFunctions hpf = null;
	ExtentReports report;
	ExtentTest test;
	
	@BeforeClass
	public void beginExecution()
	{
		obj = new Homepagetest();
		obj.createResultsFolder();
		report = new ExtentReports(resultsFolderName+"\\Homepagetest.html");
		report.addSystemInfo("User Name", "Testing QA User");
		getExcelWorkBook("TestSuite1");
	}
	
	@BeforeMethod
	public void launchApplication(Method method) 
	{
		testFolderLocation = obj.createTestsFolder(method.getName());
		obj.LaunchApplication("firefox","http://the-internet.herokuapp.com/");
		hpf =  new HomepageFunctions();
		test = report.startTest(method.getName());
		findSheetRow(method.getName());
	}
	
	@Test
	public void validateCheckbox1()
	{
		hpf.clickOnCheckboxesLink();
		assertEquals(hpf.verifyCheckbox1IsSelected(), Boolean.parseBoolean(getData("Checkbox1InitialStatus")));
		hpf.clickOnCheckbox1();
		assertEquals(hpf.verifyCheckbox1IsSelected(), Boolean.parseBoolean(getData("Checkbox1FinalStatus")));
		test.log(LogStatus.PASS, "Validated Checkbox 1");
		test.log(LogStatus.PASS, test.addScreenCapture(takeScreenshot()) + "Check box is selected");
	}
	
	@Test
	public void validateCheckbox2()
	{
		hpf.clickOnCheckboxesLink();
		assertEquals(hpf.verifyCheckbox2IsSelected(), Boolean.parseBoolean(getData("Checkbox2InitialStatus")));
		hpf.clickOnCheckbox2();
		assertEquals(hpf.verifyCheckbox2IsSelected(), Boolean.parseBoolean(getData("Checkbox2FinalStatus")));
		test.log(LogStatus.PASS, "Validated Checkbox 2");
	}
	
	@AfterMethod
	public void quitApplication() 
	{
		obj.quitBrowser();
		report.endTest(test);
	}
	
	@AfterClass
	public void pushResults()
	{
		report.flush();
	}
	
}
