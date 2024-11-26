package ExtentReport;

import Utiles.Base;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

import static ExtentReport.ExtentReporter.extentTest;

public class TestFailureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = null;

        if (driver != null) {
            String screenshotPath = takeScreenshot(result.getMethod().getMethodName(), driver);
            extentTest.get().addScreenCaptureFromPath(screenshotPath);
            extentTest.get().log(Status.FAIL, "Screenshot added with path as "+screenshotPath);


            //  Capture screenshot as Base64 and attach it directly to Extent Report
            String screenshotBase64 = takeScreenshotAsBase64(driver);
            extentTest.get().fail("Test Failed. Screenshot attached:",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
        }
    }





    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.get().log(Status.SKIP, "Test Case SKIPPED: " + result.getMethod().getMethodName());
        extentTest.get().log(Status.SKIP, result.getThrowable()); // Log the reason for the skip, if available

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestStart(ITestResult result) {
        // Empty implementation
    }

    @Override
    public void onStart(ITestContext context) {
        // Empty implementation
    }

    @Override
    public void onFinish(ITestContext context) {
        // Empty implementation
    }

    public String takeScreenshot(String methodName, WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/screenshots/" + methodName + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }

    // Method to capture screenshot as Base64 string
    public String takeScreenshotAsBase64(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}