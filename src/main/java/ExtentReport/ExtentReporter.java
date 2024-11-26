package ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.List;

public class ExtentReporter implements ITestListener {

    private static ExtentReports extent;

    static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        // Initialize Extent Reports and specify the report file path
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
        sparkReporter.config().setDocumentTitle("Automation Extent Test Report");
        sparkReporter.config().setReportName("Test Results");
        sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Tester");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test entry in the report for every test case
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log success and end the test in the report
        logTestNgReporterLogs(result);
        extentTest.get().log(Status.PASS, "Test Case PASSED is " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log failure and attach the error to the report
        logTestNgReporterLogs(result);
        extentTest.get().log(Status.FAIL, "Test Case FAILED is " + result.getMethod().getMethodName());
        extentTest.get().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log skipped tests
        logTestNgReporterLogs(result);
        extentTest.get().log(Status.SKIP, "Test Case SKIPPED is " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Finish and flush the Extent Reports
        extent.flush();
    }

    // Method to capture TestNG's Reporter logs and add them to the Extent Report
    private void logTestNgReporterLogs(ITestResult result) {
        List<String> reporterLogs = Reporter.getOutput(result);  // Fetch all Reporter.log messages
        for (String log : reporterLogs) {
            extentTest.get().log(Status.INFO, log); // Add each log to Extent Report as INFO
        }
    }

}

