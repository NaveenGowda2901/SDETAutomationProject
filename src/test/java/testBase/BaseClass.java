package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public Properties prop;
	public Logger logger;
	public Faker faker;

	@Parameters({"browser", "os"})
	@BeforeClass(alwaysRun = true)
	public void setUp(String browser, String os) throws IOException, URISyntaxException {

		logger = LogManager.getLogger(this.getClass());

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop= new Properties();
		prop.load(fis);


		if(prop.getProperty("execution_env").equals("local")) {
			switch(browser.toLowerCase()) {
			case "chrome" : WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(); break;
			case "edge" : 	WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(); break;
			case "firefox": WebDriverManager.firefoxdriver().setup(); 
			driver = new FirefoxDriver(); break;
			default: System.out.println("Invalid browser"); return;
			}

		}

		else if(prop.getProperty("execution_env").equals("remote")) {
			//			DesiredCapabilities decap = new DesiredCapabilities();
			//
			//			switch(browser.toLowerCase()) {
			//			case "chrome" : decap.setBrowserName("chrome"); break;
			//			case "edge"   : decap.setBrowserName("MicrosoftEdge"); break;
			//			case "firefox": decap.setBrowserName("firefox"); break;
			//			default: System.out.println("Invalid browser"); return;
			//			}
			//
			//			switch(os.toLowerCase()) {
			//			case "windows" : decap.setPlatform(Platform.WIN10); break;
			//			case "mac" : decap.setPlatform(Platform.MAC); break;
			//			case "linux" : decap.setPlatform(Platform.LINUX); break;
			//			default: System.out.println("Invalid browser"); return;
			//			}

			MutableCapabilities options;

			switch (browser.toLowerCase()) {
			case "chrome": options = new ChromeOptions(); break;
			case "edge": options = new EdgeOptions(); break;
			case "firefox": options = new FirefoxOptions(); break;
			case "safari": options = new SafariOptions(); break;
			default:System.out.println("Invalid browser");return;
			}

			switch (os.toLowerCase()) {
			case "windows": options.setCapability("platformName", "windows"); break;
			case "mac": options.setCapability("platformName", "mac"); break;
			case "linux": options.setCapability("platformName", "linux"); break;
			default: System.out.println("Invalid OS"); return;
			}

			URL url = new URI("http://localhost:4444/wd/hub").toURL();
			driver = new RemoteWebDriver(url, options);	
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(prop.getProperty("url"));
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	public String captureScreen(String tname) throws IOException  {

		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new Date()); //TimeStamp	

		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ tname +"_"+ timestamp+".png";

		File targetFile = new File(targetFilePath);
		FileHandler.copy(sourceFile, targetFile);
		return targetFilePath;
	}

	public Faker fakerData() {
		return faker = new Faker();
	}

}
