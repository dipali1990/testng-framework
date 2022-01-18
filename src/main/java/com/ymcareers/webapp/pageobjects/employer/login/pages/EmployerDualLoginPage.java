package com.ymcareers.webapp.pageobjects.employer.login.pages;

import com.ymcareers.webapp.pageobjects.employer.login.components.MemberLogin;
import com.ymcareers.webapp.pageobjects.employer.login.components.NonMemberLogin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates WebElements of Component classes. */
public class EmployerDualLoginPage {
  private final MemberLogin memberLogin;
  private final NonMemberLogin nonMemberLogin;

  public EmployerDualLoginPage(WebDriver driver) {
    memberLogin = PageFactory.initElements(driver, MemberLogin.class);
    nonMemberLogin = PageFactory.initElements(driver, NonMemberLogin.class);
  }

  public NonMemberLogin getNonMemberLogin() {
    return nonMemberLogin;
  }

  public MemberLogin getMemberLogin() {
    return memberLogin;
  }
}
