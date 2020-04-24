package com.php.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.php.qa.base.BaseClass;

public class LoginPage extends BaseClass{
	
	
	@FindBy(xpath = "//input[@name='username']")
	WebElement LoginTxt;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement PasswordTxt;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginbtn;
	
	@FindBy(xpath = "//a[contains(text(),'Sign Up')]")
	WebElement singupbtn;
	
	@FindBy(xpath = "//img[@class='img-responsive']")
	WebElement cpmlogo;
	
	
	public LoginPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	public String validateTitle() {
		
		return driver.getTitle();
	}
	
	public HomePage login(String username,String password) {
		
		LoginTxt.sendKeys(username);
		PasswordTxt.sendKeys(password);
		loginbtn.click();
		return new HomePage();
	 }
	
	public boolean validateLogo() {
		
		return cpmlogo.isDisplayed();
	}
}
