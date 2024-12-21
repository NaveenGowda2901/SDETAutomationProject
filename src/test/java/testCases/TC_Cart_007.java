package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;
import testBase.BaseClass;

public class TC_Cart_007 extends BaseClass {

	HomePage homepage;
	SearchPage searchpage;

	@Test()
	public void testProductsPageContinueShoppingviaPopupLink() {
		homepage = new HomePage(driver);
		searchpage = new SearchPage(driver);

		homepage.clickProducts();

		String searchtxt = "tshirt";
		searchpage.SearchProdThroughSearchBtn(searchtxt);

		searchpage.AddProductToCartViaProductPage();

		searchpage.clickDisplayPageContinueShopping();
		
		Assert.assertEquals(searchpage.validateSearchPageNavigation(), true);

	}
}
