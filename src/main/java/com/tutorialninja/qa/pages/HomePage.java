package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	// Objects

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(name = "search")
	private WebElement searchBoxField;

	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	
	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions

	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}

	public void selectOnLogin() {
		loginOption.click();

	}

	public void selectOnRegister() {
		registerOption.click();

	}

	public void enterValidProductNameToSearch(String prodcutText) {
		searchBoxField.sendKeys(prodcutText);

	}

	public void clickOnSearchButton() {
		searchButton.click();

	}
	
	

}
