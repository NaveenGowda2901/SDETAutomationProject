package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener, IRetryAnalyzer{
	
	int retryCount=0;
	int maxRetryCount=3;

	public boolean retry(ITestResult result) {
		//This method comes from IRetryAnalyzer Interface to retry executing the failed cases maximum up to 3 times
	
		if(retryCount<maxRetryCount) {
			retryCount++;
			System.out.println("Retrying the Test "+result.getName()+" for the "+retryCount+ " time");
			return true;
		}
		return false;
	}
	
	public ExtentSparkReporter sparkReporter;//UI of the report
	public ExtentReports extent;//populate common info on the report
	public ExtentTest test;//creating test case entries in the report and update status of the test report

	String repName;

	public void onStart(ITestContext testContext){

		/*	SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentDateTimeStamp = df.format(dt);
		 */

		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new Date()); //TimeStamp	
		repName="Test-Report-"+timestamp+".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);  //speciy location o the report

		sparkReporter.config().setDocumentTitle("Automation Exercise Project"); //Title of the Report
		sparkReporter.config().setReportName("Automation Exercise Functional Testing"); //Name of the Report
		sparkReporter.config().setTheme(Theme.DARK); //Theme of the report


		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Application", "Automation Exercise");
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("Tester Name", System.getProperty("user.name"));

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()){
			extent.setSystemInfo("Groups", includedGroups.toString());
		}		

	}

	public void onTestSuccess(ITestResult result){
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		// test.createNode(result.getName());
		test.log(Status.PASS, "Test case PASSED is: "+ result.getName());

	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL,"Test case FAILED is: "+result.getName());
		test.log(Status.INFO, "Test case FAILED cause is: "+result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		//	 test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case SKIPPED is: "+ result.getName());
		test.log(Status.SKIP, "Test case SKIPPED  cause is: "+ result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		extent.flush();

		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		/*	   try {


			URL url = new URI("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName).toURL();

			   //Create the email message

			   ImageHtmlEmail email = new ImageHtmlEmail();
			   email.setDataSourceResolver(new DataSourceUrlResolver(url));
			   email.setHostName("smtp.googleemail.com");
			   email.setSmtpPort(465);
			   email.setAuthenticator(new DefaultAuthenticator("safarowork@gmail.com", "newversionofMe"));
			   email.setSSLOnConnect(true);
			   email.setFrom("safarowork@gmail.com");  //sender
			   email.setSubject("Test Results");
			   email.setMsg("Please find attached report of Orange HRM Project");
			   email.addTo("sanafrq786@gmail.com");  //receiver
			   email.attach(url, "extent report", "please check report....");
			   email.send();	   
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		 */
		
	}
	
}
