package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(name = "firstname")
	private WebElement firstnameField;

	@FindBy(name = "lastname")
	private WebElement lastnameField;

	@FindBy(name = "email")
	private WebElement emailAddressField;

	@FindBy(name = "telephone")
	private WebElement telephoneField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(name = "confirm")
	private WebElement passwordConfirmField;

	@FindBy(name = "agree")
	private WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@name='newsletter' and @value='1']")
	private WebElement yesNewLetterOption;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;

	@FindBy(xpath = "//input[@name='firstname']//following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//input[@name='lastname']//following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//input[@name='email']/following-sibling::div")
	private WebElement emailAddressWarning;

	@FindBy(xpath = "//input[@name='telephone']/following-sibling::div")
	private WebElement telephoneNumbersWarning;

	@FindBy(xpath = "//input[@name='password']/following-sibling::div")
	private WebElement passwordWarning;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstnameText) {
		firstnameField.sendKeys(firstnameText);

	}

	public void enterLastName(String lastnameText) {
		lastnameField.sendKeys(lastnameText);

	}

	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);

	}

	public void enterTelephoneNumber(String telephone) {
		telephoneField.sendKeys(telephone);

	}

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);

	}

	public void enterConfirmPassword(String confirmPasswordText) {
		passwordConfirmField.sendKeys(confirmPasswordText);

	}

	public void selectPrivacyPolicy() {
		privacyPolicyField.click();

	}

	public void clickOnContinue() {
		continueButton.click();

	}

	public void selectYesNewsLetter() {
		yesNewLetterOption.click();

	}

	public String retriveDuplicateEmailAddressWarning() {
		String duplicate = duplicateEmailAddressWarning.getText();
		return duplicate;

	}

	public String retrivePrivaycPolicyWarning() {
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;

	}

	public String firstNameWarning() {
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;

	}

	public String lastNameWarning() {
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;

	}

	public String emailAddressWarning() {
		String emailAddressWarningText = emailAddressWarning.getText();
		return emailAddressWarningText;

	}

	public String telephoneNumberWarning() {
		String telephoneNumberWarningText = telephoneNumbersWarning.getText();
		return telephoneNumberWarningText;

	}

	public String passwordWarning() {
		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;

	} 

}
