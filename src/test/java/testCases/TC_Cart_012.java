package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;
import testBase.BaseClass;

public class TC_Cart_012 extends BaseClass {

	HomePage homepage;
	SearchPage searchpage;
	CartPage cart;

	@Test()
	public void testItemDeletionFromMultipleCartItems() throws InterruptedException {
		homepage = new HomePage(driver);
		searchpage = new SearchPage(driver);
		cart = new CartPage(driver);

		homepage.clickProducts();
		
		String product1 = "tops";
		searchpage.SearchProdThroughSearchBtn(product1);

		searchpage.clickViewProductOfFirstSearchProduct();
		String productName1 = searchpage.getProductName();
		searchpage.AddProductToCartViaProductDetailsPage();
		searchpage.clickDisplayPageContinueShopping();
		homepage.clickProducts();
		
		String product2 = "dress";
		searchpage.SearchProdThroughSearchBtn(product2);

		searchpage.clickViewProductOfFirstSearchProduct();
		String productName2 = searchpage.getProductName();
		searchpage.AddProductToCartViaProductDetailsPage();
		searchpage.clickDisplayPagePopupCartNavigation();
		
		cart.deleteItemFromCart(productName1);
		Thread.sleep(3000);
		Assert.assertEquals(cart.verifyItemDelete(productName1), true);
		
	}
}
