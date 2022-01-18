package com.ymcareers.webapp.pageobjects.jobseeker.login.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for Linkedin Account. */
public class LinkedIn extends ComponentValidator {

  @FindBy(xpath = "//a[contains(text(), 'LinkedIn')]")
  WebElement linkedInLink;

  @FindBy(name = "session_key")
  WebElement emailLinkedIn;

  @FindBy(name = "session_password")
  WebElement pwdLinkedIn;

  @FindBy(xpath = "//button[text()='Sign in']")
  WebElement signInButton;

  @FindBy(id = "oauth__auth-form__submit-btn")
  WebElement allowAccessButton;

  public LinkedIn(WebDriver driver) {
    super(driver);
  }

  public void selectLinkedIn() {
    linkedInLink.click();
  }

  /**
   * This method is used to login to LinkedIn Account.
   *
   * @param email Email id
   * @param pwd Password
   */
  public void linkedInLogin(String email, String pwd) {
    wait.until(ExpectedConditions.visibilityOf(emailLinkedIn));
    emailLinkedIn.clear();
    emailLinkedIn.sendKeys(email);
    log.info("LinkedIn Userid is entered as " + email);
    pwdLinkedIn.clear();
    pwdLinkedIn.sendKeys(pwd);
    log.info("Password is entered");
    signInButton.click();
    wait.until(ExpectedConditions.visibilityOf(allowAccessButton));
    allowAccessButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(linkedInLink));
    return linkedInLink.isDisplayed();
  }
}
