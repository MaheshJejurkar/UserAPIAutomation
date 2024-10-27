package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
		
		public ExtentSparkReporter sparkReporter;
		public ExtentReports reports;
		public ExtentTest test;
		
		String reportName;
			
	
	    @Override
	    public void onStart(ITestContext context) {
	    	//System.out.println("Test suite started: " + context.getName());
	    	String timeStamp = new SimpleDateFormat("dd.MM.yyyy-hh.mm.ss-a").format(new Date());
	    	reportName = "ExtentTestReport-"+timeStamp+".html";
	    	
	    	sparkReporter = new ExtentSparkReporter(".\\ExtentReport\\"+reportName);
	    	
	    	sparkReporter.config().setDocumentTitle("API Automation Report");
	    	sparkReporter.config().setReportName("User API");
	    	sparkReporter.config().setTheme(Theme.DARK);
	    	
	    	reports = new ExtentReports();
	    	reports.attachReporter(sparkReporter);
	    	reports.setSystemInfo("Application", "User API");
	    	reports.setSystemInfo("Operating System", System.getProperty("os.name"));
	    	reports.setSystemInfo("User Name", System.getProperty("user.name"));
	    	reports.setSystemInfo("Environment", "QA");
	    	reports.setSystemInfo("User", "Mahesh");
	    }
	
	    @Override
	    public void onFinish(ITestContext context) {
	        //System.out.println("Test suite finished: " + context.getName());
	    	reports.flush();
	    }	
	
	
		@Override
	    public void onTestStart(ITestResult result) {
	        //System.out.println("Test started: " + result.getName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        //System.out.println("Test succeeded: " + result.getName());
	        test = reports.createTest(result.getName());
	        test.assignCategory(result.getMethod().getGroups());
	        test.createNode(result.getName());
	        test.log(Status.PASS, "Test Passed.");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        //System.out.println("Test failed: " + result.getName());
	    	test = reports.createTest(result.getName());
	    	test.assignCategory(result.getMethod().getGroups());
	    	test.createNode(result.getName());
	    	test.log(Status.FAIL, "Test Failed.");
	    	test.log(Status.FAIL, result.getThrowable().getMessage());
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        //System.out.println("Test skipped: " + result.getName());
	    	test = reports.createTest(result.getName());
	    	test.assignCategory(result.getMethod().getGroups());
	    	test.createNode(result.getName());
	    	test.log(Status.SKIP, "Test Skipped.");
	    	test.log(Status.SKIP, result.getThrowable().getMessage());
	    }

}