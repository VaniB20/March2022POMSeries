package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By cartSuccessMessg = By.xpath("//div[@class='alert alert-success']"); 
	private By cart = By.cssSelector("div#cart button.dropdown-toggle");
	
	Map<String, String> productInfoMap;
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String getProductHeaderName() {
		return eleUtil.waitForElementVisible(productHeader, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
	}
	
	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(productImages, Constants.DEFAULT_ELEMENT_TIME_OUT).size();
	}
	

	public Map<String, String> getProductInformation() {
		 productInfoMap = new HashMap<String, String>();
		 productInfoMap.put("name",  getProductHeaderName());
		 productInfoMap.forEach((k,v) -> System.out.println(k+":"+v));
		 getProductMetaData();
		 getProductPriceData();
		 
			return productInfoMap;
		 }
		private void getProductMetaData() {
			 //meta data
			 List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
			 System.out.println("Total product Meta Data : " + metaDataList.size());
			 for(WebElement e: metaDataList) {
				String meta[] = e.getText().split(":");
				String metaKey = meta[0].trim();
				String metaValue = meta[1].trim();
				productInfoMap.put(metaKey, metaValue);
			 }	
		}
		
		private void getProductPriceData() {
			//price data
			List<WebElement> priceList = eleUtil.getElements(productPriceData);
			 String price = priceList.get(0).getText().trim();
			 String exTaxPrice = priceList.get(1).getText().trim();
			 productInfoMap.put("price", price);
			 productInfoMap.put("ExTaxPrice", exTaxPrice);
		}
		
		public String getProductInfoPageInnerText() {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String pageInnerText = js.executeScript("return document.documentElement.innerText").toString();
			System.out.println("============================\n" + pageInnerText + "\n==================");
			return pageInnerText;
		}
		
		public ProductInfoPage enterQty(String qty) {
			eleUtil.doSendKeys(quantity, qty);
			return this;
			}
		
		public ProductInfoPage clickOnAddToCart() {
			eleUtil.doClick(addToCartBtn);
			return this;
			
		}
		
		public String getCartSuccessMessg() {
			return eleUtil.waitForElementVisible(cartSuccessMessg, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
		}
		
		public String getCartItemtext() {
			return eleUtil.doGetText(cart);
		}
}
