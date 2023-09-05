package com.turotialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pages.AccountPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.LoginPage;
import com.tutorialninja.qa.utils.Utilities;

public class LoginTest extends Base {

	public WebDriver driver;

	@BeforeMethod
	public void setup() {

		loadPropertiesFile();
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver); 
		homepage.clickOnMyAccount();
		homepage.selectOnLogin();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

	@Test(priority = 5, dataProvider = "validCredentialSupplier")
	public void verifyLoginwithValidCredentials(String email, String password) {

		LoginPage loginpage = new LoginPage(driver); //Invokes the parameterized constructor
		loginpage.enterEmailAddress(email);
		loginpage.enterPassword(password);
		loginpage.clickOnLoginButton();

		AccountPage accountpage = new AccountPage(driver);
		Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformation(),
				dataProp.getProperty("emailPasswordNoMatching"));

	}

	@DataProvider(name = "validCredentialSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Search");
		return data;

	}

	@Test(priority = 1)
	public void verifyLoginwithInvalidLoginCredentials() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(Utilities.genarateEmailWithTimeStamp());
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 2)

	public void verifyLoginWithInvalidEmailAndValidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(Utilities.genarateEmailWithTimeStamp());
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(prop.getProperty("validUsername"));
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginWithOutProvidingCredentials() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message is not displayed");

	}

}
