package FunctionalLibrary;

import java.util.Properties;

import org.openqa.selenium.By;

import GenericComponents.GenericComponents;

public class HomepageFunctions extends GenericComponents
{
	Properties locators = GenericComponents.getObjectLibrary("HomePageObjects");
	GenericComponents gc = new GenericComponents();
	
	public void clickOnCheckboxesLink()
	{
		gc.click(By.xpath((String)locators.get("checkboxs_xpath")));
	}
	
	public boolean verifyCheckbox1IsSelected()
	{
		return gc.verifyCheckboxIsSelected(By.xpath((String)locators.get("checkbox1_xpath")));
	}
	
	public void clickOnCheckbox1()
	{
		gc.click(By.xpath((String)locators.get("checkbox1_xpath")));
	}
	
	public void clickOnCheckbox2()
	{
		gc.click(By.xpath((String)locators.get("checkbox2_xpath")));
	}
	
	public boolean verifyCheckbox2IsSelected()
	{
		return gc.verifyCheckboxIsSelected(By.xpath((String)locators.get("checkbox2_xpath")));
	}

}
