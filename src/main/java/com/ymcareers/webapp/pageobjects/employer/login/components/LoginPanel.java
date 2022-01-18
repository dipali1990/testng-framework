package com.ymcareers.webapp.pageobjects.employer.login.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for Employer's Login Details. */
public class LoginPanel extends ComponentValidator {

  @FindBy(id = "j_username")
  WebElement username;

  @FindBy(id = "j_password")
  WebElement password;

  @FindBy(xpath = "//span[contains(text(),'Log In')]")
  WebElement logInButton;

  public LoginPanel(WebDriver driver) {
    super(driver);
  }

  /**
   * This method is used to do Traditional Employer Login.
   *
   * @param email username
   * @param pwd password
   */
  public void signIn(String email, String pwd) {
    username.sendKeys(email);
    log.info("Userid is entered as " + email);
    password.sendKeys(pwd);
    log.info("Password is entered");
    logInButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(username));
    return username.isDisplayed();
  }
}
