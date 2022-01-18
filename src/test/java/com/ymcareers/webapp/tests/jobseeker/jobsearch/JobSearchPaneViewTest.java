package com.ymcareers.webapp.tests.jobseeker.jobsearch;

import com.relevantcodes.extentreports.LogStatus;
import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.pageobjects.common.pages.NavigationBar;
import com.ymcareers.webapp.pageobjects.home.pages.HomePage;
import com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.pages.JobSearchPaneViewPage;
import com.ymcareers.webapp.pageobjects.jobseeker.login.pages.JobSeekerCareersLoginPage;
import com.ymcareers.webapp.pageobjects.jobseeker.login.pages.JobSeekerDualLoginPage;
import com.ymcareers.webapp.pageobjects.jobseeker.myaccount.pages.JobSeekerMyAccountPage;
import com.ymcareers.webapp.tests.core.Base;
import org.apache.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class JobSearchPaneViewTest extends Base {

  private HomePage homePage;
  private NavigationBar navigationBar;
  private JobSeekerDualLoginPage jobSeekerDualLoginPage;
  private JobSeekerCareersLoginPage jobSeekerCareersLoginPage;
  private JobSeekerMyAccountPage jobSeekerMyAccountPage;
  private JobSearchPaneViewPage jobSearchPaneViewPage;

  @BeforeClass
  public void setUpPages() {
    homePage = new HomePage(DriverManager.getDriver());
    navigationBar = new NavigationBar(DriverManager.getDriver());
    jobSeekerDualLoginPage = new JobSeekerDualLoginPage(DriverManager.getDriver());
    jobSeekerCareersLoginPage = new JobSeekerCareersLoginPage(DriverManager.getDriver());
    jobSeekerMyAccountPage = new JobSeekerMyAccountPage(DriverManager.getDriver());
    jobSearchPaneViewPage = new JobSearchPaneViewPage(DriverManager.getDriver());
  }

  @Parameters({"applyMethod"})
  @Test
  public void jobSearchAndApply(String applyMethod) {
    log =
        LogManager.getLogger(
            "Verify if Job Seeker is able to search and apply the jobs from Home Page Search Panel");
    logger =
        extent.startTest(
            "Verify if Job Seeker is able to search and apply the jobs from Home Page Search Panel");
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
    jobSeekerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(LogStatus.PASS, "Job Seeker Logged-in Successfully");
    navigationBar.getJobSeekerMenu().navigateTo("search");
    jobSearchPaneViewPage.getJobAlertPopUp().handleJobAlertPopUp();
    logger.log(LogStatus.PASS, "Job Seeker is on Job Search Result Page");
    jobSearchPaneViewPage
        .getPaneViewJobSearchPanel()
        .searchJobHome(prop.getProperty("searchByKeyword1"), "");
    logger.log(
        LogStatus.PASS,
        "Entered Keyword in the Search Panel on Home Page and clicked 'Search Jobs");

    Assert.assertTrue(
        jobSearchPaneViewPage.getPaneViewJobList().selectJob(prop.getProperty("searchByKeyword1")),
        "Unable to select the job due to no relevant results found or Organic Job is not available");
    logger.log(
        LogStatus.PASS,
        "Selected Job which is matching with the Keyword: " + prop.getProperty("searchByKeyword"));
    jobSearchPaneViewPage.getJobApply().clickApplyNow(applyMethod);
    log.info("Job Seeker is able to search and apply the jobs from Home Page Search Panel");
    logger.log(
        LogStatus.PASS,
        "Job Seeker is able to search and apply the jobs from Home Page Search Panel ");
  }

  @Test
  public void jobSearchToolTip() {
    log = LogManager.getLogger("Verify ToolTip");
    logger = extent.startTest("Verify ToolTip on Job Search Page");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("search");
    logger.log(LogStatus.PASS, "Clicked on 'Job Search' Option");
    jobSearchPaneViewPage.getJobAlertPopUp().handleJobAlertPopUp();
    logger.log(LogStatus.PASS, "Job Seeker is on Job Search Result Page");
    Assert.assertTrue(
        jobSearchPaneViewPage.getPaneViewJobSearchPanel().verifyToolTip(),
        " Unable to verify tooltip");
    log.info("Tool Tip on Job Search page is displayed");
    logger.log(LogStatus.PASS, "Tool Tip on Job Search page is displayed");
  }

  @Test
  public void jobSearchFilter() throws InterruptedException {
    log = LogManager.getLogger("Verify Filter Functionality on Job Search Page for Pane View");
    logger = extent.startTest("Verify Filter Functionality on Job Search Page for Pane View");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("search");
    logger.log(LogStatus.PASS, "Clicked on 'Job Search' Option");
    jobSearchPaneViewPage.getJobAlertPopUp().handleJobAlertPopUp();
    logger.log(LogStatus.PASS, "Job Seeker is on Job Search Result Page");
    Assert.assertTrue(
        jobSearchPaneViewPage.getPaneViewFilterPanel().applyFilterOneByOne(),
        "Unable to apply filter");
    log.info("Job Seeker able to apply Filter Successfully");
    logger.log(LogStatus.PASS, "Job Seeker able to apply Filter Successfully");
  }

  @Test
  public void showAllApplyAllFilter() throws InterruptedException {
    log = LogManager.getLogger("Verify Job Seeker is able to expand 'Applied Filter' row");
    logger = extent.startTest("Verify Job Seeker is able to Apply all filters");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("search");
    logger.log(LogStatus.PASS, "Clicked on 'Job Search' Option");
    jobSearchPaneViewPage.getJobAlertPopUp().handleJobAlertPopUp();
    logger.log(LogStatus.PASS, "Job Seeker is on Job Search Result Page");
    Assert.assertTrue(
        jobSearchPaneViewPage.getPaneViewFilterPanel().applyAllFilter(), "Unable to apply filter");
    log.info("Job Seeker able to apply Filter Successfully");
    logger.log(LogStatus.PASS, "Job Seeker able to apply Filter Successfully");
  }

  @Test
  public void removeFilter() throws InterruptedException {
    log = LogManager.getLogger("Verify Job Seeker is able to expand 'Applied Filter' row");
    logger = extent.startTest("Verify Job Seeker is able to Apply all filters");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("search");
    logger.log(LogStatus.PASS, "Clicked on 'Job Search' Option");
    jobSearchPaneViewPage.getJobAlertPopUp().handleJobAlertPopUp();
    logger.log(LogStatus.PASS, "Job Seeker is on Job Search Result Page");
    Assert.assertTrue(
        jobSearchPaneViewPage.getPaneViewFilterPanel().applyAllFilter(), "Unable to apply filter");
    Assert.assertTrue(
        jobSearchPaneViewPage.getPaneViewFilterPanel().removeFilter(), "Unable to apply filter");
    log.info("Job Seeker able to apply Filter Successfully");
    logger.log(LogStatus.PASS, "Job Seeker able to apply Filter Successfully");
  }

  @Test
  public void applyFilterAfterLogin() throws InterruptedException {
    log = LogManager.getLogger("Verify Job Seeker is able to expand 'Applied Filter' row");
    logger = extent.startTest("Verify Job Seeker is able to Apply all filters");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    try {
      jobSeekerDualLoginPage.getNonMemberLogin().selectNonMemberLogin();
      logger.log(LogStatus.PASS, " Site has Dual Login Configuration");
    } catch (Exception e) {
      logger.log(LogStatus.PASS, " Dual Login Configuration is Off");
    }
    Assert.assertTrue(
        jobSeekerCareersLoginPage.getLoginPanel().isDisplayed(),
        " Unable to Navigate to Login Page");
    jobSeekerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(LogStatus.PASS, " Job Seeker Logged-in Successfully");
    navigationBar.getJobSeekerMenu().navigateTo("search");
    logger.log(LogStatus.PASS, " Clicked on 'Job Search' Option");
    jobSearchPaneViewPage.getJobAlertPopUp().handleJobAlertPopUp();
    logger.log(LogStatus.PASS, " Job Seeker is on Job Search Result Page");
    Assert.assertTrue(
        jobSearchPaneViewPage.getPaneViewFilterPanel().applyAllFilter(), " Unable to apply filter");
    Assert.assertTrue(
        jobSearchPaneViewPage.getPaneViewFilterPanel().removeFilter(), " Unable to remove filter");
    log.info("Job Seeker able to apply Filter Successfully");
    logger.log(LogStatus.PASS, "Job Seeker able to apply Filter Successfully");
  }

  @Test
  public void miniFooter() throws InterruptedException {
    log = LogManager.getLogger("Verify Mini Footer on Job Search Page");
    logger = extent.startTest("Verify Mini Footer on Job Search Page");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("search");
    logger.log(LogStatus.PASS, "Clicked on 'Job Search' Option");
    jobSearchPaneViewPage.getJobAlertPopUp().handleJobAlertPopUp();
    logger.log(LogStatus.PASS, "Job Seeker is on Job Search Result Page");
    Assert.assertTrue(
        jobSearchPaneViewPage.getPaneViewMiniFooter().verifyMiniFooter(), "Unable to apply filter");
    log.info("Footer Displays after clicking on 'More' on Mini Footer");
    logger.log(LogStatus.PASS, "Footer Displays after clicking on 'More' on Mini Footer");
  }

  @Test
  public void verifyMandatoryInformationJobDetails() throws InterruptedException {
    log = LogManager.getLogger("Verify Job Details");
    logger = extent.startTest("Verify Job Details");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("search");
    logger.log(LogStatus.PASS, "Clicked on 'Job Search' Option");
    jobSearchPaneViewPage.getJobAlertPopUp().handleJobAlertPopUp();
    logger.log(LogStatus.PASS, "Job Seeker is on Job Search Result Page");
    String logo = jobSearchPaneViewPage.getPaneViewJobList().verifyLogo();
    logger.log(LogStatus.PASS, "Logo Displayed as " + logo);
    Assert.assertTrue(jobSearchPaneViewPage.getPaneViewJobList().verifyJobDetails());
    log.info("Job Details Verified on Left Pane");
    logger.log(LogStatus.PASS, "Job Details Verified on Left Pane");
  }

  @Test
  public void saveJobBeforeLogin() throws InterruptedException {
    log =
        LogManager.getLogger("Verify if Job Seeker is prompted to Login When tries to Save a Job");
    logger = extent.startTest("Verify if Job Seeker is prompted to Login When tries to Save a Job");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("search");
    logger.log(LogStatus.PASS, "Clicked on 'Job Search' Option");
    jobSearchPaneViewPage.getJobAlertPopUp().handleJobAlertPopUp();
    logger.log(LogStatus.PASS, "Job Seeker is on Job Search Result Page");
    jobSearchPaneViewPage
        .getPaneViewJobList()
        .selectOrganicJob(prop.getProperty("searchByKeyword1"));
    jobSearchPaneViewPage.getPaneViewJobList().saveJob();
    try {
      jobSeekerDualLoginPage.getNonMemberLogin().isDisplayed();
      jobSeekerDualLoginPage.getNonMemberLogin().selectNonMemberLogin();
      logger.log(LogStatus.PASS, "Site has Dual Login Configuration");
    } catch (Exception e) {
      logger.log(LogStatus.PASS, "Dual Login Configuration is Off");
    }
    Assert.assertTrue(
            jobSeekerCareersLoginPage.getLoginPanel().isDisplayed(),
            "Unable to Navigate to Login Page");
    jobSeekerCareersLoginPage
            .getLoginPanel()
            .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(jobSearchPaneViewPage.getPaneViewJobList().verifyJobIsSaved());
    log.info("Job Seeker is prompted to Login When tries to Save a Job");
    logger.log(LogStatus.PASS, "Job Seeker is prompted to Login When tries to Save a Job");
  }

  @Test
  public void saveJobAfterLogin() throws InterruptedException {
    log =
            LogManager.getLogger("Verify if Job Seeker is able to Save the Job");
    logger = extent.startTest("Verify if Job Seeker is able to Save the Job");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("account");
    try {
      jobSeekerDualLoginPage.getNonMemberLogin().isDisplayed();
      jobSeekerDualLoginPage.getNonMemberLogin().selectNonMemberLogin();
      logger.log(LogStatus.PASS, "Site has Dual Login Configuration");
    } catch (Exception e) {
      logger.log(LogStatus.PASS, "Dual Login Configuration is Off");
    }
    Assert.assertTrue(
            jobSeekerCareersLoginPage.getLoginPanel().isDisplayed(),
            "Unable to Navigate to Login Page");
    jobSeekerCareersLoginPage
            .getLoginPanel()
            .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    navigationBar.getJobSeekerMenu().navigateTo("search");
    logger.log(LogStatus.PASS, "Clicked on 'Job Search' Option");
    jobSearchPaneViewPage.getJobAlertPopUp().handleJobAlertPopUp();
    logger.log(LogStatus.PASS, "Job Seeker is on Job Search Result Page");
    jobSearchPaneViewPage
            .getPaneViewJobList()
            .selectOrganicJob(prop.getProperty("searchByKeyword1"));
    jobSearchPaneViewPage.getPaneViewJobList().saveJob();
    Assert.assertTrue(jobSearchPaneViewPage.getPaneViewJobList().verifyJobIsSaved());
    log.info("Job Seeker is able to Save the Job");
    logger.log(LogStatus.PASS, "Job Seeker is able to Save the Job");
  }

  /*

  @Test
  public void saveJobBeforeLogin() {
    log =
        LogManager.getLogger("Verify if Job Seeker is prompted to Login When tries to Save a Job");
    logger = extent.startTest("Verify if Job Seeker is prompted to Login When tries to Save a Job");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("Job Search");
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
    navigationBar.getJobSeekerMenu().navigateTo("My Account");
    jobSeekerDualLoginPage.getNonMemberLogin().selectNonMemberLogin();
    jobSeekerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername2"), prop.getProperty("stdPassword2"));
    logger.log(LogStatus.PASS, "Job Seeker logged-In Successfully");
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    navigationBar.getJobSeekerMenu().navigateTo("Job Search");
    logger.log(LogStatus.PASS, "Navigated to the Job Search Page");
    legacyJobSearchPage.getJobAlertPopUp().handleJobAlertPopUp();
    String msg = legacyJobSearchPage.getLegacyJobList().saveJob();
    Assert.assertTrue(msg.contains("Viewing"), "No Jobs found on the Search Page");
    logger.log(LogStatus.PASS, "Job Seeker Clicked on Save Link");
    Assert.assertTrue(
        legacyJobSearchPage.getLegacyJobList().verifyJobIsSaved(), "Unable to Save the Job");
    log.info("Job is saved successfully");
    logger.log(LogStatus.PASS, "Job is saved successfully");
  }*/
}
