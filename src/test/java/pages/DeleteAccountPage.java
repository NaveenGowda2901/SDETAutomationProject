package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteAccountPage extends BasePage{

	public DeleteAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//b[normalize-space()='Account Deleted!']") WebElement displaytxt_delteAccount;
	@FindBy(xpath="//a[normalize-space()='Continue']") WebElement btn_continueDeleteAccount;
	
	public boolean validateAccountDeletion() {
		return displaytxt_delteAccount.isDisplayed();
	}
	
	public void clickContinueForAccountdeletion() {
		btn_continueDeleteAccount.click();
	}
}
