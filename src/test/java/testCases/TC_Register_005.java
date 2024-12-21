package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Register_005 extends BaseClass{

	HomePage homepage;
	SignupLoginPage signuppage;

	@Test()
	public void testExistingUserSignUp() {
		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);

		homepage.clickSinupLogin();
		signuppage.signUpName(prop.getProperty("validname"));
		signuppage.signUpEmail(prop.getProperty("validemail"));
		signuppage.clickSignUp();
		
		Assert.assertEquals(signuppage.validateSignUpWithExistingUser(), true);
	}
}
