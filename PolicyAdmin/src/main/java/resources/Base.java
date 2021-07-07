package resources;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;


public class Base {
    public WebDriver driver;
    public Set<String> windows;
    public String parent_window;
    public WebDriver child_driver;
    public Properties prop;


    public WebDriver initializeDriver() throws IOException {
        prop=new Properties();
        FileInputStream fis=new FileInputStream("C:\\softwares\\Project\\PolicyAdmin\\src\\main\\java\\resources\\data.properties");
        prop.load(fis);
        String browserName=prop.getProperty("browser");
        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\softwares\\Dependency\\Chrome\\chromedriver.exe");
            driver=new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("IE")){
            System.setProperty("webdriver.ie.driver","C:\\softwares\\Dependency\\IE\\IEDriverServer.exe");
            driver=new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;

    }

    public WebDriver getWindowControl(String page_url){
        parent_window=driver.getWindowHandle();
        windows=driver.getWindowHandles();
        for (String window_handle:windows) {
        System.out.println(window_handle);
        if(!window_handle.equals(parent_window)){
            driver.switchTo().window(window_handle);
            if(driver.getCurrentUrl().equals(page_url)){
                return driver;
            }
        }
        }
        return driver;
}

    public WebDriver getParentWindow(){
        driver.switchTo().window(parent_window);
        return driver;
    }

    public  String getSreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot screenshot=(TakesScreenshot) driver;
        File source=screenshot.getScreenshotAs(OutputType.FILE);
        String destination=System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
        FileUtils.copyFile(source,new File(destination));
        return destination;
        }

}
