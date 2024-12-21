package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage{

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	//products page
	@FindBy(xpath="//input[@id='search_product']") WebElement txt_search;
	@FindBy(xpath="//button[@id='submit_search']") WebElement btn_search;
	@FindBy(xpath="/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul/li/a") WebElement btn_viewFirstSearchProduct;
	@FindBy(xpath="/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/a") WebElement btn_productPageAddToCart;
	@FindBy(xpath="//u[normalize-space()='View Cart']") WebElement link_productPageViewCart;
	@FindBy(xpath="//button[normalize-space()='Continue Shopping']") WebElement btn_productPageContinueShopping;
	@FindBy(xpath="/html/body/section[2]/div/div/div[2]/div/div[2]") WebElement btn_productPageDefaultAddToCart;
	
	@FindBy(xpath="//p[normalize-space()='Your product has been added to cart.']") WebElement display_productAddedtoCart;	


	//product details page
	@FindBy(xpath="//b[normalize-space()='Availability:']") WebElement display_availability;
	@FindBy(xpath="//b[normalize-space()='Condition:']") WebElement display_condition;
	@FindBy(xpath="//b[normalize-space()='Brand:']") WebElement display_brand;
	@FindBy(xpath="/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[1]") WebElement display_category;
	@FindBy(xpath="/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/span") WebElement display_price;
	@FindBy(xpath="//input[@id='quantity']") WebElement txt_quantityInput;
	@FindBy(xpath="//div[@class='product-information']//h2") WebElement display_ProductName;
	@FindBy(xpath="//p[normalize-space()='Your product has been added to cart.']") WebElement display_productDetailPageAddedtoCart;
	//div[@class='product-information']//h2
	@FindBy(xpath="//button[normalize-space()='Add to cart']") WebElement btn_productDetailsPageAddtoCart;

	//pop up window
	@FindBy(xpath="//button[normalize-space()='Continue Shopping']") WebElement btn_continueShopping;
	@FindBy(xpath="//u[normalize-space()='View Cart']") WebElement link_viewCart;



	public boolean validateSearchPageNavigation() {
		if(driver.getCurrentUrl().contains("product"))
			return true;
		else
			return false;
	}
	
	public String validateSearchProduct() {
		return driver.getCurrentUrl();
	}

	//Product Page
	public void SearchProdThroughSearchBtn(String product) {
		txt_search.clear();
		txt_search.sendKeys(product);
		btn_search.click();
	}

	public void SearchProdThroughEnterKey(String product) {
		txt_search.clear();
		txt_search.sendKeys(product);
		txt_search.sendKeys(Keys.ENTER);
	}

	public String validateSearchBarPlaceholder() {
		return txt_search.getAttribute("placeholder");
	}

	public void clickViewProductOfFirstSearchProduct() {
		javaScriptExecutor().executeScript("arguments[0].click()", btn_viewFirstSearchProduct);
	}
	
	public void AddProductToCartViaProductPage() {
		javaScriptExecutor().executeScript("arguments[0].click()", btn_productPageAddToCart);
	//	waitHelper().until(ExpectedConditions.visibilityOf(btn_productPageAddToCart)).click();
	}
	
	public boolean validateProductAddedToCart() {
		return waitHelper().until(ExpectedConditions.visibilityOf(display_productAddedtoCart)).isDisplayed();
	}
	
	public boolean[] validatePopup() {
		boolean status[] = new boolean[2];
		status[0] = waitHelper().until(ExpectedConditions.visibilityOf(link_productPageViewCart)).isDisplayed();
		status[1] = waitHelper().until(ExpectedConditions.visibilityOf(btn_productPageContinueShopping)).isDisplayed();
		return status;
	}
	
	public void clickDisplayPagePopupCartNavigation() {
		waitHelper().until(ExpectedConditions.visibilityOf(link_productPageViewCart)).click();
	}
	
	public void clickDisplayPageContinueShopping() {
		waitHelper().until(ExpectedConditions.visibilityOf(btn_productPageContinueShopping)).click();
	}

	//Product Details page
	public boolean validateProductDetails(String product) {
		boolean [] flag=new boolean[5];
		flag[0] = display_availability.isDisplayed();
		flag[1] = display_brand.isDisplayed();
		flag[2] = display_category.getText().contains(product);
		flag[3] = display_condition.isDisplayed();
		flag[4] = display_price.isDisplayed();
		boolean status=false;
		for(boolean f:flag) {
			if(f==true) {
				status=true;				
			}
		}
		return status;
	}

	public void AddProductToCartViaProductDetailsPage() {
		waitHelper().until(ExpectedConditions.visibilityOf(btn_productDetailsPageAddtoCart)).click();
	}
	
	public String getProductName() {
		return waitHelper().until(ExpectedConditions.visibilityOf(display_ProductName)).getText();
	}

	public void setProductQuantity(String quantity) {
		txt_quantityInput.clear();
		txt_quantityInput.sendKeys(quantity);
		
	}

}
