package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
 WebDriver driver;


 By username=By.xpath("//input[@id='lia-login']");
 By password=By.xpath("//input[@id='lia-password']");
 By signin=By.xpath("//input[@id='submitContext']");

 public LoginPage(WebDriver driver){
  this.driver=driver;
 }

  public WebElement getUsername(){
  return driver.findElement(username);
 }

  public WebElement getPassword(){
  return driver.findElement(password);
 }

  public WebElement getSignIn(){

  return driver.findElement(signin);
 }

}

