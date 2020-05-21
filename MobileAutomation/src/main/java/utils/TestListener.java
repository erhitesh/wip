package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import driverSession.DriverSession;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener {

	private static ExtentReports extentReports = ExtentManager.createInstance();

	public static String getExecutingMethodName(ITestResult result) {
		return result.getMethod().getMethodName();
	}

	public static String getExecutingMethodDescription(ITestResult result) {
		return result.getMethod().getDescription();
	}

	public static String getThrowableMessage(ITestResult result) {
		return result.getThrowable().getMessage();
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {
		DriverSession.getExtent().log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		DriverSession.getExtent().log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		DriverSession.getExtent().log(Status.FAIL, "TC " + result.getName() + " Failed");
		DriverSession.getExtent().log(Status.FAIL,
				"Test Case Failed because of the reason.....> \n" + result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		DriverSession.getExtent().log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.YELLOW));
		DriverSession.getExtent().log(Status.SKIP, "TEST CASE SKIPED IS " + result.getName());
	}

	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extentReports.createTest(getExecutingMethodName(result),
				getExecutingMethodDescription(result));
		DriverSession.setExtent(extentTest);
	}

	public void onStart(ISuite suite) {
		RestUtils.clearScreenshotFromScreenshotFolder();
		RestUtils.clearReportsFromReportsFolder();
		DriverSession.setExtentReport(extentReports);
	}

	public void onFinish(ISuite suite) {
		DriverSession.getExtentReport().flush();
	}
}
