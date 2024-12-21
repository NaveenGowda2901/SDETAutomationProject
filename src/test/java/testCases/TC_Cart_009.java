package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;
import testBase.BaseClass;

public class TC_Cart_009 extends BaseClass {

	HomePage homepage;
	SearchPage searchpage;
	CartPage cart;

	@Test()
	public void testProductQuantityReflectsInCart() {
		homepage = new HomePage(driver);
		searchpage = new SearchPage(driver);
		cart = new CartPage(driver);

		homepage.clickProducts();
		
		String product = "tops";
		searchpage.SearchProdThroughSearchBtn(product);

		searchpage.clickViewProductOfFirstSearchProduct();
		String productChosen = searchpage.getProductName();
		
		String quantity="5";
		searchpage.setProductQuantity(quantity);
		searchpage.AddProductToCartViaProductDetailsPage();
		
		searchpage.clickDisplayPagePopupCartNavigation();

		Assert.assertEquals(cart.displayProductQuantity(productChosen), quantity);
		
	}
}
