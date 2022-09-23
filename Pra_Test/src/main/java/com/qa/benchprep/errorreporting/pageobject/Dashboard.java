package com.qa.benchprep.errorreporting.pageobject;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.benchprep.base.Base;
import com.qa.benchprep.constant.Field;

public class Dashboard {
	public WebDriver driver;
	Base base = new Base();
	ArrayList<String> a;
	String errororderNum;
	String row1;
	WebElement row1Element;
	ArrayList<String> rowValue = new ArrayList<String>();
	// String rowValue;

	@FindBy(xpath = "//b[contains(.,'Logout')]")
	WebElement Logout;

	@FindBy(xpath = "//input[@placeholder='Order#']")
	WebElement Search;

	@FindBy(xpath = "(//a[@class='actionLink'])[1]")
	WebElement Detail_first;

	@FindBy(xpath = "//p[contains(.,'{\"status\":false,\"error\":{\"code\":\"InvalidInput\",\"message\":\"Property is invalid\",\"data\":{\"fieldName\":\"dtFile\",\"fieldType\":\"string\"}}}')]")
	WebElement Detail_assertion_emptydtFile;

	@FindBy(xpath = "//button[contains(.,'Back')]")
	WebElement Back;

	@FindBy(xpath = "//button[contains(.,'Reset')]")
	WebElement reset;

	@FindBy(xpath = "(//a[@class='actionLink'])[2]")
	WebElement Delete;

	// img[@src='assets/images/loader.gif']
	// div[@class='center-loader']//img[@class='w-50']
	@FindBy(xpath = "//img[contains(@class,'w-50')])[1]")
	WebElement loader;

	@FindBy(xpath = "//p[contains(.,'No error record found.')]")
	WebElement noRecord;

	@FindBy(xpath = "//button[@class='btn danger-btn'][contains(.,'Delete All')]")
	WebElement deleteAll;

	public Dashboard(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void logout() {
		Logout.click();
	}

	public void logout_assertion() {
		Assert.assertTrue(Base.webdriver.getTitle().equalsIgnoreCase("DIH | Login"), "LogoutAssertion failed");
	}

	public void search() throws InterruptedException {
		errororderNum = Base.jsonconfig.getProperty(Field.ERROR_OREDERNUMBER_EMPTY_DTFILE);
		Search.click();
		Search.sendKeys(errororderNum);
		Thread.sleep(2000);
		Search.sendKeys(Keys.ENTER);
	}

	public void detail_first() {
		Detail_first.click();
	}

	public void detailAssert_EmptydtFile() {
		Assert.assertTrue(Detail_assertion_emptydtFile.getText().equalsIgnoreCase(
				"{\"status\":false,\"error\":{\"code\":\"InvalidInput\",\"message\":\"Property is invalid\",\"data\":{\"fieldName\":\"dtFile\",\"fieldType\":\"string\"}}}"),
				"Wrong validation message get");
	}

	public void back() {
		Back.click();
	}

	public void delete_first() throws InterruptedException {
		reset.click();
		Thread.sleep(5000);
		Delete.click();
		driver.switchTo().alert().accept();
	}

	public void deleteAssert() throws InterruptedException {
		Search.clear();
		errororderNum = Base.jsonconfig.getProperty(Field.ERROR_OREDERNUMBER_EMPTY_DTFILETYPE);
		Thread.sleep(5000);
		Search.click();
		Search.sendKeys(errororderNum);
		Thread.sleep(2000);
		Search.sendKeys(Keys.ENTER);
		try {
			Assert.assertTrue(noRecord.getText().equalsIgnoreCase("No error record found."), "Order is not deleted");
		} catch (Exception e) {
			System.out.println("Data found");
		}
		Thread.sleep(5000);
		reset.click();
	}

//	public void accesTableColumn() throws InterruptedException {
//		// table[1]/tbody[1]/tr[1]/td[1]
//		// div[@class='table-responsive tbl-dv']//table[1]/tbody[1]/tr[1]/td[1]
//		for (int i = 1; i <= 10; i++) {
//			row1 = "//div[@class='table-responsive tbl-dv']//table[1]/tbody[1]/tr[" + i + "]/td[1]";
//			row1Element = getElementByXpathContainsText(row1);
//			Thread.sleep(2000);
//			rowValue.add(row1Element.getText());
////			rowValue=rowValue+String.valueOf(i);
////			rowValue=row1Element.getText();
////			System.out.println("value is:"+rowValue);
//		}
//		System.out.println(rowValue);
//	}

	public void deletAll() throws InterruptedException {
		deleteAll.click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		reset.click();
	}

	public void deletAllAssertion() throws InterruptedException {
		Search.clear();
		errororderNum = Base.jsonconfig.getProperty(Field.ERROR_OREDERNUMBER_EMPTY_DTFILE);
		Thread.sleep(5000);
		Search.click();
		Search.sendKeys(errororderNum);
		Thread.sleep(2000);
		Search.sendKeys(Keys.ENTER);
		Assert.assertTrue(noRecord.getText().equalsIgnoreCase("No error record found."), "Errors are not deleted");
	}

	public WebElement getElementByXpathContainsText(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

}
