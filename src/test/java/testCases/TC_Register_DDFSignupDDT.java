package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignupLoginPage;
import testBase.BaseClass;

public class TC_Register_DDFSignupDDT extends BaseClass {

	HomePage homepage;
	SignupLoginPage signuppage;

	@Test(dataProvider = "signup", dataProviderClass = utilities.DataSupplier.class)
	public void testSignUpDDT(String status, String title, String password, String day, String month,
			String year, String fname, String lname, String company, String address1, String address2,
			String country, String state, String city, String zip, String mobile) {

		signuppage = new SignupLoginPage(driver);
		homepage = new HomePage(driver);

		homepage.clickSinupLogin();

		if(status.equals("noname")) {
			signuppage.signUpEmail(fakerData().internet().emailAddress());
			signuppage.clickSignUp();
			
			if(signuppage.validateMissingSignUpNameField()==true)
				Assert.assertTrue(true);
			else if(signuppage.validateMissingSignUpNameField()==false)
				Assert.assertTrue(false);
			else if(signuppage.validateSignUP()==true) {
				homepage.clickSinupLogin();
				Assert.fail();
			}
		}

		if(status.equals("noemail")) {
			signuppage.signUpName(fakerData().name().firstName());
			signuppage.clickSignUp();
			
			if(signuppage.validateMissingSignUpEmailField()==true)
				Assert.assertTrue(true);
			else if(signuppage.validateMissingSignUpEmailField()==false)
				Assert.assertTrue(false);
			else if(signuppage.validateSignUP()==true) {
				homepage.clickSinupLogin();
				Assert.fail();
			}
		}

		if(status.equals("nonameandemail")) {
			signuppage.clickSignUp();
			if(signuppage.validateMissingSignUpNameField()==true)
				Assert.assertTrue(true);
			else if(signuppage.validateMissingSignUpNameField()==false)
				Assert.assertTrue(false);
			else if(signuppage.validateSignUP()==true) {
				homepage.clickSinupLogin();
				Assert.fail();
			}
		}

		if(status.equals("validallfields")) {
			signuppage.signUpName(fakerData().name().firstName());
			signuppage.signUpEmail(fakerData().internet().emailAddress());
			signuppage.clickSignUp();
			
			if(signuppage.validateSignUP()==true) {

				signuppage.selectGender(title);
				signuppage.setAccountPassword(password);
				signuppage.selectDate(day);
				signuppage.selectMonth(month);
				signuppage.selectYear(year);
				signuppage.clickNewsLetter();
				signuppage.clickSpecialOffers();
				signuppage.setAddressFirstName(fname);
				signuppage.setAddressLastName(lname);
				signuppage.setCompany(company);
				signuppage.setAddress1(address1);
				signuppage.setAddress2(address2);
				signuppage.setCountry(country);
				signuppage.setState(state);
				signuppage.setCity(city);
				signuppage.setzip(zip);
				signuppage.setPhone(mobile);

				signuppage.clickCreateAccount();

				Assert.assertEquals(signuppage.validateAccountCreation(), true);
				signuppage.clickContinuetoLogin();
				Assert.assertEquals(homepage.validateLogoutLinkDisplay(), true);
				homepage.clickLogOut();
			}
			else
				Assert.fail();			
		}

		if(status.equals("invalidemailformat")) {
			signuppage.signUpName(fakerData().name().firstName());
			signuppage.signUpEmail(fakerData().name().firstName());
			signuppage.clickSignUp();
			if(signuppage.validateSignupInvalidEmailFormat()==true)
				Assert.assertTrue(true);
			else if(signuppage.validateSignupInvalidEmailFormat()==false)
				Assert.assertTrue(false);
			else if(signuppage.validateSignUP()==true) {
				homepage.clickSinupLogin();
				Assert.fail();
			}
		}

		if(status.equals("existinguser")) {
			signuppage.signUpName(prop.getProperty("validname"));
			signuppage.signUpEmail(prop.getProperty("validemail"));
			signuppage.clickSignUp();
			if(signuppage.validateSignUpWithExistingUser()==true)
				Assert.assertTrue(true);
			else if(signuppage.validateSignUpWithExistingUser()==false)
				Assert.assertTrue(false);
			else if(signuppage.validateSignUP()==true) {
				homepage.clickSinupLogin();
				Assert.fail();
			}
		}		

		if(status.equals("validmandatoryfields")) {
			signuppage.signUpName(fakerData().name().firstName());
			signuppage.signUpEmail(fakerData().internet().emailAddress());
			signuppage.clickSignUp();
			
			if(signuppage.validateSignUP()==true) {
				signuppage.setAccountPassword(password);
				signuppage.setAddressFirstName(fname);
				signuppage.setAddressLastName(lname);
				signuppage.setAddress1(address1);
				signuppage.setCountry(country);
				signuppage.setState(state);
				signuppage.setCity(city);
				signuppage.setzip(zip);
				signuppage.setPhone(mobile);
				signuppage.clickCreateAccount();

				Assert.assertEquals(signuppage.validateAccountCreation(), true);
				signuppage.clickContinuetoLogin();
				Assert.assertEquals(homepage.validateLogoutLinkDisplay(), true);
				homepage.clickLogOut();
			}
			else {
				homepage.clickSinupLogin();
				Assert.fail();
			}

			if(status.equals("validwithoutpassword")) {
				signuppage.signUpName(fakerData().name().firstName());
				signuppage.signUpEmail(fakerData().internet().emailAddress());
				signuppage.clickSignUp();
				
				if(signuppage.validateSignUP()==true) {
					signuppage.setAddressFirstName(fname);
					signuppage.setAddressLastName(lname);
					signuppage.setAddress1(address1);
					signuppage.setCountry(country);
					signuppage.setState(state);
					signuppage.setCity(city);
					signuppage.setzip(zip);
					signuppage.setPhone(mobile);
					signuppage.clickCreateAccount();

					Assert.assertEquals(signuppage.validateMissingAccountPasswordField(), true);
					homepage.clickSinupLogin();
				}

				else if(signuppage.validateSignUP()==false) {
					homepage.clickSinupLogin();
					Assert.fail();
				}
				else if(signuppage.validateAccountCreation()==true) {
					homepage.clickLogOut();
					Assert.fail();
				}
			}
		}

		if(status.equals("validwithoutfname")) {
			signuppage.signUpName(fakerData().name().firstName());
			signuppage.signUpEmail(fakerData().internet().emailAddress());
			signuppage.clickSignUp();
			
			if(signuppage.validateSignUP()==true) {
				signuppage.setAccountPassword(password);
				signuppage.setAddressLastName(lname);
				signuppage.setAddress1(address1);
				signuppage.setCountry(country);
				signuppage.setState(state);
				signuppage.setCity(city);
				signuppage.setzip(zip);
				signuppage.setPhone(mobile);
				signuppage.clickCreateAccount();

				Assert.assertEquals(signuppage.validateMissingAddressFirstNameField(), true);
				homepage.clickSinupLogin();
			}
			else if(signuppage.validateSignUP()==false) {
				homepage.clickSinupLogin();
				Assert.fail();
			}
			else if(signuppage.validateAccountCreation()==true) {
				homepage.clickLogOut();
				Assert.fail();
			}
		}

		if(status.equals("validwithoutlname")) {
			signuppage.signUpName(fakerData().name().firstName());
			signuppage.signUpEmail(fakerData().internet().emailAddress());
			signuppage.clickSignUp();
			
			if(signuppage.validateSignUP()==true) {
				signuppage.setAccountPassword(password);
				signuppage.setAddressFirstName(fname);
				signuppage.setAddress1(address1);
				signuppage.setCountry(country);
				signuppage.setState(state);
				signuppage.setCity(city);
				signuppage.setzip(zip);
				signuppage.setPhone(mobile);
				signuppage.clickCreateAccount();

				Assert.assertEquals(signuppage.validateMissingLastNameField(), true);
				homepage.clickSinupLogin();
			}
			else if(signuppage.validateSignUP()==false)
				Assert.fail();
			else if(signuppage.validateAccountCreation()==true) {
				homepage.clickLogOut();
				Assert.fail();
			}
		}


		if(status.equals("validwithoutaddress1")) {
			signuppage.signUpName(fakerData().name().firstName());
			signuppage.signUpEmail(fakerData().internet().emailAddress());
			signuppage.clickSignUp();
			
			if(signuppage.validateSignUP()==true) {
				signuppage.setAccountPassword(password);
				signuppage.setAddressFirstName(fname);
				signuppage.setAddressLastName(lname);
				signuppage.setCountry(country);
				signuppage.setState(state);
				signuppage.setCity(city);
				signuppage.setzip(zip);
				signuppage.setPhone(mobile);
				signuppage.clickCreateAccount();

				Assert.assertEquals(signuppage.validateMissingAddressFirstLineField(), true);
				homepage.clickSinupLogin();
			}
			else if(signuppage.validateSignUP()==false) {
				homepage.clickSinupLogin();
				Assert.fail();
			}
			else if(signuppage.validateAccountCreation()==true) {
				homepage.clickLogOut();
				Assert.fail();
			}
		}

		if(status.equals("validwithoutstate")) {
			signuppage.signUpName(fakerData().name().firstName());
			signuppage.signUpEmail(fakerData().internet().emailAddress());
			signuppage.clickSignUp();
			
			if(signuppage.validateSignUP()==true) {
				signuppage.setAccountPassword(password);
				signuppage.setAddressFirstName(fname);
				signuppage.setAddressLastName(lname);
				signuppage.setAddress1(address1);
				signuppage.setCountry(country);
				signuppage.setCity(city);
				signuppage.setzip(zip);
				signuppage.setPhone(mobile);
				signuppage.clickCreateAccount();

				Assert.assertEquals(signuppage.validateMissingAddressStateField(), true);
				homepage.clickSinupLogin();
			}
			else if(signuppage.validateSignUP()==false) {
				homepage.clickSinupLogin();
				Assert.fail();
			}
			else if(signuppage.validateAccountCreation()==true) {
				homepage.clickLogOut();
				Assert.fail();
			}
		}


		if(status.equals("validwithoutcity")) {
			signuppage.signUpName(fakerData().name().firstName());
			signuppage.signUpEmail(fakerData().internet().emailAddress());
			signuppage.clickSignUp();
			
			if(signuppage.validateSignUP()==true) {
				signuppage.setAccountPassword(password);
				signuppage.setAddressFirstName(fname);
				signuppage.setAddressLastName(lname);
				signuppage.setAddress1(address1);
				signuppage.setCountry(country);
				signuppage.setState(state);
				signuppage.setzip(zip);
				signuppage.setPhone(mobile);
				signuppage.clickCreateAccount();

				Assert.assertEquals(signuppage.validateMissingAddressCityField(), true);
				homepage.clickSinupLogin();
			}
			else if(signuppage.validateSignUP()==false) {
				homepage.clickSinupLogin();
				Assert.fail();
			}
			else if(signuppage.validateAccountCreation()==true) {
				homepage.clickLogOut();
				Assert.fail();
			}
		}


		if(status.equals("validwithoutzip")) {
			signuppage.signUpName(fakerData().name().firstName());
			signuppage.signUpEmail(fakerData().internet().emailAddress());
			signuppage.clickSignUp();
			
			if(signuppage.validateSignUP()==true) {
				signuppage.setAccountPassword(password);
				signuppage.setAddressFirstName(fname);
				signuppage.setAddressLastName(lname);
				signuppage.setAddress1(address1);
				signuppage.setCountry(country);
				signuppage.setState(state);
				signuppage.setCity(city);
				signuppage.setPhone(mobile);
				signuppage.clickCreateAccount();

				Assert.assertEquals(signuppage.validateMissingAddressZipCodeField(), true);
				homepage.clickSinupLogin();
			}
			else if(signuppage.validateSignUP()==false) {
				homepage.clickSinupLogin();
				Assert.fail();
			}
			else if(signuppage.validateAccountCreation()==true) {
				homepage.clickLogOut();
				Assert.fail();
			}
		}


		if(status.equals("validempty")) {
			signuppage.signUpName(fakerData().name().firstName());
			signuppage.signUpEmail(fakerData().internet().emailAddress());
			signuppage.clickSignUp();
			
			if(signuppage.validateSignUP()==true) {
				signuppage.clickCreateAccount();

				Assert.assertEquals(signuppage.validateMissingAccountPasswordField(), true);
				homepage.clickSinupLogin();
			}
			else if(signuppage.validateSignUP()==false) {
				homepage.clickSinupLogin();
				Assert.fail();
			}
			else if(signuppage.validateAccountCreation()==true) {
				homepage.clickLogOut();
				Assert.fail();
			}
		}

		if(status.equals("validwithoutphone")) {
			signuppage.signUpName(fakerData().name().firstName());
			signuppage.signUpEmail(fakerData().internet().emailAddress());
			signuppage.clickSignUp();
			
			if(signuppage.validateSignUP()==true) {
				signuppage.setAccountPassword(password);
				signuppage.setAddressFirstName(fname);
				signuppage.setAddressLastName(lname);
				signuppage.setAddress1(address1);
				signuppage.setCountry(country);
				signuppage.setState(state);
				signuppage.setCity(city);
				signuppage.setzip(zip);
				signuppage.clickCreateAccount();

				Assert.assertEquals(signuppage.validateMissingAddressPhoneField(), true);
				homepage.clickSinupLogin();
			}
			else if(signuppage.validateSignUP()==false) {
				homepage.clickSinupLogin();
				Assert.fail();
			}
			else if(signuppage.validateAccountCreation()==true) {
				homepage.clickLogOut();
				Assert.fail();
			}
		}

	}
}
