package stepDef;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.*;

import util.sharedData;
import util.general.DateTimeUtil;
import util.general.ExcelUtility;
import util.general.tools;
import cucumber.api.java.en.Then;

public class SFSteps
{
	@Then("^the user extracts \"(.*?)\" from \"(.*?)\"\\.$")
	public void the_user_extracts_from(String fieldName, String browserInstance) throws Throwable 
	{
		WebDriver browser = null;
		if (browserInstance.trim().equalsIgnoreCase("Browser 2")) 
			browser = sharedData.appInstance2;
		if (browserInstance.trim().equalsIgnoreCase("Browser 1")) 
			browser = sharedData.appInstance1;
		if (browserInstance.trim().equalsIgnoreCase("Browser 3")) 
			browser = sharedData.appInstance3;

		String emailField = tools.getElement(fieldName, browser).getText();
		sharedData.tempVariable = emailField;
		//System.out.println("emailField : " + emailField);
	}

	@Then("^the user clicks on \"(.*?)\" from \"(.*?)\" checkbox\\.$")
	public void the_user_clicks_on_from_checkbox(String fieldName, String browserInstance) throws Throwable
	{
		WebDriver browser = null;
		if (browserInstance.trim().equalsIgnoreCase("Browser 2")) 
			browser = sharedData.appInstance2;
		if (browserInstance.trim().equalsIgnoreCase("Browser 1")) 
			browser = sharedData.appInstance1;
		if (browserInstance.trim().equalsIgnoreCase("Browser 3")) 
			browser = sharedData.appInstance3;

		tools.getElement(fieldName, browser).click();

	}
	
	
	@Then("^user enters \"(.*?)\" from \"(.*?)\" in \"(.*?)\"\\.$")
	public void user_enters_from_in(String key, String dataType,String browserInstance) throws Throwable {
		WebDriver browser = null;
		if (browserInstance.trim().equalsIgnoreCase("Browser 2")) 
			browser = sharedData.appInstance2;
		if (browserInstance.trim().equalsIgnoreCase("Browser 1")) 
			browser = sharedData.appInstance1;
		if (browserInstance.trim().equalsIgnoreCase("Browser 3")) 
			browser = sharedData.appInstance3;

		System.out.println("sharedData.tempVariable : " + sharedData.tempVariable);
		if (dataType.length()>0) 
			tools.getElement( key, browser ).sendKeys(sharedData.tempVariable);	
	}
	
	@Then("^the user enters \"(.*?)\" in \"(.*?)\"\\.$")
	public void the_user_enters_in(String key, String browserInstance) throws Throwable
	{
		WebDriver browser = null;
		if (browserInstance.trim().equalsIgnoreCase("Browser 2")) 
			browser = sharedData.appInstance2;
		if (browserInstance.trim().equalsIgnoreCase("Browser 1")) 
			browser = sharedData.appInstance1;
		if (browserInstance.trim().equalsIgnoreCase("Browser 3")) 
			browser = sharedData.appInstance3;

		util.general.tools.getElement(key,browser).clear();	
		util.general.tools.getElement(key,browser).sendKeys(util.general.ExcelUtility.getValue(sharedData.envConfigurationSheetInstance, key ,sharedData.autPosition));
	}
	
	@Then("^the user clicks on \"(.*?)\" Button in \"(.*?)\"\\.$")
	public void the_user_clicks_on_Button_in(String key, String browserInstance ) throws Throwable
	{
		WebDriver browser = null;
		if (browserInstance.trim().equalsIgnoreCase("Browser 2")) 
			browser = sharedData.appInstance2;
		if (browserInstance.trim().equalsIgnoreCase("Browser 1")) 
			browser = sharedData.appInstance1;
		if (browserInstance.trim().equalsIgnoreCase("Browser 3")) 
			browser = sharedData.appInstance3;

		util.general.tools.getElement(key,browser).click();	
	}

	
	@Then("^the user clicks on \"(.*?)\" link in \"(.*?)\"\\.$")
	public void the_user_clicks_on_link_in(String key, String browserInstance) throws Throwable 
	{
		WebDriver browser = null;
		if (browserInstance.trim().equalsIgnoreCase("Browser 2")) 
			browser = sharedData.appInstance2;
		if (browserInstance.trim().equalsIgnoreCase("Browser 1")) 
			browser = sharedData.appInstance1;
		if (browserInstance.trim().equalsIgnoreCase("Browser 3")) 
			browser = sharedData.appInstance3;

		try {
			browser.findElement(By.linkText(key)).click();
		} catch (Exception e) {
			tools.getElement(key, browser).click();
		}
	}
	
