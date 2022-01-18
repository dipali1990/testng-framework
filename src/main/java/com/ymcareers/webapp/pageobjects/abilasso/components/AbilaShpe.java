package com.ymcareers.webapp.pageobjects.abilasso.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements and Methods to login on SHPE site. */
public class AbilaShpe extends ComponentValidator {

  @FindBy(name = "eWebLoginControl$TextBoxLoginName")
  WebElement username;

  @FindBy(name = "eWebLoginControl$TextBoxPassword")
  WebElement password;

  @FindBy(id = "eWebLoginControl_LoginGoButton")
  WebElement loginButton;

  public AbilaShpe(WebDriver driver) {
    super(driver);
  }

  /**
   * This method is created to login to SHPE site.
   *
   * @param email Username
   * @param pwd Password
   */
  public void shpeLogin(String email, String pwd) {
    username.clear();
    username.sendKeys(email);
    log.info("Userid is entered as " + email);
    password.clear();
    password.sendKeys(pwd);
    log.info("Password is entered");
    loginButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(username));
    return username.isDisplayed();
  }
}
