package com.turotialninja.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.SearchPage;

public class SearchTest extends Base {

	public WebDriver driver;

	@BeforeMethod
	public void setup() {

		loadPropertiesFile();
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();

	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {

		HomePage homePage = new HomePage(driver);
		homePage.enterValidProductNameToSearch(dataProp.getProperty("validProduct"));
		homePage.clickOnSearchButton();

		SearchPage searchpage = new SearchPage(driver);
		Assert.assertTrue(searchpage.displayStatusOFHPValidProduct(), "Valid product HP is not displayed");

	}

	@Test(priority = 2)
	public void veifySearchWithInvalidProduct() {

		HomePage homePage = new HomePage(driver);
		homePage.enterValidProductNameToSearch(dataProp.getProperty("InvalidProduct"));
		homePage.clickOnSearchButton();

		SearchPage searchpage = new SearchPage(driver);
		String actualResult = searchpage.retriveNoProductMessageText();
		Assert.assertEquals(actualResult, dataProp.getProperty("NoProductInSearchResults"),
				"No product message in search result is not displayed");
//		Assert.assertEquals(actualResult, "jfjgjgjg", "No product message in search result is not displayed");

	}

	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {

		HomePage homePage = new HomePage(driver);
		homePage.clickOnSearchButton();

		SearchPage searchpage = new SearchPage(driver);
		String actualResult = searchpage.retriveNoProductMessageText();
		Assert.assertEquals(actualResult, dataProp.getProperty("NoProductInSearchResults"),
				"No product message in search result is not displayed");

//		NoProductInSearchResults

//		driver.findElement(By.name("search")).sendKeys("");
//		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
//
//		String actualResult = driver.findElement(By.xpath("//div[@id='content']//h2/following-sibling::p")).getText();
//		Assert.assertEquals(actualResult, dataProp.getProperty("NoProductInSearchResults"),
//				"No product message in search result is not displayed");

	}

}
