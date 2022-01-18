package com.ymcareers.webapp.tests.jobseeker.login;

import com.relevantcodes.extentreports.LogStatus;
import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.pageobjects.common.pages.NavigationBar;
import com.ymcareers.webapp.pageobjects.home.pages.HomePage;
import com.ymcareers.webapp.pageobjects.jobseeker.login.pages.JobSeekerCareersLoginPage;
import com.ymcareers.webapp.pageobjects.jobseeker.login.pages.JobSeekerDualLoginPage;
import com.ymcareers.webapp.pageobjects.jobseeker.myaccount.pages.JobSeekerMyAccountPage;
import com.ymcareers.webapp.tests.core.Base;
import org.apache.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StandardLoginTest extends Base {

  private HomePage homePage;
  private NavigationBar navigationBar;
  private JobSeekerDualLoginPage jobSeekerDualLoginPage;
  private JobSeekerCareersLoginPage jobSeekerCareersLoginPage;
  private JobSeekerMyAccountPage jobSeekerMyAccountPage;

  @BeforeTest
  public void setUpPages() {
    homePage = new HomePage(DriverManager.getDriver());
    navigationBar = new NavigationBar(DriverManager.getDriver());
    jobSeekerDualLoginPage = new JobSeekerDualLoginPage(DriverManager.getDriver());
    jobSeekerCareersLoginPage = new JobSeekerCareersLoginPage(DriverManager.getDriver());
    jobSeekerMyAccountPage = new JobSeekerMyAccountPage(DriverManager.getDriver());
  }

  @Test
  public void standardLoginTest() {
    log = LogManager.getLogger("Verify Job Seeker is able to login via Traditional Login");
    logger = extent.startTest("Verify Job Seeker is able to login via Traditional Login");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    Assert.assertTrue(
        jobSeekerCareersLoginPage.getLoginPanel().isDisplayed(),
        "Unable to navigate to Job Seeker Login Page");
    jobSeekerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(navigationBar.getWelcomeMenu().isDisplayed(), "Unable to Sign-in");
    log.info("Job Seeker has successfully logged in using traditional login");
    navigationBar.getWelcomeMenu().signOut();
    Assert.assertTrue(homePage.getHeroImage().isDisplayed());
    log.info("Job Seeker has successfully logged out using traditional login");
    logger.log(
        LogStatus.PASS,
        "Job Seeker has successfully logged in and logged out using traditional login");
  }

  @Test
  public void dualSignInNonMemberLoginTest() {
    log = LogManager.getLogger("Verify Job Seeker is able to login via Traditional Login");
    logger = extent.startTest("Verify Job Seeker is able to login via Traditional Login");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    Assert.assertTrue(
        jobSeekerDualLoginPage.getMemberLogin().isDisplayed(),
        "Unable to locate Member Login Button");
    jobSeekerDualLoginPage.getNonMemberLogin().selectNonMemberLogin();
    Assert.assertTrue(
        jobSeekerCareersLoginPage.getLoginPanel().isDisplayed(),
        "Unable to navigate to Job Seeker Login Page");
    jobSeekerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(navigationBar.getWelcomeMenu().isDisplayed(), "Unable to Sign-in");
    log.info("Job Seeker has successfully logged in using traditional login");
    navigationBar.getWelcomeMenu().signOut();
    Assert.assertTrue(homePage.getHeroImage().isDisplayed());
    log.info("Job Seeker has successfully logged out using traditional login");
    logger.log(LogStatus.PASS, "Job Seeker has successfully logged in using traditional login");
  }
}
