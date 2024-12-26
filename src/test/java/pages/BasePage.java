package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	Select sel;
	Actions act;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public JavascriptExecutor javaScriptExecutor() {
		return js = (JavascriptExecutor)driver;
	}
	
	public WebDriverWait waitHelper() {
		return wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public Select select(WebElement element) {
		return sel = new Select(element);
	}
	
	public Actions actions() {
		return act = new Actions(driver);
	}

}
