package com.ymcareers.webapp.pageobjects.employer.login.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This class has WebElements for Non-Member login button which is available on Employer dual login
 * * Page.
 */
public class NonMemberLogin extends ComponentValidator {

  @FindBy(name = "nonmember")
  WebElement nonMemberLoginButton;

  public NonMemberLogin(WebDriver driver) {
    super(driver);
  }

  /** Method to select Member Login Option. */
  public void selectNonMemberLogin() {
    nonMemberLoginButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(nonMemberLoginButton));
    return nonMemberLoginButton.isDisplayed();
  }
}