	@Then("^the user puts \"(.*?)\" in \"(.*?)\" and \"(.*?)\" in \"(.*?)\"\\.$")
	public void the_user_puts_in_and_in(String startDate, String stratDateLink, String startTime, String startTimeLink) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebDriver browser = sharedData.appInstance;
		
		try {
			browser.findElement(By.id(util.general.ExcelUtility.getValue(sharedData.pomSheetInstance, stratDateLink ,2))).clear();
			browser.findElement(By.id(util.general.ExcelUtility.getValue(sharedData.pomSheetInstance, stratDateLink ,2))).sendKeys(startDate);
			browser.findElement(By.id(util.general.ExcelUtility.getValue(sharedData.pomSheetInstance, startTimeLink ,2))).clear();
			browser.findElement(By.id(util.general.ExcelUtility.getValue(sharedData.pomSheetInstance, startTimeLink ,2))).sendKeys(startTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	}
	
	
	@Then("^the user puts the data for \"(.*?)\"\\.$")
	public void the_user_puts_the_data_for(String testCase) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebDriver browser = sharedData.appInstance;
//		String parkingFee;
//		int calculatedParkingFee;
		
		try {
			Select parking = new Select(browser.findElement(By.id("ParkingLot")));
			parking.selectByVisibleText(sharedData.excelData.get(testCase).get("ParkingType"));
			browser.findElement(By.id("StartingDate")).clear();
			browser.findElement(By.id("StartingDate")).sendKeys(sharedData.excelData.get(testCase).get("StartDate"));
			browser.findElement(By.id("StartingTime")).clear();
			browser.findElement(By.id("StartingTime")).sendKeys(sharedData.excelData.get(testCase).get("StartTime"));
			if(sharedData.excelData.get(testCase).get("StartAMPM").contains("AM")) 
				browser.findElement(By.xpath("//input[@name='StartingTimeAMPM'][1]")).click();
			else
				browser.findElement(By.xpath("//input[@name='StartingTimeAMPM'][2]")).click();
			browser.findElement(By.id("LeavingDate")).clear();
			browser.findElement(By.id("LeavingDate")).sendKeys(sharedData.excelData.get(testCase).get("EndDate"));
			browser.findElement(By.id("LeavingTime")).clear();
			browser.findElement(By.id("LeavingTime")).sendKeys(sharedData.excelData.get(testCase).get("EndTime"));
			if(sharedData.excelData.get(testCase).get("EndAMPM").contains("AM")) 
				browser.findElement(By.xpath("//input[@name='LeavingTimeAMPM'][1]")).click();
			else
				browser.findElement(By.xpath("//input[@name='LeavingTimeAMPM'][2]")).click();
			if (DateTimeUtil.dateCompare(sharedData.excelData.get(testCase).get("StartDate"), sharedData.excelData.get(testCase).get("StartTime"), sharedData.excelData.get(testCase).get("StartAMPM"), sharedData.excelData.get(testCase).get("EndDate"), sharedData.excelData.get(testCase).get("EndTime"), sharedData.excelData.get(testCase).get("EndAMPM")) >= 0)
				Assert.fail("End date is before the start date");
//			browser.findElement(By.name("Submit")).click();
//			parkingFee = browser.findElement(By.xpath("//b")).getText().replace("$ ", "");
//			System.out.println(parkingFee);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("^submits the input data\\.$")
	public void submits_the_input_data() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebDriver browser = sharedData.appInstance;
		String parkingFee;
//		int calculatedParkingFee;
		
		try {
			browser.findElement(By.name("Submit")).click();
			parkingFee = browser.findElement(By.xpath("//b")).getText().replace("$ ", "");
			System.out.println(parkingFee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Then("^validate the outcome for \"(.*?)\"\\.$")
	public void validate_the_outcome_for(String testCase) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebDriver browser = sharedData.appInstance;
		double parkingFee = 0;
		double calculatedParkingFee = 0;
		
		try {
			
			parkingFee = Double.parseDouble(browser.findElement(By.xpath("//b")).getText().replace("$ ", ""));
			System.out.println(parkingFee);
			calculatedParkingFee = DateTimeUtil.calculateParikngFee(sharedData.excelData.get(testCase).get("ParkingType"), sharedData.excelData.get(testCase).get("StartDate"), sharedData.excelData.get(testCase).get("StartTime"), sharedData.excelData.get(testCase).get("StartAMPM"), sharedData.excelData.get(testCase).get("EndDate"), sharedData.excelData.get(testCase).get("EndTime"), sharedData.excelData.get(testCase).get("EndAMPM"));
			Assert.assertEquals(parkingFee, calculatedParkingFee, 0.0);
//			Assert.assertEquals(browser.findElement(By.id("StartingDate")).getText(), sharedData.excelData.get(testCase).get("StartDate"));
//			Assert.assertEquals(browser.findElement(By.id("StartingTime")).getText(), sharedData.excelData.get(testCase).get("StartTime"));
//			Assert.assertEquals(browser.findElement(By.id("LeavingDate")).getText(), sharedData.excelData.get(testCase).get("EndDate"));
//			Assert.assertEquals(browser.findElement(By.id("LeavingTime")).getText(), sharedData.excelData.get(testCase).get("EndTime"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Then("^the user clicks on \"(.*?)\" link by searching for \"(.*?)\"\\.$")
	public void the_user_clicks_on_link_by_searching_for(String linkName, String browserInstance) throws Throwable {
		List<WebElement> element = new ArrayList<WebElement>();
		WebDriver browser = null;
		if (browserInstance.trim().equalsIgnoreCase("Browser 2")) 
			browser = sharedData.appInstance2;
		if (browserInstance.trim().equalsIgnoreCase("Browser 1")) 
			browser = sharedData.appInstance1;
		if (browserInstance.trim().equalsIgnoreCase("Browser 3")) 
			browser = sharedData.appInstance3;

		element = browser.findElements(By.className("td2"));
		for (int i = 0; i < element.size();i++)
		{
			System.out.println(i + " : " + element.get(i).getText());
			//			if (element.get(i).getText().toLowerCase().contains("Verify your account"))

			if (element.get(i).getText().toLowerCase().contains("developer@salesforce.com"))
			{
				element.get(i).click();
			}
		}
	}
	@Then("^the user switches from \"(.*?)\" to \"(.*?)\"\\.$")
	public void the_user_switches_from_to(String firstBrowser, String secondBrowser) throws Throwable
	{
		WebDriver browser = null;
		if (firstBrowser.trim().equalsIgnoreCase("Browser 2")) 
			browser = sharedData.appInstance2;
		if (firstBrowser.trim().equalsIgnoreCase("Browser 1")) 
			browser = sharedData.appInstance1;
		if (firstBrowser.trim().equalsIgnoreCase("Browser 3")) 
			browser = sharedData.appInstance3;
		
		Set handles = browser.getWindowHandles();		 
        System.out.println(handles);
         for (String handle1 : browser.getWindowHandles()) 
         {
        	System.out.println(handle1);
        	browser.switchTo().window(handle1);
        	if (browser.getTitle().contains("Change Your Password")) {
        		sharedData.appInstance3 = browser;
				break;
			}

         }
	
	}

}
