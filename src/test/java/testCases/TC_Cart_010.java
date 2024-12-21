package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;
import testBase.BaseClass;

public class TC_Cart_010 extends BaseClass {

	HomePage homepage;
	SearchPage searchpage;
	CartPage cart;

	@Test()
	public void testCartTotalPrice() {
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

		int cartQuantity = Integer.parseInt(cart.displayProductQuantity(productChosen));
		Double itemPrice = cart.getItemPrice(productChosen);
		Double totalPrice = cartQuantity*itemPrice;
		System.out.println("cart quatity: "+cartQuantity);
		System.out.println("Expected total price: "+ totalPrice);
		System.out.println("Actual Total Price: " + cart.getItemTotalPrice(productChosen));
		
		Assert.assertEquals(cart.getItemTotalPrice(productChosen), totalPrice);
	}
}
