package homePage;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
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
		obj.click(By.xpath("//a[text()='Checkboxes' and @href='/checkboxes']"));
		obj.sleep(5);
		assertEquals(obj.verifyCheckboxIsSelected(By.xpath("//input[@type='checkbox'][1]")), false);
		obj.click(By.xpath("//input[@type='checkbox'][1]"));
		assertEquals(obj.verifyCheckboxIsSelected(By.xpath("//input[@type='checkbox'][1]")), true);
	}
	
	@Test
	public void validateCheckbox2()
	{
		obj.click(By.xpath("//a[text()='Checkboxes' and @href='/checkboxes']"));
		obj.sleep(5);
		assertEquals(obj.verifyCheckboxIsSelected(By.xpath("//input[@type='checkbox'][2]")), true);
		obj.click(By.xpath("//input[@type='checkbox'][2]"));
		assertEquals(obj.verifyCheckboxIsSelected(By.xpath("//input[@type='checkbox'][2]")), false);
	}
	
	@AfterMethod
	public void quitApplication() 
	{
		obj.quitBrowser();
	}
	
	
}
