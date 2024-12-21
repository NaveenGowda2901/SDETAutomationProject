package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Register_018 extends BaseClass {
	
	HomePage homepage;
	SignupLoginPage signuppage;

	@Test
	public void testAccountEmailFieldMatchesSignupName() {
		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);

		homepage.clickSinupLogin();
		String email = fakerData().internet().emailAddress();
		signuppage.signUpName(fakerData().name().firstName());
		signuppage.signUpEmail(email);
		signuppage.clickSignUp(); 
		
		Assert.assertEquals(signuppage.validateEmailFieldAccountInfo(), email);
	}

}
