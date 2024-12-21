package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SearchPage;
import testBase.BaseClass;

public class TC_Cart_001 extends BaseClass {

	HomePage homepage;
	SearchPage searchpage;

	@Test()
	public void testAddProductToCartViaProductsPage() {
		homepage = new HomePage(driver);
		searchpage = new SearchPage(driver);

		homepage.clickProducts();

		String searchtxt = "tshirt";
		searchpage.SearchProdThroughSearchBtn(searchtxt);

		searchpage.AddProductToCartViaProductPage();

		Assert.assertEquals(searchpage.validateProductAddedToCart(), true);

	}
}
