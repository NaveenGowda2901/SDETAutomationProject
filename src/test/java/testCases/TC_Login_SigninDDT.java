package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Login_SigninDDT extends BaseClass{
	HomePage homepage;
	SignupLoginPage signuppage;

	@Test(dataProvider = "login", dataProviderClass = utilities.DataSupplier.class)
	public void testSigninWithValidCredentials(String email, String password, String status) {
		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);
		
		homepage.clickSinupLogin();
		
		signuppage.siginInEmail(email);
		signuppage.siginInPassword(password);
		signuppage.clickSignIn();
		
		if(status.equals("invalidemail")) {
			if(signuppage.validateIncorrectSiginPasswordOrEmail()==true)
				Assert.assertTrue(true);
			else if(signuppage.validateIncorrectSiginPasswordOrEmail()==false)
				Assert.fail();
			else if(homepage.validateLogoutLinkDisplay()==true) {
				homepage.clickLogOut();
				Assert.fail();
			}
		}
		
		if(status.equals("invalidpassword")) {
			if(signuppage.validateIncorrectSiginPasswordOrEmail()==true)
				Assert.assertTrue(true);
			else if(signuppage.validateIncorrectSiginPasswordOrEmail()==false)
				Assert.fail();
			else if(homepage.validateLogoutLinkDisplay()==true) {
				homepage.clickLogOut();
				Assert.fail();
			}
		}
		
		if(status.equals("invalidemailformat")) {
			if(signuppage.validateSigninInvalidEmailFormat()==true)
				Assert.assertTrue(true);
			else if(signuppage.validateSigninInvalidEmailFormat()==false)
				Assert.fail();
			else if(homepage.validateLogoutLinkDisplay()==true) {
				homepage.clickLogOut();
				Assert.fail();
			}
		}
		
		if(status.equals("valid")) {
			if(homepage.validateLogoutLinkDisplay()==true) {
				Assert.assertTrue(true);
				homepage.clickLogOut();
			}
			else if(homepage.validateLogoutLinkDisplay()==false)
				Assert.fail();
		}
		
		if(status.equals("missingemail")) {
			if(signuppage.validateMissingSigninEmailField()==true)
				Assert.assertTrue(true);
			else if(signuppage.validateMissingSigninEmailField()==false)
				Assert.fail();
			else if(homepage.validateLogoutLinkDisplay()==true) {
				homepage.clickLogOut();
				Assert.fail();
			}
		}
		
		if(status.equals("missingpassword")) {
			if(signuppage.validateMissingSigninPasswordField()==true)
				Assert.assertTrue(true);
			else if(signuppage.validateMissingSigninPasswordField()==false)
				Assert.fail();
			else if(homepage.validateLogoutLinkDisplay()==true) {
				homepage.clickLogOut();
				Assert.fail();
			}
		}
		
		if(status.equals("missingemailandpassword")) {
			if(signuppage.validateMissingSigninEmailField()==true)
				Assert.assertTrue(true);
			else if(signuppage.validateMissingSigninEmailField()==false)
				Assert.fail();
			else if(homepage.validateLogoutLinkDisplay()==true) {
				homepage.clickLogOut();
				Assert.fail();
			}
		}
		
	}
}
