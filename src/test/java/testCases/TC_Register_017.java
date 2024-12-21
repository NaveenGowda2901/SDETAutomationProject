package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Register_017 extends BaseClass{

	HomePage homepage;
	SignupLoginPage signuppage;

	@Test
	public void testAccountNameFieldMatchesSignupName() {
		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);

		homepage.clickSinupLogin();
		String name = fakerData().name().firstName();
		signuppage.signUpName(name);
		signuppage.signUpEmail(fakerData().internet().emailAddress());
		signuppage.clickSignUp(); 
		
		Assert.assertEquals(signuppage.validateNameFieldAccountInfo(), name);
	}
}
