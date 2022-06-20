package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. private By Locators: OR (object repository) - locators should be private in nature.
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By logoutSuccessMesg = By.cssSelector("div#common-success h1");
	
	private By vani = By.cssSelector("vani1");
		
	//2. page constructor:
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. page actions:
	@Step("getting login page title of open cart application......")
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		
	//	return driver.getTitle();
	}
	
	@Step("getting login page url of open cart application......")
	public String getLoginPageUrl() {
		return eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
		
	//	return driver.getCurrentUrl();
	}
	
	@Step("User is able to login with username {0} and password {1}")
	public AccountsPage dologin(String un, String pwd) {
		System.out.println("Login credentials are : " + un + " : "+ pwd);
		eleUtil.waitForElementVisible(emailId, Constants.DEFAULT_ELEMENT_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
		
//		driver.findElement(emailId).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
	}
		
	@Step("isForgotPwdLinkExist......")
		public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
		
	//	return driver.findElement(forgotPwdLink).isDisplayed();
	}
	
	@Step("isRegisterLinkExist()......")
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
		
	//	return driver.findElement(registerLink).isDisplayed();
	}
	
	@Step("Fetching success message for logout.....")
	public String getLogoutSuccessMessg() {
		return eleUtil.waitForElementVisible(logoutSuccessMesg, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
		
	}
	
	@Step("Navigating to register page after clicking on register link")
	public RegisterPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
}
