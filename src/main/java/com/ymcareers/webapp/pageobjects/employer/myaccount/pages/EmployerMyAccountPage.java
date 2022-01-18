package com.ymcareers.webapp.pageobjects.employer.myaccount.pages;

import com.ymcareers.webapp.pageobjects.common.components.UserAgreementPopUp;
import com.ymcareers.webapp.pageobjects.jobseeker.myaccount.components.Settings;
import com.ymcareers.webapp.pageobjects.jobseeker.myaccount.components.ValidationRequired;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates WebElements of Component classes. */
public class EmployerMyAccountPage {

  private final Settings settings;
  private final UserAgreementPopUp userAgreementPopUp;
  private final ValidationRequired validationRequired;

  /**
   * This Constructor initiates WebElements.
   *
   * @param driver WebDriver Instance
   */
  public EmployerMyAccountPage(WebDriver driver) {
    settings = PageFactory.initElements(driver, Settings.class);
    userAgreementPopUp = PageFactory.initElements(driver, UserAgreementPopUp.class);
    validationRequired = PageFactory.initElements(driver, ValidationRequired.class);
  }

  public Settings getSettings() {
    return settings;
  }

  public UserAgreementPopUp getUserAgreementPopUp() {
    return userAgreementPopUp;
  }

  public ValidationRequired getValidationRequired() {
    return validationRequired;
  }
}
