package com.ymcareers.webapp.tests.jobseeker.login;

import com.relevantcodes.extentreports.LogStatus;
import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.pageobjects.common.pages.NavigationBar;
import com.ymcareers.webapp.pageobjects.home.pages.HomePage;
import com.ymcareers.webapp.pageobjects.jobseeker.login.pages.JobSeekerDualLoginPage;
import com.ymcareers.webapp.pageobjects.jobseeker.myaccount.pages.JobSeekerMyAccountPage;
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
  private JobSeekerDualLoginPage jobSeekerDualLoginPage;
  private SamlPcmaLoginPage samlpcmaloginPage;
  private OneLoginPage oneLoginPage;
  private JobSeekerMyAccountPage jobSeekerMyAccountPage;

  @BeforeTest
  public void setUpPages() {
    homePage = new HomePage(DriverManager.getDriver());
    navigationBar = new NavigationBar(DriverManager.getDriver());
    jobSeekerDualLoginPage = new JobSeekerDualLoginPage(DriverManager.getDriver());
    samlpcmaloginPage = new SamlPcmaLoginPage(DriverManager.getDriver());
    oneLoginPage = new OneLoginPage(DriverManager.getDriver());
    jobSeekerMyAccountPage = new JobSeekerMyAccountPage(DriverManager.getDriver());
  }

  @Test
  public void jsSamlDualLoginTest() {
    log =
        LogManager.getLogger(
            "SAML JobSeeker (Dual Login) : "
                + "Verify JobSeeker is able to Login as a Member using SAML SSO");
    logger =
        extent.startTest(
            "SAML JobSeeker (Dual Login) : "
                + "Verify JobSeeker is able to Login as a Member using SAML SSO");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    Assert.assertTrue(
        jobSeekerDualLoginPage.getMemberLogin().isDisplayed(),
        "Unable to locate Member Login Button");
    jobSeekerDualLoginPage.getMemberLogin().selectMemberLogin();
    Assert.assertTrue(
        samlpcmaloginPage.getSamlpcma().isDisplayed(), "Unable to navigate to SAML Login Page");
    samlpcmaloginPage
        .getSamlpcma()
        .samlLogin(prop.getProperty("518_samlUsername"), prop.getProperty("518_samlPwd"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(navigationBar.getWelcomeMenu().isDisplayed(), "Unable to Sign-in");
    log.info("Job Seeker has successfully logged in using SAML SSO with Dual Login");
    logger.log(
        LogStatus.PASS, "Job Seeker has successfully logged in using SAML SSO with Dual Login");
  }

  @Test
  public void jsSamlLogin_MemberValidation() {
    log =
        LogManager.getLogger(
            "Verify 'Validation Required' Message displays "
                + "after Login when Validation is On using SAML SSO");
    logger =
        extent.startTest(
            "Verify 'Validation Required' Message displays "
                + "after Login when Validation is On using SAML SSO");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    Assert.assertTrue(
        jobSeekerDualLoginPage.getMemberLogin().isDisplayed(),
        "Unable to locate Member Login Button");
    jobSeekerDualLoginPage.getMemberLogin().selectMemberLogin();
    Assert.assertTrue(
        oneLoginPage.getOneLogIn().isDisplayed(), "Unable to navigate to SAML Login Page");
    oneLoginPage
        .getOneLogIn()
        .oneLogin(prop.getProperty("oneLoginUsername"), prop.getProperty("oneLoginPassword"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(
        jobSeekerMyAccountPage.getValidationRequired().isDisplayed(), "Unable to Sign-in");
    log.info(jobSeekerMyAccountPage.getValidationRequired().getValidationMessage());
    log.info("Job Seeker has successfully logged in using SAML SSO with Dual Login");
    logger.log(
        LogStatus.PASS, "Job Seeker has successfully logged in using SAML SSO with Dual Login");
  }

  @Test
  public void jsSamlLogin_CompleteSSO() {
    log = LogManager.getLogger("Verify Job Seeker is able to login via Complete SAML SSO");
    logger = extent.startTest("Verify Job Seeker is able to login via Complete SAML SSO");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    Assert.assertTrue(
        oneLoginPage.getOneLogIn().isDisplayed(), "Unable to navigate to SAML Login Page");
    oneLoginPage
        .getOneLogIn()
        .oneLogin(prop.getProperty("oneLoginUsername"), prop.getProperty("oneLoginPassword"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(navigationBar.getWelcomeMenu().isDisplayed(), "Unable to Sign-in");
    log.info("Job Seeker has successfully logged in using SAML SSO with Complete SSO");
    logger.log(
        LogStatus.PASS, "Job Seeker has successfully logged in using SAML SSO with Complete SSO");
  }
}
