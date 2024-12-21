package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
	}

	//Product table
	@FindBy(xpath="//table[@id='cart_info_table']//tbody//tr//td[2]//a") List<WebElement> display_productName;
	@FindBy(xpath="//table[@id='cart_info_table']//tbody//tr//td[4]//button") List<WebElement> display_productQuantity;
	@FindBy(xpath="//table[@id='cart_info_table']//tbody//tr//td[5]//p") List<WebElement> display_productTotalPrice;
	@FindBy(xpath="//table[@id='cart_info_table']//tbody//tr//td[3]//p") List<WebElement> display_productPrice;
	@FindBy(xpath="//table[@id='cart_info_table']//tbody//tr//td[6]//a") List<WebElement> btn_deleteProduct;

	@FindBy(xpath="//a[normalize-space()='Proceed To Checkout']") WebElement btn_proceedToCheckout;
	@FindBy(xpath="//b[normalize-space()='Cart is empty!']") WebElement display_emptyCart;
	@FindBy(xpath="//u[normalize-space()='here']") WebElement link_buyProducts;
	@FindBy(xpath="//b[normalize-space()='Cart is empty!']") WebElement display_allItemsDeleteFromCart;
	
	public boolean validateCartPage() {
		if(driver.getCurrentUrl().contains("cart"))
			return true;
		else
			return false;
	}

	public String displayCartProductName() {
		String cartname=null;
		for(int i=1;i<=display_productName.size();i++) {
			cartname = driver.findElement(By.xpath("//table[@id='cart_info_table']//tbody//tr["+i+"]//td[2]//a")).getText();
		}
		return cartname;
	}

	//cart
	public String displayProductQuantity(String prodname) {
		String cartquantity=null;
		for(int i=1;i<=display_productName.size();i++) {
			String pname = driver.findElement(By.xpath("//table[@id='cart_info_table']//tbody//tr["+i+"]//td[2]//a")).getText();
			if(pname.equals(prodname)) {
				cartquantity = driver.findElement(By.xpath("//table[@id='cart_info_table']//tbody//tr["+i+"]//td[4]//button")).getText();
			}
		}
		return cartquantity;
	}

	public double getItemPrice(String prodName) {
		String itemPrice=null;
		for(int i=1;i<=display_productName.size();i++) {
			String pname = driver.findElement(By.xpath("//table[@id='cart_info_table']//tbody//tr["+i+"]//td[2]//a")).getText();
			if(pname.equals(prodName)) {
				itemPrice = driver.findElement(By.xpath("//table[@id='cart_info_table']//tbody//tr["+i+"]//td[3]//p")).getText();
			}
		}
		String[] price = itemPrice.split(" ");
		return Double.parseDouble(price[1]);
	}

	public double getItemTotalPrice(String prodName) {
		String totalItemPrice=null;
		for(int i=1;i<=display_productName.size();i++) {
			String pname = driver.findElement(By.xpath("//table[@id='cart_info_table']//tbody//tr["+i+"]//td[2]//a")).getText();
			if(pname.equals(prodName)) {
				totalItemPrice = driver.findElement(By.xpath("//table[@id='cart_info_table']//tbody//tr["+i+"]//td[5]//p")).getText();
			}
		}
		String[] price =totalItemPrice.split(" ");
		return Double.parseDouble(price[1]);
	}

	public void deleteItemFromCart(String prodName) throws InterruptedException {
		for(int i=1;i<=display_productName.size();i++) {
			String pname = driver.findElement(By.xpath("//table[@id='cart_info_table']//tbody//tr["+i+"]//td[2]//a")).getText();
			if(pname.equals(prodName)) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//table[@id='cart_info_table']//tbody//tr["+i+"]//td[6]//a")).click();
			}
		}
	}

	public boolean verifyItemDelete(String prodName) {
		boolean status = true;
		for(int i=1;i<=display_productName.size();i++) {
			String pname = driver.findElement(By.xpath("//table[@id='cart_info_table']//tbody//tr["+i+"]//td[2]//a")).getText();
			if(pname.equals(prodName)) {
				status = false;
			}
		}
		return status;
	}

	public void deleteAllCartItems() {
		for(WebElement delete:btn_deleteProduct) {
			delete.click();
		}
	}
	
	public boolean validateAllItemsDeleted() {
		return display_allItemsDeleteFromCart.isDisplayed();
	}

}
