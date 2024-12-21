package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Register_002 extends BaseClass{
	
	HomePage homepage;
	SignupLoginPage signuppage;

	@Test()
	public void testSignUpWithEmptyEmailField() {
		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);
		
		homepage.clickSinupLogin();
		signuppage.signUpName(fakerData().name().name());
		signuppage.clickSignUp();
		
		Assert.assertEquals(signuppage.validateMissingSignUpEmailField(), true);
	}
}
