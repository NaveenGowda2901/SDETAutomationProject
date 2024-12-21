package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Login_002 extends BaseClass{
	
	HomePage homepage;
	SignupLoginPage signuppage;

	@Test()
	public void testSigninWithInvalidEmail() {
		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);
		
		homepage.clickSinupLogin();
		
		signuppage.siginInEmail(fakerData().internet().emailAddress());
		signuppage.siginInPassword(prop.getProperty("validpassword"));
		signuppage.clickSignIn();

		Assert.assertEquals(signuppage.validateIncorrectSiginPasswordOrEmail(), true);
	}
}
