package com.php.qa.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import com.php.qa.base.BaseClass;
import com.php.qa.pages.HomePage;
import com.php.qa.pages.LoginPage;

public class LoginPageTest extends BaseClass{
	
	
	LoginPage loginpage;
	HomePage homepage;
	
	public LoginPageTest(){
		super();
	}
	

	@BeforeMethod
	public void setUp() {
		
		initialization();
		loginpage = new LoginPage();
		
	}
	
	@Test(priority = 1)
	public void PageTitleTest() {
		
		String Title = loginpage.validateTitle();
		Assert.assertEquals(Title,"CRMPRO - CRM software for customer relationship management, sales, and support.");
	}
	
	@Test(priority = 2)
	public void LogoTest() {
		
		boolean Status = loginpage.validateLogo();
		Assert.assertTrue(Status);	
	}
	
	@Test(priority = 3)
	public void LoginTest() {
		

		homepage = loginpage.login(prop.getProperty("USERNAME"), prop.getProperty("PASSWORD"));	
	}
	

	
	@AfterMethod
	public void teardown() {
        
		driver.quit();
	}

}
