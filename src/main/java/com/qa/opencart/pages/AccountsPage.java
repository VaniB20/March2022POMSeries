package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By header = By.cssSelector("div#logo a");
	private By logoutLink = By.linkText("Logout");
	private By sectionHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
		
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String getAccountsPageTitle() {
		return eleUtil.waitForTitleIs(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT );
	}
	
	public String getAccountsPageUrl() {
		return eleUtil.waitForUrlContains(Constants.ACCOUNT_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccountsPageHeader() {
		return eleUtil.doGetText(header);
	}
	
	public List<String> getAccountsPageSectionsList() {
		List<WebElement> secList = eleUtil.getElements(sectionHeaders);
		List<String> secValList = new ArrayList<String>();
		for(WebElement e : secList) {
			String text = e.getText();
			secValList.add(text);
		}
		return secValList;
	}
	
	public boolean isLogoutLinkExist() {
	return eleUtil.waitForElementVisible(logoutLink, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}
	
	public LoginPage clickOnLogout() {
		if(isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
		return new LoginPage(driver);
	}
	
	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		if (isSearchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		}
		return null;
	}

	
}
