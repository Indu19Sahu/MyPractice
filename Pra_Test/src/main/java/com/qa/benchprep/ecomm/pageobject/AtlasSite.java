package com.qa.benchprep.ecomm.pageobject;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.benchprep.base.Base;
import com.qa.benchprep.constant.Field;
import com.qa.benchprep.util.FileCompareFromFolders_directory_and_files;
import com.qa.benchprep.util.UnzipUtility;

public class AtlasSite {
	public WebDriver driver;
	Base base = new Base();
	String orderNum;
	String xpathforacces_orderdetail;
	String xpathfor_checkboxordenumber;
	String xpathfor_checkbox;
	ArrayList<String> a;
	WebElement order_detail;
	WebElement checkbox;
	Timestamp timestamp;

	@FindBy(xpath = "//a[contains(text(),'Accept')]")
	WebElement Accept;

	@FindBy(xpath = "//span[contains(text(),'Login')]")
	WebElement login;

	@FindBy(xpath = "//input[@id='Email']")
	WebElement Email;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "//button[contains(@type,'submit')]")
	WebElement login_button;

	@FindBy(xpath = "//span[contains(text(),'Welcome Demo!')]")
	WebElement welcome;

	@FindBy(xpath = "//a[contains(.,'View Order History')]")
	WebElement order_history;

	@FindBy(xpath = "(//a[@href='#'][contains(.,'Go to the next page')])[1]")
	WebElement next_order;

	@FindBy(xpath = "(//div[@class='order-history']//ul[@class='k-pager-numbers k-reset'])")
	WebElement next;

	@FindBy(xpath = "//h1[contains(.,'Order Number: ')]")
	WebElement orderDetailAssertByOrderNumber;

	@FindBy(xpath = "//button[contains(text(),'Download BOL & DT')]")
	WebElement download;

	@FindBy(xpath = "//a[contains(text(),'Download BOL & DT')]")
	WebElement downloadFromDetail;

	@FindBy(xpath = "(//input[ @type='checkbox'])")
	WebElement checkboxes;

	public AtlasSite(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void assertion() {
		if (driver.findElement(By.xpath("//span[contains(text(),'Welcome')]")) == null) {
			System.out.println("Unsuccessfull login");
		}
	}

	public void accept() {
		Accept.click();
	}

	public void Login() {
		login.click();
	}

	public void email() {
		Email.sendKeys(Base.webconfig.getProperty(Field.EMAIL_ATLAS));
	}

	public void password() {
		Password.sendKeys(Base.webconfig.getProperty(Field.PASSWORD_ATLAS));
	}

	public void Login_button() {
		login_button.click();
	}

	public void view_orderHistory() {
		order_history.click();
	}

	public void assert_checkbox_forAny_notavailable() {
		try {
			Assert.assertFalse(checkboxes.isDisplayed(), "checkbox is available");
		} catch (AssertionError e) {
			System.out.println(e);
		}
	}

	public void next_Order() {
		int next_element = next.findElements(By.xpath("//div[@class='order-history']//li")).size();
		for (int i = 0; i < next_element; i++) {
			next_order.click();
		}
	}

	public void accesOrderNumber() {
		orderNum = Base.webconfig.getProperty("orderNumber1");
		// System.out.println(orderNum);
		xpathforacces_orderdetail = "//a[@href='/Account/Order/" + orderNum + "']";

		xpathfor_checkboxordenumber = "(//input[@value='" + orderNum + "'and @type='checkbox'])";
		// System.out.println(xpath1);
		checkbox = getElementByXpathContainsText(xpathfor_checkboxordenumber);
		order_detail = getElementByXpathContainsText(xpathforacces_orderdetail);
	}

	public WebElement getElementByXpathContainsText(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	public void navigateToDetailsOfOrder() {
		order_detail.click();
	}

	public void orderDetailassertion() {
		Assert.assertTrue(orderDetailAssertByOrderNumber.getText().contains("" + orderNum + ""),
				"Wrong detail(wrong order number");
	}

	public void click_to_all_download() throws InterruptedException {
		download.click();
	}

	public void assert_checkbox_forOrdeNumber_notavailable() {
		try {
			Assert.assertFalse(checkbox.isDisplayed(), "checkbox is available");
		} catch (AssertionError e) {
			System.out.println(e);
		}
	}

	public void assert_checkbox_forOrdeNumber_available() {
		try {
			Assert.assertTrue(checkbox.isDisplayed(), "checkbox is not available");
		} catch (AssertionError e) {
			System.out.println(e);
		}
	}

	public void click_checkbox_oderNumber1() {
		checkbox.click();
	}

	public void details_click_to_all_download() {
		downloadFromDetail.click();
	}

	public void unzip() {
		File dir1 = new File(Base.DownloadFilepath);
		for (File file1 : dir1.listFiles()) {
			if (file1.getName().endsWith((".zip"))) {
				String zipfile = file1.getName();
				File zipFile = new File(Base.DownloadFilepath, zipfile);
				String zippath = zipFile.getAbsolutePath();
				String destDirectory = (Base.DownloadFilepath);
				UnzipUtility unzipper = new UnzipUtility();
				try {
					unzipper.unzip(zippath, destDirectory);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public void compare() {
		try {
			FileCompareFromFolders_directory_and_files.listFiles(Base.DownloadFilepath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
