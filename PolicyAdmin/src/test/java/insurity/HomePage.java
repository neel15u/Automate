package insurity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

import java.io.IOException;
import java.util.Set;


public class HomePage extends Base {
    public WebDriver driver;// for parallel mode
    public Set<String> windowhandles;

    public static Logger log= LogManager.getLogger(Base.class.getName());

    @BeforeTest
    public void initialize() throws IOException {
        driver=initializeDriver();
        log.info("driver initialized");
    }

    @Test(dataProvider = "getData")
    public void basePageNavigation(String username, String password, String text) {
        driver.get(prop.getProperty("home_url"));
        log.info("navigated to home page");
        LandingPage landingPage= new LandingPage(driver);
        landingPage.getLogIn().click();
        log.info("clicked on log in");
        driver= getWindowControl(prop.getProperty("engage_url"));
        LoginPage loginPage= new LoginPage(driver);
        loginPage.getUsername().sendKeys(username);
        log.info("entered username");
        loginPage.getPassword().sendKeys(password);
        log.info("entered password");
        log.info(text);
        loginPage.getSignIn().click();
        log.info("clicked on sign in");
        driver.close();
        getParentWindow();
    }

    @DataProvider
    public Object[][] getData(){
       Object[][] data=new Object[2][3];
       data[0][0]="usename1";
       data[0][1]="password1";
       data[0][2]="restricted";

       data[1][0]="usename2";
       data[1][1]="password2";
       data[1][2]="not-restricted";
       return  data;
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
        log.info("driver quit");
    }
}
