package com.ymcareers.webapp.tests.employer.login;

import com.relevantcodes.extentreports.LogStatus;
import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.pageobjects.common.pages.NavigationBar;
import com.ymcareers.webapp.pageobjects.employer.login.pages.EmployerCareersLoginPage;
import com.ymcareers.webapp.pageobjects.employer.login.pages.EmployerDualLoginPage;
import com.ymcareers.webapp.pageobjects.employer.myaccount.pages.EmployerMyAccountPage;
import com.ymcareers.webapp.pageobjects.home.pages.HomePage;
import com.ymcareers.webapp.tests.core.Base;
import org.apache.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StandardLoginTest extends Base {

  private HomePage homePage;
  private NavigationBar navigationBar;
  private EmployerDualLoginPage employerDualLoginPage;
  private EmployerCareersLoginPage employerCareersLoginPage;
  private EmployerMyAccountPage employerMyAccountPage;

  @BeforeTest
  public void setUpPages() {
    homePage = new HomePage(DriverManager.getDriver());
    navigationBar = new NavigationBar(DriverManager.getDriver());
    employerDualLoginPage = new EmployerDualLoginPage(DriverManager.getDriver());
    employerCareersLoginPage = new EmployerCareersLoginPage(DriverManager.getDriver());
    employerMyAccountPage = new EmployerMyAccountPage(DriverManager.getDriver());
  }

  @Test
  public void standardLoginTest() {
    log = LogManager.getLogger("Verify Employer is able to login via Traditional Login");
    logger = extent.startTest("Verify Employer is able to login via Traditional Login");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(),
        "Unable to navigate to Employer Login Page");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(navigationBar.getWelcomeMenu().isDisplayed(), "Unable to Sign-in");
    log.info("Employer has successfully logged in using traditional login");
    navigationBar.getWelcomeMenu().signOut();
    Assert.assertTrue(navigationBar.getWelcomeMenu().verifyLogout());
    log.info("Employer has logged out successfully");
    logger.log(
        LogStatus.PASS,
        "Employer has successfully logged in and logged out using traditional login");
  }

  @Test
  public void dualSignInNonMemberLoginTest() {
    log = LogManager.getLogger("Verify Employer is able to login via Traditional Login");
    logger = extent.startTest("Verify Employer is able to login via Traditional Login");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    Assert.assertTrue(
        employerDualLoginPage.getMemberLogin().isDisplayed(),
        "Unable to locate Member Login Button");
    employerDualLoginPage.getNonMemberLogin().selectNonMemberLogin();
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(),
        "Unable to navigate to Employer Login Page");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(navigationBar.getWelcomeMenu().isDisplayed(), "Unable to Sign-in");
    log.info("Employer has successfully logged in using traditional login");
    navigationBar.getWelcomeMenu().signOut();
    Assert.assertTrue(navigationBar.getWelcomeMenu().verifyLogout());
    log.info("Employer has logged out successfully");
    logger.log(
        LogStatus.PASS,
        "Employer has successfully logged in and logged out using traditional login");
  }
}
