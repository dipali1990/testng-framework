package com.ymcareers.webapp.pageobjects.jobseeker.login.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElement for Non-Member Login button. */
public class NonMemberLogin extends ComponentValidator {

  @FindBy(xpath = "//a[@aria-label='Non-Member Login']")
  WebElement jsNonMemberLoginButton;

  public NonMemberLogin(WebDriver driver) {
    super(driver);
  }

  public void selectNonMemberLogin() {
    jsNonMemberLoginButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(jsNonMemberLoginButton));
    return jsNonMemberLoginButton.isDisplayed();
  }
}
