package com.php.qa.pages;



import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.php.qa.base.BaseClass;

public class HomePageWithWindowHandles extends BaseClass{

	
	@FindBy(xpath="//a[@title='Deals']")
	WebElement Deal;
	
	@FindBy(xpath="//a[@title='New Deal']")
	WebElement NewDeal;
	
	@FindBy(css = "a[title='Contacts']")
	WebElement contact;
	
	@FindBy(css = "a[title='New Contact']")
	WebElement newContact;
	
	public HomePageWithWindowHandles() {
		PageFactory.initElements(driver, this);
	}
	
	public DealPage ClickNewDealLink() {
		
		Actions act = new Actions(driver);
		act.moveToElement(Deal).perform();
		
		NewDeal.click();
		return new DealPage();
	}
	
	public DealPage clickDealLink() {
		
		Deal.click();
		return new DealPage();
	}
	
	public ContactPage clickNewContactLink() {
		
		String homePage = driver.getWindowHandle();
		
		Actions act = new Actions(driver);
		act.moveToElement(contact).moveToElement(newContact).keyDown(Keys.COMMAND).click().keyUp(Keys.COMMAND).build().perform();
		
		Set<String> allWindows = driver.getWindowHandles();
		
		for(String contactPage : allWindows) {
			
			if(!homePage.equals(contactPage)) {
				
				driver.switchTo().window(contactPage);
			}
		}
		
		return new ContactPage();
	}
	
	
    public ContactPage clickContactLink() {
		
		contact.click();
		return new ContactPage();
	}
	
}
