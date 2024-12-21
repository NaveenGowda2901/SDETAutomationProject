package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DeleteAccountPage;
import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Delete_001 extends BaseClass{
	
	HomePage homepage;
	DeleteAccountPage deletepage;
	SignupLoginPage signuppage;
	
	@Test()
	public void testAccountDeletion() {
		//This test depends on TC_Register_007(account registration)
		homepage = new HomePage(driver);
		deletepage = new DeleteAccountPage(driver);
		signuppage = new SignupLoginPage(driver);
		
		homepage.clickSinupLogin();
		
		//Signin with registered user account
		signuppage.siginInEmail(prop.getProperty("testuser"));
		signuppage.siginInPassword(prop.getProperty("testpassword"));
		signuppage.clickSignIn();
			
		homepage.clickDeleteAccount();
		Assert.assertEquals(deletepage.validateAccountDeletion(), true);
		
		deletepage.clickContinueForAccountdeletion();
		Assert.assertEquals(homepage.validateSignupLoginLink(), true);
	}

}
