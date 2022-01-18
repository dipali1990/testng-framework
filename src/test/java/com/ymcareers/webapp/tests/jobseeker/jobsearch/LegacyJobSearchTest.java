package com.ymcareers.webapp.tests.jobseeker.jobsearch;

import com.relevantcodes.extentreports.LogStatus;
import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.pageobjects.common.pages.NavigationBar;
import com.ymcareers.webapp.pageobjects.home.pages.HomePage;
import com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.pages.LegacyJobSearchPage;
import com.ymcareers.webapp.pageobjects.jobseeker.login.pages.JobSeekerCareersLoginPage;
import com.ymcareers.webapp.pageobjects.jobseeker.login.pages.JobSeekerDualLoginPage;
import com.ymcareers.webapp.pageobjects.jobseeker.myaccount.pages.JobSeekerMyAccountPage;
import com.ymcareers.webapp.tests.core.Base;
import org.apache.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LegacyJobSearchTest extends Base {

  private HomePage homePage;
  private NavigationBar navigationBar;
  private JobSeekerDualLoginPage jobSeekerDualLoginPage;
  private JobSeekerCareersLoginPage jobSeekerCareersLoginPage;
  private JobSeekerMyAccountPage jobSeekerMyAccountPage;
  private LegacyJobSearchPage legacyJobSearchPage;

  @BeforeTest
  public void setUpPages() {
    homePage = new HomePage(DriverManager.getDriver());
    navigationBar = new NavigationBar(DriverManager.getDriver());
    jobSeekerDualLoginPage = new JobSeekerDualLoginPage(DriverManager.getDriver());
    jobSeekerCareersLoginPage = new JobSeekerCareersLoginPage(DriverManager.getDriver());
    jobSeekerMyAccountPage = new JobSeekerMyAccountPage(DriverManager.getDriver());
    legacyJobSearchPage = new LegacyJobSearchPage(DriverManager.getDriver());
  }

  @Test
  public void jobSearchApplyFromHomePage() {
    log =
        LogManager.getLogger(
            "Verify if Job Seeker is able to search and apply the jobs from Home Page Search Panel");
    logger =
        extent.startTest(
            "Verify if Job Seeker is able to search and apply the jobs from Home Page Search Panel");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    jobSeekerDualLoginPage.getNonMemberLogin().selectNonMemberLogin();
    Assert.assertTrue(
        jobSeekerCareersLoginPage.getLoginPanel().isDisplayed(),
        "Unable to Navigate to Login Page");
    jobSeekerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(LogStatus.PASS, "Job Seeker Logged-in Successfully");
    navigationBar.getJobSeekerMenu().navigateTo("Home");
    logger.log(LogStatus.PASS, "Navigated to the Home Page");
    homePage.getJobSearch().searchJobHome(prop.getProperty("searchByKeyword"), "");
    logger.log(
        LogStatus.PASS,
        "Entered Keyword in the Search Panel on Home Page and clicked 'Search Jobs");
    legacyJobSearchPage.getJobAlertPopUp().handleJobAlertPopUp();
    Assert.assertTrue(
        legacyJobSearchPage.getLegacyJobList().isDisplayed(), "Jobs are not getting displayed");
    logger.log(LogStatus.PASS, "Job Seeker is on Job Search Result Page");
    Assert.assertTrue(
        legacyJobSearchPage.getLegacyJobList().selectJob(prop.getProperty("searchByKeyword")),
        "Unable to select the job due to no relevant results found");
    logger.log(
        LogStatus.PASS,
        "Selected Job which is matching with the Keyword: " + prop.getProperty("searchByKeyword"));
    Assert.assertTrue(legacyJobSearchPage.getJobView().isDisplayed());
    logger.log(LogStatus.PASS, "Job Seeker is on Job Details Page");
    String msg = legacyJobSearchPage.getJobView().clickApplyNow(prop.getProperty("jobBoard"));
    Assert.assertTrue(msg.contains("Success"), msg);
    log.info("Job Seeker is able to search and apply the jobs from Home Page Search Panel");
    logger.log(
        LogStatus.PASS,
        "Job Seeker is able to search and apply the jobs from Home Page Search Panel " + msg);
  }

  @Test
  public void jobApplyUsingEmail() {
    log =
        LogManager.getLogger("Verify if Job Seeker is able to search and apply using Email Option");
    logger =
        extent.startTest("Verify if Job Seeker is able to search and apply using Email Option");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    jobSeekerDualLoginPage.getNonMemberLogin().selectNonMemberLogin();
    Assert.assertTrue(
        jobSeekerCareersLoginPage.getLoginPanel().isDisplayed(),
        "Unable to Navigate to Login Page");
    jobSeekerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername2"), prop.getProperty("stdPassword2"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(LogStatus.PASS, "Job Seeker Logged-in Successfully");
    navigationBar.getJobSeekerMenu().navigateTo("Home");
    logger.log(LogStatus.PASS, "Navigated to the Home Page");
    homePage.getJobSearch().searchJobHome(prop.getProperty("searchByKeyword"), "");
    logger.log(
        LogStatus.PASS,
        "Entered Keyword in the Search Panel on Home Page and clicked 'Search Jobs");
    legacyJobSearchPage.getJobAlertPopUp().handleJobAlertPopUp();
    Assert.assertTrue(
        legacyJobSearchPage.getLegacyJobList().isDisplayed(), "Jobs are not getting displayed");
    logger.log(LogStatus.PASS, "Job Seeker is on Job Search Result Page");
    Assert.assertTrue(
        legacyJobSearchPage.getLegacyJobList().selectJob(prop.getProperty("searchByKeyword")),
        "Unable to select the job due to no relevant results found");
    logger.log(
        LogStatus.PASS,
        "Job Seeker selected a job matching with the keyword "
            + prop.getProperty("searchByKeyword"));
    Assert.assertTrue(legacyJobSearchPage.getJobView().isDisplayed());
    logger.log(LogStatus.PASS, "Job Seeker is on Job Details Page");
    String msg = legacyJobSearchPage.getJobView().clickApplyNow(prop.getProperty("emailApply"));
    log.info("Job Seeker is able to search and apply to the jobs & Message displayed as " + msg);
    logger.log(
        LogStatus.PASS,
        "Job Seeker is able to search and apply to the jobs & Message displayed as " + msg);
  }

  @Test
  public void jobSearchByLocationDropdownFromHomePage() {
    log =
        LogManager.getLogger(
            "Verify if Job Seeker is able to select location from the dropdown and search Jobs");
    logger =
        extent.startTest(
            "Verify if Job Seeker is able to select location from the dropdown and search Jobs");
    homePage.getCookieConsent().setCookieConsent();
    logger.log(LogStatus.PASS, "Job Seeker is on the Home Page");
    homePage
        .getJobSearch()
        .searchJobHome(prop.getProperty("searchByKeyword"), prop.getProperty("searchByLocation"));
    logger.log(
        LogStatus.PASS,
        "Entered Keyword as '"
            + prop.getProperty("searchByKeyword")
            + "' & selected location from the dropdown as '"
            + prop.getProperty("searchByLocation")
            + "' on Home Page and clicked 'Search Jobs'");
    legacyJobSearchPage.getJobAlertPopUp().handleJobAlertPopUp();
    String msg =
        legacyJobSearchPage.getLegacyJobList().getSearchResult(prop.getProperty("searchByKeyword"));
    Assert.assertTrue(msg.contains("Viewing") || msg.contains("Hmm"));
    log.info("Job Seeker got following on the Search Page " + msg);
    logger.log(LogStatus.PASS, "Job Seeker got following on the Search Page :" + msg);
  }

  @Test
  public void jobSearchByLocationDropdownFromJobSearchPage() {
    log =
        LogManager.getLogger(
            "Verify if Job Seeker is able to select location from the dropdown and search Jobs");
    logger =
        extent.startTest(
            "Verify if Job Seeker is able to select location from the dropdown and search Jobs");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("search");
    logger.log(LogStatus.PASS, "Navigated to Job Search Page");
    legacyJobSearchPage.getJobAlertPopUp().handleJobAlertPopUp();
    legacyJobSearchPage
        .getJobSearchPanel()
        .searchJobHome(prop.getProperty("searchByKeyword"), prop.getProperty("searchByLocation"));
    logger.log(
        LogStatus.PASS,
        "Entered Keyword as '"
            + prop.getProperty("searchByKeyword")
            + "' & selected location from the dropdown as '"
            + prop.getProperty("searchByLocation")
            + "' on Home Page and clicked 'Search Jobs'");
    String msg =
        legacyJobSearchPage.getLegacyJobList().getSearchResult(prop.getProperty("searchByKeyword"));
    Assert.assertTrue(msg.contains("Viewing") || msg.contains("Hmm"));
    log.info("Job Seeker got following on the Search Page " + msg);
    logger.log(LogStatus.PASS, "Job Seeker got following on the Search Page : " + msg);
  }

  @Test
  public void saveJobBeforeLogin() {
    log =
        LogManager.getLogger("Verify if Job Seeker is prompted to Login When tries to Save a Job");
    logger = extent.startTest("Verify if Job Seeker is prompted to Login When tries to Save a Job");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("search");
    logger.log(LogStatus.PASS, "Job Seeker navigated to the Job Search Page");
    legacyJobSearchPage.getJobAlertPopUp().handleJobAlertPopUp();
    String msg = legacyJobSearchPage.getLegacyJobList().saveJob();
    Assert.assertTrue(jobSeekerDualLoginPage.getNonMemberLogin().isDisplayed());
    Assert.assertTrue(msg.contains("Viewing"), "No Jobs found on the Search Page");
    logger.log(LogStatus.PASS, "Job Seeker Clicked on Save Link");
    jobSeekerDualLoginPage.getNonMemberLogin().selectNonMemberLogin();
    jobSeekerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    logger.log(LogStatus.PASS, "Job Seeker prompted to login");
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(
        legacyJobSearchPage.getLegacyJobList().verifyJobIsSaved(), "Unable to Save the Job");
    log.info("Job is saved successfully");
    logger.log(LogStatus.PASS, "Job is saved successfully");
  }

  @Test
  public void saveJobAfterLogin() {
    log = LogManager.getLogger("Verify if Job Seeker is able to Save the Job");
    logger = extent.startTest("Verify if Job Seeker is able to Save the Job");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    jobSeekerDualLoginPage.getNonMemberLogin().selectNonMemberLogin();
    jobSeekerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername2"), prop.getProperty("stdPassword2"));
    logger.log(LogStatus.PASS, "Job Seeker logged-In Successfully");
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    navigationBar.getJobSeekerMenu().navigateTo("search");
    logger.log(LogStatus.PASS, "Navigated to the Job Search Page");
    legacyJobSearchPage.getJobAlertPopUp().handleJobAlertPopUp();
    String msg = legacyJobSearchPage.getLegacyJobList().saveJob();
    Assert.assertTrue(msg.contains("Viewing"), "No Jobs found on the Search Page");
    logger.log(LogStatus.PASS, "Job Seeker Clicked on Save Link");
    Assert.assertTrue(
        legacyJobSearchPage.getLegacyJobList().verifyJobIsSaved(), "Unable to Save the Job");
    log.info("Job is saved successfully");
    logger.log(LogStatus.PASS, "Job is saved successfully");
  }
}
