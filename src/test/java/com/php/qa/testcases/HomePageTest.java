package com.php.qa.testcases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.php.qa.base.BaseClass;
import com.php.qa.pages.HomePage;
import com.php.qa.pages.LoginPage;
import com.php.qa.pages.DealPage;
import com.php.qa.util.TestUtils;

public class HomePageTest extends BaseClass {
	
	LoginPage loginpage;
	HomePage homepage;
	DealPage dealpage;
	
	public HomePageTest() {
		
		super();
	}
	
	@BeforeMethod
	public void setup() {
		
		initialization();
		loginpage = new LoginPage();
		//dealpage =new DealPage();
		homepage = loginpage.login(prop.getProperty("USERNAME"), prop.getProperty("PASSWORD"));
		
	}
	
	@Test(priority = 1)
	public void HomePageTitleTest() {
		
		String Title = driver.getTitle();
		Assert.assertEquals(Title, "CRMPRO");
		
	}
	
	@Test(priority = 2)
	public void ClickNewDealTest() {
		
		TestUtils.switchToFrame();
		dealpage = homepage.ClickNewDealLink();
	}
	
	@AfterMethod
	public void teardown(ITestResult result) {
		
        if(result.getStatus()==ITestResult.FAILURE) {
        	
			TestUtils.screenshot();
		}
        
		driver.quit();
	}

}
