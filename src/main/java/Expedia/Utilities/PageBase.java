package Expedia.Utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PageBase {
    public static ExtentReports reports;
    public static ExtentTest logger;
    public static String imagePath;
    private static final String dateFormat = "yyyyMMdd";
    private static final String reportFormat = ".html";
    private static final String imageFormat = ".png";
    private static final String errorValidationMessage = "Validation Failed...";

    // Initialize Tests
    public void initializeExtent() {
        boolean isInitialized = false;
        if (!isInitialized) {
            // Initialize Extent Report
            reports = new ExtentReports(
                    "Reports\\ExpediaReport" + new SimpleDateFormat(dateFormat).format(new Date()) + reportFormat, true);

            isInitialized = true;

        } else {
            System.out.println("Extent Report is already initialized...");
        }
    }

    public static void getScreenshot(ITestResult testResult, WebDriver driver) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            // Initialize Screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destinationPath = System.getProperty("user.dir") + "\\Reports\\Screenshots\\" + testResult.getName()
                    + new SimpleDateFormat(dateFormat).format(new Date()) + imageFormat;
            File destination = new File(destinationPath);
            FileUtils.copyFile(src, destination);
            imagePath = logger.addScreenCapture(destinationPath);
            logger.log(LogStatus.INFO, "==>", imagePath);
            System.out.println(errorValidationMessage);
        }
    }
}
