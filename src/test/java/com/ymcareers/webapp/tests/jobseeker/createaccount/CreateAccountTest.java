package com.ymcareers.webapp.tests.jobseeker.createaccount;

import com.relevantcodes.extentreports.LogStatus;
import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.pageobjects.common.pages.NavigationBar;
import com.ymcareers.webapp.pageobjects.home.pages.HomePage;
import com.ymcareers.webapp.pageobjects.jobseeker.login.pages.JobSeekerCareersLoginPage;
import com.ymcareers.webapp.pageobjects.jobseeker.login.pages.JobSeekerDualLoginPage;
import com.ymcareers.webapp.tests.core.Base;
import org.apache.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateAccountTest extends Base {

  private HomePage homePage;
  private NavigationBar navigationBar;
  private JobSeekerDualLoginPage jobSeekerDualLoginPage;
  private JobSeekerCareersLoginPage jobSeekerCareersLoginPage;

  @BeforeTest
  public void setUpPages() {
    homePage = new HomePage(DriverManager.getDriver());
    navigationBar = new NavigationBar(DriverManager.getDriver());
    jobSeekerDualLoginPage = new JobSeekerDualLoginPage(DriverManager.getDriver());
    jobSeekerCareersLoginPage = new JobSeekerCareersLoginPage(DriverManager.getDriver());
  }

  @Test
  public void createAccount() {
    log = LogManager.getLogger("Verify Job Seeker is able to create a New Account");
    logger = extent.startTest("Verify Job Seeker is able to create a New Account");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    try {
      jobSeekerDualLoginPage.getNonMemberLogin().selectNonMemberLogin();
      logger.log(LogStatus.PASS, "Site has Dual Login Configuration");
    } catch (Exception e) {
      logger.log(LogStatus.PASS, "Dual Login Configuration is Off");
    }
    Assert.assertTrue(
        jobSeekerCareersLoginPage.getLoginPanel().isDisplayed(),
        "Unable to Navigate to Login Page");
    String name =
        jobSeekerCareersLoginPage.getCreateAccount().createAccount(prop.getProperty("password"));
    logger.log(LogStatus.PASS, "Entered Information for '" + name + "'");
    Assert.assertTrue(
        navigationBar.getWelcomeMenu().verifyWelcome(name), "Unable to verify Welcome Text");
    logger.log(LogStatus.PASS, "Job Seeker is able to create a new account successfully.");
  }
}
