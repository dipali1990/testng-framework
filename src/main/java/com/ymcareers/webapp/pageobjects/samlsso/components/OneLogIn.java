package com.ymcareers.webapp.pageobjects.samlsso.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for OneLogin. */
public class OneLogIn extends ComponentValidator {

  @FindBy(id = "username")
  WebElement username;

  @FindBy(xpath = "//span[text()='Continue']")
  WebElement continueButton;

  @FindBy(id = "password")
  WebElement password;

  public OneLogIn(WebDriver driver) {
    super(driver);
  }

  /**
   * Method to login to OneLogin SSO.
   *
   * @param email Username
   * @param pwd Password
   */
  public void oneLogin(String email, String pwd) {
    wait.until(ExpectedConditions.visibilityOf(username));
    username.clear();
    username.sendKeys(email);
    log.info("Userid is entered as " + email);
    continueButton.click();
    wait.until(ExpectedConditions.visibilityOf(password));
    password.click();
    password.sendKeys(pwd);
    log.info("Password is entered");
    continueButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(username));
    return username.isDisplayed();
  }
}
