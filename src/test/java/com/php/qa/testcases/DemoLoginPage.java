package com.php.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.php.qa.base.BaseClass;
import com.php.qa.pages.HomePage;
import com.php.qa.pages.LoginPage;
import com.php.qa.util.TestUtils;

public class DemoLoginPage  extends BaseClass{
	
	
	LoginPage loginpage;
	HomePage homepage;
	
	public DemoLoginPage(){
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		
		initialization();
		loginpage = new LoginPage();
		
	}
	
	@Test(priority = 1)
	public void LoginTest() {
		
		String data1 = TestUtils.addTestData1("Deal");
		
		System.out.println(data1);

		homepage = loginpage.login(data1, "ravin@123");
		
	}
	

	
	@AfterMethod
	public void teardown() {
        
		driver.quit();
	}

}
