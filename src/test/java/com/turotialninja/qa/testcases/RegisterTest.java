package com.turotialninja.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pages.AccountSuccessPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.RegisterPage;
import com.tutorialninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	public WebDriver driver;

	@BeforeMethod
	public void setup() {

		loadPropertiesFile();
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		homepage.selectOnRegister();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {

		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmailAddress(Utilities.genarateEmailWithTimeStamp());
		registerpage.enterTelephoneNumber(dataProp.getProperty("telphoneNumber"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.selectPrivacyPolicy();
		registerpage.clickOnContinue();

		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		String successMessage = accountSuccessPage.retrivieAccountSuccessPageHeading();
		Assert.assertEquals(successMessage, dataProp.getProperty("accountSuccessfulCreatedHeading"),
				"Account Success Page is not Displayed");
	}

	@Test(priority = 2)
	public void verifyRegisteringAnAccountByProvidingAllFields() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmailAddress(Utilities.genarateEmailWithTimeStamp());
		registerpage.enterTelephoneNumber(dataProp.getProperty("telphoneNumber"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.selectYesNewsLetter();
		registerpage.selectPrivacyPolicy();
		registerpage.clickOnContinue();

		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		String successMessage = accountSuccessPage.retrivieAccountSuccessPageHeading();
		Assert.assertEquals(successMessage, dataProp.getProperty("accountSuccessfulCreatedHeading"),
				"Account Success Page is not Displayed");

	}

	@Test(priority = 3)
	public void verifyRegisteringAnAccountWithExistingEmail() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmailAddress(prop.getProperty("validUsername"));
		registerpage.enterTelephoneNumber(dataProp.getProperty("telphoneNumber"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.selectYesNewsLetter();
		registerpage.selectPrivacyPolicy();
		registerpage.clickOnContinue();

		String emailregistered = registerpage.retriveDuplicateEmailAddressWarning();
		Assert.assertEquals(emailregistered, dataProp.getProperty("duplicateEmailWarning"),
				"Warning E-mail Address is not displayed");

	}

	@Test(priority = 4)
	public void verifyRegisteringAnAccountWithoutFillingDetails() {

		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.clickOnContinue();

		String privacypolicymessage = registerpage.retrivePrivaycPolicyWarning();
		Assert.assertEquals(privacypolicymessage, dataProp.getProperty("privacyPolicyWarning"),
				"Privacy policy message is not displayed");

		String firstnameMessage = registerpage.firstNameWarning();
		Assert.assertEquals(firstnameMessage, dataProp.getProperty("firstNameWarning"),
				"First Name message is not displayed");

		String lastnameMessage = registerpage.lastNameWarning();
		Assert.assertEquals(lastnameMessage, dataProp.getProperty("lastNameWarning"),
				"Last Name message is not displayed");

		String emailMessage = registerpage.emailAddressWarning();
		Assert.assertEquals(emailMessage, dataProp.getProperty("emailWarning"),
				"Email Address message  is not  displayed");

		String telephoneMessage = registerpage.telephoneNumberWarning();
		Assert.assertEquals(telephoneMessage, dataProp.getProperty("telephoneWarning"),
				"Telephone Address message is not displayed");

		String passwordMessage = registerpage.passwordWarning();
		Assert.assertEquals(passwordMessage, dataProp.getProperty("paswordWarning"),
				"Password message is not displayed");

	}

}
