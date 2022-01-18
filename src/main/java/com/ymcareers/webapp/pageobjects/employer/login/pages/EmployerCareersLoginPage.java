package com.ymcareers.webapp.pageobjects.employer.login.pages;

import com.ymcareers.webapp.pageobjects.employer.login.components.CreateAccount;
import com.ymcareers.webapp.pageobjects.employer.login.components.LoginPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates WebElements of Component Class. */
public class EmployerCareersLoginPage {

  private final LoginPanel loginPanel;
  private final CreateAccount createAccount;

  public EmployerCareersLoginPage(WebDriver driver) {
    loginPanel = PageFactory.initElements(driver, LoginPanel.class);
    createAccount = PageFactory.initElements(driver, CreateAccount.class);
  }

  public LoginPanel getLoginPanel() {
    return loginPanel;
  }

  public CreateAccount getCreateAccount() {
    return createAccount;
  }
}
