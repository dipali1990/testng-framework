package com.ymcareers.webapp.pageobjects.jobseeker.login.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for Google Sign in. */
public class Google extends ComponentValidator {

  @FindBy(xpath = "//a[contains(text(), ' Google')]")
  WebElement googleLink;

  @FindBy(name = "identifier")
  WebElement emailGoogle;

  @FindBy(xpath = "//span[contains(text(),'Next')]")
  WebElement nextButton;

  @FindBy(name = "password")
  WebElement pwdGoogle;

  public Google(WebDriver driver) {
    super(driver);
  }

  public void selectGoogle() {
    googleLink.click();
  }

  /**
   * This method is used to Sign in to Gmail Account.
   *
   * @param email email id
   * @param pwd Password
   */
  public void googleSignIn(String email, String pwd) {
    wait.until(ExpectedConditions.visibilityOf(emailGoogle));
    emailGoogle.sendKeys(email);
    log.info("Userid is entered as " + email);
    nextButton.click();
    wait.until(ExpectedConditions.visibilityOf(pwdGoogle));
    pwdGoogle.sendKeys(pwd);
    log.info("Password is entered");
    nextButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(googleLink));
    return googleLink.isDisplayed();
  }
}
