package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Register_016 extends BaseClass{

	HomePage homepage;
	SignupLoginPage signuppage;

	@Test
	public void testSignUpAllWithAllFieldsofAccountInfoEmpty() {
		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);

		homepage.clickSinupLogin();
		signuppage.signUpName(fakerData().name().firstName());
		signuppage.signUpEmail(fakerData().internet().emailAddress());
		signuppage.clickSignUp(); 

		Assert.assertEquals(signuppage.validateSignUP(), true);
		
		signuppage.clickCreateAccount();

		Assert.assertEquals(signuppage.validateMissingAccountPasswordField(), true);
	}
}
