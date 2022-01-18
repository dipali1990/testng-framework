package com.ymcareers.webapp.tests.jobseeker.login;

import com.relevantcodes.extentreports.LogStatus;
import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.pageobjects.common.pages.NavigationBar;
import com.ymcareers.webapp.pageobjects.home.pages.HomePage;
import com.ymcareers.webapp.pageobjects.jobseeker.login.pages.JobSeekerCareersLoginPage;
import com.ymcareers.webapp.pageobjects.jobseeker.myaccount.pages.JobSeekerMyAccountPage;
import com.ymcareers.webapp.tests.core.Base;
import org.apache.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SocialLoginTest extends Base {

  private HomePage homePage;
  private NavigationBar navigationBar;
  private JobSeekerCareersLoginPage jobSeekerCareersLoginPage;
  private JobSeekerMyAccountPage jobSeekerMyAccountPage;

  @BeforeTest
  public void setUpPages() {
    homePage = new HomePage(DriverManager.getDriver());
    navigationBar = new NavigationBar(DriverManager.getDriver());
    jobSeekerCareersLoginPage = new JobSeekerCareersLoginPage(DriverManager.getDriver());
    jobSeekerMyAccountPage = new JobSeekerMyAccountPage(DriverManager.getDriver());
  }

  @Test
  public void googleLoginTest() {
    log = LogManager.getLogger("Verify Job Seeker is able to login via Google Login");
    logger = extent.startTest("Verify Job Seeker is able to login via Google Login");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    Assert.assertTrue(
        jobSeekerCareersLoginPage.getGoogle().isDisplayed(),
        "Unable to locate Google Login Button");
    jobSeekerCareersLoginPage.getGoogle().selectGoogle();
    jobSeekerCareersLoginPage
        .getGoogle()
        .googleSignIn(prop.getProperty("googleUsername"), prop.getProperty("googlePassword"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(navigationBar.getWelcomeMenu().isDisplayed(), "Unable to Sign-in");
    log.info("Job Seeker has successfully logged in using Google login");
    logger.log(LogStatus.PASS, "Job Seeker has successfully logged in using Google login");
  }

  @Test
  public void linkedInLoginTest() {
    log = LogManager.getLogger("Verify Job Seeker is able to login via LinkedIn Login");
    logger = extent.startTest("Verify Job Seeker is able to login via LinkedIn Login");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    Assert.assertTrue(
        jobSeekerCareersLoginPage.getLinkedIn().isDisplayed(),
        "Unable to locate LinkedIn Login Button");
    jobSeekerCareersLoginPage.getLinkedIn().selectLinkedIn();
    jobSeekerCareersLoginPage
        .getLinkedIn()
        .linkedInLogin(prop.getProperty("linkedInUsername"), prop.getProperty("linkedInPassword"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(navigationBar.getWelcomeMenu().isDisplayed(), "Unable to Sign-in");
    log.info("Job Seeker has successfully logged in using LinkedIn login");
    logger.log(LogStatus.PASS, "Job Seeker has successfully logged in using LinkedIn login");
  }
}
