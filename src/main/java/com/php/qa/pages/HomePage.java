package com.php.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.php.qa.base.BaseClass;

public class HomePage extends BaseClass{

	
	@FindBy(xpath="//a[@title='Deals']")
	WebElement Deal;
	
	@FindBy(xpath="//a[@title='New Deal']")
	WebElement NewDeal;
	
	@FindBy(css = "a[title='Contacts']")
	WebElement contact;
	
	@FindBy(css = "a[title='New Contact']")
	WebElement newContact;
	
	public HomePage() {
		PageFactory.initElements(driver, this); //we have to pass driver and page object
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
		
		Actions act = new Actions(driver);
		act.moveToElement(contact).moveToElement(newContact).click().build().perform();
		
		return new ContactPage();
	}
	
	
    public ContactPage clickContactLink() {
		
		contact.click();
		return new ContactPage();
	}
	
}
