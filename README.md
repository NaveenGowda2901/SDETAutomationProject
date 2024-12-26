# Selenium Web Automation Project(with Java)

### Author: Sana Anwar Farooqui
   
![WebAutomationFlow](https://github.com/user-attachments/assets/6438745c-9018-4c54-988e-3d4c574ac91a)

### Framework important components
- Framework Type: Selenium-TestNG Hybrid framework
 - Tools and techniques used: Java, Maven, Selenium, TestNG, Page Object Model
 - To manage Data - Excel/Properties files - Apache POI
 - Log42j for logging with logging level info, debug and Warn
 - Reports: TestNG reports and Extent reports 
 - Screenshot upon test failure using ITestListener interface
 - Retry executing failed test cases for three times using 'IRetryAnalyzer' and 'IAnnotationTransformer' interface
 - Base class with Before and After Methods for setUp and tearDown.
 - setUp method to run the test cases localy and remote via Selenium Grid and Docker(docker-compose.yml)
 - Continous Integration to Jenkins and execute tests via Jenkins
 -  Functionalities Automated: Account Registration, Signin, Account Deletion, Search and Cart

### Base Class setup to launch Driver to execute tests locally and remote
  
```bash
public class BaseClass {
  WebDriver driver;
  public Properties prop;

  @Parameters({"browser", "os"}) //parameters supplied from testng.xml file
  @BeforeClass(alwaysRun = true)
  public void setUp(String browser, String os){

	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
	prop= new Properties();
	prop.load(fis);

  	if(prop.getProperty("execution_env").equals("local")) {
		switch(browser.toLowerCase()) {
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" :driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default: System.out.println("Invalid browser"); return;
		}
     	}
 	else if(prop.getProperty("execution_env").equals("remote")) {
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
	driver.get(prop.getProperty("url"));  //URL is supplied form properties file
   }
}

```
### testng.xml file to run tests by appending listeneres and supplying parameters to the testcases

```bash
  <?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Suite">

	<listeners>
		<listener class-name="utilities.ExtentReportManager" />
		<listener class-name="utilities.RetryListener" />
	</listeners>

	<parameter name="browser" value="chrome" />
	<parameter name="os" value="windows" />

	<test name="Test">
		<classes>
			<class name="testCases.TC_Register_007" />
			<class name="testCases.TC_Login_001" />
			<class name="testCases.TC_Delete_001" />
			<class name="testCases.TC_Delete_003" />
		</classes>
	</test> <!-- Test -->
</suite> <!-- Suite -->
```

### Local execution of tests

- Set the execution_env to local in properties file and execute the tests via testng.xml file
  
- Output:

![ConsoleOutput](https://github.com/user-attachments/assets/9823d53b-8e78-4e02-b590-b72fe4712d49)

![ExtentReport](https://github.com/user-attachments/assets/98b8da86-97e0-452d-97da-d1512134b22d)

### Remote Execution using Selenium Grid(Standalone configuration)
- Start Selenium server in standalone mode to run tests remotely

```bash
  java -jar selenium-server-4.27.0.jar standalone
```

- Confirm test execution

![image](https://github.com/user-attachments/assets/98b6699b-9869-4bb3-b536-cc42eacfd2c6)

### Running via CI/CD

- Download the .war file for Jenkins from jenkins website. Complete the Jenkins setup.

- Run the following command to start jenkins:
```bash
  java -jar jenkins.war --enable-future-java
```
- Install Maven
- Add maven-surefire-plugin to pom.xml file

```bash
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-surefire-plugin</artifactId>
	<version>3.5.0</version>
	<configuration>
	<suiteXmlFiles>
	<suiteXmlFile>{suiteXMLFile}</suiteXmlFile>
	</suiteXmlFiles>
	</configuration>
</plugin>
```

- Run the project build from the command line to ensure it works:

```bash
  mvn clean test
```

- Upload the project to git repository
- Open a browser and go to http://localhost:8080

- Setup jenkins locally by installing all the requied plugins.

- Create and execute jenkins job
  
![CICD](https://github.com/user-attachments/assets/6c7ec5fd-6f22-44fc-b220-7ee9ab076b82)





