package com.ymcareers.webapp.pageobjects.jobseeker.login.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for Job Seeker Login Panel. */
public class LoginPanel extends ComponentValidator {

  @FindBy(name = "j_username")
  WebElement username;

  @FindBy(name = "j_password")
  WebElement password;

  @FindBy(id = "login-button")
  WebElement loginButton;

  public LoginPanel(WebDriver driver) {
    super(driver);
  }

  /**
   * This method is used to login as a Job Seeker.
   *
   * @param email Username
   * @param pwd Password
   */
  public void signIn(String email, String pwd) {
    username.sendKeys(email);
    log.info("Userid is entered as " + email);
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
