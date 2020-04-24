package com.php.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.php.qa.base.BaseClass;
import com.php.qa.util.TestUtils;

public class ContactPage extends BaseClass{

	
	@FindBy(css = "input#first_name")
	WebElement firstName;
	
	@FindBy(css="input[name='surname']")
	WebElement lastName;
	
	@FindBy(css="input[name='client_lookup']")
	WebElement company;
	
	@FindBy(xpath="//select[@name='category']")
	WebElement category;
	
	@FindBy(xpath="//select[@name='status']")
	WebElement status;
	
	@FindBy(css="input[name='receive_email'][value='N']")
	WebElement radioBtn;
	
	@FindBy(css="input[value='Load From Company']+input")
	WebElement saveBtn;
	
	@FindBy(xpath="//legend[@class='fieldset' and text()='Contact Information']")
	WebElement formHeading;
	
	@FindBy(css="img[id=f_trigger_c_birthday][title='Date selector']")
	WebElement dateSelector;
	
	@FindBy(css = "span[id= 'first_name']")
	WebElement summaryFname;
	
	@FindBy(css="input[name='cs_name']")
	WebElement nameSearch;
	
	@FindBy(css="input[name='cs_submit'][type='submit']")
	WebElement searchBtn;
	
	@FindBy(css="span[context='phone']")
	WebElement phoneNumCheck;
	
	@FindBy(id="phone")
	WebElement phoneNum;
	
	@FindBy(css="textarea[name='description']")
	WebElement description;
	
	@FindBy(xpath="//input[@id='fieldId_birthday']")
	WebElement birthdayField;
	
	
	public  ContactPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	public String pageVerification() {
		
		return formHeading.getText();
	}
	
	public void formFilling(String fname, String lname, String cname, String categoryValue, String statusValue,String phoneNumber, String birthdayDate, String notes) {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		wait.until(ExpectedConditions.visibilityOf(formHeading));
		
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		company.sendKeys(cname);
		
		Select sel = new Select(category);
		sel.selectByVisibleText(categoryValue);
		
		Select sel1 = new Select(status);
		sel1.selectByVisibleText(statusValue);
		
		phoneNum.sendKeys(phoneNumber);
		
		radioBtn.click();
		
		//dateSelector.click();
		
		/*TestUtils.calendar(birthdayDate);*/
		
		TestUtils.calendarJS(birthdayDate,birthdayField);
		
		TestUtils.pageScroll(description);
		
		description.sendKeys(notes);
		
		//TestUtils.pageScroll(saveBtn);
		//executeScript("window.scrollBy(x-pixels,y-pixels)");
		saveBtn.click();
		
	}
	
	
	public String summaryPage() {
		
		return summaryFname.getText();
	}
	
	public String searchMethod(String name) {
		
		nameSearch.sendKeys(name);
		searchBtn.click();
		return phoneNumCheck.getText();
		
	}
	
	public void alertCheck(String name) {
		
		firstName.sendKeys(name);
		saveBtn.click();
	}
	
	
	
	
}
