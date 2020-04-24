package com.php.qa.testcases;



import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.php.qa.base.BaseClass;
import com.php.qa.pages.DealPage;
import com.php.qa.pages.HomePage;
import com.php.qa.pages.LoginPage;
import com.php.qa.util.TestUtils;

public class DealPageTest extends BaseClass {
	
	LoginPage loginpage;
	HomePage homepage;
	DealPage dealpage;
	
	public DealPageTest() {
		
		super();
	}

	@BeforeMethod
	public void setup() {
		
		initialization();
		loginpage = new LoginPage();
		homepage = loginpage.login(prop.getProperty("USERNAME"), prop.getProperty("PASSWORD"));
		TestUtils.switchToFrame();
		dealpage = homepage.ClickNewDealLink();
	}
	
	
	@Test(priority=1)
	public void titleTest() {
		
		String actual=dealpage.pageTitle();
		Assert.assertEquals(actual, "Deal");
	}
	
	@DataProvider
	public Object[][] testData(){
		
		Object testdata[][] = TestUtils.addTestData("Deal");
		return testdata;
		
	}
	
	@Test(priority=2, dataProvider ="testData")
	public void formFillingTest(String Title, String Company, String Amount, String Product,  String Predicteddate, String Actualdate) {
		
		dealpage.formFilling(Title, Company, Amount, Product,Predicteddate,Actualdate);
		
		String dealnumber = dealpage.dealNo();
		
		dealpage = homepage.clickDealLink();
		
		String actualTitle = dealpage.searchMethod(dealnumber);
		
		Assert.assertEquals(actualTitle, Title);
		
		
	}
	
	
	
	@AfterMethod
	public void teardown(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			
			TestUtils.screenshot();
		}
		
		driver.quit();
	}
	
	
	
	
}
