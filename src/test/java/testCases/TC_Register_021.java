package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Register_021 extends BaseClass{
	
	HomePage homepage;
	SignupLoginPage signuppage;

	@Test()
	public void testMandatoryFieldsMarked() {
		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);

		homepage.clickSinupLogin();
		signuppage.signUpName(fakerData().name().name());
		signuppage.signUpEmail(fakerData().internet().emailAddress());
		signuppage.clickSignUp();
		
		String[] result = signuppage.validateMandatoryFieldsMarked();
		
		boolean flag=false;
		for(String i:result) {
			if(i.equals("true")) 
				flag = true;
		}
		Assert.assertEquals(flag, true);
		
	}
}
