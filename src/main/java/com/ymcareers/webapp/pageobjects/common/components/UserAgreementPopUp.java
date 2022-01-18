package com.ymcareers.webapp.pageobjects.common.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for User Agreement Pop Up and a Method to handle it. */
public class UserAgreementPopUp extends ComponentValidator {

  @FindBy(id = "tou")
  WebElement usageAgreementCheckbox;

  @FindBy(id = "privacyPolicy")
  WebElement privacyPolicyCheckbox;

  @FindBy(id = "ga_create_alert")
  WebElement continueButton;

  public UserAgreementPopUp(WebDriver driver) {
    super(driver);
  }

  /** This Method Accepts Terms and Conditions of User Agreement. */
  public void handleUserAgreementConsent() {
    try {
      wait.until(ExpectedConditions.visibilityOf(continueButton));
      if (continueButton.isDisplayed()) {
        log.info("Privacy policy and user agreement pop up displayed");
        usageAgreementCheckbox.click();
        privacyPolicyCheckbox.click();
        continueButton.click();
        wait.until(ExpectedConditions.invisibilityOf(continueButton));
      } else {
        log.info("Privacy policy and user agreement pop up not displayed");
      }
    } catch (Exception e) {
      log.info("Privacy policy and user agreement pop up not displayed");
    }
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(continueButton));
    return continueButton.isDisplayed();
  }
}
