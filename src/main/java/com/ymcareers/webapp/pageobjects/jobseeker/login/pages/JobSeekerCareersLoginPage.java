package com.ymcareers.webapp.pageobjects.jobseeker.login.pages;

import com.ymcareers.webapp.pageobjects.jobseeker.login.components.CreateAccount;
import com.ymcareers.webapp.pageobjects.jobseeker.login.components.Google;
import com.ymcareers.webapp.pageobjects.jobseeker.login.components.LinkedIn;
import com.ymcareers.webapp.pageobjects.jobseeker.login.components.LoginPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates WebElements available on the Job Seeker login Page. */
public class JobSeekerCareersLoginPage {
  private final LoginPanel loginPanel;
  private final Google google;
  private final LinkedIn linkedIn;
  private final CreateAccount createAccount;

  /**
   * Constructor to initiate WebElements for the Component classes.
   *
   * @param driver WebDriver Instance
   */
  public JobSeekerCareersLoginPage(WebDriver driver) {
    loginPanel = PageFactory.initElements(driver, LoginPanel.class);
    google = PageFactory.initElements(driver, Google.class);
    linkedIn = PageFactory.initElements(driver, LinkedIn.class);
    createAccount = PageFactory.initElements(driver,CreateAccount.class);
  }

  public LoginPanel getLoginPanel() {
    return loginPanel;
  }

  public Google getGoogle() {
    return google;
  }

  public LinkedIn getLinkedIn() {
    return linkedIn;
  }

  public CreateAccount getCreateAccount() {
    return createAccount;
  }
}
