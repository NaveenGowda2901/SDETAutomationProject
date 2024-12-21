package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Register_006 extends BaseClass{
	
	HomePage homepage;
	SignupLoginPage signuppage;

	@Test(priority=1)
	public void testSignUpWithValidInfo() {
		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);
		
		homepage.clickSinupLogin();
		signuppage.signUpName(prop.getProperty("validname"));
		signuppage.signUpEmail(fakerData().internet().emailAddress());
		signuppage.clickSignUp(); 
		
		Assert.assertEquals(signuppage.validateSignUP(), true);
		
		signuppage.selectGender(prop.getProperty("gender"));
		signuppage.setAccountPassword(fakerData().internet().password());
		signuppage.selectDate(prop.getProperty("date"));
		signuppage.selectMonth(prop.getProperty("month"));
		signuppage.selectYear(prop.getProperty("year"));
		signuppage.clickNewsLetter();
		signuppage.clickSpecialOffers();
		signuppage.setAddressFirstName(fakerData().name().firstName());
		signuppage.setAddressLastName(fakerData().name().lastName());
		signuppage.setCompany(fakerData().company().name());
		signuppage.setAddress1(fakerData().address().streetAddress());
		signuppage.setAddress2(fakerData().address().streetAddress());
		signuppage.setCountry(prop.getProperty("country"));
		signuppage.setState(fakerData().address().state());
		signuppage.setCity(fakerData().address().cityName());
		signuppage.setzip(fakerData().address().zipCode());
		signuppage.setPhone(fakerData().number().digits(10));
		
		signuppage.clickCreateAccount();
		
		Assert.assertEquals(signuppage.validateAccountCreation(), true);
		
		signuppage.clickContinuetoLogin();
		
		Assert.assertEquals(homepage.validateLogoutLinkDisplay(), true);
	}
	
}
