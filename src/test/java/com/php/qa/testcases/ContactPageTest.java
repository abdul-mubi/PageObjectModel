package com.php.qa.testcases;



import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.php.qa.base.BaseClass;
import com.php.qa.pages.ContactPage;
import com.php.qa.pages.HomePage;
import com.php.qa.pages.LoginPage;
import com.php.qa.util.TestUtils;

public class ContactPageTest extends BaseClass{
	
	LoginPage loginpage;
	HomePage homepage;
	ContactPage contactpage;
	
	public ContactPageTest() {
		
		super();
	}

	@BeforeMethod
	public void setup() {
		
		initialization();
		loginpage = new LoginPage();
		homepage = loginpage.login(prop.getProperty("USERNAME"), prop.getProperty("PASSWORD"));
		TestUtils.switchToFrame();
		contactpage = homepage.clickNewContactLink();
	}
	
	
	@Test(priority=1)
	public void pageVerificationTest() {
		String Actual = contactpage.pageVerification();
		Assert.assertEquals(Actual, "Contact Information");
		
	}
	
	@Test(priority=2)
	public void alertTest() {
		
		contactpage.alertCheck("abd");
		driver.switchTo().alert().accept();
		Assert.assertTrue(true);
	}
	
	
	@DataProvider
	public Object[][] testData(){
		Object testdata[][] = TestUtils.addTestData("Contacts");
		return testdata;
		
	}
	
	@Test(priority=3,dataProvider ="testData")
	public void formFillingTest(String firstname,String lastname, String companyname,String category,String status,String phnumber,String birthDate, String desNote) {
		
		contactpage.formFilling(firstname, lastname, companyname, category, status, phnumber, birthDate, desNote);
		String nameTxt = contactpage.summaryPage();
		homepage.clickContactLink();
		String phoneNo = contactpage.searchMethod(nameTxt);
		Assert.assertEquals(phoneNo, phnumber);
		//TestUtils.screenshot();
	}
	
	
	
	@AfterMethod
	public void teardown(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			
			TestUtils.screenshot();
		}
		
		driver.quit();
	}

}
