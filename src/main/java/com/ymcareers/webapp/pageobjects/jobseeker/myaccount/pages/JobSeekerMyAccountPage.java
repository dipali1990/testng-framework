package com.ymcareers.webapp.pageobjects.jobseeker.myaccount.pages;

import com.ymcareers.webapp.pageobjects.common.components.UserAgreementPopUp;
import com.ymcareers.webapp.pageobjects.jobseeker.myaccount.components.ValidationRequired;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates all WebElements available on the My Account Page. */
public class JobSeekerMyAccountPage {
  private final UserAgreementPopUp userAgreementPopUp;
  private final ValidationRequired validationRequired;

  public JobSeekerMyAccountPage(WebDriver driver) {
    userAgreementPopUp = PageFactory.initElements(driver, UserAgreementPopUp.class);
    validationRequired = PageFactory.initElements(driver, ValidationRequired.class);
  }

  public UserAgreementPopUp getUserAgreementPopUp() {
    return userAgreementPopUp;
  }

  public ValidationRequired getValidationRequired() {
    return validationRequired;
  }
}
