package insurity;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.Base;
import resources.ReportsObject;

import java.io.IOException;

public class Listeners extends Base implements ITestListener {

    ExtentTest extentTest;
    ExtentReports extentReports = ReportsObject.config();
    ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {

        extentTest = extentReports.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS,"Test passed");
    }

    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());
        String testCaseName=result.getMethod().getMethodName();
        WebDriver driver=null;

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            test.get().addScreenCaptureFromPath(getSreenshot(testCaseName,driver),result.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //getSreenshot(testCaseName,driver);

    }

    public void onTestSkipped(ITestResult result) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {
    extentReports.flush();
    }
}



