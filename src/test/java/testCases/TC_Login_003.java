package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Login_003 extends BaseClass{
	
	HomePage homepage;
	SignupLoginPage signuppage;

	@Test()
	public void testSigninWithInvalidPassword() {
		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);
		
		homepage.clickSinupLogin();
		
		signuppage.siginInEmail(prop.getProperty("validemail"));
		signuppage.siginInPassword(fakerData().name().name());
		signuppage.clickSignIn();

		Assert.assertEquals(signuppage.validateIncorrectSiginPasswordOrEmail(), true);
	}
}
