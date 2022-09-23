package com.qa.benchprep.errorreporting.pageobject;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.benchprep.base.Base;
import com.qa.benchprep.constant.Field;

public class Login {
	public WebDriver driver;
	Base base = new Base();
	ArrayList<String> a;

	@FindBy(xpath = "//input[@type='password']")
	WebElement Password;

	@FindBy(xpath = "//input[@placeholder='Email ID']")
	WebElement Email;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement Submit;

	@FindBy(xpath = "//h2[@class='cart-heading'][contains(.,'List of Error')]")
	WebElement Heading;

	public Login(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// ArrayList<String> a=b.webaccess("Login");
	}

	public void SendEmail() {
		// Email.sendKeys(a.get(0));
		Email.sendKeys(Base.webconfig.getProperty(Field.EMAIL_SPA));
	}

	public void SendPassword() {
		// Password.sendKeys(a.get(1));
		Password.sendKeys(Base.webconfig.getProperty(Field.PASSWORD_SPA));
	}

	public void Submit() {
		Submit.click();
	}

	public void assertion() {
		// using page heading
		Assert.assertEquals(Heading.getText(), "List of Error");
		// using page title
		// Assert.assertTrue(b.webdriver.getTitle().contains("Dashboard"));
	}

}
