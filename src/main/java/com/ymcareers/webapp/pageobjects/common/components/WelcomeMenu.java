package com.ymcareers.webapp.pageobjects.common.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This class has Webelements for Welcome Link which gets displayed on the top right corner after
 * user signs in.
 */
public class WelcomeMenu extends ComponentValidator {

  @FindBy(xpath = "//li[@class='logged-in']")
  WebElement welcomeLink;

  @FindBy(xpath = "//li[@class='logged-in']//child::li[2]")
  WebElement logOut;

  @FindBy(xpath = "//div[@class='jt_purchase_notification']")
  WebElement empLogOutMessage;

  public WelcomeMenu(WebDriver driver) {
    super(driver);
  }

  /** This method will go to the Welcome Link and then click on the logout. */
  public void signOut() {
    welcomeLink.click();
    wait.until(ExpectedConditions.visibilityOf(logOut));
    logOut.click();
  }

  public boolean verifyLogout() {
    wait.until(ExpectedConditions.visibilityOf(empLogOutMessage));
    return empLogOutMessage.isDisplayed();
  }

  public boolean verifyWelcome(String name){
    boolean flag = false;
    try{
      wait.until(ExpectedConditions.visibilityOf(welcomeLink));
      welcomeLink.isDisplayed();
      String n = welcomeLink.getText();
      if(welcomeLink.getText().contains(name)){
        flag=true;
        log.info("Text Displayed as " +  welcomeLink.getText());
      }
    }catch (Exception e){
      log.info("Unable to verify");
    }
    return flag;
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(welcomeLink));
    return welcomeLink.isDisplayed();
  }
}
