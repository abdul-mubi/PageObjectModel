package com.php.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.php.qa.base.BaseClass;

public class DealPage extends BaseClass {
	
	@FindBy(xpath="//input[@name='title']")
	WebElement Title; 
	
	@FindBy(xpath="//input[@name='client_lookup']")
	WebElement Company;
	
	@FindBy(xpath="//input[@name='amount']")
	WebElement Amount;
	
	@FindBy(xpath="//legend[text()='Deal']")
	WebElement DealTxt;
	
	@FindBy(xpath="//input[@name='close_date']//following-sibling::img[@title='Date selector']")
	WebElement PredictedDate;
	
	@FindBy(xpath="//input[@name='actual_close_date']//following-sibling::img[@title='Date selector']")
	WebElement ActualDate;
	
	@FindBy(xpath="//select[@name='product_id']")
	WebElement ProdSelect;
	
	@FindBy(xpath="//input[@value='Save and Create Another']//preceding-sibling::input[@value='Save']")
	WebElement Savebtn;
	
	@FindBy(xpath="//td[@align='left' and @class='datafield']")
	WebElement dealno;
	
	@FindBy(xpath = "//input[@name='cs_keyword']")
	WebElement keywordText;
	
	@FindBy(xpath = "//input[@name='cs_submit' and @value = 'Search']")
	WebElement searchBtn;
	
	@FindBy(xpath = "//a[@context='deal']//parent::td//following-sibling::td//a[@context='deal']")
	WebElement resultTitle;
	
	public DealPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public String pageTitle() {
		
		return DealTxt.getText();
	}
	
	public void formFilling(String title, String company,String amount, String product, String predictedDate, String actualDate) {
		
		Title.sendKeys(title);
		Company.sendKeys(company);
		Amount.sendKeys(amount);
		
		Select sel = new Select(ProdSelect);
		sel.selectByVisibleText(product);
		
		PredictedDate.click();
		//html/body/div[6]/table/tbody/tr[3]/td[5]
		//html/body/div[6]/table/tbody/tr[5]/td[6]
		String startXPath = "/html/body/div[6]/table/tbody/tr[";
		String endXPath = "]/td[";
		
		for(int i=1; i<=6; i++) {
			for(int j=2; j<=8; j++) {
				
				WebElement ele = driver.findElement(By.xpath(startXPath+i+endXPath+j+"]"));
				
				String out = ele.getText();
				
				if(out.equals(predictedDate)) {
					driver.findElement(By.xpath(startXPath+i+endXPath+j+"]")).click();
					break;
				}
			}
		}
		
		
		ActualDate.click();
		String startXPath1 = "/html/body/div[6]/table/tbody/tr[";
		String endXPath1 = "]/td[";
		
		for(int i=1; i<=6; i++) {
			for(int j=2; j<=8; j++) {
				
				WebElement ele = driver.findElement(By.xpath(startXPath1+i+endXPath1+j+"]"));
				
				String out = ele.getText();
				
				if(out.equals(actualDate)) {
					driver.findElement(By.xpath(startXPath1+i+endXPath1+j+"]")).click();
					break;
				}
			}
		
	}
		
		Savebtn.click();
		
	}
		
	public String dealNo() {
		
		return dealno.getText();
		
	}
	
	public String searchMethod(String KeywordNum) {
		
		keywordText.sendKeys(KeywordNum);
		searchBtn.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return resultTitle.getText();
		
	}
	
}
	
	
	

