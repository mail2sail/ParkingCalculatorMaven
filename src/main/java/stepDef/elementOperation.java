package stepDef;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

//import com.thoughtworks.selenium.webdriven.commands.Click;

import util.sharedData;
import util.general.tools;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class elementOperation {
	@Then("^The user enters \"(.*?)\"\\.$")
	public void the_user_enters( String key ) throws Throwable
	{
		util.general.tools.getElement(key).clear();	
		util.general.tools.getElement(key).sendKeys(util.general.ExcelUtility.getValue(sharedData.envConfigurationSheetInstance, key ,sharedData.autPosition));
	}
	
	@Then("^The user inputs \"(.*?)\" as \"(.*?)\"\\.$")
	public void the_user_inputs_as(String key, String inuptString) throws Throwable {
		util.general.tools.getElement(key).sendKeys(inuptString);
	}

	@Given("^The user hovers on \"(.*?)\" to go to \"(.*?)\"\\.$")
	public void the_user_hovers_on_to_go_to(String hoverItem, String linkName ) throws Throwable {
		new Actions( sharedData.appInstance ).moveToElement( util.general.tools.getElement(hoverItem)).perform();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new Actions( sharedData.appInstance ).moveToElement( util.general.tools.getElement(linkName)).click().perform();
	
	}
	
	@Then("^The user clicks on \"(.*?)\" Button\\.$")
	public void the_user_clicks_on_Button(String buttonValue ) throws Throwable {
			try {
				//K2 : button(contains(., 'press me')]
				util.general.tools.pointTheElement("xPath", "//*[@value='"+buttonValue+"']").click();
			} catch (Exception e) {
				try {
					util.general.tools.getElement(buttonValue).click();
					} catch (Exception e2) 
					{
						e2.printStackTrace();
						
						//K2 : Print a message in log that the element is not available in the page , ask the developer is a force wait is required.
						//K2 : Fail this scenario.
					}
			}
	}
	@Then("^The user clicks on \"(.*?)\" Radio Button\\.$")
	public void the_user_clicks_on__Radio_Button(String buttonValue ) throws Throwable {
			try {
		//		util.general.tools.pointTheElement("xPath", "//*[@value='"+buttonValue+"']").click();
		//		util.general.tools.pointTheElement("xPath", "//*[text()="+buttonValue+"']").click();
				util.general.tools.pointTheElement("xPath", "//*[contains(text(),'"+buttonValue+"')]" ).click();
			} catch (Exception e) {
				try {
					util.general.tools.getElement(buttonValue).click();
					} catch (Exception e2) 
					{
						//K2 : Print a message in log that the element is not available in the page , ask the developer is a force wait is required.
						//K2 : Fail this scenario.
					}
			}
	}
	@Then("^The user selects value from \"(.*?)\" drop down\\.$")
	public void the_user_selects_value_from_drop_down(String dropDownKey ) throws Throwable 
	{
		new Select (util.general.tools.getElement(dropDownKey)).selectByVisibleText(util.general.ExcelUtility.getValue(sharedData.envConfigurationSheetInstance, dropDownKey ,sharedData.autPosition));
	}
	@Then("^The user selects \"(.*?)\" value from \"(.*?)\" drop down\\.$")
	public void the_user_selects_value_from_drop_down(String dropDownValue, String dropDownKey) throws Throwable {
		new Select (util.general.tools.getElement(dropDownKey)).selectByVisibleText( dropDownValue );
	}
	@Then("^The user selects \"(.*?)\" option from from \"(.*?)\" drop down\\.$")
	public void the_user_selects_option_from_from_drop_down(String indexOfDropDown, String dropDownKey) throws Throwable {
		new Select (util.general.tools.getElement(dropDownKey)).selectByIndex(Integer.parseInt(indexOfDropDown));
	}
	@Then("^the user clicks on \"(.*?)\" link\\.$")
	public void the_user_clicks_on_link(String linkName ) throws Throwable {
		try {
			util.general.tools.pointTheElement("linkText", linkName ).click();
		} catch (Exception e) {
			util.general.tools.getElement(linkName).click();
		}
	}
	
	@Then("^The user will reach to \"(.*?)\" Page\\.$")
	public void the_user_will_reach_to_Page(String pageName ) throws Throwable {
		if (!pageName.trim().equals(sharedData.appInstance.getTitle().trim())) 
		{
			String expectedPageTitle = util.general.ExcelUtility.getValue(sharedData.envConfigurationSheetInstance, pageName ,sharedData.autPosition);
			Assert.assertEquals("The user is expected to reach " + expectedPageTitle + " ,but reached to : " + sharedData.appInstance.getTitle() ,expectedPageTitle.trim(),sharedData.appInstance.getTitle().trim());
		}
	}
	
	//K2 : Implement , so that you can submit any page witout clicking any button.
	//	Then The user submits the form.
	@Then("^The user submits the form\\.$")
	public void the_user_submits_the_form() throws Throwable 
	{
		sharedData.appInstance.findElement(By.tagName("head")).submit();
	}
	
	//K2 : Implement , so that you can send enter to any page.
	@Then("^The user enters \"(.*?)\" Key\\.$")
	public void the_user_enters_Key(String arg1) throws Throwable {
		sharedData.appInstance.findElement(By.tagName("body")).sendKeys(Keys.ENTER);

	}
/*	@Then("^The user enters \"(.*?)\" Key in \"(.*?)\" field\\.$")
	public void the_user_enters_Key_in_field(String keyName, String fieldName ) throws Throwable {
		try {
			Key k = 
			//util.general.tools.getElement( fieldName ).sendKeys( keyName );
			String key =  util.general.ExcelUtility.getValue(sharedData.envConfigurationSheetInstance, keyName ,sharedData.autPosition).trim();
			System.out.println("key : " + key);
			util.general.tools.getElement( fieldName ).sendKeys( Keys.ENTER );

		} catch (Exception e) {
			String key =  util.general.ExcelUtility.getValue(sharedData.envConfigurationSheetInstance, keyName ,sharedData.autPosition).trim();
			System.out.println("key : " + key);
			util.general.tools.getElement( fieldName ).sendKeys(key );
		}
		util.general.tools.customWait(Thread.currentThread());

		}
*/
	@Then("^The user scrolls to \"(.*?)\"\\.$")
	public void the_user_scrolls_to(String key) throws Throwable {
		new Actions( sharedData.appInstance ).moveToElement( util.general.tools.getElement(key)).perform();

	}
	@Then("^the user waits for the page to load\\.$")
	public void the_user_waits_for_the_page_to_load() throws Throwable {
			tools.customWait(Thread.currentThread());
	}
}
