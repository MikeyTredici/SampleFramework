package Expedia.Listeners;

import Expedia.Utilities.PageBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listeners extends TestListenerAdapter {
    public static ExtentReports reports;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("on test start");
        PageBase.logger.log(LogStatus.INFO, iTestResult.getMethod().getMethodName() + "test started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("on test success");
        PageBase.logger.log(LogStatus.PASS, iTestResult.getMethod().getMethodName() + "test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("on test failure");
        PageBase.logger.log(LogStatus.FAIL, iTestResult.getMethod().getMethodName() + "test failed");
        PageBase.logger.log(LogStatus.FAIL, iTestResult.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
