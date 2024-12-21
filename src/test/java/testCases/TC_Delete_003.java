package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DeleteAccountPage;
import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Delete_003 extends BaseClass{
	
	HomePage homepage;
	DeleteAccountPage deletepage;
	SignupLoginPage signuppage;
	
	@Test()
	public void testNavigatingBackAccountDeletion() {
		homepage = new HomePage(driver);
		deletepage = new DeleteAccountPage(driver);
		signuppage = new SignupLoginPage(driver);
		
		homepage.clickSinupLogin();
		signuppage.signUpName(fakerData().name().name());
		String email = fakerData().internet().emailAddress();
		signuppage.signUpEmail(email);
		
		signuppage.clickSignUp(); 
		
		String password = fakerData().internet().password();
		signuppage.setAccountPassword(password);
		signuppage.setAddressFirstName(fakerData().name().firstName());
		signuppage.setAddressLastName(fakerData().name().lastName());
		signuppage.setAddress1(fakerData().address().streetAddress());
		signuppage.setCountry(prop.getProperty("country"));
		signuppage.setState(fakerData().address().state());
		signuppage.setCity(fakerData().address().cityName());
		signuppage.setzip(fakerData().address().zipCode());
		signuppage.setPhone(fakerData().phoneNumber().cellPhone());
		signuppage.clickCreateAccount();
		
		signuppage.clickContinuetoLogin();
		
		homepage.clickDeleteAccount();
		Assert.assertEquals(deletepage.validateAccountDeletion(), true);
		
		deletepage.clickContinueForAccountdeletion();
		Assert.assertEquals(homepage.validateSignupLoginLink(), true);
		
		homepage.navigateBack();
		homepage.navigateBack();
		
		Assert.assertEquals(homepage.validateSignupLoginLink(), true);
		
	}

}
