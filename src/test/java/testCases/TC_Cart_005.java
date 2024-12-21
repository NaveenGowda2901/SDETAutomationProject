package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;
import testBase.BaseClass;

public class TC_Cart_005 extends BaseClass {

	HomePage homepage;
	SearchPage searchpage;
	CartPage cartpage;

	@Test()
	public void testProductsPageNavigateToCartviaPopupLink() {
		homepage = new HomePage(driver);
		searchpage = new SearchPage(driver);
		cartpage = new CartPage(driver);

		homepage.clickProducts();

		String searchtxt = "tshirt";
		searchpage.SearchProdThroughSearchBtn(searchtxt);

		searchpage.AddProductToCartViaProductPage();

		searchpage.clickDisplayPagePopupCartNavigation();
		
		Assert.assertEquals(cartpage.validateCartPage(), true);

	}
}
