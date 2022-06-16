package com.qa.opencart.testa;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

/**
 * @author Hansika
 *
 */

@Epic("Epic - 100: this epic is for Accounts page of open cart application")
@Story("LOGIN - 101: Design Accounts Page with various features")

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.dologin(prop.getProperty("username"), prop.getProperty("password").trim());
	//	accPage = new AccountsPage(driver);
	}

	@Description("Accounts page title test....")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void accPageTitleTest() {
		String accTitle = accPage.getAccountsPageTitle();
		System.out.println("Account Page Title : " + accTitle);
		Assert.assertEquals(accTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Description("Accounts page url test....")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageUrlTest() {
		String accURL = accPage.getAccountsPageUrl();
		System.out.println("Account Page URL : " + accURL);
		Assert.assertTrue(accURL.contains(Constants.ACCOUNT_PAGE_URL_FRACTION));
	}
	
	@Description("Accounts page header test....")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageHeaderTest() {
		Assert.assertEquals(accPage.getAccountsPageHeader(), Constants.ACCOUNTS_PAGE_HEADER);
	}
	
	@Test
	public void accPageSectionsTest() {
			List<String> accPageSecList = accPage.getAccountsPageSectionsList();
			System.out.println("Account Sections List : " + accPageSecList);
			Assert.assertEquals(accPageSecList, Constants.EXPECTED_ACCOUNTS_SECTION_LIST);
	}
	
	@Description("logoutLinkTest....")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void logoutLinkTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Description("searchExistTest....")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	
	@Description("User is able to logout to open cart application test....")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void logoutTest() {
		Assert.assertEquals(accPage.clickOnLogout().getLogoutSuccessMessg(), Constants.LOGOUT_SUCCESS_MESSG);
	}
	
	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
		};
		
	}
	
	@Description("search test....")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider ="getSearchKey")
	public void searchTest(String searchKey) {
		searchResPage = accPage.doSearch(searchKey);
		Assert.assertTrue(searchResPage.getSearchResultsCount()>0);
	}
	
//	@DataProvider
//	public Object[][] getProductName() {
//		return new Object[][] {
//			{"Macbook", "MacBook Pro"},
//			{"Macbook", "MacBook Air"},
//			{"Macbook", "MacBook"},
//			{"iMac", "iMac"},
//			{"Apple", "Apple Cineme 30\""},
//			{"Samsung","Samsung SyncMaster 941BW"}
//		};
//		
//	}
	
	@DataProvider
	public Object[][] getProductName() {
	return ExcelUtil.getTestdata(Constants.PRODUCT_SHEET_NAME);	
	}
	
	@Test(dataProvider ="getProductName", enabled = false)
	public void selectProductTest(String searchKey, String productName ) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		String productHeader = productInfoPage.getProductHeaderName();
		System.out.println("product header: " + productHeader);
		Assert.assertEquals(productHeader, productName);
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro", 4},
			{"Samsung","Samsung SyncMaster 941BW", 1}
		};
		
	}
	
//	@DataProvider
//	public Object[][] getProductData() {
//		return ExcelUtil.getTestdata(Constants.PRODUCT_SHEET_DATA);
//	}
	
	@Test(dataProvider ="getProductData", enabled = false)
	public void productImageTest(String searchKey, String productName, int productImageCount ) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), productImageCount);
	}
	

//	@DataProvider
//	public Object[][] getProductInfoData() {
//		return new Object[][] {
//			{"Brand", "Apple"},
//			{"Product Code","Product 18"},
//			{"Reward Points", "800"},
//			{"Availability", "In Stock"},
//			{"price", "$2,000.00"},
//			{"Ex Tax","$2,000.00"}
//		};
//		
//	}
//	
	@DataProvider
	public Object[][] getProductInfoData() {
		return ExcelUtil.getTestdata(Constants.PRODUCT_SHEET_INFO_DATA);
	}
	
	
	@Test(dataProvider = "getProductInfoData", enabled = false)
	public void productInfoTest(String name, String productInfo) {
		accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String, String> accProductInfoMap = productInfoPage.getProductInformation();
		softAssert.assertEquals(accProductInfoMap, productInfo);
//		softAssert.assertEquals(accProductInfoMap.get("Brand"), "Apple");
//		softAssert.assertEquals(accProductInfoMap.get("Availability"), "In Stock");
//		softAssert.assertEquals(accProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertAll();
	}
	
//	@Test
//	public void productInfoTest() {
//		searchResPage = accPage.doSearch("Macbook");
//		productInfoPage = searchResPage.selectProduct("MacBook Pro");
//		Map<String, String> actProductInfoMap = productInfoPage.getProductInformation();
//		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
//		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
//		softAssert.assertEquals(actProductInfoMap.get("Availability"), "In Stock");
//		softAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
//		softAssert.assertAll();
//	}
	
	@Test(enabled = false)
	public void productInfoDescriptionTest() {
		accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains("Latest Intel mobile architecture"));
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains("the new Core 2 Duo MacBook Pro"));
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains("Designed for life on the road"));
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains("Next-generation wireless"));
		softAssert.assertAll();
	}
	
	@Test(enabled = false)
	public void addToCartTest() {
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		String actSuccessMessg = productInfoPage.enterQty("1").clickOnAddToCart().getCartSuccessMessg();
		System.out.println("cart message :" + actSuccessMessg );
		softAssert.assertTrue(actSuccessMessg.contains("MacBook Pro"));
		String actCartItemText = productInfoPage.getCartItemtext();
		softAssert.assertTrue(actCartItemText.contains("1" + " item(s)"));
		
	}
}
