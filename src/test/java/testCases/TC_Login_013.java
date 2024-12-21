package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Login_013 extends BaseClass{
	
	HomePage homepage;
	SignupLoginPage signuppage;

	@Test()
	public void testPasswordVisibilityInPageSource() {
		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);

		homepage.clickSinupLogin();
		
		String password = fakerData().internet().password();
		signuppage.siginInPassword(password);
		
	//	boolean validation = signuppage.validateSigninPasswordInPageSource().contains(password);
		
		Assert.assertEquals(signuppage.validateSigninPasswordInPageSource(password), false);
	}
}
