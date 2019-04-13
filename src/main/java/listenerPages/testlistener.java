package listenerPages;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import baseclass.Chrome_Driver;

public class testlistener implements ITestListener, IRetryAnalyzer {

	// When Test case get failed, this method is called.
	public void onTestFailure(ITestResult result) {
		System.out.println("***** Error " + result.getName() + " test has failed *****");
		try {
			String screenshotPath = getScreenshot("MethodsName", Chrome_Driver.driver);
			String path = "<span>" + "<a href=\"file://" + screenshotPath + "\" height=150 width=200/>" + "</span>";
			Reporter.log("Screenshot is here " + path);
		} catch (Exception e) {
			System.out.println("Exception while takescreenshot " + e.getMessage());
		}
	}

	private String getScreenshot(String screenshotName, WebDriver driver) throws IOException {
		DateFormat dateformate = new SimpleDateFormat("dd-mm-yy-hh-mm-ss");
		Date date = new Date();
		String currentdate = dateformate.format(date);
		String imageName = screenshotName + currentdate;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String location = System.getProperty("user.dir") + "\\target\\screenshot\\" + imageName + ".png";
		File screenshotLocation = new File(location);
		FileUtils.copyFile(source, screenshotLocation);
		return location;
	}

	public void onFinish(ITestContext arg0) {
		Reporter.log("Completed executing test " + arg0.getName(), true);
	}

	// This belongs to ITestListener and will execute only when the test is pass

	public void onTestSuccess(ITestResult arg0) {
		printTestResults(arg0);
	}

	public void onTestStart(ITestResult arg0) {
		System.out.println("The execution of the main test starts now");
	}

	// This belongs to ITestListener and will execute only if any of the main
	// test(@Test) get skipped
	public void onTestSkipped(ITestResult arg0) {
		printTestResults(arg0);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	// This is the method which will be executed in case of test pass or fail

	// This will provide the information on the test

	private void printTestResults(ITestResult result) {
		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);
		if (result.getParameters().length != 0) {
			String params = null;
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ",";
			}
			Reporter.log("Test Method had the following parameters : " + params, true);
		}
		String status = null;
		switch (result.getStatus()) {

		case ITestResult.SUCCESS:
			status = "Pass";
			break;

		case ITestResult.FAILURE:
			status = "Failed";
			break;
		case ITestResult.SKIP:
			status = "Skipped";
		}
		Reporter.log("Test Status: " + status, true);
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
	}

	private int retryCount = 0;
	private int maxRetryCount = 2;

	@Override
	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) { // Check if test not succeed
			if (retryCount < maxRetryCount) { // Check if maxtry count is reached
				retryCount++; // Increase the maxTry count by 1
				result.setStatus(ITestResult.FAILURE); // Mark test as failed
				return true; // Tells TestNG to re-run the test
			} else {
				result.setStatus(ITestResult.FAILURE); // If maxCount reached,test marked as failed
			}
		} else {
			result.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
		}
		return false;
	}

	public String getResultStatusName(int status) {
		String resultName = null;
		if (status == 1)
			resultName = "SUCCESS";
		if (status == 2)
			resultName = "FAILURE";
		if (status == 3)
			resultName = "SKIP";
		return resultName;
	}

}