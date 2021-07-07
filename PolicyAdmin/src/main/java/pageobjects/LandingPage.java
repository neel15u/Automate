package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
    WebDriver driver;

    By login=By.xpath("//*[@href='https://community.insurity.com/']");
    By title=By.xpath("//*[@class='et_pb_text_inner']/h3");


    public LandingPage(WebDriver driver){
        this.driver = driver;
    }

    public WebElement getLogIn(){
        return driver.findElement(login);
    }
    public WebElement getTitle(){
        return driver.findElement(title);
    }

}
