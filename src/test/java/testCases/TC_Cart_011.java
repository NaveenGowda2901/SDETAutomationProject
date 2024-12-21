package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;
import testBase.BaseClass;

public class TC_Cart_011 extends BaseClass {

	HomePage homepage;
	SearchPage searchpage;
	CartPage cart;

	@Test()
	public void testProductDeletionFromCart() throws InterruptedException {
		homepage = new HomePage(driver);
		searchpage = new SearchPage(driver);
		cart = new CartPage(driver);

		homepage.clickProducts();
		
		String product = "tops";
		searchpage.SearchProdThroughSearchBtn(product);

		searchpage.clickViewProductOfFirstSearchProduct();
		String productChosen = searchpage.getProductName();
		
		searchpage.AddProductToCartViaProductDetailsPage();
		
		searchpage.clickDisplayPagePopupCartNavigation();
		cart.deleteItemFromCart(productChosen);
		Thread.sleep(3000);
		Assert.assertEquals(cart.verifyItemDelete(productChosen), true);
		
	}
}
