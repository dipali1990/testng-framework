package com.ymcareers.webapp.tests.employer.login;

import com.relevantcodes.extentreports.LogStatus;
import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.pageobjects.common.pages.NavigationBar;
import com.ymcareers.webapp.pageobjects.employer.login.pages.EmployerDualLoginPage;
import com.ymcareers.webapp.pageobjects.employer.myaccount.pages.EmployerMyAccountPage;
import com.ymcareers.webapp.pageobjects.home.pages.HomePage;
import com.ymcareers.webapp.pageobjects.samlsso.page.OneLoginPage;
import com.ymcareers.webapp.pageobjects.samlsso.page.SamlPcmaLoginPage;
import com.ymcareers.webapp.tests.core.Base;
import org.apache.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SamlLoginTest extends Base {
  private HomePage homePage;
  private NavigationBar navigationBar;
  private EmployerDualLoginPage employerDualLoginPage;
  private SamlPcmaLoginPage samlpcmaloginPage;
  private OneLoginPage oneLoginPage;
  private EmployerMyAccountPage employerMyAccountPage;

  @BeforeTest
  public void setUpPages() {
    homePage = new HomePage(DriverManager.getDriver());
    navigationBar = new NavigationBar(DriverManager.getDriver());
    samlpcmaloginPage = new SamlPcmaLoginPage(DriverManager.getDriver());
    oneLoginPage = new OneLoginPage(DriverManager.getDriver());
    employerDualLoginPage = new EmployerDualLoginPage(DriverManager.getDriver());
    employerMyAccountPage = new EmployerMyAccountPage(DriverManager.getDriver());
  }

  @Test
  public void empSamlDualLoginTest() {
    log =
        LogManager.getLogger(
            "SAML Employer (Dual Login) : "
                + "Verify Employer is able to Login as a Member using SAML SSO");
    logger =
        extent.startTest(
            "SAML Employer (Dual Login) : "
                + "Verify Employer is able to Login as a Member using SAML SSO");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    Assert.assertTrue(
        employerDualLoginPage.getMemberLogin().isDisplayed(),
        "Unable to locate Member Login Button");
    employerDualLoginPage.getMemberLogin().selectMemberLogin();
    Assert.assertTrue(
        samlpcmaloginPage.getSamlpcma().isDisplayed(), "Unable to navigate to SAML Login Page");
    samlpcmaloginPage
        .getSamlpcma()
        .samlLogin(prop.getProperty("518_samlUsername"), prop.getProperty("518_samlPwd"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(navigationBar.getWelcomeMenu().isDisplayed(), "Unable to Sign-in");
    log.info("Employer has successfully logged in using SAML SSO with Dual Login");
    logger.log(
        LogStatus.PASS, "Employer has successfully logged in using SAML SSO with Dual Login");
  }

  @Test
  public void empSamlLogin_MemberValidation() {
    log =
        LogManager.getLogger(
            "Verify 'Validation Required' Message displays "
                + "after Login when Validation is On using SAML SSO");
    logger =
        extent.startTest(
            "Verify 'Validation Required' Message displays "
                + "after Login when Validation is On using SAML SSO");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    Assert.assertTrue(
        employerDualLoginPage.getMemberLogin().isDisplayed(),
        "Unable to locate Member Login Button");
    employerDualLoginPage.getMemberLogin().selectMemberLogin();
    Assert.assertTrue(
        oneLoginPage.getOneLogIn().isDisplayed(), "Unable to navigate to SAML Login Page");
    oneLoginPage
        .getOneLogIn()
        .oneLogin(prop.getProperty("oneLoginUsername"), prop.getProperty("oneLoginPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(
        employerMyAccountPage.getValidationRequired().isDisplayed(), "Unable to Sign-in");
    log.info(employerMyAccountPage.getValidationRequired().getValidationMessage());
    log.info("Employer has successfully logged in using SAML SSO with Dual Login");
    logger.log(
        LogStatus.PASS, "Employer has successfully logged in using SAML SSO with Dual Login");
  }

  @Test
  public void empSamlLogin_CompleteSSO() {
    log = LogManager.getLogger("Verify Employer is able to login via Complete SAML SSO");
    logger = extent.startTest("Verify Employer is able to login via Complete SAML SSO");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    Assert.assertTrue(
        oneLoginPage.getOneLogIn().isDisplayed(), "Unable to navigate to SAML Login Page");
    oneLoginPage
        .getOneLogIn()
        .oneLogin(prop.getProperty("oneLoginUsername"), prop.getProperty("oneLoginPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(navigationBar.getWelcomeMenu().isDisplayed(), "Unable to Sign-in");
    log.info("Employer has successfully logged in using SAML SSO with Complete SSO");
    logger.log(
        LogStatus.PASS, "Employer has successfully logged in using SAML SSO with Complete SSO");
  }
}
