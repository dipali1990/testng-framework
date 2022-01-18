package com.ymcareers.webapp.pageobjects.jobseeker.login.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElement for Member Login option available on Dual Login Page. */
public class MemberLogin extends ComponentValidator {

  @FindBy(xpath = "//a[@aria-label='Member Login']")
  WebElement jsMemberloginButton;

  public MemberLogin(WebDriver driver) {
    super(driver);
  }

  public void selectMemberLogin() {
    jsMemberloginButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(jsMemberloginButton));
    return jsMemberloginButton.isDisplayed();
  }
}
