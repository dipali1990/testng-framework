package com.ymcareers.webapp.tests.employer.postajob;

import com.relevantcodes.extentreports.LogStatus;
import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.pageobjects.common.pages.NavigationBar;
import com.ymcareers.webapp.pageobjects.employer.login.pages.EmployerCareersLoginPage;
import com.ymcareers.webapp.pageobjects.employer.login.pages.EmployerDualLoginPage;
import com.ymcareers.webapp.pageobjects.employer.myaccount.pages.EmployerMyAccountPage;
import com.ymcareers.webapp.pageobjects.employer.postajob.pages.JobPostingPage;
import com.ymcareers.webapp.pageobjects.employer.postajob.pages.UpgradePage;
import com.ymcareers.webapp.pageobjects.home.pages.HomePage;
import com.ymcareers.webapp.pageobjects.payment.pages.PaymentsPage;
import com.ymcareers.webapp.tests.core.Base;
import org.apache.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JobPostingTest extends Base {

  private HomePage homePage;
  private NavigationBar navigationBar;
  private EmployerDualLoginPage employerDualLoginPage;
  private EmployerCareersLoginPage employerCareersLoginPage;
  private EmployerMyAccountPage employerMyAccountPage;
  private JobPostingPage jobPostingPage;
  private UpgradePage upgradePage;
  private PaymentsPage paymentsPage;

  @BeforeTest
  public void setUpPages() {
    homePage = new HomePage(DriverManager.getDriver());
    navigationBar = new NavigationBar(DriverManager.getDriver());
    employerDualLoginPage = new EmployerDualLoginPage(DriverManager.getDriver());
    employerCareersLoginPage = new EmployerCareersLoginPage(DriverManager.getDriver());
    employerMyAccountPage = new EmployerMyAccountPage(DriverManager.getDriver());
    jobPostingPage = new JobPostingPage(DriverManager.getDriver());
    paymentsPage = new PaymentsPage(DriverManager.getDriver());
    upgradePage = new UpgradePage(DriverManager.getDriver());
  }

  @Test
  public void postAJob() {
    log = LogManager.getLogger("Verify Employer is able to Post a Job");
    logger = extent.startTest("Verify Employer is able to Post a Job");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("post");
    logger.log(LogStatus.PASS, "'Post a Job' selected from Employer dropdown");
    try{
      employerDualLoginPage.getNonMemberLogin().selectNonMemberLogin();
      logger.log(LogStatus.PASS, "Site has Dual Login Configuration");
    } catch (Exception e) {
      logger.log(LogStatus.PASS, "Dual Login Configuration is Off");
    }
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(), "Login Page not displayed");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(LogStatus.PASS, "Employer logged in successfully");
    Assert.assertTrue(
        jobPostingPage.getSettings().isDisplayed(), "Unable to navigate to Job Posting Page");
    logger.log(LogStatus.PASS, "Employer navigated to Post a Job Page");
    String jobName = jobPostingPage.getSettings().setJobName();
    jobPostingPage.getJobBasics().setCompanyName(prop.getProperty("companyName"));
    jobPostingPage.getJobBasics().setCompanyLogo(prop.getProperty("companyLogo"));
    jobPostingPage.getJobBasics().setPositionTitle(jobName);
    jobPostingPage.getJobBasics().setJobFunction(prop.getProperty("jobFunction"));
    jobPostingPage.getJobDescription().setJobDescription(prop.getProperty("jobDescription"));
    jobPostingPage.getJobRequirements().setJobRequirement(prop.getProperty("jobRequirement"));
    jobPostingPage
        .getJobDetails()
        .setJobDetails(prop.getProperty("fromSalary"), prop.getProperty("toSalary"));
    jobPostingPage
        .getLocation()
        .setLocation(
            prop.getProperty("city"),
            prop.getProperty("state"),
            prop.getProperty("zipCode"),
            prop.getProperty("country"));
    jobPostingPage
        .getContactInformation()
        .setContactInformation(prop.getProperty("contactPerson"), prop.getProperty("emailAddress"));
    jobPostingPage
        .getApplications()
        .selectOnlineApplication(
            prop.getProperty("recipientFirstName"),
            prop.getProperty("recipientLastName"),
            prop.getProperty("recipientEmail"));
    jobPostingPage.getApplications().submit();
    logger.log(LogStatus.PASS, "Successfully Filled the Job Posting Form");
    if (upgradePage.getUpgrades().isDisplayed()) {
      upgradePage.getUpgrades().cancelUpgrade();
    }
    paymentsPage
        .getCreditCard()
        .completeOrder(
            prop.getProperty("cardNumber"), prop.getProperty("cardType"), prop.getProperty("cvv"));
    logger.log(LogStatus.PASS, "Entered Credit Card details and Clicked on 'Pay'");
    Assert.assertTrue(paymentsPage.getCreditCard().verifyOrderPlaced(), "Unable to complete order");
    log.info("Employer is able to Post a Job");
    logger.log(LogStatus.PASS, "Employer is able to Post a Job");
  }

  @Test
  public void jobPostingWithoutDescription() {
    log =
        LogManager.getLogger(
            "Verify Employer is not allowed to post a job without Job Description");
    logger =
        extent.startTest("Verify Employer is not allowed to post a job without Job Description");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("post");
    logger.log(LogStatus.PASS, "'Post a Job' selected from Employer dropdown");
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(), "Login Page not displayed");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(LogStatus.PASS, "Employer logged in successfully");
    Assert.assertTrue(
        jobPostingPage.getSettings().isDisplayed(), "Unable to navigate to Job Posting Page");
    logger.log(LogStatus.PASS, "Employer navigated to Post a Job Page");
    String jobName = jobPostingPage.getSettings().setJobName();
    jobPostingPage.getJobBasics().setCompanyName(prop.getProperty("companyName"));
    jobPostingPage.getJobBasics().setCompanyLogo(prop.getProperty("companyLogo"));
    jobPostingPage.getJobBasics().setPositionTitle(jobName);
    jobPostingPage.getJobBasics().setJobFunction(prop.getProperty("jobFunction"));
    jobPostingPage.getJobRequirements().setJobRequirement(prop.getProperty("jobRequirement"));
    jobPostingPage
        .getJobDetails()
        .setJobDetails(prop.getProperty("fromSalary"), prop.getProperty("toSalary"));
    jobPostingPage
        .getLocation()
        .setLocation(
            prop.getProperty("city"),
            prop.getProperty("state"),
            prop.getProperty("zipCode"),
            prop.getProperty("country"));
    jobPostingPage
        .getContactInformation()
        .setContactInformation(prop.getProperty("contactPerson"), prop.getProperty("emailAddress"));
    jobPostingPage
        .getApplications()
        .selectOnlineApplication(
            prop.getProperty("recipientFirstName"),
            prop.getProperty("recipientLastName"),
            prop.getProperty("recipientEmail"));
    jobPostingPage.getApplications().submit();
    logger.log(LogStatus.PASS, "Successfully Filled the Job Posting Form");
    Assert.assertTrue(
        jobPostingPage.getErrorMessage().isErrorMessageDisplayed(), "Error Message Not Displayed");
    log.info("Employer is not able to Post a Job without Job Description");
    logger.log(LogStatus.PASS, "Employer is not able to Post a Job without Job Description");
  }
}
