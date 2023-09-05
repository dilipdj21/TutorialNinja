package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	// Objects

	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailAddressField;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
	
	//Constructor which refers the instance variable of class

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Actions

	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);

	}

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);

	}

	public void clickOnLoginButton() {
		loginButton.click();

	}

	public String retrieveWarningMessageText() {
		String warningText = emailPasswordNotMatchingWarning.getText();
		return warningText;

	}
}
