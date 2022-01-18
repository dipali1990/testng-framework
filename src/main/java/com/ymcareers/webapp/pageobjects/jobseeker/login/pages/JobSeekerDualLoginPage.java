package com.ymcareers.webapp.pageobjects.jobseeker.login.pages;

import com.ymcareers.webapp.pageobjects.jobseeker.login.components.MemberLogin;
import com.ymcareers.webapp.pageobjects.jobseeker.login.components.NonMemberLogin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates all WebElements on the Dual Login Page. */
public class JobSeekerDualLoginPage {
  private final MemberLogin memberLogin;
  private final NonMemberLogin nonMemberLogin;

  /**
   * Constructor to initiate WebElements for the component classes.
   *
   * @param driver WebDriver Instance
   */
  public JobSeekerDualLoginPage(WebDriver driver) {
    memberLogin = PageFactory.initElements(driver, MemberLogin.class);
    nonMemberLogin = PageFactory.initElements(driver, NonMemberLogin.class);
  }

  public MemberLogin getMemberLogin() {
    return memberLogin;
  }

  public NonMemberLogin getNonMemberLogin() {
    return nonMemberLogin;
  }
}
