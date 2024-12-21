package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;
import testBase.BaseClass;

public class TC_Cart_008 extends BaseClass {

	HomePage homepage;
	SearchPage searchpage;

	@Test()
	public void testProductDetailsPageContinueShoppingviaPopupLink() {
		homepage = new HomePage(driver);
		searchpage = new SearchPage(driver);

		homepage.clickProducts();

		String searchtxt = "tshirt";
		searchpage.SearchProdThroughSearchBtn(searchtxt);

		searchpage.clickViewProductOfFirstSearchProduct();
		searchpage.AddProductToCartViaProductDetailsPage();

		searchpage.clickDisplayPageContinueShopping();

		Assert.assertEquals(searchpage.validateSearchPageNavigation(), true);

	}
}
