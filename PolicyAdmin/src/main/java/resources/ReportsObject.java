package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportsObject {

    public static ExtentReports extentReports;

    public static ExtentReports config() {
        String path = System.getProperty("user.dir") +"\\reports\\index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setDocumentTitle("Test Results");
        extentSparkReporter.config().setReportName("Automation results");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Tester", "Neela");
        return extentReports;
    }
}
