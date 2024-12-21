package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Logout_002 extends BaseClass {
	HomePage homepage;
	SignupLoginPage signuppage;

	@Test()
	public void testLogoutandLogbackin() {
		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);

		homepage.clickSinupLogin();

		signuppage.siginInEmail(prop.getProperty("validemail"));
		signuppage.siginInPassword(prop.getProperty("validpassword"));
		signuppage.clickSignIn();
		
		homepage.clickLogOut();
		homepage.clickSinupLogin();
		
		signuppage.siginInEmail(prop.getProperty("validemail"));
		signuppage.siginInPassword(prop.getProperty("validpassword"));
		signuppage.clickSignIn();
		
		Assert.assertEquals(homepage.validateLogoutLinkDisplay(), true);
		
	}
}
