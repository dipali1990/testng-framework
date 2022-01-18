package com.ymcareers.webapp.tests.jobseeker.login;

import com.relevantcodes.extentreports.LogStatus;
import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.pageobjects.home.pages.HomePage;
import com.ymcareers.webapp.pageobjects.abilasso.pages.AbilaShpeLoginPage;
import com.ymcareers.webapp.pageobjects.common.pages.NavigationBar;
import com.ymcareers.webapp.pageobjects.jobseeker.login.pages.JobSeekerDualLoginPage;
import com.ymcareers.webapp.pageobjects.jobseeker.myaccount.pages.JobSeekerMyAccountPage;
import com.ymcareers.webapp.tests.core.Base;
import org.apache.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AbilaLoginTest extends Base {

  private HomePage homePage;
  private NavigationBar navigationBar;
  private JobSeekerDualLoginPage jobSeekerDualLoginPage;
  private AbilaShpeLoginPage abilashpeloginPage;
  private JobSeekerMyAccountPage jobSeekerMyAccountPage;

  @BeforeTest
  public void setUpPages() {
    homePage = new HomePage(DriverManager.getDriver());
    navigationBar = new NavigationBar(DriverManager.getDriver());
    jobSeekerDualLoginPage = new JobSeekerDualLoginPage(DriverManager.getDriver());
    abilashpeloginPage = new AbilaShpeLoginPage(DriverManager.getDriver());
    jobSeekerMyAccountPage = new JobSeekerMyAccountPage(DriverManager.getDriver());
  }

  @Test
  public void jsAbilaDualLoginTest() {
    log =
        LogManager.getLogger(
            "Abila JobSeeker (Dual Login) : "
                + "Verify JobSeeker is able to Login as a Member using Abila SSO");
    logger =
        extent.startTest(
            "Abila JobSeeker (Dual Login) : "
                + "Verify JobSeeker is able to Login as a Member using Abila SSO");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    Assert.assertTrue(
        jobSeekerDualLoginPage.getMemberLogin().isDisplayed(),
        "Unable to locate Member Login Button");
    jobSeekerDualLoginPage.getMemberLogin().selectMemberLogin();
    Assert.assertTrue(
        abilashpeloginPage.getAbilashpe().isDisplayed(), "Unable to navigate to Abila Login Page");
    abilashpeloginPage
        .getAbilashpe()
        .shpeLogin(prop.getProperty("usernameShpe"), prop.getProperty("pwdShpe"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(navigationBar.getWelcomeMenu().isDisplayed(), "Unable to Sign-in");
    log.info("Job Seeker has successfully logged in using Abila SSO with Dual Login");
    logger.log(
        LogStatus.PASS, "Job Seeker has successfully logged in using Abila SSO with Dual Login");
  }

  @Test
  public void jsAbilaLogin_CompleteSSO() {
    log = LogManager.getLogger("Verify Job Seeker is able to login via Complete Abila SSO");
    logger = extent.startTest("Verify Job Seeker is able to login via Complete Abila SSO");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    Assert.assertTrue(
        abilashpeloginPage.getAbilashpe().isDisplayed(), "Unable to navigate to Abila Login Page");
    abilashpeloginPage
        .getAbilashpe()
        .shpeLogin(prop.getProperty("usernameAmia"), prop.getProperty("pwdAmia"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(navigationBar.getWelcomeMenu().isDisplayed(), "Unable to Sign-in");
    log.info("Job Seeker has successfully logged in using Abila SSO with Complete SSO");
    logger.log(
        LogStatus.PASS, "Job Seeker has successfully logged in using Abila SSO with Complete SSO");
  }
}
