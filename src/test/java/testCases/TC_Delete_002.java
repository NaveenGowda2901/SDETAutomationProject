package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DeleteAccountPage;
import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Delete_002 extends BaseClass{
	
	HomePage homepage;
	DeleteAccountPage deletepage;
	SignupLoginPage signuppage;
	
	@Test()
	public void testLoginAfterAccountDeletion() {
		//This method is dependent on TC_Delete_001(delete registered user)
		homepage = new HomePage(driver);
		deletepage = new DeleteAccountPage(driver);
		signuppage = new SignupLoginPage(driver);
		
		homepage.clickSinupLogin();
	
		homepage.clickSinupLogin();
		signuppage.siginInEmail(prop.getProperty("testuser"));
		signuppage.siginInPassword(prop.getProperty("testpassword"));
		signuppage.clickSignIn();
		
		Assert.assertEquals(signuppage.validateIncorrectSiginPasswordOrEmail(), true);
	}

}
