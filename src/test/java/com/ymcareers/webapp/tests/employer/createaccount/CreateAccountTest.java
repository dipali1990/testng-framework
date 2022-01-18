package com.ymcareers.webapp.tests.employer.createaccount;

import com.relevantcodes.extentreports.LogStatus;
import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.pageobjects.common.pages.NavigationBar;
import com.ymcareers.webapp.pageobjects.employer.login.pages.EmployerCareersLoginPage;
import com.ymcareers.webapp.pageobjects.employer.login.pages.EmployerDualLoginPage;
import com.ymcareers.webapp.pageobjects.home.pages.HomePage;
import com.ymcareers.webapp.tests.core.Base;
import org.apache.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CreateAccountTest extends Base {

  private HomePage homePage;
  private NavigationBar navigationBar;
  private EmployerDualLoginPage employerDualLoginPage;
  private EmployerCareersLoginPage employerCareersLoginPage;

  @BeforeTest
  public void setUpPages() {
    homePage = new HomePage(DriverManager.getDriver());
    navigationBar = new NavigationBar(DriverManager.getDriver());
    employerDualLoginPage = new EmployerDualLoginPage(DriverManager.getDriver());
    employerCareersLoginPage = new EmployerCareersLoginPage(DriverManager.getDriver());
  }

  @Parameters({"orgType"})
  @Test
  public void createAccount(String orgType) {
    log = LogManager.getLogger("Verify Employer is able to create a New Account");
    logger = extent.startTest("Verify Employer is able to create a New Account");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    try {
      employerDualLoginPage.getNonMemberLogin().selectNonMemberLogin();
      logger.log(LogStatus.PASS, "Site has Dual Login Configuration");
    } catch (Exception e) {
      logger.log(LogStatus.PASS, "Dual Login Configuration is Off");
    }
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(), "Unable to Navigate to Login Page");
    String name = employerCareersLoginPage.getCreateAccount().enterPersonalInformation();
    logger.log(LogStatus.PASS, "Entered Personal Information for '" + name + "'");
    employerCareersLoginPage
        .getCreateAccount()
        .enterCompanyInformation(
            orgType,
            prop.getProperty("addressCreateAccount"),
            prop.getProperty("cityCreateAccount"),
            prop.getProperty("stateCreateAccount"),
            prop.getProperty("zipCodeCreateAccount"),
            prop.getProperty("countryCreateAccount"));
    logger.log(LogStatus.PASS, "Entered Company Information");
    employerCareersLoginPage.getCreateAccount().createAccount(prop.getProperty("password"));
    logger.log(LogStatus.PASS, "Clicked Create Account Button");
    Assert.assertTrue(
        navigationBar.getWelcomeMenu().verifyWelcome(name), "Unable to verify Welcome Text");
    logger.log(LogStatus.PASS, "Employer is able to create a new account successfully.");
  }
}
