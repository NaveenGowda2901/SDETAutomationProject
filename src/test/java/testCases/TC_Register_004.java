package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Register_004 extends BaseClass{
	
	HomePage homepage;
	SignupLoginPage signuppage;
	
	@Test()
	public void testSignupInvalidEmailFormat() {
		
		homepage = new HomePage(driver);
		signuppage = new SignupLoginPage(driver);
		
		homepage.clickSinupLogin();
		signuppage.signUpName(fakerData().name().firstName());
		signuppage.signUpEmail(fakerData().name().lastName());
		signuppage.clickSignUp();
		
		Assert.assertEquals(signuppage.validateSignupInvalidEmailFormat(), true);
	}

}
