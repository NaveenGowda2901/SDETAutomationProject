package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SearchPage;
import testBase.BaseClass;

public class TC_Search_001 extends BaseClass{
	
	HomePage homepage;
	SearchPage searchpage;
	
	@Test()
	public void testSearchPageNavigation() {
		
		homepage = new HomePage(driver);
		searchpage = new SearchPage(driver);
		
		homepage.clickProducts();
		
		Assert.assertEquals(searchpage.validateSearchPageNavigation(), true);
		
	}

}
