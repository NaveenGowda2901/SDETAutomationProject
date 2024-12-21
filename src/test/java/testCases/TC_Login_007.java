package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Login_007 extends BaseClass{
	
	HomePage homepage;
	SignupLoginPage signuppage;

	@Test()
	public void testSigninWithMissingPassword() {
		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);
		
		homepage.clickSinupLogin();
		
		signuppage.siginInEmail(fakerData().internet().emailAddress());
		signuppage.clickSignIn();

		Assert.assertEquals(signuppage.validateMissingSigninPasswordField(), true);
	}
}
