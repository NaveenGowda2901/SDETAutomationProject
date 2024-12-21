package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[normalize-space()='Signup / Login']") WebElement link_signUpLogin;
	@FindBy(xpath="//a[normalize-space()='Logout']") WebElement link_logout;
	@FindBy(xpath="//a[normalize-space()='Delete Account']") WebElement link_deleteAccount;
	@FindBy(xpath="//a[@href='/products']") WebElement link_products;
	
	
	
	public boolean validateHomePage(){
		if(driver.getTitle().equals("Automation Exercise")) //Automation Exercise
			return true;
		else 
			return false;
	}
	
	//Click Header Links
	public void clickSinupLogin() {
		link_signUpLogin.click();
	}
	
	public void clickLogOut() {
		link_logout.click();
	}
	
	public void clickDeleteAccount() {
		link_deleteAccount.click();
	}
	
	public void clickProducts() {
		link_products.click();
	}
	
	//Validations
	public boolean validateLogoutLinkDisplay() {
		return link_logout.isDisplayed();
	}
	
	public boolean validateSignupLoginLink() {
		return link_signUpLogin.isDisplayed();
	}
	
	public void navigateBack() {
		driver.navigate().back();
	}


}
