package testCases;

import java.io.FileOutputStream;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Register_007 extends BaseClass{

	HomePage homepage;
	SignupLoginPage signuppage;

	@Test(priority=1)
	public void testSignUpWithMandatoryFieldsOnly() throws IOException {
		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);

		homepage.clickSinupLogin();
		signuppage.signUpName(fakerData().name().name());
		String email = fakerData().internet().emailAddress();
		signuppage.signUpEmail(email);
		signuppage.clickSignUp(); 

		Assert.assertEquals(signuppage.validateSignUP(), true);

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

		//These statements are to store the registered email and password in properties file for later use
				prop.put("testuser", email);
				prop.put("testpassword", password);

				FileOutputStream fo = new FileOutputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
				
				prop.store(fo, "");
				
		Assert.assertEquals(signuppage.validateAccountCreation(), true);

		signuppage.clickContinuetoLogin();

		Assert.assertEquals(homepage.validateLogoutLinkDisplay(), true);
	}

}
