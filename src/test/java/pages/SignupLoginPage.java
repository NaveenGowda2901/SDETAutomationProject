package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignupLoginPage extends BasePage{

	public SignupLoginPage(WebDriver driver) {
		super(driver);
	}
	
	//Signup elements
	@FindBy(xpath="//input[@placeholder='Name']") WebElement txt_signUpName;
	@FindBy(xpath="//input[@data-qa='signup-email']") WebElement txt_signUpEmail;
	@FindBy(xpath="//button[normalize-space()='Signup']") WebElement btn_signUp;
	
	//Sigin elements
	@FindBy(xpath="//input[@data-qa='login-email']") WebElement txt_loginEmail;
	@FindBy(xpath="//input[@placeholder='Password']") WebElement txt_loginPassword;
	@FindBy(xpath="//button[normalize-space()='Login']") WebElement btn_login;
	
	@FindBy(xpath="//img[@alt='Website for automation practice']") WebElement logo_HomePage;
	@FindBy(xpath="//b[normalize-space()='Enter Account Information']") WebElement txtdisplay_accountInfo;
	@FindBy(xpath="//p[normalize-space()='Email Address already exist!']") WebElement txtDisplay_exitingEmail;
	@FindBy(xpath="//p[normalize-space()='Your email or password is incorrect!']") WebElement txtDisplay_incorrectPassword;
	
	//Account info
	@FindBy(xpath="//input[@id='name']") WebElement txt_accountinfoName;
	@FindBy(xpath="//input[@id='email']") WebElement disabled_accountinfoEmail;
	@FindBy(xpath="//div[@class='clearfix']//div[@class=\"radio-inline\"]//label") List<WebElement> btn_gender;
	@FindBy(xpath="//input[@id='password']") WebElement txt_accountRegPassword;
	@FindBy(xpath="//select[@id='days']") WebElement dropdown_dateOfBirth;
	@FindBy(xpath="//select[@id='months']") WebElement dropdown_monthOfBirth;
	@FindBy(xpath="//select[@id='years']") WebElement dropdown_yearOfBirth;
	@FindBy(xpath="//input[@id='newsletter']") WebElement chkbox_newsletter;
	@FindBy(xpath="//input[@id='optin']") WebElement chkbox_offers;
	@FindBy(xpath="//input[@id='first_name']") WebElement txt_addressFname;
	@FindBy(xpath="//input[@id='last_name']") WebElement txt_addressLname;
	@FindBy(xpath="//input[@id='company']") WebElement txt_addressComany;
	@FindBy(xpath="//input[@id='address1']") WebElement txt_address1;
	@FindBy(xpath="//input[@id='address2']") WebElement txt_address2;
	@FindBy(xpath="//select[@id='country']") WebElement dropdown_country;
	@FindBy(xpath="//input[@id='state']") WebElement txt_state;
	@FindBy(xpath="//input[@id='city']") WebElement txt_city;
	@FindBy(xpath="//input[@id='zipcode']") WebElement txt_zipcode;
	@FindBy(xpath="//input[@id='mobile_number']") WebElement txt_phone;
	@FindBy(xpath="//button[normalize-space()='Create Account']") WebElement btn_createAccount;
	@FindBy(xpath="//b[normalize-space()='Account Created!']") WebElement display_text;
	@FindBy(xpath="//a[normalize-space()='Continue']") WebElement btn_continue;
//	@FindBy(xpath="//a[normalize-space()='Logout']") WebElement btn_logout;
	
	
	
	public boolean validateSignupLoginPage() {
		if(driver.getTitle().equals("Automation Exercise - Signup / Login"))
			return true;
		else
			return false;
	}
	
	//Signup methods
	public void signUpName(String name) {
		txt_signUpName.clear();
		txt_signUpName.sendKeys(name);
	}
	
	public void signUpEmail(String email) {
		txt_signUpEmail.clear();
		txt_signUpEmail.sendKeys(email);
	}
	
	public void clickSignUp() {
		btn_signUp.click();
	}
	
	//Signin methods
	public void siginInEmail(String email) {
		txt_loginEmail.clear();
		txt_loginEmail.sendKeys(email);
	}
	
	public void siginInPassword(String pwd) {
		txt_loginPassword.clear();
		txt_loginPassword.sendKeys(pwd);
	}
	
	public void clickSignIn() {
		btn_login.click();
	}
	
	//signin validations
	public boolean validateMissingSigninEmailField() {
		waitHelper().until(ExpectedConditions.attributeContains(txt_loginEmail, "validationMessage", "Please fill out this field."));
		String validationMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_loginEmail);
		if(validationMessage.equals("Please fill out this field."))
			return true;
		else 
			return false;
	}
	
	public boolean validateMissingSigninPasswordField() {
		waitHelper().until(ExpectedConditions.attributeContains(txt_loginPassword, "validationMessage", "Please fill out this field."));
		String validationMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_loginPassword);
		if(validationMessage.equals("Please fill out this field."))
			return true;
		else 
			return false;
	}
	
	public boolean validateSigninInvalidEmailFormat() {
		String validateMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_loginEmail);
		if(validateMessage.contains("Please include an '@' in the email address"))
			return true;
		else
			return false;
	}
	
	public boolean validateIncorrectSiginPasswordOrEmail() {
		return waitHelper().until(ExpectedConditions.visibilityOf(txtDisplay_incorrectPassword)).isDisplayed();
	}
	
	public boolean validateSigninPlaceHolders() {
		if(txt_loginEmail.getAttribute("placeholder").equals("Email Address") && txt_loginPassword.getAttribute("placeholder").equals("Password"))
			return true;
		else
			return false;
	}
	
	public boolean validateSigninToggledPasswordField() {
		if(txt_loginPassword.getAttribute("type").equals("password"))
			return true;
		else
			return false;
	}
	
	public boolean validateSigninPasswordInPageSource(String password) {
		return driver.getPageSource().contains(password);
	}
	
	
	//Signup validation methods
	public boolean validateMissingSignUpEmailField() {
		waitHelper().until(ExpectedConditions.attributeContains(txt_signUpEmail, "validationMessage", "Please fill out this field."));
		String validationMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_signUpEmail);
		if(validationMessage.equals("Please fill out this field."))
			return true;
		else 
			return false;
	}
	
	public boolean validateMissingSignUpNameField() {
		waitHelper().until(ExpectedConditions.attributeContains(txt_signUpName, "validationMessage", "Please fill out this field."));
		String validationMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_signUpName);
		if(validationMessage.equals("Please fill out this field."))
			return true;
		else 
			return false;
	}
	
	public boolean validateSignUpWithExistingUser() {
		waitHelper().until(ExpectedConditions.visibilityOf(txtDisplay_exitingEmail));
		return txtDisplay_exitingEmail.isDisplayed();
	}
	
	public boolean validateSignupInvalidEmailFormat() {
		String validateMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_signUpEmail);
		if(validateMessage.contains("Please include an '@' in the email address"))
			return true;
		else
			return false;
	}
	
	public boolean validateSignUP() {
		return waitHelper().until(ExpectedConditions.visibilityOf(txtdisplay_accountInfo)).isDisplayed();
	}
	
	//Account info methods
	public void selectGender(String gender) {
		for(WebElement gen:btn_gender) {
			if(gen.getText().equals(gender)) {
				gen.click();
				break;
			}
		}
	}
	
	public void setAccountPassword(String pwd) {
		txt_accountRegPassword.clear();
		txt_accountRegPassword.sendKeys(pwd);
	}
	
	public void selectDate(String date) {
		select(dropdown_dateOfBirth).selectByValue(date);
	}
	
	public void selectMonth(String month) {
		select(dropdown_monthOfBirth).selectByValue(month);
	}
	
	public void selectYear(String year) {
		select(dropdown_yearOfBirth).selectByValue(year);
	}
	
	public void clickNewsLetter() {
		javaScriptExecutor().executeScript("arguments[0].click()", chkbox_newsletter);
	}
	
	public void clickSpecialOffers() {
		javaScriptExecutor().executeScript("arguments[0].click()", chkbox_offers);
	}
	
	public void setAddressFirstName(String name) {
		txt_addressFname.clear();
		txt_addressFname.sendKeys(name);
	}
	
	public void setAddressLastName(String name) {
		txt_addressLname.clear();
		txt_addressLname.sendKeys(name);
	}
	
	public void setCompany(String company) {
		txt_addressComany.clear();
		txt_addressComany.sendKeys(company);
	}
	
	public void setAddress1(String address) {
		txt_address1.clear();
		txt_address1.sendKeys(address);
	}
	
	public void setAddress2(String address) {
		txt_address2.clear();
		txt_address2.sendKeys(address);
	}
	
	public void setCountry(String country) {
		select(dropdown_country).selectByValue(country);
	}
	
	public void setState(String state) {
		txt_state.sendKeys(state);
	}
	
	public void setCity(String city) {
		txt_city.clear();
		txt_city.sendKeys(city);
	}
	
	public void setzip(String zip) {
		txt_zipcode.clear();
		txt_zipcode.sendKeys(zip);
	}
	
	public void setPhone(String phone) {
		txt_phone.clear();
		txt_phone.sendKeys(phone);
	}
	
	public void clickCreateAccount() {
		javaScriptExecutor().executeScript("arguments[0].click()", btn_createAccount);
	}
	
	public boolean validateAccountCreation() {
		return display_text.isDisplayed();
	}
	
	public void clickContinuetoLogin() {
		btn_continue.click();
	}
	
	//AccountInfo validation
	
	public boolean validateMissingAccountPasswordField() {
		waitHelper().until(ExpectedConditions.attributeContains(txt_accountRegPassword, "validationMessage", "Please fill out this field."));
		String validationMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_accountRegPassword);
		if(validationMessage.equals("Please fill out this field."))
			return true;
		else 
			return false;
	}
	
	public boolean validateMissingAddressFirstNameField() {
		waitHelper().until(ExpectedConditions.attributeContains(txt_addressFname, "validationMessage", "Please fill out this field."));
		String validationMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_addressFname);
		if(validationMessage.equals("Please fill out this field."))
			return true;
		else 
			return false;
	}
	
	public boolean validateMissingLastNameField() {
		waitHelper().until(ExpectedConditions.attributeContains(txt_addressLname, "validationMessage", "Please fill out this field."));
		String validationMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_addressLname);
		if(validationMessage.equals("Please fill out this field."))
			return true;
		else 
			return false;
	}
	
	public boolean validateMissingAddressFirstLineField() {
		waitHelper().until(ExpectedConditions.attributeContains(txt_address1, "validationMessage", "Please fill out this field."));
		String validationMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_address1);
		if(validationMessage.equals("Please fill out this field."))
			return true;
		else 
			return false;
	}
	
	public boolean validateMissingAddressStateField() {
		waitHelper().until(ExpectedConditions.attributeContains(txt_state, "validationMessage", "Please fill out this field."));
		String validationMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_state);
		if(validationMessage.equals("Please fill out this field."))
			return true;
		else 
			return false;
	}
	
	public boolean validateMissingAddressCityField() {
		waitHelper().until(ExpectedConditions.attributeContains(txt_city, "validationMessage", "Please fill out this field."));
		String validationMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_city);
		if(validationMessage.equals("Please fill out this field."))
			return true;
		else 
			return false;
	}
	
	public boolean validateMissingAddressZipCodeField() {
		waitHelper().until(ExpectedConditions.attributeContains(txt_zipcode, "validationMessage", "Please fill out this field."));
		String validationMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_zipcode);
		if(validationMessage.equals("Please fill out this field."))
			return true;
		else 
			return false;
	}
	
	public boolean validateMissingAddressPhoneField() {
		waitHelper().until(ExpectedConditions.attributeContains(txt_phone, "validationMessage", "Please fill out this field."));
		String validationMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_phone);
		if(validationMessage.equals("Please fill out this field."))
			return true;
		else 
			return false;
	}
	
	public boolean validateInvalidAddressPhoneField() {
		waitHelper().until(ExpectedConditions.attributeContains(txt_phone, "validationMessage", "Alphabets not allowed in this field."));
		String validationMessage = (String) javaScriptExecutor().executeScript("return arguments[0].validationMessage", txt_phone);
		if(validationMessage.equals("Alphabets not allowed in this field."))
			return true;
		else 
			return false;
	}
	
	public String validateNameFieldAccountInfo() {
		return txt_accountinfoName.getAttribute("value");
	}
	
	public String validateEmailFieldAccountInfo() {
		return disabled_accountinfoEmail.getAttribute("value");
	}
	
	public boolean validateEmailFieldAccountInfoDisabled() {
		return disabled_accountinfoEmail.isEnabled();
	}
	
	public String[] validateMandatoryFieldsMarked(){
		
		String man[] = new String[10];
		man[0] = txt_accountRegPassword.getAttribute("required");
		man[1] = txt_addressFname.getAttribute("required");
		man[2] = txt_addressFname.getAttribute("required");
		man[3] = txt_addressLname.getAttribute("required");
		man[4] = txt_address1.getAttribute("required");
		man[5] = dropdown_country.getAttribute("required");
		man[6] = txt_state.getAttribute("required");
		man[7] = txt_city.getAttribute("required");
		man[8] = txt_zipcode.getAttribute("required");
		man[9] = txt_phone.getAttribute("required");
		
		return man;
	}
	
}
