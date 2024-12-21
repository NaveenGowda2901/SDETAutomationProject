package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SearchPage;
import testBase.BaseClass;

public class TC_Search_002 extends BaseClass{
	
	HomePage homepage;
	SearchPage searchpage;
	
	@Test()
	public void testSearchProductviaSearchBtn() {
		
		homepage = new HomePage(driver);
		searchpage = new SearchPage(driver);
		
		homepage.clickProducts();
		
		String searchtxt = "tshirt";
		searchpage.SearchProdThroughSearchBtn(searchtxt);
		Assert.assertEquals(searchpage.validateSearchProduct().contains(searchtxt), true);
	}

}
