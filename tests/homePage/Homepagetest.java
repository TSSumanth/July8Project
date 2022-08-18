package homePage;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import FunctionalLibrary.HomepageFunctions;
import GenericComponents.GenericComponents;

public class Homepagetest extends GenericComponents
{
	String resultsLocation = null;
	Homepagetest obj = null;
	HomepageFunctions hpf = null;
	
	@BeforeClass
	public void beginExecution()
	{
		obj = new Homepagetest();
		resultsLocation = obj.createResultsFolder();
	}
	
	@BeforeMethod
	public void launchApplication() 
	{
		obj.LaunchApplication("firefox","http://the-internet.herokuapp.com/");
		hpf =  new HomepageFunctions();
	}
	
	@Test
	public void validateCheckbox1()
	{
		hpf.clickOnCheckboxesLink();
		assertEquals(hpf.verifyCheckbox1IsSelected(), false);
		hpf.clickOnCheckbox1();
		assertEquals(hpf.verifyCheckbox1IsSelected(), true);
	}
	
	@Test
	public void validateCheckbox2()
	{
		hpf.clickOnCheckboxesLink();
		assertEquals(hpf.verifyCheckbox2IsSelected(), true);
		hpf.clickOnCheckbox2();
		assertEquals(hpf.verifyCheckbox2IsSelected(), false);
	}
	
	@AfterMethod
	public void quitApplication() 
	{
		obj.quitBrowser();
	}
	
	
}
