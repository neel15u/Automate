package insurity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.LandingPage;
import resources.Base;

import java.io.IOException;

public class ValidateCaption extends Base {
    public WebDriver driver;
    public static Logger log= LogManager.getLogger(Base.class.getName());
    @BeforeTest
    public void initialize() throws IOException {
        driver=initializeDriver();
        log.info("driver initialized");
        driver.get(prop.getProperty("home_url"));
        log.info("navigated to home page");
    }

    @Test
    public void validateTitle()  {
        LandingPage landingPage= new LandingPage(driver);
        Assert.assertEquals(landingPage.getTitle().getText(),"/////WHY WE’RE DIFFERENT");
        log.info("title validated");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
        log.info("driver quit");
    }

}
